package com.yzit.shop.service.impl;

import java.util.List;

import com.yzit.shop.dao.BrandMapper;
import com.yzit.shop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import com.yzit.shop.entity.Brand;
/**
 * 
 * <br>
 * <b>功能：</b>品牌表--服务实现类<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-04-27 <br>
 * <b>版权所有： 2020，云优众<br>
 */ 
@Service("brandService")
@Transactional
public class BrandServiceImpl   implements BrandService {
	//private final static Logger log= Logger.getLogger(BrandService.class);
	
	@Autowired
	protected BrandMapper brandDao;

	/**
	 * 保存
	 * 
	 * @param brand
	 */
	public int save(Brand  brand){
		return	brandDao.insertSelective(brand);
	}

	/**
	 * 修改对象
	 * 
	 * @param brand
	 */
	 
	public int update(Brand  brand){
		return brandDao.updateByPrimaryKeySelective(brand);
	}

	/**
	 * 检索所有的对象
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Brand> findAll(){
		return brandDao.findAll();
	}

	/**
	 * 根据条件检索对象
	 * 
	 * @param brand
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Brand> findByList(Brand  brand){
		return brandDao.findByList(brand);
	}

	/**
	 * 根据id检索对象
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Brand  findById(Long id){
		return brandDao.selectByPrimaryKey(id);
	}


	/**
	 * 根据id删除对象
	 *
	 * @param id
	 */
	public int del(Long id){
		return brandDao.deleteByPrimaryKey(id);
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
				brandDao.deleteByPrimaryKey(id);
            }
        }
        return true;
    }
}
