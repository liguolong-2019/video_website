package com.cqupt.demo.Controller;


import com.alibaba.fastjson.JSONObject;
import com.cqupt.demo.Service.AdminService;
import com.cqupt.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;
    @PostMapping("/login")
    public JSONObject login(@RequestParam String userName, @RequestParam String password, HttpSession session){
        return userService.login(userName, password, session);
    }
    @PostMapping("/adlogin")
    public JSONObject adlogin(@RequestParam String adminName, @RequestParam String password, HttpSession session){
        return adminService.adlogin(adminName, password, session);
    }
}
