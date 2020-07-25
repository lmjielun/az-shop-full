package com.yzit.shop.service;

import com.yzit.shop.entity.AD;

import java.util.List;

public interface ADService {
    List<AD> findByList(AD ad);

    List<AD> findAll();

    AD findById(Long id);

    int save(AD ad);

    int update(AD ad);

    int delete(Long id);
}
