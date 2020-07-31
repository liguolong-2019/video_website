package com.cqupt.demo.Service;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpSession;


public interface UserService {
    public JSONObject login(String userName, String password, HttpSession session);
}
