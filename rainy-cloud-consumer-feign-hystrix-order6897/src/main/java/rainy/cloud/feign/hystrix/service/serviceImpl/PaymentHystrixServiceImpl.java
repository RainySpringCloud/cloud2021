package rainy.cloud.feign.hystrix.service.serviceImpl;

import org.springframework.stereotype.Component;
import rainy.cloud.feign.hystrix.service.PaymentHystrixService;

@Component
public class PaymentHystrixServiceImpl implements PaymentHystrixService {

    @Override
    public void getPayment(Integer id) {

    }

    @Override
    public String paymentInfo_Time(Integer id) throws InterruptedException {

        return "-- PaymentHystrixServiceImpl paymentInfo_Time--";
    }
}
