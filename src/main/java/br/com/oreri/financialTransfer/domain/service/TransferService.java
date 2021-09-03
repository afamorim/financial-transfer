package br.com.oreri.financialTransfer.domain.service;

import br.com.oreri.financialTransfer.domain.entity.Transfer;
import br.com.oreri.financialTransfer.domain.repository.TransferRepository;
import br.com.oreri.financialTransfer.domain.service.handler.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransferService {

    private TransferHandler transferHandler;

    private final TransferRepository  transferRepository;

    public TransferService(TransferRepository aTransferRepository){
        this.transferRepository = aTransferRepository;

        //At this moment the dev can see all policies for tax calculation that will be executed
        //The code wil be easy to mantain like that
        transferHandler  = new TransferSameDay(
            new TransferLessThanTenDays(
                new TransferMoreThanTenLessThanTwentyDays(
                    new TransferMoreThanTwentyLessThanThirtyDays(
                        new TransferMoreThanThirtyLessThanFortyDays(
                            new TransferMoreThanFortyDays(null))))));
    }

    public Transfer create(Transfer aTransfer){
        aTransfer.setDate(new Date());
        transferHandler.calcuteTax(aTransfer);
        return transferRepository.insert(aTransfer);
    }

    public List<Transfer> findAll(){
        return transferRepository.findAll();
    }
}
