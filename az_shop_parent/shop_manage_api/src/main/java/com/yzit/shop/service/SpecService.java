package com.yzit.shop.service;

import com.yzit.shop.entity.Spec;

import java.util.List;

public interface SpecService {
    List<Spec> findByList(Spec spec);

    Spec findById(Long id);

    int save(Spec spec);

    int update(Spec spec);

    int del(Long id);

    List<Spec> findByList2();
}
