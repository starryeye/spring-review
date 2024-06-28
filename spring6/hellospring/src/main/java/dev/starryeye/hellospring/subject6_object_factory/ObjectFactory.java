package dev.starryeye.hellospring.subject6_object_factory;

public class ObjectFactory {

    public ExchangeRateProvider exchangeRateProvider() {
        return new WebApiExchangeRateProvider();
    }

    public PaymentService paymentService() {
        return new PaymentService(exchangeRateProvider());
    }
}
