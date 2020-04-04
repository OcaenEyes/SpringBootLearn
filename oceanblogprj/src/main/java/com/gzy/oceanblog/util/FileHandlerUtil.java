package com.gzy.oceanblog.util;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileHandlerUtil {
    private static String absolutePath = "";
    private static String staticDir = "static";
    private static String fileDir = "/upload/";

    public static String upload(InputStream inputStream, String path, String filename) {
        createDirIfNotExists();

        String resultPath = fileDir + path + filename;
        File uploadFile = new File(absolutePath, staticDir + resultPath);
        try {
            FileUtils.copyInputStreamToFile(inputStream, uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return resultPath;
    }

    public static void createDirIfNotExists() {
        if (!absolutePath.isEmpty()) {
            return;
        }
        File file = null;
        try {
            file = new File(ResourceUtils.getURL("classpath").getPath());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("获取根目录失败，无法创建上传目录");
        }

        if (!file.exists()) {
            file = new File("");
        }
        absolutePath = file.getAbsolutePath();

        File upload = new File(absolutePath, staticDir + fileDir);
        if (!upload.exists()) {
            upload.mkdirs();
        }
    }

    public boolean delete(String path) {
        File file = new File(absolutePath, staticDir + path);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }


}
