package com.yzit.shop.service;

import com.yzit.shop.entity.Brand;

import java.util.List;

public interface BrandService {

    /**
     * 保持对象
     *
     * @param brand
     */
    public int save(Brand brand);

    /**
     * 根据id删除对象
     *
     * @param id
     */
    public int del(Long id);

    /**
     * 修改对象
     *
     * @param brand
     */
    public int  update(Brand brand);

    /**
     * 检索所有的对象
     *
     * @return
     */
    public List<Brand> findAll();

    /**
     * 根据条件检索对象
     *
     * @param brand
     * @return
     */
    public List<Brand> findByList(Brand brand);

    /**
     * 根据id检索对象
     *
     * @param id
     * @return
     */
    public Brand  findById(Long id);


    /**
     * 批量删除信息
     *
     * @param ids 需要删除的ID集合
     * @return 结果
     */
    public boolean batchDel(Long[] ids) ;
}


