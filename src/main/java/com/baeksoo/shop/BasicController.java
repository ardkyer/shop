package com.baeksoo.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.ZonedDateTime;

@Controller
public class BasicController {
    @GetMapping("")
    String home() {
        return "index.html";
    }

    @GetMapping("/mypage")
    @ResponseBody
    String mypage() {
        return "Hello, mypage!";
    }

    @GetMapping("/date")
    @ResponseBody
    String date() {
        return ZonedDateTime.now().toString();
    }
}


