package rainy.springcloud.openfegin.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignConfigLogger(){

        return Logger.Level.FULL;
    }
}
