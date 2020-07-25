package com.yzit.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzit.framework.web.ui.AjaxResult;
import com.yzit.shop.entity.Category;
import com.yzit.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/category")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    // 分页+查询
    @GetMapping("/page")
    public PageInfo<Category> page(Category category){

        PageHelper.startPage(category.getPageNo(),category.getPageSize());

        List<Category> list = categoryService.findByList(category);

        PageInfo<Category> pageInfo = new PageInfo<Category>(list);

        return pageInfo;
    }

    @GetMapping("/list")
    public List<Category> findAll(Category category){
        return categoryService.findByList(category);
    }

    // 级联查询
    @GetMapping("/cascadeList")
    public List<Category> cascadeList(Category  category){
        /** 1、调用findByList方法，查询一级产品 */
        List<Category> categoryList_1 = categoryService.findByList(category);
        /** 2、定义二级分类集合 */
        List<Category> categoryList_2 = null;
        /** 3、定义三级级分类集合 */
        List<Category> categoryList_3 = null;

        /** 4、判断是否级联  如果Cascade == 1 说明是级联*/
        if(category.getIsCascade() != null && category.getIsCascade().intValue() == 1){

            /** 5、级联的话，开始循环一级分类数据集合，查询二级分类数据 */
            for (Category category1 : categoryList_1){

                /** 6、设置传递过来的category的parentId值为当前类的id */
                category.setParentId(category1.getId());

                /** 7、根据设置好的parentId值，查询二级数据 调用findByList方法 */
                categoryList_2 = categoryService.findByList(category);


                /** 8、判断二级数据是否为空 */
                if(categoryList_2 != null && !categoryList_2.isEmpty()){
                    /** 10、循环二级分类数据集合，查询三级分类数据 */
                    for(Category category2 :categoryList_2 ){

                        /** 11、设置传递过来的category的parentId值为当前类的id */
                        category.setParentId(category2.getId());

                        /** 12、调用findByList方法，查询三级分类数据*/
                        categoryList_3 = categoryService.findByList(category);

                        /** 13、将查询到的三级分类，赋值给二级分类的children属性*/
                        category2.setChildren(categoryList_3);
                    }

                 /** 9、将二级分类数据赋值给一级分类的children属性*/
                 category1.setChildren(categoryList_2);
                }
            }
        }
        /** 14、 将集合返回*/
        return categoryList_1;
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable Long id){
       return  categoryService.findById(id);
    }

    @PutMapping("/category")
    public AjaxResult update(@RequestBody Category category){
        int count = categoryService.update(category);
        if(count > 0 ){
            return AjaxResult.OK();
        }else{
            return AjaxResult.ERROR("操作失败");
        }
    }

    @PostMapping("/category")
    public AjaxResult save(@RequestBody Category category){
        int count = categoryService.save(category);
        if(count > 0 ){
            return AjaxResult.OK();
        }else{
            return AjaxResult.ERROR("操作失败");
        }
    }

    // 删除第一种方法，如果该商品有下级，则不能删除
    @DeleteMapping("/{id}")
    public AjaxResult delete(@PathVariable Long id){
        int count = categoryService.delete(id);
        if(count > 0 ){
            return AjaxResult.OK();
        }else{
            return AjaxResult.ERROR("删除失败，该商品存在下级分类，不能删除，请从最底层商品开始删除");
        }
    }

    // 删除第二种方式，如果有下级商品，将下级商品一并删除
/*    @DeleteMapping("/{id}")
    public AjaxResult delete(@PathVariable Long id){
        int count = categoryService.delete(id);
        if(count > 0 ){
            return AjaxResult.OK();
        }else{
            return AjaxResult.ERROR("删除失败");
        }
    }*/

}
