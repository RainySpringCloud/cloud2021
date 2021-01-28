package rainy.cloud.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableEurekaClient
@SpringBootApplication
@EnableCircuitBreaker//回路
public class PaymentHystrixApplication
{
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixApplication.class,args);
    }
}
