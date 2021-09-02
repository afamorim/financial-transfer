package br.com.oreri.financialTransfer.domain.service.business;

import br.com.oreri.financialTransfer.domain.entity.Transfer;

import java.math.BigDecimal;

public class TransferMoreThanThirtyLessThanFortyDays extends TransferHandler{

    private Double PERCENT_AMOUNT = 4.0;

    public TransferMoreThanThirtyLessThanFortyDays(TransferHandler aNext) {
        super(aNext);
    }

    @Override
    public void calcuteTax(Transfer aTransfer) {
        int dateDifference = calculateDateDifference(aTransfer);
        if (dateDifference > 30 && dateDifference <= 40) {
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
