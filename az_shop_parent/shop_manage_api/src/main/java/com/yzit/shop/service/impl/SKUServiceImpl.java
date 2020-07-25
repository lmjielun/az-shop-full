package com.yzit.shop.service.impl;

import java.util.List;

import com.yzit.shop.service.SKUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import com.yzit.shop.entity.SKU;
import com.yzit.shop.dao.SKUDao;

/**
 * 
 * <br>
 * <b>功能：</b>SKU--服务实现类<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-05-08 <br>
 * <b>版权所有： 2020，云优众<br>
 */ 
@Service("sKUService")
@Transactional
public class SKUServiceImpl   implements SKUService {
	//private final static Logger log= Logger.getLogger(SKUService.class);
	
	@Autowired
	protected SKUDao  sKUDao;

	/**
	 * 保持对象
	 * 
	 * @param sKU
	 */
	public int save(SKU  sKU){
		return	sKUDao.save(sKU);
	}

	/**
	 * 根据id删除对象
	 * 
	 * @param id
	 */
	public int del(Long id){
		return sKUDao.delById(id);
	}

	/**
	 * 修改对象
	 * 
	 * @param sKU
	 */
	 
	public int update(SKU  sKU){
		return sKUDao.update(sKU);
	}

	/**
	 * 检索所有的对象
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<SKU> findAll(){
		return sKUDao.findAll();
	}

	/**
	 * 根据条件检索对象
	 * 
	 * @param sKU
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<SKU> findByList(SKU  sKU){
		return sKUDao.findByList(sKU);
	}

	/**
	 * 根据id检索对象
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public SKU  findById(Long id){
		System.out.println(id);
		return sKUDao.findById(id);
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
                sKUDao.delById(id);
            }
        }
        return true;
    }
}
