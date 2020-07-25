package com.yzit.shop.service;
import java.util.List;
import com.yzit.shop.entity.AdCategory;
//import com.github.pagehelper.PageInfo;
/**
 * 
 * <br>
 * <b>功能：</b>AdCategory--服务实现类<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-05-16 <br>
 * <b>版权所有： 2020，云优众<br>
 */ 
public interface AdCategoryService {
	/**
	 * 保持对象
	 * 
	 * @param adCategory
	 */
	public int save(AdCategory adCategory);

	/**
	 * 根据id删除对象
	 * 
	 * @param id
	 */
	public int del(Long id);

	/**
	 * 修改对象
	 * 
	 * @param adCategory
	 */
	public int  update(AdCategory adCategory);

	/**
	 * 检索所有的对象
	 * 
	 * @return
	 */
	public List<AdCategory> findAll();

	/**
	 * 根据条件检索对象
	 * 
	 * @param adCategory
	 * @return
	 */
	public List<AdCategory> findByList(AdCategory adCategory);

	/**
	 * 根据id检索对象
	 * 
	 * @param id
	 * @return
	 */
	public AdCategory  findById(Long id);

	
	/**
     * 批量删除信息
     * 
     * @param ids 需要删除的ID集合
     * @return 结果
     */
    public boolean batchDel(Long[] ids) ;
}