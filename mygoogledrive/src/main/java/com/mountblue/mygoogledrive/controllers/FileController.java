package com.mountblue.mygoogledrive.controllers;

import com.mountblue.mygoogledrive.entities.File;
import com.mountblue.mygoogledrive.services.FileService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping("/")
    public String home(Model model) {
        List<File> allFiles = fileService.getAllFiles();
        model.addAttribute("allFiles",allFiles);
        return "home";
    }
    private Logger logger = LoggerFactory.getLogger(java.io.File.class);

    @PostMapping("/upload")
    public String fileUpload(@RequestParam("files[]") MultipartFile[] files, Model model) throws IOException {
        Arrays.stream(files).forEach(multipartFile -> {
            File uploadFile = new File();
            uploadFile.setFileName(multipartFile.getOriginalFilename());
            System.out.println(multipartFile.getContentType());
            try {
                uploadFile.setContent(multipartFile.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            uploadFile.setSize(multipartFile.getSize());
            fileService.createFile(uploadFile);
        });

        model.addAttribute("success", "File Upload Successfully");
        return "home";
    }

    @GetMapping("/download")
    public void downloadFile(@Param("fileId") Long fileId, Model model, HttpServletResponse response) throws IOException {
        File file = fileService.findFileByFileId(fileId);
        if (file != null) {
            response.setContentType("application.octet-stream");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment: filename= " + file.getFileName();
            response.setHeader(headerKey, headerValue);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(file.getContent());
            outputStream.close();
        }
    }

    @GetMapping("/image")
    public void showImage(@Param("fileId") Long fileId, HttpServletResponse response, File file) throws IOException {
        file = fileService.findFileByFileId(fileId);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif, image/pdf");
        response.getOutputStream().write(file.getContent());
        response.getOutputStream().close();
    }

}
