package rainy.cloud.nacos.lb;


import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLb implements LoadBalancer {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int next;
        int current;

        do{
            current = this.atomicInteger.get();
            next = current>Integer.MAX_VALUE?0:current+1;
        }while(!this.atomicInteger.compareAndSet(current,next));

        return next;
    }


    @Override  //返回指定的机器信息
    public ServiceInstance instance(List<ServiceInstance> serviceList) {

        //求得机器编号
        int index = getAndIncrement() % serviceList.size();
        return serviceList.get(index);
    }
}
