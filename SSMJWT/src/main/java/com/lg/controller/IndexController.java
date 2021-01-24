package com.lg.controller;

import com.lg.dto.ResponseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController("indexController")
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/index")
    public ResponseData index() {
        return ResponseData.ok();
    }

}
