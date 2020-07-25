package com.yzit.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzit.framework.web.ui.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.yzit.shop.entity.AdCategory;
import com.yzit.shop.service.AdCategoryService;

@RestController
@RequestMapping("/api/adCategory")
@CrossOrigin
@Api("AdCategory管理")
public class AdCategoryController {

    @Autowired
    private AdCategoryService adCategoryService;

    /**
     * 分页 方法
     * @param adCategory
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分页方法")
    public PageInfo<AdCategory> page(AdCategory adCategory  ){
        PageHelper.startPage( adCategory.getPageNo(),adCategory.getPageSize()  );

        List<AdCategory> adCategoryList = adCategoryService.findByList(adCategory);
        PageInfo<AdCategory> pageInfo  = new PageInfo<AdCategory>(adCategoryList);
        return pageInfo;
    }

    /**
     * 查询所有的AdCategory
     * @return
     */
    @ApiOperation("所有AdCategory")
    @GetMapping("/list")
    public List<AdCategory> list(){
       return  adCategoryService.findAll();
    }

    /**
     * 根据id查询AdCategory对象
     * @param id
     * @return
     */
    @ApiOperation("根据id查询AdCategory")
    @GetMapping("/{id}")
    public AdCategory findById(@PathVariable Long  id){
       return  adCategoryService.findById(id);
    }

    /**
     * 添加AdCategory
     * @param adCategory
     * @return
     */
    @ApiOperation("添加AdCategory")
    @PostMapping("/adCategory")
    public AjaxResult save( @RequestBody AdCategory adCategory   ){
       int count =  adCategoryService.save(adCategory);
       if(count > 0 ){
	       return AjaxResult.OK();
       }
       return AjaxResult.ERROR("新增AdCategory失败");
    }

    /**
     * 修改AdCategory
     * @param adCategory
     * @return
     */
    @ApiOperation("修改AdCategory")
    @PutMapping("/adCategory")
    public AjaxResult update( @RequestBody  AdCategory adCategory){
        int count =adCategoryService.update(adCategory);

        if(count > 0 ){
            return AjaxResult.OK();
        }
        return AjaxResult.ERROR("修改AdCategory失败");
    }
    @ApiOperation("删除AdCategory")
    @DeleteMapping("/{id}")
    public AjaxResult  del( @PathVariable Long id  ){
        int count = adCategoryService.del(id);
        if(count > 0 ){
         	return AjaxResult.OK();
        }
        return AjaxResult.ERROR("删除AdCategory失败");
    }
    @ApiOperation("批量删除AdCategory")
    @DeleteMapping("/batch/{ids}")
    public AjaxResult  batchDel(@PathVariable Long []  ids  ){
       boolean   success =  adCategoryService.batchDel(ids);
       if(success){//删除成功
           return AjaxResult.OK();
       }
        return AjaxResult.ERROR("批量删除AdCategory失败");
    }
}