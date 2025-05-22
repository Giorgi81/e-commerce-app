package com.services.notification.enums;

import lombok.Getter;

public enum EmailTemplete {
    PAYMENT_CONFIRMATION("payment-confirmation.html", "payment succesfully proccesed"),
    ORDER_CONFIRMATION("order-confirmation.html", "order succesfully proccesed"),;

    @Getter
    private String template;

    @Getter
    private String subject;


    EmailTemplete(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
