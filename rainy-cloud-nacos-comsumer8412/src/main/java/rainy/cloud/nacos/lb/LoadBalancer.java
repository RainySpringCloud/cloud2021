package rainy.cloud.nacos.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {

    //根据指定的服务名获取对应的地址列表 然后遍历得到各个列表信息
    ServiceInstance instance (List<ServiceInstance> serviceList);
}
