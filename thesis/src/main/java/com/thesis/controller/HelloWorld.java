package com.thesis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author LeiPeng
 * @Date 2019/12/3 - 23:11
 */
@RestController
@RequestMapping("/helloWorld")
public class HelloWorld {

    @RequestMapping("/say")
    public void say(){
        System.out.println("HelloWod");
    }
}
