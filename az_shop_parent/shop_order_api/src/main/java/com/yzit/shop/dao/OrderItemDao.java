package com.yzit.shop.dao;
import java.util.List;
import com.yzit.shop.entity.OrderItem;
import org.springframework.stereotype.Repository;

/**
 * 
 * <br>
 * <b>功能：</b>订单详情-持久层<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-06-01 <br>
 * <b>版权所有： 2020，云优众<br>
 */

@Repository
public interface OrderItemDao  {
	
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
	public int delById(Long id);

	/**
	 * 修改对象
	 * 
	 * @param orderItem
	 */
	public int update(OrderItem orderItem);

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
}