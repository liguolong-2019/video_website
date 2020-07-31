package com.cqupt.demo.Service;

import com.cqupt.demo.Bean.User;
import org.springframework.stereotype.Service;

public interface UserService {
    int addUser(User user);

    User queryUser(String userName);
}
