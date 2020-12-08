package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping(value ="/payment/create")
    public CommonResult create (Payment payment) {
        int result = paymentService.create(payment);
        log.info("******** insert result + " + result);
        if (result > 0) {
            return new CommonResult(200, "insert DB success", result);
        } else {
            return new CommonResult(444, "insert DB failed",null);
        }
    }

    @GetMapping(value ="/payment/get/{id}")
    public CommonResult create (@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("******** query result + " + payment);
        if (payment != null) {
            return new CommonResult(200, "query success", payment);
        } else {
            return new CommonResult(444, "query failed",null);
        }
    }
}
