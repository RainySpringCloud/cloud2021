package rainy.cloud.nacos.controller;

import com.oracle.tools.packager.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import rainy.cloud.nacos.entities.CommonResult;
import rainy.cloud.nacos.entities.Payment;
import rainy.cloud.nacos.service.PaymentService;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping(value ="/payment/create")
    public CommonResult create(@RequestBody Payment payment){

        int result =paymentService.create(payment);
        Log.info("success  " + result);

        if(result>0){
            return new CommonResult(200,"success and good " ,result);
        }else {
            return new CommonResult(404,"false and bad " ,null);
        }

    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){

        Payment payment = paymentService.getPayment(id);
        Log.info("结果 " +payment);

        if(payment!=null){
            return new CommonResult(200,"success and good " ,payment);
        }else {
            return new CommonResult(404,"false and bad " ,null);
        }

    }

}
