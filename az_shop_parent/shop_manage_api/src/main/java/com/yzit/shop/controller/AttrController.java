package com.yzit.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzit.framework.web.ui.AjaxResult;
import com.yzit.shop.entity.Attr;
import com.yzit.shop.entity.Brand;
import com.yzit.shop.service.AttrService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/attr")
public class AttrController {

    @Autowired
    private AttrService attrService;

    @GetMapping("/page")
    @ApiOperation("分页方法")
    public PageInfo<Attr> page(Attr attr ){
        PageHelper.startPage( attr.getPageNo(),attr.getPageSize());
        List<Attr> list = attrService.findByList(attr);
        PageInfo<Attr> pageInfo = new PageInfo<Attr>(list);
        return pageInfo;
    }


    @ApiOperation("属性列表+查询")
    @GetMapping("/list")
    public List<Attr> getBrandService(Attr attr ) {
        return attrService.findByList(attr);
    }

 /*   @GetMapping("/findCategoryId/{categoryId}")
    public List<Attr> findByCategoryId(@PathVariable Long  categoryId){
        Attr attr = new Attr();
        attr.setCategoryId(categoryId);
        return  attrService.findByList(attr);
    }*/

    @ApiOperation("根据id查询属性")
    @GetMapping("/{id}")
    public Attr findById(@PathVariable Long  id){
        return  attrService.findById(id);
    }

    /**
     * 添加属性表
     * @param
     * @return
     */
    @ApiOperation("添加属性")
    @PostMapping("/attr")
    public AjaxResult save(@RequestBody Attr attr   ){
        int count =  attrService.save(attr);
        if(count > 0 ){
            return AjaxResult.OK();
        }
        return AjaxResult.ERROR("新增属性失败");
    }

    @ApiOperation("修改属性")
    @PutMapping("/attr")
    public AjaxResult update(@RequestBody Attr attr){
        int count =attrService.update(attr);

        if(count > 0 ){
            return AjaxResult.OK();
        }
        return AjaxResult.ERROR("修改属性失败");
    }


    @ApiOperation("删除属性")
    @DeleteMapping("/{id}")
    public AjaxResult  del( @PathVariable Long id  ){
        int count = attrService.del(id);
        if(count > 0 ){
            return AjaxResult.OK();
        }
        return AjaxResult.ERROR("删除属性失败");
    }
}
