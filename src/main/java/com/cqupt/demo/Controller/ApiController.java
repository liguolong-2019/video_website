package com.cqupt.demo.Controller;


import com.alibaba.fastjson.JSONObject;
import com.cqupt.demo.Bean.Admin;
import com.cqupt.demo.Bean.Movie;
import com.cqupt.demo.Bean.Room;
import com.cqupt.demo.Bean.User;
import com.cqupt.demo.Service.AdminService;
import com.cqupt.demo.Service.MovieService;
import com.cqupt.demo.Service.RoomService;
import com.cqupt.demo.Service.UserService;
import com.cqupt.demo.utils.FfmpegImpl;
import com.cqupt.demo.utils.FfmpegUtil;
import com.cqupt.demo.utils.PathUtil;
import com.cqupt.demo.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Resource
    private UserService userService;

    @Resource
    private MovieService movieService;

    @Resource
    private RoomService roomService;

    @Resource
    private AdminService adminService;

    @Resource
    JavaMailSenderImpl mailSender;

    /**
     * 普通用户注册
     *
     * @param user
     * @return
     */
    @PostMapping("/user")
    public Map<String, Object> addUser(@RequestBody User user) throws MessagingException {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (!UserUtil.isNull(user)) {
            User temp = userService.queryUser(user.getUserName());
            if (temp != null) {
                modelMap.put("success", false);
                modelMap.put("Msg", "用户名已注册");
                return modelMap;
            }
                try {
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setSubject("welcome to video system !!!!");
                    String text = "恭喜你注册成功\n你的账号为:" + user.getUserName() + "\n" + "密码为:" + user.getPassword();
                    message.setText(text);
                    message.setTo(user.getEmail());
                    message.setFrom(mailSender.getUsername());
                    mailSender.send(message);
                    if (userService.addUser(user) == 1) {
                        modelMap.put("success", true);
                        modelMap.put("Msg", "注册成功");
                    }else {
                        modelMap.put("Msg", "注册失败");
                    }
                } catch (Exception e) {
                    modelMap.put("Msg", "请确定邮箱是否有效");
                }

        } else {
            modelMap.put("success", false);
            modelMap.put("Msg", "用户信息不能为空,请重新输入");
        }
        return modelMap;
    }

    /**
     * 管理员注册
     *
     * @param admin
     * @return
     */
    @PostMapping("/admin")
    public Map<String, Object> addAdmin(@RequestBody Admin admin) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (admin.getAdminName() != null && admin.getPassword() != null) {
            Admin temp = adminService.queryAdmin(admin.getAdminName());
            if (temp != null) {
                modelMap.put("success", false);
                modelMap.put("Msg", "用户名已存在");
                return modelMap;
            }
            adminService.addAdmin(admin);
            modelMap.put("success", true);
            modelMap.put("Msg", "注册成功");

        } else {
            modelMap.put("success", false);
            modelMap.put("Msg", "注册失败");
        }

        return modelMap;

    }

    /**
     * 普通用户登录
     *
     * @param
     * @param request
     * @return
     */
    @PostMapping("/login")
    public JSONObject login(@RequestBody User user, HttpServletRequest request) {

        String userName = user.getUserName();
        String password = user.getPassword();
        HttpSession session = request.getSession(true);
        return userService.login(userName, password, session);
    }

    /**
     * 管理员用户登录
     *
     * @param map
     * @param request
     * @return
     */
    @PostMapping("/adlogin")
    public JSONObject adlogin(@RequestBody Map map, HttpServletRequest request) {
        String adminName = (String) map.get("adminName");
        String password = (String) map.get("password");
        HttpSession session = request.getSession(true);
        return adminService.adlogin(adminName, password, session);
    }


    /**
     * 请求修改用户资料
     *
     * @param userId
     * @return
     */
    @GetMapping("/editor")
    public JSONObject editor(@RequestParam Integer userId) {
        JSONObject editor = userService.editor(userId);
        return editor;
    }

    /**
     * 提交修改资料
     *
     * @param user
     * @return
     */
    @PostMapping("/editing")
    public JSONObject editing(@RequestBody User user) {
        JSONObject editing = userService.editing(user);
        return editing;
    }


    /**
     * 上传影片
     *
     * @param file
     * @param movieName
     * @param session
     * @return
     */
    @PostMapping("/upload")
    public Map<String, Object> addMovie(@RequestParam("file") MultipartFile file, @RequestParam("movieName") String movieName, HttpSession session) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Admin admin = (Admin) session.getAttribute("loginAdmin");
        if (!file.isEmpty() && movieName != null) {
            Movie movie = new Movie();
            String basePath = PathUtil.getMovieBasePath();
            StringBuilder builder = new StringBuilder(file.getOriginalFilename());
            String suffix = builder.substring(builder.indexOf("."));
            String fileName = PathUtil.getRandomFileName(suffix);
            movie.setMovieName(movieName);
            movie.setAdminId(admin.getAdminId());
            movie.setSrc("rtmp://47.97.214.211:1935/myapp" + File.separator +fileName);
            File filepath = new File(basePath, fileName);
            if (!filepath.exists()) {
                filepath.getParentFile().mkdirs();
            }
            File file1 = new File(basePath + File.separator + fileName);
            try {
                file.transferTo(file1);
                movieService.addMovie(movie);
                List<Movie> movies = movieService.queryBy_Adid(admin.getAdminId());
                modelMap.put("movieList", movies);
                modelMap.put("Msg", "上传成功");
                modelMap.put("success", true);
            } catch (IOException e) {
                modelMap.put("Msg", "文件上传失败");
                modelMap.put("success", false);
            }
        } else {
            modelMap.put("Msg", "上传失败");
            modelMap.put("success", false);
        }

        return modelMap;
    }

    /**
     * 删除影片
     *
     * @param movieId
     * @return
     */
    @GetMapping("/delete")
    public Map<String, Object> delMovie(@RequestParam("movieId") Integer movieId) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Movie movie = movieService.getById(movieId);
        File file = new File(movie.getSrc());
        int removeMovie = 0;
        try {
            removeMovie = movieService.removeMovie(movieId);
            file.delete();
        } catch (Exception e) {
            modelMap.put("Msg", "电影正在播放，无法删除");
        }
        if (removeMovie == 1) {
            modelMap.put("Msg", "删除成功");
            modelMap.put("success", true);
        } else {
            modelMap.put("Msg", "删除失败");
            modelMap.put("success", false);
        }
        return modelMap;
    }


    /**
     * 获取所有影片
     *
     * @param session
     * @return
     */
    @GetMapping("/movies")
    public Map<String, Object> getMovies(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("loginAdmin");
        User user = (User) session.getAttribute("loginUser");
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<Movie> movies = new ArrayList<Movie>();
        if (admin != null) {
            try {
                movies = movieService.queryBy_Adid(admin.getAdminId());
                Map<String, Object> temp = new HashMap<String, Object>();
                temp.put("movieList", movies);
                modelMap.put("success", true);
                modelMap.put("data", temp);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
        } else if (user != null) {
            try {
                movies = movieService.queryAll();
                Map<String, Object> temp = new HashMap<String, Object>();
                temp.put("movieList", movies);
                modelMap.put("success", true);
                modelMap.put("data", temp);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
        }
        return modelMap;
    }

    /**
     * 创建私密房间
     *
     * @param session
     * @param room
     * @return
     */
    @PostMapping("/private")
    public Map<String, Object> createPri(HttpSession session, @RequestBody Room room) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        try {
            Map<String, Object> temp = new HashMap<String, Object>();
            User user = (User) session.getAttribute("loginUser");
            room.setUserId(user.getUserId());
            Movie movie = movieService.getById(room.getMovieId());
            String movieSrc=movie.getSrc();
            String fileName = FfmpegImpl.getFileName(movieSrc);
            FfmpegImpl.pushStream(room.getRoomName(), fileName);
            String prefixMovieSrc = FfmpegImpl.getPrefixMovieSrc(movieSrc);
            movie.setSrc(prefixMovieSrc + File.separator+room.getRoomName());
            roomService.addPriRoom(room);
            room.setMovie(movie);
            temp.put("room", room);
            modelMap.put("data", temp);
            modelMap.put("success", true);
            modelMap.put("Msg", "创建成功");
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("Msg", "创建失败" + e.getMessage());
        }
        return modelMap;
    }

    /**
     * 进入私密房间
     *
     * @param map
     * @return
     */
    @PostMapping("/enterprivate")
    public Map<String, Object> enterPri(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        System.out.println(request.getServletPath());
        System.out.println(request.getContextPath());

        Map<String, Object> modelMap = new HashMap<String, Object>();
        Integer roomId = (Integer) map.get("roomId");
        String password = (String) map.get("password");
        Room room = roomService.queryPri(roomId, password);
        if (room != null) {
            Movie movie = movieService.getById(room.getMovieId());
            String src = movie.getSrc();
            String roomName = room.getRoomName();
            String movieSrc = src.substring(0, src.lastIndexOf("/")) + File.separator + roomName ;
            movie.setSrc(movieSrc);
            Map<String, Object> temp = new HashMap<String, Object>();
            modelMap.put("success", true);
            room.setMovie(movie);
            temp.put("room", room);
            modelMap.put("data", temp);

        }
        return modelMap;
    }
    /**
     * 创建公共房间
     * @param map
     * @param session
     * @return
     */
    @PostMapping("/public")
    public JSONObject creatPublicRoom(@RequestBody Map map,HttpSession session){
        User loginUser = (User) session.getAttribute("loginUser");
        String  roomName = (String) map.get("roomName");
        Integer  movieId = (Integer) map.get("movieId");
        return roomService.creatPublicRoom(roomName,movieId,loginUser.getUserId());
    }

    /**
     * 进入公共房间
     * @param roomId
     * @param session
     * @return
     */
    @GetMapping("/enterpublic")
    public JSONObject enterPublicRoom(@RequestParam Integer roomId,HttpSession session){
        JSONObject jsonObject = roomService.enterPublicRoom(roomId);
        return jsonObject;
    }

    /**
     * 显示全部房间
     * @return
     */
    @GetMapping("/rooms")
    public JSONObject rooms(){
        boolean success;
        JSONObject result=new JSONObject();
        JSONObject data=new JSONObject();
        List<Room> rooms;
        try{
            rooms=roomService.rooms();
            success=true;
            data.put("roomList",rooms);
        }catch (Exception e){
            success=false;
        }
        result.put("success",success);
        result.put("data",data);
        return result;
    }
    /**
     * 解散房间
     * @param roomId
     * @return
     */
    @GetMapping("/deleteRoom")
    public Map<String, Object> removeRoom(@RequestParam Integer roomId) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (roomId != 0) {
            int i = roomService.removeRoom(roomId);
            if (i <= 0) {
                modelMap.put("success", false);
                modelMap.put("Msg", "房间解散失败");

            } else {
                modelMap.put("success", true);
                modelMap.put("Msg", "房间已解散");
            }
        }
        return modelMap;

    }

    @GetMapping("/change-username")
    public String readCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("username", "ray");
        cookie.setSecure(false);
        response.addCookie(cookie);
        return "Username is changed";
    }



}
