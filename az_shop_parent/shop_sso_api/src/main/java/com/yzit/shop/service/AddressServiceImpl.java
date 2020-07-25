package com.yzit.shop.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import com.yzit.shop.entity.Address;
import com.yzit.shop.dao.AddressDao;

/**
 * 
 * <br>
 * <b>功能：</b>地址表--服务实现类<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-06-01 <br>
 * <b>版权所有： 2020，云优众<br>
 */ 
@Service("addressService")
@Transactional
public class AddressServiceImpl   implements AddressService {
	//private final static Logger log= Logger.getLogger(AddressService.class);

	@Autowired
	protected AddressDao  addressDao;

	/**
	 *  查找默认的收货地址
	 * @return
	 */
	@Override
	public Address findDefaultAddress() {
		return addressDao.findDefaultAddress();
	}

	@Override
	public int deleteDefulatAddress(String userId) {
		return addressDao.deleteDefulatAddress(userId);

	}

	@Override
	public int updateDefulatAddress(Integer id) {
		return addressDao.updateDefulatAddress(id);
	}

	/**
	 * 保持对象
	 * 
	 * @param address
	 */
	public int save(Address  address){
		return	addressDao.save(address);
	}

	/**
	 * 根据id删除对象
	 * 
	 * @param id
	 */
	public int del(Long id){
		return addressDao.delById(id);
	}

	/**
	 * 修改对象
	 * 
	 * @param address
	 */
	 
	public int update(Address  address){
		return addressDao.update(address);
	}

	/**
	 * 检索所有的对象
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Address> findAll(){
		return addressDao.findAll();
	}

	/**
	 * 根据条件检索对象
	 * 
	 * @param address
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Address> findByList(Address  address){
		return addressDao.findByList(address);
	}

	/**
	 * 根据id检索对象
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Address  findById(Long id){
		return addressDao.findById(id);
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
                addressDao.delById(id);
            }
        }
        return true;
    }
}
