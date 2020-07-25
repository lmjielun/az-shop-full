package com.yzit.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzit.framework.web.ui.AjaxResult;
import com.yzit.shop.entity.AD;
import com.yzit.shop.service.ADService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Api
@RequestMapping("api/aD")
public class ADController {

    @Autowired
    private ADService adService;

    @GetMapping("/page")
    @ApiOperation("广告分页")
    public PageInfo<AD> page(AD ad){
        PageHelper.startPage(ad.getPageNo(),ad.getPageSize());
        List<AD> adList = adService.findByList(ad);
        PageInfo<AD> pageInfo = new PageInfo<AD>(adList);
        return pageInfo;
    }

    @ApiOperation("广告列表")
    @GetMapping("/list")
    public List<AD> list(AD ad){
        // 调用分页插件
        PageHelper.startPage(1,ad.getCount());

        return  adService.findByList(ad);
    }

    @ApiOperation("根据广告id查询")
    @GetMapping("/{id}")
    public AD findById(@PathVariable Long id){
        return  adService.findById(id);
    }

    @ApiOperation("保存广告")
    @PostMapping("/aD")
    public AjaxResult save(@RequestBody AD ad){
        int count = adService.save(ad);
        if(count > 0 ){
            return AjaxResult.OK();
        }else{
            return AjaxResult.ERROR("添加广告失败");
        }
    }
    @ApiOperation("修改广告")
    @PutMapping("/aD")
    public AjaxResult update(@RequestBody AD ad){
        int count = adService.update(ad);
        if(count > 0 ){
            return AjaxResult.OK();
        }else{
            return AjaxResult.ERROR("修改广告失败");
        }
    }

    @ApiOperation("删除广告")
    @DeleteMapping("/{id}")
    public AjaxResult delete(@PathVariable Long id){
        int count = adService.delete(id);
        if(count > 0 ){
            return AjaxResult.OK();
        }else{
            return AjaxResult.ERROR("添加广告失败");
        }
    }

}
