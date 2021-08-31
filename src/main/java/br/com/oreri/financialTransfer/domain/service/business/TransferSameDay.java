package br.com.oreri.financialTransfer.domain.service.business;

import br.com.oreri.financialTransfer.domain.entity.Transfer;

import java.math.BigDecimal;

public class TransferSameDay extends  TransferHandler{

    private int SAME_DAY = 0;

    private Double  TAX_AMOUNT = 3.0;
    private Double  PERCENT_AMOUNT = 3.0;

    public TransferSameDay(TransferHandler aNext) {
        super(aNext);
    }

    @Override
    public void calcuteTax(Transfer aTransfer) {
        if (dateDiference(aTransfer) == SAME_DAY){
            //IRÁ SOMAR A PORCENTAGEM CALCULADA DO VALOR TRANSFERIDO COM O VALOR DA TAXA SOLICITADO
           aTransfer.setTax(
            ((BigDecimal.valueOf(aTransfer.getTransferAmount())
                .multiply(BigDecimal.valueOf(PERCENT_AMOUNT)))
                    .divide(BigDecimal.valueOf(100)))
                        .add(BigDecimal.valueOf(TAX_AMOUNT))
                            .doubleValue()
           );
        }else{
            getNext().calcuteTax(aTransfer);
        }
    }
}
