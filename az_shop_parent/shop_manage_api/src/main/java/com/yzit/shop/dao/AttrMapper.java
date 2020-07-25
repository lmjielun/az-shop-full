package com.yzit.shop.dao;

import com.yzit.shop.entity.Attr;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AttrMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Attr record);

    int insertSelective(Attr record);

    Attr selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Attr record);

    int updateByPrimaryKey(Attr record);

    List<Attr> findByList(Attr attr);

    List<Attr> findAll();
}