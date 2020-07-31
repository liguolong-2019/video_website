package com.cqupt.demo.Service.Impl;

import com.cqupt.demo.Bean.Admin;
import com.cqupt.demo.Dao.AdminDao;
import com.cqupt.demo.Service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDao adminDao;

    @Override
    public int addAdmin(Admin admin) {
        return adminDao.insertAdmin(admin);
    }

    @Override
    public Admin queryAdmin(String adminName) {
        return adminDao.selectByName(adminName);
    }

}
