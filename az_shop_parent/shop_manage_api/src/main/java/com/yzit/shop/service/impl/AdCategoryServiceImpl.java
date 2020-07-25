package com.yzit.shop.service.impl;

import java.util.List;

import com.yzit.shop.service.AdCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import com.yzit.shop.entity.AdCategory;
import com.yzit.shop.dao.AdCategoryDao;

/**
 * 
 * <br>
 * <b>功能：</b>AdCategory--服务实现类<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-05-16 <br>
 * <b>版权所有： 2020，云优众<br>
 */ 
@Service("adCategoryService")
@Transactional
public class AdCategoryServiceImpl   implements AdCategoryService {
	//private final static Logger log= Logger.getLogger(AdCategoryService.class);
	
	@Autowired
	protected AdCategoryDao  adCategoryDao;

	/**
	 * 保持对象
	 * 
	 * @param adCategory
	 */
	public int save(AdCategory  adCategory){
		return	adCategoryDao.save(adCategory);
	}

	/**
	 * 根据id删除对象
	 * 
	 * @param id
	 */
	public int del(Long id){
		return adCategoryDao.delById(id);
	}

	/**
	 * 修改对象
	 * 
	 * @param adCategory
	 */
	 
	public int update(AdCategory  adCategory){
		return adCategoryDao.update(adCategory);
	}

	/**
	 * 检索所有的对象
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<AdCategory> findAll(){
		return adCategoryDao.findAll();
	}

	/**
	 * 根据条件检索对象
	 * 
	 * @param adCategory
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<AdCategory> findByList(AdCategory  adCategory){
		return adCategoryDao.findByList(adCategory);
	}

	/**
	 * 根据id检索对象
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public AdCategory  findById(Long id){
		return adCategoryDao.findById(id);
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
                adCategoryDao.delById(id);
            }
        }
        return true;
    }
}
