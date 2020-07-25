package com.yzit.shop.service;
import java.util.List;
import com.yzit.shop.entity.OrderItem;
//import com.github.pagehelper.PageInfo;
/**
 * 
 * <br>
 * <b>功能：</b>订单详情--服务实现类<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-06-01 <br>
 * <b>版权所有： 2020，云优众<br>
 */ 
public interface OrderItemService {
	/**
	 * 保持对象
	 * 
	 * @param orderItem
	 */
	public int save(OrderItem orderItem);

	/**
	 * 根据id删除对象
	 * 
	 * @param id
	 */
	public int del(Long id);

	/**
	 * 修改对象
	 * 
	 * @param orderItem
	 */
	public int  update(OrderItem orderItem);

	/**
	 * 检索所有的对象
	 * 
	 * @return
	 */
	public List<OrderItem> findAll();

	/**
	 * 根据条件检索对象
	 * 
	 * @param orderItem
	 * @return
	 */
	public List<OrderItem> findByList(OrderItem orderItem);

	/**
	 * 根据id检索对象
	 * 
	 * @param id
	 * @return
	 */
	public OrderItem  findById(Long id);

	
	/**
     * 批量删除信息
     * 
     * @param ids 需要删除的ID集合
     * @return 结果
     */
    public boolean batchDel(Long[] ids) ;
}