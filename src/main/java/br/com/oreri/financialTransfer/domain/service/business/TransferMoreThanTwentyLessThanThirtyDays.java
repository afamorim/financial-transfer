package br.com.oreri.financialTransfer.domain.service.business;

import br.com.oreri.financialTransfer.domain.entity.Transfer;

import java.math.BigDecimal;

public class TransferMoreThanTwentyLessThanThirtyDays extends TransferHandler{
    private Double PERCENT_AMOUNT = 6.0;

    public TransferMoreThanTwentyLessThanThirtyDays(TransferHandler aNext) {
        super(aNext);
    }

    @Override
    public void calcuteTax(Transfer aTransfer) {
        int dateDifference = calculateDateDifference(aTransfer);
        if (dateDifference > 20 && dateDifference <= 30) {
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
