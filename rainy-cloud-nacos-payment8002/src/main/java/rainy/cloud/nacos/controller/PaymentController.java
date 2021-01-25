package rainy.cloud.nacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


import rainy.cloud.nacos.entities.CommonResult;
import rainy.cloud.nacos.entities.Payment;
import rainy.cloud.nacos.service.PaymentService;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    private final static Logger log = LoggerFactory.getLogger(PaymentController.class);

    @Value("${server.port}")
    private String port;

    @Autowired
    private PaymentService paymentService;

    @PostMapping(value ="/payment/create")
    public CommonResult create(@RequestBody Payment payment){



        int result =paymentService.create(payment);
        log.info("success  " + result);


        if(result>0){
            return new CommonResult(200,"success and good ,and the port is " + port ,result);
        }else {
            return new CommonResult(404,"false and bad " ,null);
        }

    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){

        Payment payment = paymentService.getPayment(id);
        log.info("结果 " +payment);

        if(payment!=null){
            return new CommonResult(200,"success and good ,and the port is " + port ,payment);
        }else {
            return new CommonResult(404,"false and bad " ,null);
        }

    }

    @GetMapping("/payment/lb")
    public String getPaymentLb(){

        return  port;
    }

    @GetMapping("/payment/openfeign/timeout")
    public String getPaymentTimeout() throws InterruptedException {

        Thread.sleep(3000);
        return port;
    }
}
