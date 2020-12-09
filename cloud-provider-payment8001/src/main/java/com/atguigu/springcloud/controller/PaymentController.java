package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.service.PaymentService;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value ="/payment/create")
    public CommonResult create (@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("******** insert result + " + result);
        if (result > 0) {
            return new CommonResult(200, "insert DB success,server Port: " + serverPort, result);
        } else {
            return new CommonResult(444, "insert DB failed",null);
        }
    }

    @GetMapping(value ="/payment/get/{id}")
    public CommonResult create (@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("******** query result + " + payment);
        if (payment != null) {
            return new CommonResult(200, "query success,server port: " + serverPort, payment);
        } else {
            return new CommonResult(444, "query failed",null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("***** service: " + service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort());
        }

        return this.discoveryClient;
    }
}
