package com.cqupt.demo.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.cqupt.demo.Bean.Admin;
import com.cqupt.demo.Bean.Movie;
import com.cqupt.demo.Dao.AdminDao;
import com.cqupt.demo.Dao.MovieDao;
import com.cqupt.demo.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;
    @Autowired
    MovieDao movieDao;
    @Override
    public JSONObject adlogin(String userName, String password, HttpSession session) {
        boolean success;
        JSONObject result=new JSONObject();
        JSONObject adminInfo=new JSONObject();
        List<Movie> movies=new ArrayList<>();
        Admin loginAdmin = adminDao.adlogin(userName, password);
        if (loginAdmin==null){
            success=false;
        }
        else {
            success=true;
            session.setAttribute("loginAdmin",loginAdmin);
            movies=movieDao.queryBy_Adid(loginAdmin.getAdminId());

        }
        result.put("success",success);
        result.put("data",loginAdmin);
        result.put("movieList",movies);
        return result;
    }
}
