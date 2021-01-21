package rainy.cloud.nacos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import rainy.cloud.nacos.dao.PaymentDao;


import rainy.cloud.nacos.entities.Payment;
import rainy.cloud.nacos.service.PaymentService;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPayment(Long id) {

        return paymentDao.getPayment(id);
    }
}
