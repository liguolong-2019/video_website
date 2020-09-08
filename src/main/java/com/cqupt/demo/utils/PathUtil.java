package com.cqupt.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class PathUtil {

//    获取系统的分隔符，windows用的\  而linux用的/
    private static String seperator = System.getProperty("file.separator");
//    获取时间对象
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    //  获取随机对象
    private static final Random r = new Random();

    public static String getMovieBasePath() {
//       获取操作系统的名称
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "C:/prodev/movie/";
        }else{
            basePath = "/home/ray/movie/";
        }

        basePath = basePath.replace("/", seperator);

        return basePath;
    }

    public static String getRandomFileName(String suffix) {
//        获取随机的五位数
        int rannum=r.nextInt(89999)+10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum+suffix;
    }


//    public static String getFileNamePrefix() {
//        int rannum=r.nextInt(89999)+10000;
//        String nowTimeStr = sDateFormat.format(new Date());
//        return nowTimeStr + rannum;
//    }
}
