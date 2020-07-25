package com.yzit.shop.dao;
import java.util.List;
import com.yzit.shop.entity.SysLog;
/**
 * 
 * <br>
 * <b>功能：</b>日志类-持久层<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-06-08 <br>
 * <b>版权所有： 2020，云优众<br>
 */ 
public interface SysLogDao  {
	
	/**
	 * 保持对象
	 * 
	 * @param sysLog
	 */
	public int save(SysLog sysLog);

	/**
	 * 根据id删除对象
	 * 
	 * @param id
	 */
	public int delById(Long id);

	/**
	 * 修改对象
	 * 
	 * @param sysLog
	 */
	public int update(SysLog sysLog);

	/**
	 * 检索所有的对象
	 * 
	 * @return
	 */
	public List<SysLog> findAll();

	/**
	 * 根据条件检索对象
	 * 
	 * @param sysLog
	 * @return
	 */
	public List<SysLog> findByList(SysLog sysLog);

	/**
	 * 根据id检索对象
	 * 
	 * @param id
	 * @return
	 */
	public SysLog  findById(Long id);

	void batchInsert(List<SysLog> sysLogList);
}