package com.cqupt.demo.utils;

import com.cqupt.demo.Bean.User;

public class UserUtil {
    public static Boolean isNull(User user) {
        if (user.getUserName() == null && user.getAge() == null && user.getEmail() == null && user.getPassword() == null) {
             return true;
        }
             return false;
    }
}
