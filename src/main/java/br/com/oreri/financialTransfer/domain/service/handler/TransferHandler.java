package br.com.oreri.financialTransfer.domain.service.handler;

import br.com.oreri.financialTransfer.domain.entity.Transfer;

public abstract class TransferHandler {

    private TransferHandler next;

    private Long MILLESSECONDS_DAY = 86400000L;

    public TransferHandler(TransferHandler aNext){
        this.next = aNext;
    }

    //Will be implemented in all concrete classes to build a Chain off responsability
    public abstract void calcuteTax(Transfer aTransfer);

    protected int calculateDateDifference(Transfer aTransfer){
        return Long.valueOf(
                (aTransfer.getTransferDate().getTime()-aTransfer.getDate().getTime()) / MILLESSECONDS_DAY)
            .intValue();
    }

    protected TransferHandler getNext() {
        if (next == null){
            throw new IllegalArgumentException("Can not calculate the tax by this desired day");
        }
        return next;
    }
}
