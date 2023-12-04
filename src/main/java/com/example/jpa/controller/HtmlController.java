package com.example.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import com.example.jpa.model.Demo;
import com.example.jpa.repository.DemoRepository;

@Controller
public class HtmlController {
     @Autowired
    DemoRepository  demoRepository;
    @GetMapping("/home")
    public String home(Model model){
        List<String> list = new ArrayList<>();
        list.add("list1");
        list.add("list2");
        list.add("list3");
        model.addAttribute("list", list);
        
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "map1");
        map.put("key2", "map2");
        model.addAttribute("map", map);
        
        
        List<Demo> demo =demoRepository.findAll();
        model.addAttribute("demo", demo);
        
        return "html/home";
    }

    @GetMapping("/user")
    public String user(Model model){
        Map<String, Object> user =null;
        user = new HashMap<>();
        user.put("userId", "no");
        user.put("userName","sol");
        user.put("userAge", 26);
        model.addAttribute("user", user);
        return "html/user";
    }

    @GetMapping("/userList")
    public String userList(Model model){
        List<Map<String,Object>> userList= new ArrayList<>();
        Map<String,Object> user = null;
        user = new HashMap<>();
        user.put("userId", "a");
        user.put("userName", "apple");
        user.put("userAge", 21);
        userList.add(user);
        user = new HashMap<>();
        user.put("userId", "b");
        user.put("userName", "banana");
        user.put("userAge", 22);
        userList.add(user);
        user = new HashMap<>();
        user.put("userId", "c");
        user.put("userName", "carrot");
        user.put("userAge", 23);
        userList.add(user);
        model.addAttribute("userList", userList);
        return "html/userList";

    }

    @GetMapping("/mode")
    public String mode(Model model){
        Demo mode = demoRepository.findByUser("BBB");
        model.addAttribute("mode", mode);
        return "html/mode";

    }

    @GetMapping("/paging")
    public String pagination(
        Model model,
        @RequestParam(defaultValue = "1") int page)
        {
        int startPage = (page - 1) / 10 * 10 + 1;
        int endPage = startPage + 9;
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        return "html/pagination";
    }

    @GetMapping("/linkurl")
  public String linkurl(
    Model model, @RequestParam(defaultValue = "1") int page) {
    int startPage = (page - 1) / 10 * 10 + 1;
    int endPage = startPage + 9;
    model.addAttribute("startPage", startPage);
    model.addAttribute("endPage", endPage);
    model.addAttribute("page", page);
    return "html/linkurl";
  }

}
