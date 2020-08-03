package com.cqupt.demo.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.cqupt.demo.Bean.Admin;
import com.cqupt.demo.Bean.Movie;
import com.cqupt.demo.Dao.AdminDao;
import com.cqupt.demo.Dao.MovieDao;
import com.cqupt.demo.Service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDao adminDao;

    @Resource
    private MovieDao movieDao;
    @Override
    public int addAdmin(Admin admin) {
        return adminDao.insertAdmin(admin);
    }

    @Override
    public Admin queryAdmin(String adminName) {
        return adminDao.selectByName(adminName);
    }

    @Override
    public JSONObject adlogin(String userName, String password, HttpSession session) {
        boolean success;
        JSONObject result=new JSONObject();
        JSONObject dataJ=new JSONObject();
        List<Movie> movies=new ArrayList<Movie>();
        Admin loginAdmin = adminDao.adlogin(userName, password);
        if (loginAdmin==null){
            success=false;
        }
        else {
            success=true;
            session.setAttribute("loginAdmin",loginAdmin);
            movies=movieDao.queryBy_Adid(loginAdmin.getAdminId());

        }
        dataJ.put("admin",loginAdmin);
        dataJ.put("movieList",movies);
        result.put("success",success);
        result.put("data",dataJ);
        return result;
    }


}
