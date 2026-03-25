package com.example.SwiggyClone.dto.request;

import com.example.SwiggyClone.enums.PaymentMethod;
import com.example.SwiggyClone.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDto {

    private Long orderId;

    private Long customerId;
   
    private PaymentStatus paymentStatus;

    private PaymentMethod paymentMethod;

    private Double amount;
}
