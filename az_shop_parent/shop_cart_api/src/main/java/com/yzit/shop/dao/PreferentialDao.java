package com.yzit.shop.dao;
import java.util.List;
import com.yzit.shop.entity.Preferential;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 
 * <br>
 * <b>功能：</b>优惠表-持久层<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-05-29 <br>
 * <b>版权所有： 2020，云优众<br>
 */
@Repository
public interface PreferentialDao  {

	// 根据分类id查询满减规则

	/**
	 * @param  @Param注解括号内的参数进行引用,mybatis采用#{}的方式把注解括号内的参数进行引用
	 * @param categoryId
	 * @return
	 */
	Preferential findByCategoryId(@Param("categoryId")Integer categoryId);
	
	/**
	 * 保持对象
	 * 
	 * @param preferential
	 */
	public int save(Preferential preferential);

	/**
	 * 根据id删除对象
	 * 
	 * @param id
	 */
	public int delById(Long id);

	/**
	 * 修改对象
	 * 
	 * @param preferential
	 */
	public int update(Preferential preferential);

	/**
	 * 检索所有的对象
	 * 
	 * @return
	 */
	public List<Preferential> findAll();

	/**
	 * 根据条件检索对象
	 * 
	 * @param preferential
	 * @return
	 */
	public List<Preferential> findByList(Preferential preferential);

	/**
	 * 根据id检索对象
	 * 
	 * @param id
	 * @return
	 */
	public Preferential  findById(Long id);


}