package com.yzit.shop.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import com.yzit.shop.entity.OrderItem;
import com.yzit.shop.dao.OrderItemDao;

/**
 * 
 * <br>
 * <b>功能：</b>订单详情--服务实现类<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-06-01 <br>
 * <b>版权所有： 2020，云优众<br>
 */ 
@Service("orderItemService")
@Transactional
public class OrderItemServiceImpl   implements OrderItemService {
	//private final static Logger log= Logger.getLogger(OrderItemService.class);
	
	@Autowired
	protected OrderItemDao  orderItemDao;

	/**
	 * 保持对象
	 * 
	 * @param orderItem
	 */
	public int save(OrderItem  orderItem){
		return	orderItemDao.save(orderItem);
	}

	/**
	 * 根据id删除对象
	 * 
	 * @param id
	 */
	public int del(Long id){
		return orderItemDao.delById(id);
	}

	/**
	 * 修改对象
	 * 
	 * @param orderItem
	 */
	 
	public int update(OrderItem  orderItem){
		return orderItemDao.update(orderItem);
	}

	/**
	 * 检索所有的对象
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<OrderItem> findAll(){
		return orderItemDao.findAll();
	}

	/**
	 * 根据条件检索对象
	 * 
	 * @param orderItem
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<OrderItem> findByList(OrderItem  orderItem){
		return orderItemDao.findByList(orderItem);
	}

	/**
	 * 根据id检索对象
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public OrderItem  findById(Long id){
		return orderItemDao.findById(id);
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
                orderItemDao.delById(id);
            }
        }
        return true;
    }
}
