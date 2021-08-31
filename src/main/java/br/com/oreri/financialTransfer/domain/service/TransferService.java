package br.com.oreri.financialTransfer.domain.service;

import br.com.oreri.financialTransfer.domain.entity.Transfer;
import br.com.oreri.financialTransfer.domain.repository.TransferRepository;
import br.com.oreri.financialTransfer.domain.service.business.TransferHandler;
import br.com.oreri.financialTransfer.domain.service.business.TransferSameDay;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    private TransferHandler transferHandler;

    private TransferRepository  transferRepository;

    public TransferService(){
        transferHandler  = new TransferSameDay(null);
    }

    public Transfer create(Transfer aTransfer){
        transferHandler.calcuteTax(aTransfer);
        return transferRepository.insert(aTransfer);
    }
}
