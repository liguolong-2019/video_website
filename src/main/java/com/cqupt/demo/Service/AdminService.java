package com.cqupt.demo.Service;

import com.cqupt.demo.Bean.Admin;

public interface AdminService {
    int addAdmin(Admin admin);

    Admin queryAdmin(String adminName);
}
