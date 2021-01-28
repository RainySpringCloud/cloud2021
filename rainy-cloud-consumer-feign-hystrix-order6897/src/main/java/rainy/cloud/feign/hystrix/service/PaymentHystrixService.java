package rainy.cloud.feign.hystrix.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rainy.cloud.feign.hystrix.service.serviceImpl.PaymentHystrixServiceImpl;

@Component
@FeignClient(value="rainy-cloud-hystrix-payment",fallback = PaymentHystrixServiceImpl.class) // 做全局服务降级
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/{id}")
    public void getPayment(@PathVariable(value="id") Integer id);



    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_Time(@PathVariable(value="id") Integer id) throws InterruptedException;




}
