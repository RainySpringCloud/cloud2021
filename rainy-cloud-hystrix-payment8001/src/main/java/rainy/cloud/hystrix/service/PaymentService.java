package rainy.cloud.hystrix.service;


import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PaymentService {

    public String paymentInfo(Integer id)  {

        return "线程池 " + Thread.currentThread().getName() + "   paymentInfo: id" + id + " 冠军";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandler",commandProperties = {
            //设置超时时间最长3000毫秒 否则走服务降级策略
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="10000")
    })
    public String paymentInfo_Time(Integer id) throws InterruptedException{

        //int i = 10/0;
        Thread.sleep(2000);//休眠5秒钟 超时
        return "线程池 " + Thread.currentThread().getName() + "   paymentInfo_Timeout: id" + id + " 耗时"  ;
    }


    public String paymentInfo_timeoutHandler(Integer id){

        return "线程池 " + Thread.currentThread().getName() + "   paymentInfo_timeoutHandler: id" + id + " 服务降级策略"  ;
    }

    //服务熔断
    //10秒的窗口期 10次请求 有60%是失败的
    @HystrixCommand(fallbackMethod =  "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value="true"),//是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="10"),//请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="10000"),//时间范围
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="60"),//失败率
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){

        if(id<0){
            throw new RuntimeException("不可以小于0");
        }

        //糊涂工具类 生成日期
        String  serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+ " 调用流水号 " + serialNumber;
    }


    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){

        return "请检查 id 不可以是负数";

    }

}
