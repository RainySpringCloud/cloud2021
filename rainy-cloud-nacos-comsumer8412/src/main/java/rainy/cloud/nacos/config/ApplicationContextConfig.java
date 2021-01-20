package rainy.cloud.nacos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    //引入RestTemplate 远程调用框架
    @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
