package com.yzit.shop.service;
import java.util.List;
import com.yzit.shop.entity.Address;
//import com.github.pagehelper.PageInfo;
/**
 * 
 * <br>
 * <b>功能：</b>地址表--服务实现类<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-06-01 <br>
 * <b>版权所有： 2020，云优众<br>
 */ 
public interface AddressService {
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
	public int del(Long id);

	/**
	 * 修改对象
	 * 
	 * @param address
	 */
	public int  update(Address address);



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
     * 批量删除信息
     *
     * @param ids 需要删除的ID集合
     * @return 结果
     */
    public boolean batchDel(Long[] ids) ;

	public int  deleteDefulatAddress(String userId);

	public int updateDefulatAddress(Integer id);

	/**
	 *  查找默认的收货地址
	 * @return
	 */
    Address findDefaultAddress();
}