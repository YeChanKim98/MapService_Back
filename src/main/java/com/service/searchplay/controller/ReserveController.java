package com.service.searchplay.controller;

import com.service.searchplay.model.resv.Reserve;
import com.service.searchplay.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@RequestMapping(value = "/reserve", method = {RequestMethod.GET, RequestMethod.POST})
public class ReserveController {

    private final ReserveService reserveService;

    @Autowired
    public ReserveController(ReserveService reserveService){
        this.reserveService = reserveService;
    }

    @PostMapping("/request")
    public Reserve reserveRequest(Reserve reserve){
        reserveService.reserveRequest(reserve);
        return reserve;
    }

}
