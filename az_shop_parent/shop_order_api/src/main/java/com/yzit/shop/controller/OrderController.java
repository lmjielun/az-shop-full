package com.yzit.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzit.framework.web.ui.AjaxResult;

import com.yzit.shop.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.yzit.shop.entity.Order;
import com.yzit.shop.service.OrderService;

@RestController
@RequestMapping("/api/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 注入雪花算法java类
    @Autowired
    private IdWorker idWorker;

    /**
     * 分页 方法
     * @param order
     * @return
     */
    @GetMapping("/page")
    public PageInfo<Order> page(Order order  ){
        PageHelper.startPage( order.getPageNo(),order.getPageSize()  );

        List<Order> orderList = orderService.findByList(order);
        PageInfo<Order> pageInfo  = new PageInfo<Order>(orderList);
        return pageInfo;
    }

    /**
     * 查询所有的订单
     * @return
     */
    @GetMapping("/list")
    public List<Order> list(){
       return  orderService.findAll();
    }

    /**
     * 根据id查询订单对象
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Order findById(@PathVariable Long  id){
       return  orderService.findById(id);
    }

    /**
     * 添加订单
     * @param order
     * @return
     */
    @PostMapping("/order")
    public AjaxResult save( @RequestBody Order order   ){
       int count =  orderService.save(order);
       if(count > 0 ){
	       return AjaxResult.OK();
       }
       return AjaxResult.ERROR("新增订单失败");
    }

    /**
     * 修改订单
     * @param order
     * @return
     */
    @PutMapping("/order")
    public AjaxResult update( @RequestBody  Order order){
        int count =orderService.update(order);
        if(count > 0 ){
            return AjaxResult.OK();
        }
        return AjaxResult.ERROR("修改订单失败");
    }

    @DeleteMapping("/{id}")
    public AjaxResult  del( @PathVariable Long id  ){
        int count = orderService.del(id);
        if(count > 0 ){
         	return AjaxResult.OK();
        }
        return AjaxResult.ERROR("删除订单失败");
    }
    @DeleteMapping("/batch/{ids}")
    public AjaxResult  batchDel(@PathVariable Long []  ids  ){
       boolean   success =  orderService.batchDel(ids);
       if(success){//删除成功
           return AjaxResult.OK();
       }
        return AjaxResult.ERROR("批量删除订单失败");
    }
}