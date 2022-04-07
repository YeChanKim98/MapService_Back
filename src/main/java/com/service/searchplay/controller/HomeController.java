package com.service.searchplay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest request){
        // 테스트용 유저id, 장소id 추가
        HttpSession session = request.getSession();
        session.setAttribute("id","Tester");
        session.setAttribute("place_id",123456);
        return "home";
    }
}
