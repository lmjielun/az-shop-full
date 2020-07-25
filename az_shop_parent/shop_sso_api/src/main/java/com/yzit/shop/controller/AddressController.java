package com.yzit.shop.controller;
import com.yzit.framework.web.ui.AjaxResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.yzit.shop.entity.Address;
import com.yzit.shop.service.AddressService;

@RestController
@RequestMapping("/api/address")
@CrossOrigin
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * 修改默认地址
     * @param id
     * @return
     */
    @GetMapping("/{userId}/{id}")
    public AjaxResult findById(@PathVariable String userId,@PathVariable Integer  id ){
        addressService.deleteDefulatAddress(userId);
        addressService.updateDefulatAddress(id);
        return  AjaxResult.OK();
    }

    @GetMapping("/defaultAddress")
    public Address findDefaultAddress( ){
        return addressService.findDefaultAddress();
    }


    /**
     * 根据用户的id查询用户下所有的收货地址
     * @return
     */
    @GetMapping("/list/{userId}")
    public List<Address> list(@PathVariable Integer userId){
        Address address = new Address();
        address.setUserId(userId.toString());
       return  addressService.findByList(address);
    }

    /**
     * 根据id查询地址表对象
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Address findById(@PathVariable Long  id){
       return  addressService.findById(id);
    }


    /**
     * 添加地址表
     * @param address
     * @return
     */
    @PostMapping("/address")
    public AjaxResult save( @RequestBody Address address   ){
       int count =  addressService.save(address);
       if(count > 0 ){
	       return AjaxResult.OK();
       }
       return AjaxResult.ERROR("新增地址表失败");
    }

    /**
     * 修改地址表
     * @param address
     * @return
     */
    @PutMapping("/address")
    public AjaxResult update( @RequestBody  Address address){
        int count =addressService.update(address);

        if(count > 0 ){
            return AjaxResult.OK();
        }
        return AjaxResult.ERROR("修改地址表失败");
    }
    @DeleteMapping("/{id}")
    public AjaxResult  del( @PathVariable Long id  ){
        int count = addressService.del(id);
        if(count > 0 ){
         	return AjaxResult.OK();
        }
        return AjaxResult.ERROR("删除地址表失败");
    }
    @DeleteMapping("/batch/{ids}")
    public AjaxResult  batchDel(@PathVariable Long []  ids  ){
       boolean   success =  addressService.batchDel(ids);
       if(success){//删除成功
           return AjaxResult.OK();
       }
        return AjaxResult.ERROR("批量删除地址表失败");
    }
}