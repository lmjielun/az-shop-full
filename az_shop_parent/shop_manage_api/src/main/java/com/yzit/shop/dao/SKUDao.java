package com.yzit.shop.dao;
import java.util.List;
import com.yzit.shop.entity.SKU;
import org.springframework.stereotype.Repository;

/**
 * 
 * <br>
 * <b>功能：</b>SKU-持久层<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-05-08 <br>
 * <b>版权所有： 2020，云优众<br>
 */
@Repository
public interface SKUDao  {
	
	/**
	 * 保持对象
	 * 
	 * @param sKU
	 */
	public int save(SKU sKU);

	/**
	 * 根据id删除对象
	 * 
	 * @param id
	 */
	public int delById(Long id);

	/**
	 * 修改对象
	 * 
	 * @param sKU
	 */
	public int update(SKU sKU);

	/**
	 * 检索所有的对象
	 * 
	 * @return
	 */
	public List<SKU> findAll();

	/**
	 * 根据条件检索对象
	 * 
	 * @param sKU
	 * @return
	 */
	public List<SKU> findByList(SKU sKU);

	/**
	 * 根据id检索对象
	 * 
	 * @param id
	 * @return
	 */
	public SKU  findById(Long id);

    void insertBatch(List<SKU> skuList);
}