package br.com.oreri.financialTransfer.domain.service.business;

import br.com.oreri.financialTransfer.domain.entity.Transfer;

import java.math.BigDecimal;

public class TransferMoreThanFortyDays extends TransferHandler{

    private Double PERCENT_AMOUNT = 2.0;

    private Double TRANSFER_AMOUNT = 100000.00;

    public TransferMoreThanFortyDays(TransferHandler aNext) {
        super(aNext);
    }

    @Override
    public void calcuteTax(Transfer aTransfer) {
        int dateDifference = calculateDateDifference(aTransfer);
        if (dateDifference > 40 && aTransfer.getTransferAmount() > TRANSFER_AMOUNT) {
            aTransfer.setTax(
                    ((BigDecimal.valueOf(aTransfer.getTransferAmount())
                            .multiply(BigDecimal.valueOf(PERCENT_AMOUNT)))
                            .divide(BigDecimal.valueOf(100)))
                            .doubleValue()
            );
        }else{
            getNext().calcuteTax(aTransfer);
        }
    }
}
