package com.cqupt.demo.Service;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpSession;

public interface AdminService {
    public JSONObject adlogin(String userName, String password, HttpSession session);
}
