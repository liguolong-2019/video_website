package com.cqupt.demo.Service;

import com.alibaba.fastjson.JSONObject;
import com.cqupt.demo.Bean.Admin;

import javax.servlet.http.HttpSession;

public interface AdminService {
    int addAdmin(Admin admin);

    Admin queryAdmin(String adminName);

    JSONObject adlogin(String userName, String password, HttpSession session);
}
