package com.yzit.shop.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import com.yzit.shop.entity.SysLog;
import com.yzit.shop.dao.SysLogDao;

/**
 * 
 * <br>
 * <b>功能：</b>日志类--服务实现类<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-06-08 <br>
 * <b>版权所有： 2020，云优众<br>
 */ 
@Service("sysLogService")
@Transactional
public class SysLogServiceImpl   implements SysLogService {
	//private final static Logger log= Logger.getLogger(SysLogService.class);


	@Autowired
	protected SysLogDao  sysLogDao;

	/**
	 *  批量插入
	 * @param sysLogList
	 */

	@Override
	public void batchInsert(List<SysLog> sysLogList) {
		sysLogDao.batchInsert(sysLogList);
	}

	/**
	 * 保持对象
	 * 
	 * @param sysLog
	 */
	public int save(SysLog  sysLog){
		return	sysLogDao.save(sysLog);
	}

	/**
	 * 根据id删除对象
	 * 
	 * @param id
	 */
	public int del(Long id){
		return sysLogDao.delById(id);
	}

	/**
	 * 修改对象
	 * 
	 * @param sysLog
	 */
	 
	public int update(SysLog  sysLog){
		return sysLogDao.update(sysLog);
	}

	/**
	 * 检索所有的对象
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<SysLog> findAll(){
		return sysLogDao.findAll();
	}

	/**
	 * 根据条件检索对象
	 * 
	 * @param sysLog
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<SysLog> findByList(SysLog  sysLog){
		return sysLogDao.findByList(sysLog);
	}

	/**
	 * 根据id检索对象
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public SysLog  findById(Long id){
		return sysLogDao.findById(id);
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
                sysLogDao.delById(id);
            }
        }
        return true;
    }
}
