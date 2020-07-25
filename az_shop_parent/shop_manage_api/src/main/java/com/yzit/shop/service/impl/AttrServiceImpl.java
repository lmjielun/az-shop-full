package com.yzit.shop.service.impl;

import com.alibaba.fastjson.JSON;
import com.yzit.framework.web.ui.Node;
import com.yzit.shop.dao.AttrMapper;
import com.yzit.shop.entity.Attr;
import com.yzit.shop.service.AttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("attr")
public class AttrServiceImpl implements AttrService {

    @Autowired
    private AttrMapper attrDao;

    @Override
    public List<Attr> findByList(Attr attr) {
        return attrDao.findByList(attr);
    }

    @Override
    public List<Attr> findAll() {
        return attrDao.findAll();
    }

    @Override
    public Attr findById(Long id) {
        return attrDao.selectByPrimaryKey(id);
    }

    @Override
    public int save(Attr attr) {

        // 使用fastJson，将list集合转为json字符串,存储到数据库的一个字段中
        String jsonString = JSON.toJSONString(attr.getItemList());
        // 换为字符串后，存入到数据库的 items 字段中
        attr.setAttrItems(jsonString);

        // 调用dao 进行保存
        return attrDao.insertSelective(attr);
    }

    @Override
    public int update(Attr attr) {

        String  str = JSON.toJSONString(attr.getItemList());

        attr.setAttrItems(str);

        return attrDao.updateByPrimaryKey(attr);
    }

    @Override
    public int del(Long id) {
        return attrDao.deleteByPrimaryKey(id);
    }
}
