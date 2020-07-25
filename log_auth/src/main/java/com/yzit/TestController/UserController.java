package com.yzit.TestController;

import com.yzit.TestEntityClass.User;
import com.yzit.annotation.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class UserController {

    @Log(name="查询用户列表",option = "SELECT")
    @GetMapping("/user/list")
    public List<User> userList(){

        return null;
    }

    @Log(name="添加用户",option="INSERT")
    @PostMapping("/user/save")
    public void saveUser(User user){

        System.out.println("执行保存用户");
    }
}
