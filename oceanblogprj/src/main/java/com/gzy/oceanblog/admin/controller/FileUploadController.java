package com.gzy.oceanblog.admin.controller;

import com.gzy.oceanblog.util.FileHandlerUtil;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;


@Controller
@Api
public class FileUploadController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/fileUpload")
    @ResponseBody
    public String fileUplaod(MultipartFile file) throws IOException {
        logger.info("收到文件");
        String fileUrl = FileHandlerUtil.upload(file.getInputStream(), "images/", file.getOriginalFilename());
        logger.info(fileUrl);
        return fileUrl;
    }
}
