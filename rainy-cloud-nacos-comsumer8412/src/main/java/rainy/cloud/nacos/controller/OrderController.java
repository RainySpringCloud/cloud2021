package rainy.cloud.nacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rainy.cloud.nacos.entities.CommonResult;
import rainy.cloud.nacos.entities.Payment;
import rainy.cloud.nacos.lb.LoadBalancer;



import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    //加入服务提供者的服务名
    public static final String PAYMENT_URL = "http://RAINY-CLOUD-NACOS-PAYMENT8001";

    @Resource
    private LoadBalancer myLb;

    @Autowired
    private DiscoveryClient discoveryClient;


    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){

        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment, CommonResult.class);


    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/getEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){

        ResponseEntity<CommonResult> responseEntity  = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id,CommonResult.class);

        if(responseEntity.getStatusCode().is2xxSuccessful()){
            return responseEntity.getBody();
        }else {
            return new CommonResult<>(404,"出现错误");
        }

    }

    @GetMapping("/consumer/payment/lb")
    public String getPaymentLb(){

        List<ServiceInstance> list = discoveryClient.getInstances("RAINY-CLOUD-NACOS-PAYMENT8001");


        if(list == null || list.size()<=    0){
           return null;
        }
        ServiceInstance instance  = myLb.instance(list);
        URI uri = instance.getUri();
       String paymentLb  = restTemplate.getForObject(uri + "/payment/lb" , String.class);

       return paymentLb;

    }

}
