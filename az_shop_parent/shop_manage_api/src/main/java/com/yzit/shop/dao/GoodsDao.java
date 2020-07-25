package com.yzit.shop.dao;
import java.util.List;
import com.yzit.shop.entity.Goods;
import org.springframework.stereotype.Repository;

/**
 * 
 * <br>
 * <b>功能：</b>Goods-持久层<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-05-08 <br>
 * <b>版权所有： 2020，云优众<br>
 */
@Repository
public interface GoodsDao  {
	
	/**
	 * 保持对象
	 * 
	 * @param goods
	 */
	public int save(Goods goods);

	/**
	 * 根据id删除对象
	 * 
	 * @param id
	 */
	public int delById(Long id);

	/**
	 * 修改对象
	 * 
	 * @param goods
	 */
	public int update(Goods goods);

	/**
	 * 检索所有的对象
	 * 
	 * @return
	 */
	public List<Goods> findAll();

	/**
	 * 根据条件检索对象
	 * 
	 * @param goods
	 * @return
	 */
	public List<Goods> findByList(Goods goods);

	/**
	 * 根据id检索对象
	 * 
	 * @param id
	 * @return
	 */
	public Goods  findById(Long id);
}