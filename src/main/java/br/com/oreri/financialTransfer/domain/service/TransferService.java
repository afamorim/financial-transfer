package br.com.oreri.financialTransfer.domain.service;

import br.com.oreri.financialTransfer.domain.entity.Transfer;
import br.com.oreri.financialTransfer.domain.repository.TransferRepository;
import br.com.oreri.financialTransfer.domain.service.business.*;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    private TransferHandler transferHandler;

    private TransferRepository  transferRepository;

    public TransferService(){
        transferHandler  = new TransferSameDay(
            new TransferLessThanTenDays(
                new TransferMoreThanTenLessThanTwentyDays(
                    new TransferMoreThanTwentyLessThanThirtyDays(
                        new TransferMoreThanThirtyLessThanFortyDays(
                            new TransferMoreThanFortyDays(null))))));
    }

    public Transfer create(Transfer aTransfer){
        transferHandler.calcuteTax(aTransfer);
        return transferRepository.insert(aTransfer);
    }
}
