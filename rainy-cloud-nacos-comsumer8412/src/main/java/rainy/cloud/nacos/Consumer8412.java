package rainy.cloud.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import rainy.cloud.Rules.MyselfRule;

@SpringBootApplication
@EnableEurekaClient
//@RibbonClient(name = "rainy-cloud-nacos-payment8001",configuration = MyselfRule.class)//使用自定义的随机负载均衡算法
public class Consumer8412 {

    public static void main(String[] args) {
        SpringApplication.run(Consumer8412.class,args);
    }

}
