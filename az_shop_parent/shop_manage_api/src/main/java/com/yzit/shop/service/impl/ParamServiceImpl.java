package com.yzit.shop.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yzit.framework.web.ui.Node;
import com.yzit.shop.dao.ParamMapper;
import com.yzit.shop.entity.Param;
import com.yzit.shop.service.ParamService;
import org.codehaus.jettison.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("param")
public class ParamServiceImpl implements ParamService {

    @Autowired
    private ParamMapper paramDao;

    public List<Param> findByList(Param param) {
        return paramDao.findByList(param);
    }


    public Param findById(Long id) {
        // 1、拿到param对象
        Param param = paramDao.selectByPrimaryKey(id);
        // 2、获取param对象的 json字符串
        String paramItems = param.getParamItems();
        // 3、定义一个Node集合
        List<Node> list = new ArrayList<Node>();
        // 4、将param对象的json字符串转为node集合
        list = JSONObject.parseArray(paramItems,Node.class);
        // 5、将node集合 赋值给param对象的 paramList集合
        param.setParamList(list);
        // 6、返回param对象
        return  param;
    }

    public int save(Param param) {

        // 1、获取到paramList集合，将其转为json字符
        List<Node> paramList = param.getParamList();
        String jsonStr = JSON.toJSONString(paramList);

        // 2、将转好的jsonStr赋值给paramItems，保存到数据库
        param.setParamItems(jsonStr);

        return paramDao.insertSelective(param);
    }

    public int update(Param param) {
        // 1、获取param对象的 paramList集合
        List<Node> paramList = param.getParamList();
        // 2、使用fastJson 将集合转为json字符串
        String jsonStr = JSON.toJSONString(paramList);
        // 3、将jsonStr字符串，赋值给param对象的 paramItems属性
        param.setParamItems(jsonStr);

        // 4、调用dao进行修改
        return paramDao.updateByPrimaryKey(param);
    }

    public int del(Long id) {
        return paramDao.deleteByPrimaryKey(id);
    }
}
