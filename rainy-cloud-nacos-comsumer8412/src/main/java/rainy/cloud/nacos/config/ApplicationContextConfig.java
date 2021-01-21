package rainy.cloud.nacos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    //引入RestTemplate 远程调用框架
    @Bean
    //给予RestTemplate 负载均衡的能力
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
