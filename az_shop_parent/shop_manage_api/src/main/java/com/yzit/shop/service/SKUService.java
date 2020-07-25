package com.yzit.shop.service;
import java.util.List;
import com.yzit.shop.entity.SKU;
//import com.github.pagehelper.PageInfo;
/**
 * 
 * <br>
 * <b>功能：</b>SKU--服务实现类<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-05-08 <br>
 * <b>版权所有： 2020，云优众<br>
 */ 
public interface SKUService {
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
	public int del(Long id);

	/**
	 * 修改对象
	 * 
	 * @param sKU
	 */
	public int  update(SKU sKU);

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

	
	/**
     * 批量删除信息
     * 
     * @param ids 需要删除的ID集合
     * @return 结果
     */
    public boolean batchDel(Long[] ids) ;
}