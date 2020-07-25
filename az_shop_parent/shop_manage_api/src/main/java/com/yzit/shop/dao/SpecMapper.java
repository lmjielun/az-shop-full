package com.yzit.shop.dao;

import com.yzit.shop.entity.Spec;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SpecMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Spec record);

    int insertSelective(Spec record);

    Spec selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Spec record);

    int updateByPrimaryKey(Spec record);

    List<Spec> findByList(Spec spec);

    List<Spec> findByList2();
}