package com.cqupt.demo.utils;

public class FfmpegImpl {

    public static String getPrefixMovieSrc(String movieSrc) {
        String prefixMovieSrc = movieSrc.substring(0, movieSrc.lastIndexOf("/"));
        return prefixMovieSrc;
    }


    public static void pushStream(String roomName, String fileName) {
        FfmpegUtil.pushVideoAsRTSP("/home/ray/movie", fileName, roomName);
    }

    public static String getFileName(String movieSrc) {
        String fileName = movieSrc.substring(movieSrc.lastIndexOf("/"));
        return fileName;
    }
}
