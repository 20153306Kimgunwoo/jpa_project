package com.example.jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FileListController {
    @GetMapping("/filelist")
    public String filelist(){
        return "html/filelist";
    }

}
