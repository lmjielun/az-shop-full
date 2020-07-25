package com.yzit.shop.service.impl;

import com.yzit.shop.dao.ADDao;
import com.yzit.shop.entity.AD;
import com.yzit.shop.service.ADService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("ad")
public class ADServiceImpl implements ADService {

    @Autowired
    private ADDao adDao;

    // 注入Redis
    @Autowired
    private RedisTemplate redisTemplate;

    public List<AD> findByList(AD ad) {
        return adDao.findByList(ad);
    }

    public List<AD> findAll() {
        return adDao.findAll();
    }

    public AD findById(Long id) {
        return adDao.findById(id);
    }

    public int save(AD ad) {
        // 获取数据：和redis命令相同，相当于keys * INDEX_AD_* 获取图片的key
        Set<String> keySet = redisTemplate.keys("*INDEX_AD_*");
        if(!keySet.isEmpty()){
            // 遍历获取到的key集合
            for(String key : keySet){
                System.out.println(key);
                // 然后将key删除
                redisTemplate.delete(key);
            }
        }

        return adDao.save(ad);
    }

    public int update(AD ad) {
        return adDao.update(ad);
    }

    public int delete(Long id) {
        return adDao.delById(id);
    }
}
