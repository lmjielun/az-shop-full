package com.yzit.shop.service;

import java.util.Date;
import java.util.List;

import com.yzit.shop.dao.OrderItemDao;
import com.yzit.shop.entity.Goods;
import com.yzit.shop.entity.OrderItem;
import com.yzit.shop.entity.SKU;
import com.yzit.shop.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import com.yzit.shop.entity.Order;
import com.yzit.shop.dao.OrderDao;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * <br>
 * <b>功能：</b>订单--服务实现类<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-06-01 <br>
 * <b>版权所有： 2020，云优众<br>
 */ 
@Service("orderService")
@Transactional
public class OrderServiceImpl   implements OrderService {
	//private final static Logger log= Logger.getLogger(OrderService.class);
	
	@Autowired
	protected OrderDao  orderDao;

	@Autowired
	private OrderItemDao orderItemDao;

	@Autowired
	private IdWorker idWorker;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 保持对象
	 * 
	 * @param order
	 */
	public int save(Order  order){
 		// 订单创建时间
		order.setCreateTime(new Date());

		// 1、雪花算法生成分布式--订单数据量大，将来要分库分表，避免id出现重复
		long id = idWorker.nextId(); // 调用雪花算法，idworker得到机器码，作为主键id
		order.setId(String.valueOf(id));

		// 2、必须先保存订单，然后在保存订单详情
		// 因为订单详情需要用到订单的主键
		int flag = orderDao.save(order);

		// 3、批量插入订单详情表,使用order先得到orderItemList集合，然后遍历该集合
		for(OrderItem orderItem : order.getOrderItemList()){
			// 订单详情的order_id为 订单order的主键Id
			orderItem.setOrderId(order.getId());
			orderItem.setPayMoney(order.getPayMoney());
			orderItem.setMoney(order.getTotalMoney());

			// 使用restTemplate远程调用后台接口，根据商品的goodsId获取商品对象的三级分类，赋值给orderItem的商品分类
			Goods goods = restTemplate.getForObject("http://api.shop.com/api/goods/"+orderItem.getGoodsId(), Goods.class);
			orderItem.setCategoryId1(goods.getCategory1Id());
			orderItem.setCategoryId2(goods.getCategory2Id());
			orderItem.setCategoryId3(goods.getCategory3Id());
			orderItem.setName(goods.getGoodsName());
			orderItem.setImage(goods.getImage());
			// 使用restTemplate远程调用后台接口，根据skuId获取skuid 对象，获取单价，等信息
			SKU sku = restTemplate.getForObject("http://api.shop.com/api/sku/"+orderItem.getSkuId(), SKU.class);
			orderItem.setPrice(sku.getPrice());
			orderItem.setId(String.valueOf(idWorker.nextId()));

			// 调用订单详情的dao，批量插入订单详情
			orderItemDao.save(orderItem);
		}

		return flag;
	}

	/**
	 * 根据id删除对象
	 * 
	 * @param id
	 */
	public int del(Long id){
		return orderDao.delById(id);
	}

	/**
	 * 修改对象
	 * 
	 * @param order
	 */
	 
	public int update(Order  order){
		return orderDao.update(order);
	}

	/**
	 * 检索所有的对象
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Order> findAll(){
		return orderDao.findAll();
	}

	/**
	 * 根据条件检索对象
	 * 
	 * @param order
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Order> findByList(Order  order){
		return orderDao.findByList(order);
	}

	/**
	 * 根据id检索对象
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Order  findById(Long id){
		return orderDao.findById(id);
	}

	/**
     * 批量删除信息
     * 
     * @param ids 需要删除的ID集合
     * @return 结果
     */
   public boolean batchDel(Long[] ids){
        if(ids != null && ids.length > 0){
            for(Long id : ids){
                orderDao.delById(id);
            }
        }
        return true;
    }
}
