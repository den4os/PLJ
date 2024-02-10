package io.zanin.mock3.adapter.rest.balance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BalanceRequestDTO {
    private String rqUID;
    private String clientId;
    private String account;
    private String openDate;
    private String closeDate;
}
