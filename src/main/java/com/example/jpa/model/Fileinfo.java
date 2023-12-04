package com.example.jpa.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Fileinfo {
    private MultipartFile file;
    private MultipartFile[] files;
}
