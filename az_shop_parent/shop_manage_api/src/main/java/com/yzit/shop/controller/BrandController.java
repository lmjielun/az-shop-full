package com.yzit.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzit.framework.web.ui.AjaxResult;
import com.yzit.shop.entity.Brand;
import com.yzit.shop.service.BrandService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 分页 方法
     * @param brand
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分页方法")
    public PageInfo<Brand> page(Brand brand ){
        PageHelper.startPage( brand.getPageNo(),brand.getPageSize());
        List<Brand> list = brandService.findByList(brand);
        PageInfo<Brand> pageInfo = new PageInfo<Brand>(list);
        return pageInfo;
    }


    @ApiOperation("品牌列表+查询")
    @GetMapping("/list")
    public List<Brand> getBrandService() {

        return brandService.findAll();
    }

    /**
     * 根据id查询品牌表对象
     * @param id
     * @return
     */
    @ApiOperation("根据id查询品牌表")
    @GetMapping("/{id}")
    public Brand findById(@PathVariable Long  id){
        return  brandService.findById(id);
    }

    /**
     * 添加品牌表
     * @param brand
     * @return
     */
    @ApiOperation("添加品牌表")
    @PostMapping("/brand")
    public AjaxResult save( @RequestBody Brand brand   ){
        int count =  brandService.save(brand);
        if(count > 0 ){
            return AjaxResult.OK();
        }
        return AjaxResult.ERROR("新增品牌表失败");
    }

    @ApiOperation("添加品牌表")
    @PostMapping("/save.do")
    public AjaxResult save2( Brand brand   ){
        int count =  brandService.save(brand);
        if(count > 0 ){
            return AjaxResult.OK();
        }
        return AjaxResult.ERROR("新增品牌表失败");
    }

    /**
     * 修改品牌表
     * @param brand
     * @return
     */
    @ApiOperation("修改品牌表")
    @PutMapping("/brand")
    public AjaxResult update(@RequestBody Brand brand){
        int count =brandService.update(brand);

        if(count > 0 ){
            return AjaxResult.OK();
        }
        return AjaxResult.ERROR("修改品牌表失败");
    }


    @ApiOperation("删除品牌表")
    @DeleteMapping("/{id}")
    public AjaxResult  del( @PathVariable Long id  ){
        int count = brandService.del(id);
        if(count > 0 ){
            return AjaxResult.OK();
        }
        return AjaxResult.ERROR("删除品牌表失败");
    }


    @ApiOperation("批量删除品牌表")
    @DeleteMapping("/batch/{ids}")
    public AjaxResult  batchDel(@PathVariable Long []  ids  ){
        boolean   success =  brandService.batchDel(ids);
        if(success){//删除成功
            return AjaxResult.OK();
        }
        return AjaxResult.ERROR("批量删除品牌表失败");
    }

}
