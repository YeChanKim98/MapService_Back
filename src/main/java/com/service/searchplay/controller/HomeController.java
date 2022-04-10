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

//    @Autowired private UserReposirtory userReposirtory;
//    @Autowired private PasswordEncoder passwordEncoder;
//    @Autowired private JwtAuthenticationProvider jwtAuthenticationProvider;

    @CrossOrigin(origins = { "http://localhost:3000" }, allowedHeaders = "*", allowCredentials = "true") // 결과값을 넘기기 위함
    @RequestMapping("/")
    public String home(HttpServletRequest request){
        // 테스트용 유저id, 장소id 추가
        HttpSession session = request.getSession();
        session.setAttribute("id","Tester");
        session.setAttribute("place_id",123456);
        System.out.println("User ID : "+session.getAttribute("id"));
        System.out.println("Place ID : "+session.getAttribute("place_id"));
//        return "home";

        // JWT 테스트

        return "redirect:http://localhost:3000";
    }
}
