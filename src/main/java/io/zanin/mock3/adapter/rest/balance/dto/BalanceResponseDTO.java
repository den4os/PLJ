package io.zanin.mock3.adapter.rest.balance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BalanceResponseDTO {
    private String rqUID;
    private String clientId;
    private String account;
    private String currency;
    private String balance;
    private String maxLimit;
}
