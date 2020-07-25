package com.yzit.shop.service;

import com.yzit.shop.entity.Attr;

import java.util.List;

public interface AttrService {
    List<Attr> findByList(Attr attr);

    List<Attr> findAll();

    Attr findById(Long id);

    int save(Attr attr);

    int update(Attr attr);

    int del(Long id);
}
