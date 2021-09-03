package br.com.oreri.financialTransfer.domain.repository;

import br.com.oreri.financialTransfer.domain.entity.Transfer;

import java.util.List;

public interface TransferRepository {

    public Transfer insert(Transfer aTransfer);

    public List<Transfer> findAll();
}
