package br.com.oreri.financialTransfer.domain.service.business;

import br.com.oreri.financialTransfer.domain.entity.Transfer;

import java.math.BigDecimal;

public class TransferLessThanTenDays extends TransferHandler{

    private double MULTIPLIER_VALUE = 9.0;

    public TransferLessThanTenDays(TransferHandler aNext) {
        super(aNext);
    }

    @Override
    public void calcuteTax(Transfer aTransfer) {
        int dateDifference = calculateDateDifference(aTransfer);
        if (dateDifference > 0 && dateDifference < 10){
            aTransfer.setTax(
                BigDecimal.valueOf(MULTIPLIER_VALUE)
                    .multiply(BigDecimal.valueOf(dateDifference))
                        .doubleValue());
        }else{
            getNext().calcuteTax(aTransfer);
        }
    }
}
