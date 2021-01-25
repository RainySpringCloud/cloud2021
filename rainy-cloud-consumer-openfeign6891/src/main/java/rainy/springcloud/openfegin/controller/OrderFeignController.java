package rainy.springcloud.openfegin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import rainy.cloud.commons.entities.CommonResult;
import rainy.cloud.commons.entities.Payment;
import rainy.springcloud.openfegin.service.PaymentFeignService;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/openfegin/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("consumer/payment/openfeign/timeout")
    public String getPaymentTimeout() throws InterruptedException {
        //openfeign 客户端最多等待1秒钟 需要得到结果
        return  paymentFeignService.getPaymentTimeout();

    }

}
