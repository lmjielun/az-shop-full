package com.yzit.shop.dao;
import java.util.List;
import com.yzit.shop.entity.AdCategory;
import org.springframework.stereotype.Repository;

/**
 * 
 * <br>
 * <b>功能：</b>AdCategory-持久层<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-05-16 <br>
 * <b>版权所有： 2020，云优众<br>
 */
@Repository
public interface AdCategoryDao  {
	
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
	public int delById(Long id);

	/**
	 * 修改对象
	 * 
	 * @param adCategory
	 */
	public int update(AdCategory adCategory);

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
}