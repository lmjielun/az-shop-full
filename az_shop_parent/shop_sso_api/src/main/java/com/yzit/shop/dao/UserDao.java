package com.yzit.shop.dao;
import java.util.List;
import com.yzit.shop.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 
 * <br>
 * <b>功能：</b>用户表-持久层<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-05-22 <br>
 * <b>版权所有： 2020，云优众<br>
 */
@Repository
public interface UserDao  {
	
	/**
	 * 保持对象
	 * 
	 * @param user
	 */
	public int save(User user);

	/**
	 * 根据id删除对象
	 * 
	 * @param id
	 */
	public int delById(Long id);

	/**
	 * 修改对象
	 * 
	 * @param user
	 */
	public int update(User user);

	/**
	 * 检索所有的对象
	 * 
	 * @return
	 */
	public List<User> findAll();

	/**
	 * 根据条件检索对象
	 * 
	 * @param user
	 * @return
	 */
	public List<User> findByList(User user);

	/**
	 * 根据id检索对象
	 * 
	 * @param id
	 * @return
	 */
	public User  findById(Long id);

	//登录
    User doLogin(User user);

    User findByUser(User user);
}