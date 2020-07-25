package com.yzit.shop.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yzit.framework.web.ui.Node;
import com.yzit.shop.dao.SpecMapper;
import com.yzit.shop.entity.Spec;
import com.yzit.shop.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("spec")
public class SpecServiceImpl implements SpecService {

    @Autowired
    private SpecMapper specDao;

    @Override
    public List<Spec> findByList(Spec spec) {
        return specDao.findByList(spec);
    }

    @Override
    public List<Spec> findByList2() {

        List<Spec> specs = specDao.findByList2();

        for(Spec s : specs){
            s.setSpecList(JSON.parseArray(s.getSpecItems(), Node.class));
        }

        return  specs;
    }

    @Override
    public Spec findById(Long id) {
        // 1、获取到规格对象
        Spec spec = specDao.selectByPrimaryKey(id);

        // 2、拿到specItems，json格式的对象，将其转为list集合
        String jsonStr = spec.getSpecItems();

        // 3、使用fastJson将 jsonStr 转为list集合，Node是一个类
        List<Node> specList = JSONArray.parseArray(jsonStr, Node.class);

        // 4、将speList 赋值给 spec 对象的 specList集合
        spec.setSpecList(specList);

        // 5、将spec对象返回
        return spec;

    }

    @Override
    public int save(Spec spec) {
        // 获取specList集合，将其转为json字符串
        String jsonStr = JSON.toJSONString(spec.getSpecList());
        // 将json字符串赋值给 specItems
        spec.setSpecItems(jsonStr);
        // 调用dao进行添加
        return specDao.insertSelective(spec);
    }

    @Override
    public int update(Spec spec) {
        // 获取到spec对象的lsit集合，将其转为json对象，以json字符串的形式存入数据库
        spec.setSpecItems(JSON.toJSONString(spec.getSpecList()));
        return specDao.updateByPrimaryKeySelective(spec);
    }

    @Override
    public int del(Long id) {
        return specDao.deleteByPrimaryKey(id);
    }
}
