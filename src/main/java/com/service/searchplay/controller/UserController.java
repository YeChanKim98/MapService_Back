package com.service.searchplay.controller;

import com.service.searchplay.model.user.JpatestService;
import com.service.searchplay.model.user.jpatest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class UserController {

    private final JpatestService jpatestService;

    @Autowired
    public UserController(JpatestService jpatestService) {
        this.jpatestService = jpatestService;
    }

    // 어드민용 유저 목록 출력
    @RequestMapping("/getList")
    public String findAllUser(Model testList){
        List<jpatest> tests = jpatestService.findAllUser();
        testList.addAttribute("tests",tests);
        return "redirect : /";
    }


}
