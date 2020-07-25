package com.yzit.shop.service;

import com.yzit.shop.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findByList(Category category);

    List<Category> findAll();

    Category findById(Long id);

    int update(Category category);

    int save(Category category);

    int delete(Long id);
}
