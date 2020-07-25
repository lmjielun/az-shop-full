package com.yzit.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzit.framework.web.ui.AjaxResult;

import com.yzit.framework.web.ui.FileVo;
import com.yzit.shop.entity.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.yzit.shop.entity.Goods;
import com.yzit.shop.service.GoodsService;

@RestController
@RequestMapping("/api/goods")
@CrossOrigin
@Api("Goods管理")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 分页 方法
     * @param goods
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分页方法")
    public PageInfo<Goods> page(Goods goods  ){
        PageHelper.startPage( goods.getPageNo(),goods.getPageSize()  );

        List<Goods> goodsList = goodsService.findByList(goods);
        PageInfo<Goods> pageInfo  = new PageInfo<Goods>(goodsList);
        return pageInfo;
    }

    /**
     * 查询所有的Goods
     * @return
     */
    @ApiOperation("所有Goods")
    @GetMapping("/list")
    public List<Goods> list(){
       return  goodsService.findAll();
    }


    @ApiOperation("根据三级分类查询商品")
    @GetMapping("/category/{categoryId}")
    public List<Goods> findByCategoryId(@PathVariable Integer categoryId){

        List<Goods> goodsList = new ArrayList<Goods>();

        if(categoryId != null ){
            Goods goods = new Goods();
            goods.setCategory3Id(categoryId);
            goodsList = goodsService.findByList(goods);
        }
        return  goodsList;
    }

    /**
     * 根据id查询Goods对象
     * @param id
     * @return
     */
    @ApiOperation("根据id查询Goods")
    @GetMapping("/{id}")
    public Goods findById(@PathVariable Long  id){
       return  goodsService.findById(id);
    }


    /**
     * 根据商品Id获取商品分类
     * @param id
     * @return
     */
    @ApiOperation("根据所有的商品分类查询id")
    @GetMapping("/goodsId/{id}")
    public Category findByGoodsId(@PathVariable Long  id){
        return  goodsService.findByGoodsId(id);
    }




    /**
     * 添加Goods
     * @param goods
     * @return
     */
    @ApiOperation("添加Goods")
    @PostMapping("/goods")
    public AjaxResult save( @RequestBody Goods goods   ){
       int count =  goodsService.save(goods);
       if(count > 0 ){
	       return AjaxResult.OK();
       }
       return AjaxResult.ERROR("新增Goods失败");
    }

    /**
     * 修改Goods
     * @param goods
     * @return
     */
    @ApiOperation("修改Goods")
    @PutMapping("/goods")
    public AjaxResult update( @RequestBody  Goods goods){
        int count =goodsService.update(goods);

        if(count > 0 ){
            return AjaxResult.OK();
        }
        return AjaxResult.ERROR("修改Goods失败");
    }
    @ApiOperation("删除Goods")
    @DeleteMapping("/{id}")
    public AjaxResult  del( @PathVariable Long id  ){
        int count = goodsService.del(id);
        if(count > 0 ){
         	return AjaxResult.OK();
        }
        return AjaxResult.ERROR("删除Goods失败");
    }
    @ApiOperation("批量删除Goods")
    @DeleteMapping("/batch/{ids}")
    public AjaxResult  batchDel(@PathVariable Long []  ids  ){
       boolean   success =  goodsService.batchDel(ids);
       if(success){//删除成功
           return AjaxResult.OK();
       }
        return AjaxResult.ERROR("批量删除Goods失败");
    }
}