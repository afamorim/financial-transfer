package br.com.oreri.financialTransfer.domain.repository;

import br.com.oreri.financialTransfer.domain.entity.Transfer;

public interface TransferRepository {

    public Transfer insert(Transfer aTransfer);
}
