package br.com.oreri.financialTransfer.infrastructure.repository;

import br.com.oreri.financialTransfer.domain.entity.Transfer;
import br.com.oreri.financialTransfer.domain.repository.TransferRepository;
import br.com.oreri.financialTransfer.infrastructure.MemoryDB;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransferMemoryRepositoryImpl implements TransferRepository {

    @Override
    public Transfer insert(Transfer aTransfer) {
        MemoryDB.getTransferTable().get(MemoryDB.TRANSFER_TABLE_KEY).add(aTransfer);

        return aTransfer;
    }

    @Override
    public List<Transfer> findAll() {
        return MemoryDB.getTransferTable().get(MemoryDB.TRANSFER_TABLE_KEY);
    }
}
