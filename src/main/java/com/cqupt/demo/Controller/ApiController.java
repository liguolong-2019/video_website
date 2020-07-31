package com.cqupt.demo.Controller;


import com.cqupt.demo.Bean.Admin;
import com.cqupt.demo.Bean.User;
import com.cqupt.demo.Service.AdminService;
import com.cqupt.demo.Service.UserService;
import com.cqupt.demo.utils.UserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Resource
    private UserService userService;

    @Resource
    private AdminService adminService;

    /**
     * 普通用户注册
     * @param user
     * @return
     */
    @PostMapping("/user")
    public Map<String, Object> addUser(User user){
        Map<String, Object> modelMap = new HashMap<>();
        if (!UserUtil.isNull(user)){
            User temp = userService.queryUser(user.getUserName());
            if (temp != null) {
                modelMap.put("success", false);
                modelMap.put("Msg","用户名已注册");
                return modelMap;
            }
            userService.addUser(user);
            modelMap.put("success",true);
            modelMap.put("Msg","注册成功");
        }else{
            modelMap.put("success", false);
            modelMap.put("Msg","用户信息不能为空,请重新输入");
        }
        return modelMap;
    }

    /**
     * 管理员注册
     * @param admin
     * @return
     */
    @PostMapping("/admin")
    public Map<String, Object> addAdmin(Admin admin) {
        Map<String, Object> modelMap = new HashMap<>();
        if (admin.getAdminName() != null && admin.getPassword() != null) {
            Admin temp = adminService.queryAdmin(admin.getAdminName());
            if (temp!=null){
                modelMap.put("success", false);
                modelMap.put("Msg","用户名已存在");
                return modelMap;
            }
            adminService.addAdmin(admin);
            modelMap.put("success", true);
            modelMap.put("Msg","注册成功");
        }else{
            modelMap.put("success", false);
            modelMap.put("Msg","注册失败");
        }

        return modelMap;

    }
}
