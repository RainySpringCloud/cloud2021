package rainy.cloud.hystrix.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import rainy.cloud.hystrix.service.PaymentService;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    private final static Logger logger = LoggerFactory.getLogger(PaymentController.class);


    @Resource
    PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @GetMapping("/payment/hystrix/{id}")
    public void getPayment(@PathVariable Integer id){

        String result = paymentService.paymentInfo(id);
        logger.info(result);

    }

    //服务降级策略

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String getPaymentTimeout(@PathVariable Integer id) throws InterruptedException {

        String result = paymentService.paymentInfo_Time(id);
        logger.info( "ssaasassaas      "+ result);
        return result;
    }

    //服务熔断
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result = paymentService.paymentCircuitBreaker(id);
        logger.info("----- " + result);
        return result;
    }


}
