package com.cqupt.demo.utils;

import org.apache.ibatis.annotations.Param;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FfmpegUtil {
    private static Process process;

    public static void pushVideoAsRTSP(String basePath,String fileName,String roomName){

        String line = "";
        try {
            if(process != null){
                process.destroy();
                System.out.println(">>>>>>>>>>推流视频切换<<<<<<<<<<");
            }

            File movieFilePath = new File("/hls/" + roomName);
            if (!movieFilePath.exists()){
                movieFilePath.mkdirs();
            }

            String lastFileName = fileName.substring(fileName.lastIndexOf("/"), fileName.indexOf("."));
            // cmd命令拼接，注意命令中存在空格
            String command="";
            command += "ffmpeg -re"; // ffmpeg开头，-re代表按照帧率发送，在推流时必须有
            command += " -i " + basePath + lastFileName + ".mp4"; // 指定要推送的视频
            command += " -vcodec libx264 -vprofile baseline -acodec aac";
            command += " -ar 44100 -strict -2 -ac 1 -f flv -s 1280x720 -q 10";
            command += " rtmp://47.97.214.211:1935/myapp"+File.separator+roomName; // 指定推送服务器，-f：指定格式
            System.out.println("ffmpeg推流命令：" + command);

            // 运行cmd命令，获取其进程
            process = Runtime.getRuntime().exec(command);

//            输出ffmpeg推流日志
//            BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
//
//            while((line = br.readLine()) != null) {
//                System.out.println("视频推流信息[" + line + "]");
//            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
