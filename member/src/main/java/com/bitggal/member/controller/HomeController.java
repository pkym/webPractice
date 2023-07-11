package com.bitggal.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    /**기본페이지 요청 메소드*/
    @GetMapping("/")
    public String index(){
        return "main"; // templates 폴더의 main.html
    }

}
