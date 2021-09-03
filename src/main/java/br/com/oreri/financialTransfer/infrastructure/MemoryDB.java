package br.com.oreri.financialTransfer.infrastructure;

import br.com.oreri.financialTransfer.domain.entity.Transfer;

import java.util.*;

public class MemoryDB {

    public static final String TRANSFER_TABLE_KEY = "TRANSFER";

    private static Map<String, List<Transfer>> transferTable = null;

    public static Map<String, List<Transfer>> getTransferTable() {
        if (Objects.isNull(transferTable)) {
            transferTable = new HashMap<>();
            transferTable.put(TRANSFER_TABLE_KEY, new ArrayList<>());
        }
        return transferTable;
    }
}
