package com.yzit.shop.dao;
import java.util.List;
import com.yzit.shop.entity.AD;
import org.springframework.stereotype.Repository;

/**
 * 
 * <br>
 * <b>功能：</b>AD-持久层<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-05-16 <br>
 * <b>版权所有： 2020，云优众<br>
 */
@Repository
public interface ADDao  {
	
	/**
	 * 保持对象
	 * 
	 * @param aD
	 */
	public int save(AD aD);

	/**
	 * 根据id删除对象
	 * 
	 * @param id
	 */
	public int delById(Long id);

	/**
	 * 修改对象
	 * 
	 * @param aD
	 */
	public int update(AD aD);

	/**
	 * 检索所有的对象
	 * 
	 * @return
	 */
	public List<AD> findAll();

	/**
	 * 根据条件检索对象
	 * 
	 * @param aD
	 * @return
	 */
	public List<AD> findByList(AD aD);

	/**
	 * 根据id检索对象
	 * 
	 * @param id
	 * @return
	 */
	public AD  findById(Long id);
}