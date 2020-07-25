package com.yzit.shop.dao;

import com.yzit.shop.entity.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParamMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Param record);

    int insertSelective(Param record);

    Param selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Param record);

    int updateByPrimaryKey(Param record);

    List<Param> findByList(Param param);
}