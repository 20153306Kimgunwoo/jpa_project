package com.example.jpa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.jpa.repository.UploadFileRepository;

@Controller
public class DownLoadController {
    @Autowired
    UploadFileRepository uploadFileRepository;
    @GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam("uid") String uid) throws Exception{
        String downLoadFolder="C:/work/jpa/src/main/resources/static/image";
        String oName= uploadFileRepository.findByUuid(uid).getOriginFileName();
        File file = new File(downLoadFolder+oName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
               .header("content-disposition", "filename="+URLEncoder.encode(file.getName(),"utf-8"))
               .contentLength(file.length())
               .contentType(MediaType.parseMediaType("application/octet-stream"))
               .body(resource);
    }
}
