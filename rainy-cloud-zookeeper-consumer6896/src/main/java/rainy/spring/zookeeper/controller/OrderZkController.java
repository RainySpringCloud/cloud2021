package rainy.spring.zookeeper.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rainy.spring.zookeeper.config.RestTemplateConfig;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderZkController {

    public static final String INVOKE_URL = "http://rainy-cloud-nacos-payment8001";

    @Resource
    private RestTemplateConfig restTemplateConfig;

    @GetMapping("/consumer/payment/zk")
    public String getPayment(){
       return restTemplateConfig.restTemplate().getForObject(INVOKE_URL+ "/payment/zk",String.class);
    }


}
