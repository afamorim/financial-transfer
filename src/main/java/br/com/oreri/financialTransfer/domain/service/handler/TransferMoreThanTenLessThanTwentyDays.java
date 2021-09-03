package br.com.oreri.financialTransfer.domain.service.handler;

import br.com.oreri.financialTransfer.domain.entity.Transfer;

import java.math.BigDecimal;

public class TransferMoreThanTenLessThanTwentyDays extends TransferHandler{

    private Double PERCENT_AMOUNT = 8.0;

    public TransferMoreThanTenLessThanTwentyDays(TransferHandler aNext) {
        super(aNext);
    }

    @Override
    public void calcuteTax(Transfer aTransfer) {
        int dateDifference = calculateDateDifference(aTransfer);
        if (dateDifference > 10 && dateDifference <= 20) {
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
