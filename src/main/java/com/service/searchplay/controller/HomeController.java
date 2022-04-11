package com.service.searchplay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// @RestController // 리턴값으로 준 주소를 그대로 문자로 인식하고 넘겨버림
@Controller
public class HomeController {

    @CrossOrigin(origins = { "http://localhost:3000" }, allowedHeaders = "*", allowCredentials = "true") // 결과값을 넘기기 위함
    @RequestMapping("/")
    public String home(){
        System.out.println("접속 요청");
        return "redirect:http://localhost:3000";
    }
}
