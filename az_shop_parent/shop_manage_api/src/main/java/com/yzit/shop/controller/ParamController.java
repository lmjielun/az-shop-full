package com.yzit.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzit.framework.web.ui.AjaxResult;
import com.yzit.shop.entity.Param;
import com.yzit.shop.service.ParamService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/param")
public class ParamController {
    
    
    @Autowired
    private ParamService paramService;

    @GetMapping("/page")
    @ApiOperation("分页方法")
    public PageInfo<Param> page(Param param ){
        PageHelper.startPage( param.getPageNo(),param.getPageSize());
        List<Param> list = paramService.findByList(param);
        PageInfo<Param> pageInfo = new PageInfo<Param>(list);
        return pageInfo;
    }


    @ApiOperation("属性列表+查询")
    @GetMapping("/list")
    public List<Param> findAll(Param param ) {
        return paramService.findByList(param);
    }



    @ApiOperation("根据id查询属性")
    @GetMapping("/{id}")
    public Param findById(@PathVariable Long  id){
        return  paramService.findById(id);
    }

    /**
     * 添加属性表
     * @param
     * @return
     */
    @ApiOperation("添加属性")
    @PostMapping("/param")
    public AjaxResult save(@RequestBody Param param   ){
        int count =  paramService.save(param);
        if(count > 0 ){
            return AjaxResult.OK();
        }
        return AjaxResult.ERROR("新增属性失败");
    }

    @ApiOperation("修改属性")
    @PutMapping("/param")
    public AjaxResult update(@RequestBody Param param){
        int count =paramService.update(param);

        if(count > 0 ){
            return AjaxResult.OK();
        }
        return AjaxResult.ERROR("修改属性失败");
    }


    @ApiOperation("删除属性")
    @DeleteMapping("/{id}")
    public AjaxResult  del( @PathVariable Long id  ){
        int count = paramService.del(id);
        if(count > 0 ){
            return AjaxResult.OK();
        }
        return AjaxResult.ERROR("删除属性失败");
    }
    
    
    
}
