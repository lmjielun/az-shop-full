package com.yzit.shop.service;

import java.util.Date;
import java.util.List;

import com.yzit.framework.web.ui.AjaxResult;
import com.yzit.shop.common.MD5Util2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.yzit.shop.entity.User;
import com.yzit.shop.dao.UserDao;

/**
 * 
 * <br>
 * <b>功能：</b>用户表--服务实现类<br>
 * <b>作者：</b>Administrator<br>
 * <b>日期：</b> 2020-05-22 <br>
 * <b>版权所有： 2020，云优众<br>
 */ 
@Service("userService")
@Transactional
public class UserServiceImpl   implements UserService {
	//private final static Logger log= Logger.getLogger(UserService.class);
	
	@Autowired
	protected UserDao  userDao;

	@Autowired
    private RedisTemplate redisTemplate;

	/**
	 * 保持对象
	 * 
	 * @param user
	 */
	public int save(User  user){
		return	userDao.save(user);
	}

	/**
	 * 根据id删除对象
	 * 
	 * @param id
	 */
	public int del(Long id){
		return userDao.delById(id);
	}

	/**
	 * 修改对象
	 * 
	 * @param user
	 */
	 
	public int update(User  user){
		return userDao.update(user);
	}

	/**
	 * 检索所有的对象
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<User> findAll(){
		return userDao.findAll();
	}

	/**
	 * 根据条件检索对象
	 * 
	 * @param user
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<User> findByList(User  user){
		return userDao.findByList(user);
	}

	/**
	 * 根据id检索对象
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public User  findById(Long id){
		return userDao.findById(id);
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
                userDao.delById(id);
            }
        }
        return true;
    }
	// 登录
	public User doLogin(User user) {
		return userDao.doLogin(user);
	}

	// 注册校验
    @Override
	public AjaxResult checkUser(User user) {
        // 用户创建的时间
        user.setCreated(new Date());

        // 创建一个user2对象
        User user2 = new User();
        // 将User的username赋值给user2
        user2.setUsername(user.getUsername());
        // 调用findByUser查询user2是否存在
        User byUser = userDao.findByUser(user2);
        // 如果不是空，说明存在
        if(byUser != null){
            // 存在了，就返回错误信息，不可注册
           return AjaxResult.ERROR("用户名已存在");

        }else{ // 否则，为空，继续判断 电话是否存在
            // 一个User3对象
           User user3 =  new User();
            // 将user的电话 赋值给user3
           user3.setPhone(user.getPhone());
           // 查询user3是否存在
           User resUser = userDao.findByUser(user3);
            // 如果结果不是null 说明电话已存在
            if(resUser != null){
                // 返回错误信息
                return AjaxResult.ERROR("手机号已存在");
            }else{ // 否则说明电话号码不存在，继续判断 邮箱
                // 创建一个user4对象
                User user4 = new User();
                // 将User对象的 邮箱 赋值给user4
                user4.setEmail(user.getEmail());
                // 查询user4
                User resUser2 = userDao.findByUser(user4);
                //  如果结果不是null
                if(resUser2 != null){
                    // 说明邮箱已经存在，然后返回错误信息
                    return AjaxResult.ERROR("邮箱已存在");
                }else { // 如果为null，经过三层筛选，说明可以添加
                    // 使用md5进行加密 得到秘钥
                    String md5 = MD5Util2.encode(user.getPassword(), "MD5");
                    // 将秘钥赋值给传递过来的user
                    user.setPassword(md5);
                    // 保存用户
                    userDao.save(user);
                    // 设置token，使用uuid创建一个token
                    String token = UUID.randomUUID().toString().replaceAll("-","");
                    // 存储到redis服务器：将token作为key，user作为对象，存储到redis数据库
                    redisTemplate.opsForValue().set("USER_INFO_"+token,user);
                    // 设置redis的生命周期 30分钟
                    redisTemplate.expire("USER_INFO_"+token,30, TimeUnit.MINUTES);
                    return AjaxResult.OK(token);
                }
            }
        }

    }

}
