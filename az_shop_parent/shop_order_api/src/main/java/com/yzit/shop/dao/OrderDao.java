package com.yzit.shop.dao;
import java.util.List;
import com.yzit.shop.entity.Order;
import org.springframework.stereotype.Repository;

/**
 * 
 * <br>
 * <b>功能：</b>订单-持久层<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-06-01 <br>
 * <b>版权所有： 2020，云优众<br>
 */
@Repository
public interface OrderDao  {
	
	/**
	 * 保持对象
	 * 
	 * @param order
	 */
	public int save(Order order);

	/**
	 * 根据id删除对象
	 * 
	 * @param id
	 */
	public int delById(Long id);

	/**
	 * 修改对象
	 * 
	 * @param order
	 */
	public int update(Order order);

	/**
	 * 检索所有的对象
	 * 
	 * @return
	 */
	public List<Order> findAll();

	/**
	 * 根据条件检索对象
	 * 
	 * @param order
	 * @return
	 */
	public List<Order> findByList(Order order);

	/**
	 * 根据id检索对象
	 * 
	 * @param id
	 * @return
	 */
	public Order  findById(Long id);
}