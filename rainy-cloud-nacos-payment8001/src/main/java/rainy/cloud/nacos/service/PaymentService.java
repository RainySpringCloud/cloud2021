package rainy.cloud.nacos.service;


import rainy.cloud.nacos.entities.Payment;

public interface PaymentService {

    public int create(Payment payment);

    public Payment getPayment( Long id );
}
