package com.develop.estore.UserService.query;

import core.dto.PaymentDetails;
import core.dto.User;
import core.query.FetchUserPaymentDetailsQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserEventsHandler {

    @QueryHandler
    public User on(FetchUserPaymentDetailsQuery fetchUserPaymentDetailsQuery) {
        PaymentDetails paymentDetails = PaymentDetails.builder()
                .cardNumber("123Card")
                .cvv("123")
                .name("SERGEY KARGOPOLOV")
                .validUntilMonth(12)
                .validUntilYear(2030)
                .build();

        log.info("Handling FetchUserPaymentDetailsQuery: " + fetchUserPaymentDetailsQuery.getUserId());
        return User.builder()
                .firstName("Sergey")
                .lastName("Kargopolov")
                .userId(fetchUserPaymentDetailsQuery.getUserId())
                .paymentDetails(paymentDetails)
                .build();
    }
}
