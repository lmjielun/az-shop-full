package com.yzit.shop.service;

import com.yzit.shop.entity.Param;

import java.util.List;

public interface ParamService {

    List<Param> findByList(Param param);

    Param findById(Long id);

    int save(Param param);

    int update(Param param);

    int del(Long id);
}
