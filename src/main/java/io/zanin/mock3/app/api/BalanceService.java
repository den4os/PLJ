package io.zanin.mock3.app.api;

import io.zanin.mock3.adapter.rest.balance.dto.BalanceRequestDTO;
import io.zanin.mock3.adapter.rest.balance.dto.BalanceResponseDTO;

public interface BalanceService {
    BalanceResponseDTO getBalance(BalanceRequestDTO balanceRequestDTO) throws Exception;
}
