package com.example.cashsplash.dtos.campanha;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class CampaignRequestDTO {
    String name;
    String description;
    BigDecimal offValue;
}
