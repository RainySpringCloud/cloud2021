package rainy.springcloud.openfegin.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rainy.cloud.commons.entities.CommonResult;
import rainy.cloud.commons.entities.Payment;

@Component
@FeignClient(value = "RAINY-CLOUD-NACOS-PAYMENT8001")
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/openfeign/timeout")
    public String getPaymentTimeout() throws InterruptedException ;
}
