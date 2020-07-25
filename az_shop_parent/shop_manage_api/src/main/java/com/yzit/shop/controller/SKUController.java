package com.yzit.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzit.framework.web.ui.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.yzit.shop.entity.SKU;
import com.yzit.shop.service.SKUService;

@RestController
@RequestMapping("/api/sku")
@CrossOrigin
@Api("SKU管理")
public class SKUController {

    @Autowired
    private SKUService sKUService;

    /**
     * 分页 方法
     * @param sKU
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分页方法")
    public PageInfo<SKU> page(SKU sKU  ){
        PageHelper.startPage( sKU.getPageNo(),sKU.getPageSize()  );

        List<SKU> sKUList = sKUService.findByList(sKU);
        PageInfo<SKU> pageInfo  = new PageInfo<SKU>(sKUList);
        return pageInfo;
    }

    /**
     * 查询所有的SKU
     * @return
     */
    @ApiOperation("所有SKU")
    @GetMapping("/list")
    public List<SKU> list(){
       return  sKUService.findAll();
    }

    /**
     * 根据id查询SKU对象
     * @param id
     * @return
     */
    @ApiOperation("根据id查询SKU")
    @GetMapping("/{id}")
    public SKU findById(@PathVariable Long  id){
       return  sKUService.findById(id);
    }

    /**
     * 添加SKU
     * @param sKU
     * @return
     */
    @ApiOperation("添加SKU")
    @PostMapping("/sKU")
    public AjaxResult save( @RequestBody SKU sKU   ){
       int count =  sKUService.save(sKU);
       if(count > 0 ){
	       return AjaxResult.OK();
       }
       return AjaxResult.ERROR("新增SKU失败");
    }

    /**
     * 修改SKU
     * @param sKU
     * @return
     */
    @ApiOperation("修改SKU")
    @PutMapping("/sKU")
    public AjaxResult update( @RequestBody  SKU sKU){
        int count =sKUService.update(sKU);

        if(count > 0 ){
            return AjaxResult.OK();
        }
        return AjaxResult.ERROR("修改SKU失败");
    }
    @ApiOperation("删除SKU")
    @DeleteMapping("/{id}")
    public AjaxResult  del( @PathVariable Long id  ){
        int count = sKUService.del(id);
        if(count > 0 ){
         	return AjaxResult.OK();
        }
        return AjaxResult.ERROR("删除SKU失败");
    }
    @ApiOperation("批量删除SKU")
    @DeleteMapping("/batch/{ids}")
    public AjaxResult  batchDel(@PathVariable Long []  ids  ){
       boolean   success =  sKUService.batchDel(ids);
       if(success){//删除成功
           return AjaxResult.OK();
       }
        return AjaxResult.ERROR("批量删除SKU失败");
    }
}