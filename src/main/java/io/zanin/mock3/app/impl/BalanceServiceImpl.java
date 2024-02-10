package io.zanin.mock3.app.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.zanin.mock3.adapter.rest.balance.dto.BalanceRequestDTO;
import io.zanin.mock3.adapter.rest.balance.dto.BalanceResponseDTO;
import io.zanin.mock3.app.api.BalanceService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BalanceServiceImpl.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final BigDecimal MAX_LIMIT_EU = new BigDecimal("1000.00");
    private static final BigDecimal MAX_LIMIT_US = new BigDecimal("2000.00");
    private static final BigDecimal MAX_LIMIT_RUB = new BigDecimal("50000.00");

    private static final String CURRENCY_EU = "EU";
    private static final String CURRENCY_US = "US";
    private static final String CURRENCY_RUB = "RUB";

    private static final char FIRST_CHAR_CLIENT_EU = '9';
    private static final char FIRST_CHAR_CLIENT_US = '8';

    @Override
    public BalanceResponseDTO getBalance(BalanceRequestDTO balanceRequestDTO) throws Exception{
            BalanceResponseDTO result = new BalanceResponseDTO();

            String rqUID = balanceRequestDTO.getRqUID();
            String clientId = balanceRequestDTO.getClientId();
            String account = balanceRequestDTO.getAccount();
            String currency = setCurrency(clientId);
            BigDecimal maxLimit = setMaxLimit(currency);
            BigDecimal balance = generateRandomBalance(maxLimit);

            result.setRqUID(rqUID);
            result.setClientId(clientId);
            result.setAccount(account);
            result.setCurrency(currency);
            result.setBalance(balance.toString());
            result.setMaxLimit(maxLimit.toString());

            LOGGER.info("RequestDTO: {}", MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(balanceRequestDTO));
            LOGGER.info("ResponseDTO: {}", MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(result));
            return result;
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private String setCurrency(String clientId) {
        switch (getFirstChar(clientId)) {
            case FIRST_CHAR_CLIENT_EU -> {
                return CURRENCY_EU;
            }
            case FIRST_CHAR_CLIENT_US -> {
                return CURRENCY_US;
            }
            default -> {
                return CURRENCY_RUB;
            }
        }
    }

    private char getFirstChar(String clientId) {
        return clientId.charAt(0);
    }

    private BigDecimal setMaxLimit(String currency) {
        switch (currency) {
            case "EU" -> {
                return MAX_LIMIT_EU;
            }
            case "US" -> {
                return MAX_LIMIT_US;
            }
            default -> {
                return MAX_LIMIT_RUB;
            }
        }
    }

    private BigDecimal generateRandomBalance(BigDecimal maxLimit) {
        return BigDecimal.valueOf(Math.random()).multiply(maxLimit).setScale(2, RoundingMode.HALF_UP);
    }
}
