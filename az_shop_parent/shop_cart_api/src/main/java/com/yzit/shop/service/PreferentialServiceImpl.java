package com.yzit.shop.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import com.yzit.shop.entity.Preferential;
import com.yzit.shop.dao.PreferentialDao;

/**
 * 
 * <br>
 * <b>功能：</b>优惠表--服务实现类<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-05-29 <br>
 * <b>版权所有： 2020，云优众<br>
 */ 
@Service("preferentialService")
@Transactional
public class PreferentialServiceImpl   implements PreferentialService {

	// 注入dao
	@Autowired
	protected PreferentialDao  preferentialDao;

	/**
	 *  实现根据分类id 查询满减规则
	 * @param categoryId
	 * @return
	 */
	@Override
	public Preferential findByCategoryId(Integer categoryId) {
		return preferentialDao.findByCategoryId(categoryId);
	}

	/**
	 * 保持对象
	 * 
	 * @param preferential
	 */
	public int save(Preferential  preferential){
		return	preferentialDao.save(preferential);
	}

	/**
	 * 根据id删除对象
	 * 
	 * @param id
	 */
	public int del(Long id){
		return preferentialDao.delById(id);
	}

	/**
	 * 修改对象
	 * 
	 * @param preferential
	 */
	 
	public int update(Preferential  preferential){
		return preferentialDao.update(preferential);
	}

	/**
	 * 检索所有的对象
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Preferential> findAll(){
		return preferentialDao.findAll();
	}

	/**
	 * 根据条件检索对象
	 * 
	 * @param preferential
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Preferential> findByList(Preferential  preferential){
		return preferentialDao.findByList(preferential);
	}

	/**
	 * 根据id检索对象
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Preferential  findById(Long id){
		return preferentialDao.findById(id);
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
                preferentialDao.delById(id);
            }
        }
        return true;
    }
}
