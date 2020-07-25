package com.yzit.shop.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzit.framework.web.ui.AjaxResult;
import com.yzit.framework.web.ui.Node;
import com.yzit.shop.entity.Spec;
import io.swagger.annotations.ApiOperation;
import org.codehaus.jettison.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/spec")
public class SpecController {
    @Autowired
    private com.yzit.shop.service.SpecService specService;

    @GetMapping("/page")
    @ApiOperation("分页方法")
    public PageInfo<Spec> page(Spec spec ){
        PageHelper.startPage( spec.getPageNo(),spec.getPageSize());
        List<Spec> list = specService.findByList(spec);
        PageInfo<Spec> pageInfo = new PageInfo<Spec>(list);
        return pageInfo;
    }


    @ApiOperation("属性列表+查询")
    @GetMapping("/list")
    public List<Spec> findAll(Spec spec ) {
        return specService.findByList(spec);
    }

    @ApiOperation("属性列表+查询")
    @GetMapping("/list2")
    public List<Spec> findAll2(  ) {

         return  specService.findByList2();
    }


    @ApiOperation("根据id查询属性")
    @GetMapping("/{id}")
    public Spec findById(@PathVariable Long  id){
        return  specService.findById(id);
    }

    /**
     * 添加属性表
     * @spec
     * @return
     */
    @ApiOperation("添加属性")
    @PostMapping("/spec")
    public AjaxResult save(@RequestBody Spec spec   ){
        int count =  specService.save(spec);
        if(count > 0 ){
            return AjaxResult.OK();
        }
        return AjaxResult.ERROR("新增属性失败");
    }

    @ApiOperation("修改属性")
    @PutMapping("/spec")
    public AjaxResult update(@RequestBody Spec spec){
        int count =specService.update(spec);

        if(count > 0 ){
            return AjaxResult.OK();
        }
        return AjaxResult.ERROR("修改属性失败");
    }


    @ApiOperation("删除属性")
    @DeleteMapping("/{id}")
    public AjaxResult  del( @PathVariable Long id  ){
        int count = specService.del(id);
        if(count > 0 ){
            return AjaxResult.OK();
        }
        return AjaxResult.ERROR("删除属性失败");
    }


}
