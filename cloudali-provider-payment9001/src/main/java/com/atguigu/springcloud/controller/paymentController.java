package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class paymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/nacos/get/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        return "nacos registry, serverport: " + serverPort + "\t" + "id: " + id;    }
}
