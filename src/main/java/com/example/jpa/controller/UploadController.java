package com.example.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.io.File;
import java.io.IOException;
import com.example.jpa.util.getCurrentTime;
import com.example.jpa.model.Picture;
import com.example.jpa.repository.PictureRepository;
import com.example.jpa.model.UploadFile;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

import com.example.jpa.repository.UploadFileRepository;
import com.example.jpa.model.Fileinfo;
@Controller
public class UploadController {
    @Autowired
    UploadFileRepository uploadFileRepository;
    @Autowired
    PictureRepository pictureRepository;
    
    @GetMapping("/upload1")
    public String upload1(){
        return "html/upload1";
    }

    @PostMapping("/upload1")
    @ResponseBody
    public String upload1Post(MultipartHttpServletRequest mRequest) throws IllegalStateException, IOException{
        String result="";
        MultipartFile mFile = mRequest.getFile("file");
        String oName = mFile.getOriginalFilename();
        result += oName + "<br>" + mFile.getSize();
        mFile.getContentType();
        
        String saveFolder = "C:/data/";
        File saveFile = new File(saveFolder+oName);
        try {
            mFile.transferTo(saveFile);
        } catch (IllegalStateException | IOException e){
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/upload2")
    public String upload2(){
        return "html/upload2";
    }

    @PostMapping("/upload2")
    @ResponseBody
    public String upload2Post(@RequestParam("file") MultipartFile mFile,
                              @RequestParam("memberId") String memberId,
                              HttpServletRequest request
    ){
                              
        UploadFile uploadFile = new UploadFile();
        String regDate = getCurrentTime.getTime();
        String uid = UUID.randomUUID().toString();
        String oName = mFile.getOriginalFilename();
        String secretFlag = "";
        if( request.getParameter("secretFlag")==null){
            secretFlag = "1";
        }else{
        secretFlag = request.getParameter("secretFlag");
    
        }System.out.println(secretFlag);

        
        String saveFolder = "C:/work/jpa/src/main/resources/static/image/";
        // String saveFolder = "C:/data/";
        File saveFile = new File(saveFolder+uid);
        try {
            int seq = uploadFileRepository.findAll().size()+1;
            uploadFile.setOriginFileName(oName);
            uploadFile.setUuid(uid);
            uploadFile.setSeq(seq);
            uploadFile.setRegDate(regDate);
            uploadFile.setMemberId(memberId);
            uploadFile.setSecretFlag(secretFlag);
            uploadFileRepository.save(uploadFile);
            mFile.transferTo(saveFile);
        } catch (IllegalStateException | IOException e){
            e.printStackTrace();
        }
        return oName+"성공적으로 저장 되었습니다";
    }

    @GetMapping("/pictureupload")
    public String pictureUpload(){
        return "html/pictureUpload";
    }

    @PostMapping("/pictureupload")
    @ResponseBody
    public String pictureUploadPost(@RequestParam("file") MultipartFile mFile,
                                    @RequestParam("memberId") String memberId){
        Picture picture = new Picture();
        String updateDate = getCurrentTime.getTime(); 
        String fileName = mFile.getOriginalFilename();
        String memberFileName = memberId+"_"+fileName;
        String saveFolder = "C:/work/jpa/src/main/resources/static/image/";
        File saveFile = new File(saveFolder+memberFileName);
        int seq = pictureRepository.findAll().size() + 1;
        try {
            mFile.transferTo(saveFile);
            picture.setSeq(seq);
            picture.setFileName(memberFileName);
            picture.setUpdateDate(updateDate);
            picture.setUserId(memberId);
            pictureRepository.save(picture);
        }catch (IllegalStateException|IOException e){
            e.printStackTrace();
        }
        
        return "<div><img src='/image/"+memberFileName+"'></div>";
    }
    @GetMapping("/upload3")
    public String upload3(){
        return "html/upload3";
    }

    @PostMapping("/upload3")
    @ResponseBody
    public String upload3Post(@ModelAttribute Fileinfo info){
        String result="";
        String oName = info.getFile().getOriginalFilename();
        Long size = info.getFile().getSize();
        String type = info.getFile().getContentType();
        return result += oName+" "+size+" "+type;
    }

    @GetMapping("/upload4")
    public String upload4(){
        return "html/upload4";
    }

    @PostMapping("/upload4")
    @ResponseBody
    public String upload4Post(
        @RequestParam("file") MultipartFile[] mFiles
    ){
        String result="";
        for(MultipartFile mFile : mFiles){
            String oName = mFile.getOriginalFilename();
            result += oName + " : " + mFile.getSize() + "<br>";
        }
        return result;
    }

    @GetMapping("/upload5")
    public String upload5(){
        return "html/upload5";
    }

    @PostMapping("/upload5")
    @ResponseBody
    public String upload5Post(@ModelAttribute Fileinfo info){
        String result="";
        for(MultipartFile mFile : info.getFiles()){
            String oName = mFile.getOriginalFilename();
            result += oName + " : " + mFile.getSize() + "<br>";
        }
        return result;
    }
}
