package com.cqupt.demo.Service;

import com.alibaba.fastjson.JSONObject;
import com.cqupt.demo.Bean.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

public interface UserService {
    int addUser(User user);

    User queryUser(String userName);

    JSONObject login(String userName, String password, HttpSession session);

    JSONObject editor(Integer userId);

    JSONObject editing(User user);
}
