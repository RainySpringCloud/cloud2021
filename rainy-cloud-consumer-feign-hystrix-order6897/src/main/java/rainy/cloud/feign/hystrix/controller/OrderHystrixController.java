package rainy.cloud.feign.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;
import rainy.cloud.feign.hystrix.service.PaymentHystrixService;


import javax.annotation.Resource;


@RestController
@Slf4j
@DefaultProperties(defaultFallback =  "paymentGlobalFallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;


    @GetMapping("/consumer/payment/ok/{id}")
    public void getPayment(@PathVariable Integer id) {

      paymentHystrixService.getPayment(id);
    }

    //本方法是什么样的返回类型 处理方法就需要是什么样的返回类型
//    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutFallback",commandProperties = {
//            //设置远程调用超时时间最长1500毫秒 否则走服务降级策略
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="10000")
//    })
    @GetMapping("/consumer/payment/timeout/{id}")
    @HystrixCommand //如果使用全局的 只需加这么一个注解
    public String getPaymentTimeout(@PathVariable Integer id) throws InterruptedException {
        //int a = 10/0; 出现异常 直接报错
       String x  =  paymentHystrixService.paymentInfo_Time(id);
       return x;
    }


    public String paymentInfo_timeoutFallback(Integer id){

       // System.out.println( "服务请求方 因为对方故障 需要服务降级");
        return "服务请求方 因为对方故障 需要服务降级 或自己系统出现异常 请检查";
    }

    //全局fallback
    public String paymentGlobalFallbackMethod(){

        return "全局返回降级服务";
    }
}
