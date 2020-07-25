package com.yzit.shop.service.impl;

import com.yzit.shop.dao.CategoryMapper;
import com.yzit.shop.entity.Category;
import com.yzit.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryDao;


    @Override
    public List<Category> findByList(Category category) {
        return categoryDao.findByList(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryDao.selectByPrimaryKey(id);
    }

    @Override
    public int update(Category category) {
        return categoryDao.updateByPrimaryKey(category);
    }

    @Override
    public int save(Category category) {
        return categoryDao.insertSelective(category);
    }

    // 删除方式第一种，如果有下级，那么不允许删除
   @Override
    public int delete(Long id) {
        // 把要删除的主键id 作为 parentId进行查询，如果能查询到，说明他是父类，是父类就不能删除
        List<Category> categoryList = categoryDao.selectByParentId(id);
        // 判断 拿到的list集合是否大于0，如果大于0说明有下级，不能删除，返回0
        if(categoryList.size() >0 ){
            return 0;
        }else{
            // 否则，说明没有下级商品，可以删除
            return categoryDao.deleteByPrimaryKey(id);
        }
    }

    // 删除方式第二种，如果有下级，一并删除
    /*@Override
    public int delete(Long id) {
        // 获取到他的下级商品
        List<Category> categoryList = categoryDao.selectByParentId(id);

        if(categoryList.size() > 0 ){
            for(Category c : categoryList){
                // 循环获取到下级商品的id
                Long id1 = c.getId();
                // 在根据下级商品的id查询他的下级商品
                List<Category> categories = categoryDao.selectByParentId(id1);
                // 在判断下是集合是否有值
                if(categories.size()>0){
                    for(Category cc : categories){
                        // 删除三级数据
                        categoryDao.deleteByPrimaryKey(cc.getId());
                    }
                }
                // 删除二级数据
                categoryDao.deleteByPrimaryKey(c.getId());
            }
        }
        // 删除以及数据
       return categoryDao.deleteByPrimaryKey(id);
    }*/
}
