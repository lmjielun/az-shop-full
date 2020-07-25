package com.yzit.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzit.framework.web.ui.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.yzit.shop.entity.Preferential;
import com.yzit.shop.service.PreferentialService;

@RestController
@RequestMapping("/api/preferential")
@CrossOrigin
public class PreferentialController {

    @Autowired
    private PreferentialService preferentialService;

    /**
     * 分页 方法
     * @param preferential
     * @return
     */
    @GetMapping("/page")
    public PageInfo<Preferential> page(Preferential preferential  ){
        PageHelper.startPage( preferential.getPageNo(),preferential.getPageSize()  );

        List<Preferential> preferentialList = preferentialService.findByList(preferential);
        PageInfo<Preferential> pageInfo  = new PageInfo<Preferential>(preferentialList);
        return pageInfo;
    }

    /**
     * 查询所有的优惠表
     * @return
     */
    @GetMapping("/list")
    public List<Preferential> list(){
       return  preferentialService.findAll();
    }

    /**
     * 根据id查询优惠表对象
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Preferential findById(@PathVariable Long  id){
       return  preferentialService.findById(id);
    }

    /**
     * 添加优惠表
     * @param preferential
     * @return
     */
    @PostMapping("/preferential")
    public AjaxResult save( @RequestBody Preferential preferential   ){
       int count =  preferentialService.save(preferential);
       if(count > 0 ){
	       return AjaxResult.OK();
       }
       return AjaxResult.ERROR("新增优惠表失败");
    }

    /**
     * 修改优惠表
     * @param preferential
     * @return
     */
    @PutMapping("/preferential")
    public AjaxResult update( @RequestBody  Preferential preferential){
        int count =preferentialService.update(preferential);

        if(count > 0 ){
            return AjaxResult.OK();
        }
        return AjaxResult.ERROR("修改优惠表失败");
    }
    @DeleteMapping("/{id}")
    public AjaxResult  del( @PathVariable Long id  ){
        int count = preferentialService.del(id);
        if(count > 0 ){
         	return AjaxResult.OK();
        }
        return AjaxResult.ERROR("删除优惠表失败");
    }
    @DeleteMapping("/batch/{ids}")
    public AjaxResult  batchDel(@PathVariable Long []  ids  ){
       boolean   success =  preferentialService.batchDel(ids);
       if(success){//删除成功
           return AjaxResult.OK();
       }
        return AjaxResult.ERROR("批量删除优惠表失败");
    }
}