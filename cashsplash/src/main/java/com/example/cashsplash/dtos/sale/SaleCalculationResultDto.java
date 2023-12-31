package com.example.cashsplash.dtos.sale;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class SaleCalculationResultDto {
    private BigDecimal totalAmount;
    private BigDecimal cashback;
}
