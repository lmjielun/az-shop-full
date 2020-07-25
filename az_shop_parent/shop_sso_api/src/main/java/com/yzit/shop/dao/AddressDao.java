package com.yzit.shop.dao;
import java.util.List;
import com.yzit.shop.entity.Address;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 
 * <br>
 * <b>功能：</b>地址表-持久层<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-06-01 <br>
 * <b>版权所有： 2020，云优众<br>
 */

@Repository
public interface AddressDao  {
	
	/**
	 * 保持对象
	 * 
	 * @param address
	 */
	public int save(Address address);

	/**
	 * 根据id删除对象
	 * 
	 * @param id
	 */
	public int delById(Long id);

	/**
	 * 修改对象
	 * 
	 * @param address
	 */
	public int update(Address address);

	/**
	 * 检索所有的对象
	 * 
	 * @return
	 */
	public List<Address> findAll();

	/**
	 * 根据条件检索对象
	 * 
	 * @param address
	 * @return
	 */
	public List<Address> findByList(Address address);

	/**
	 * 根据id检索对象
	 * 
	 * @param id
	 * @return
	 */
	public Address  findById(Long id);

	/**
	 * 将默认地址字段修改为0
	 * @return
	 */

    int deleteDefulatAddress(@Param("userId") String userId);

	/**
	 *  将默认地址为0修改回来
	 * @return
	 */
	int updateDefulatAddress(@Param("id") Integer id);

	/**
	 *  查找默认的收货地址
	 * @return
	 */
    Address findDefaultAddress();
}