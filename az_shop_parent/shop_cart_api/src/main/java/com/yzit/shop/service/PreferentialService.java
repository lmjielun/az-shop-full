package com.yzit.shop.service;
import java.util.List;
import com.yzit.shop.entity.Preferential;
//import com.github.pagehelper.PageInfo;
/**
 * 
 * <br>
 * <b>功能：</b>优惠表--服务实现类<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-05-29 <br>
 * <b>版权所有： 2020，云优众<br>
 */ 
public interface PreferentialService {

	/**
	 *  根据分类id查询满减规则
	 *  最好返回的是list集合
	 * @return
	 */
	public Preferential findByCategoryId(Integer categoryId);

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
	public int del(Long id);

	/**
	 * 修改对象
	 * 
	 * @param preferential
	 */
	public int  update(Preferential preferential);

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

	
	/**
     * 批量删除信息
     * 
     * @param ids 需要删除的ID集合
     * @return 结果
     */
    public boolean batchDel(Long[] ids) ;
}