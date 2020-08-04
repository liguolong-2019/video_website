package com.cqupt.demo.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cqupt.demo.Bean.User;
import com.cqupt.demo.Dao.UserDao;
import com.cqupt.demo.Service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public int addUser(User user) { return userDao.insertUser(user); }

    @Override
    public User queryUser(String userName) {
        return userDao.selectUser(userName);
    }

    @Override
    public JSONObject editing(User user) {
        boolean success;
        String Msg;
        JSONObject result=new JSONObject();
        JSONObject userInfo=new JSONObject();
        int i = userDao.updateUserInfoByName(user);
        User userByName=userDao.selectUser(user.getUserName());
        if (i==0){
            Msg="修改失败";
            success=false;
        }
        else {
            Msg="修改成功";
            success=true;
            userInfo.put("user",userByName);
        }
        result.put("Msg",Msg);
        result.put("success",success);
        result.put("data",userInfo);
        return result;
    }

    @Override
    public JSONObject login(String userName, String password, HttpSession session) {
        String Msg;
        boolean success;
        JSONObject result=new JSONObject();
        JSONObject userInfo=new JSONObject();
        User loginUser = userDao.login(userName, password);
        userInfo.put("user",loginUser);
        if (loginUser==null){
            Msg="用户名或密码错误";
            success=false;
        }
        else {
            Msg="登录成功";
            success=true;
            session.setAttribute("loginUser",loginUser);
        }
        result.put("Msg",Msg);
        result.put("success",success);
        result.put("data",userInfo);
        return result;
    }

    @Override
    public JSONObject editor(Integer userId) {
        boolean success;
        User user = userDao.selectUserById(userId);
        JSONObject userInfo=new JSONObject();
        JSONObject result = new JSONObject();
        if (user==null){
            success=false;
        }
        else {
            success=true;
            userInfo.put("user",user);
        }

        result.put("success",success);
        result.put("data",userInfo);
        return result;
    }
}
