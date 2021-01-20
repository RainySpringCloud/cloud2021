package rainy.cloud.nacos.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import rainy.cloud.nacos.entities.Payment;


@Mapper
@Repository
public interface PaymentDao {

     int create(Payment payment);

     Payment getPayment(@Param("id") Long id );

}
