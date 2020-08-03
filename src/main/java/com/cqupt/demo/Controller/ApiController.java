package com.cqupt.demo.Controller;


import com.alibaba.fastjson.JSONObject;
import com.cqupt.demo.Bean.Admin;
import com.cqupt.demo.Bean.User;
import com.cqupt.demo.Service.AdminService;
import com.cqupt.demo.Service.UserService;
import com.cqupt.demo.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Resource
    private UserService userService;

    @Resource
    private AdminService adminService;

    @Resource
    JavaMailSenderImpl mailSender;

    /**
     * 普通用户注册
     *
     * @param user
     * @return
     */
    @PostMapping("/user")
    public Map<String, Object> addUser(@RequestBody User user) throws MessagingException {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (!UserUtil.isNull(user)) {
            User temp = userService.queryUser(user.getUserName());
            if (temp != null) {
                modelMap.put("success", false);
                modelMap.put("Msg", "用户名已注册");
                return modelMap;
            }
            if(userService.addUser(user)==1) {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setSubject("welcome to video system !!!!");
                String text = "恭喜你注册成功\n你的账号为:" + user.getUserName() + "\n" + "密码为:" + user.getPassword();
                message.setText(text);
                message.setTo(user.getEmail());
                message.setFrom(mailSender.getUsername());
                mailSender.send(message);
                modelMap.put("success", true);
                modelMap.put("Msg", "注册成功");
            }else{
                modelMap.put("Msg", "用户注册失败");
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("Msg", "用户信息不能为空,请重新输入");
        }
        return modelMap;
    }

    /**
     * 管理员注册
     * @param admin
     * @return
     */
    @PostMapping("/admin")
    public Map<String, Object> addAdmin(@RequestBody Admin admin) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (admin.getAdminName() != null && admin.getPassword() != null) {
            Admin temp = adminService.queryAdmin(admin.getAdminName());
            if (temp!=null){
                modelMap.put("success", false);
                modelMap.put("Msg","用户名已存在");
                return modelMap;
            }
                adminService.addAdmin(admin);
                modelMap.put("success", true);
                modelMap.put("Msg", "注册成功");

        }else{
            modelMap.put("success", false);
            modelMap.put("Msg","注册失败");
        }

        return modelMap;

    }

    /**
     * 普通用户登陆
     * @param userName
     * @param password
     * @param request
     * @return
     */
    @PostMapping("/login")
    public JSONObject login(@RequestParam String userName, @RequestParam String password, HttpServletRequest request){
        HttpSession session = request.getSession(true);
        return userService.login(userName, password, session);
    }

    /**
     * 管理员用户登陆
     * @param adminName
     * @param password
     * @param request
     * @return
     */
    @PostMapping("/adlogin")
    public JSONObject adlogin(@RequestParam String adminName, @RequestParam String password, HttpServletRequest request){
        HttpSession session = request.getSession(true);
        return adminService.adlogin(adminName, password, session);
    }
}
