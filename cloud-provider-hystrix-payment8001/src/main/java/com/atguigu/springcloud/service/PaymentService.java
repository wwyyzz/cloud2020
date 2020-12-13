package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class PaymentService {
    public String paymentInfo_OK(Integer id) {
        return "Threadpool:  " + Thread.currentThread().getName() + " paymentInfo_OK, Id: " + id + "\t" + "haha!";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",
            commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")})
    public String paymentInfo_Timeout(Integer id) {
        int timenumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timenumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Threadpool:  " + Thread.currentThread().getName() + " paymentInfo_OK, Id: " + id + "\t" + "haha! Time = " + timenumber+ " seconds";
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "Threadpool:  " + Thread.currentThread().getName() + " paymentInfo_OK, Id: " + id + "\t" + "wuwuwuwuwuwuwu!";
    }
}
