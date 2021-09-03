package br.com.oreri.financialTransfer.application.mapper;

import br.com.oreri.financialTransfer.application.representation.TransferRepresentation;
import br.com.oreri.financialTransfer.domain.entity.Transfer;

import org.springframework.stereotype.Component;

@Component("transferJsonMapper")
public class TranslatorJsonMapper {


    public Transfer transformJsonToObject(TransferRepresentation aTransferRequest) {

        return transferRequestToTransfer(aTransferRequest);
    }

    public TransferRepresentation transformObjectToJson(Transfer aTransfer) {

        return transferToTransferRepr(aTransfer);
    }

    private Transfer transferRequestToTransfer(TransferRepresentation aTransferRequest) {
        Transfer transfer = Transfer.builder()
                .destinationAccount(aTransferRequest.getDestinationAccount())
                .transferAmount(aTransferRequest.getTransferAmount())
                .originAccount(aTransferRequest.getOriginAccount())
                .transferDate(aTransferRequest.getTransferDate())
                .destinationAccount(aTransferRequest.getDestinationAccount())
                .build();

        return transfer;
    }

    private TransferRepresentation transferToTransferRepr(Transfer aTransfer) {
        TransferRepresentation transferRepr = TransferRepresentation.builder()
                .destinationAccount(aTransfer.getDestinationAccount())
                .transferAmount(aTransfer.getTransferAmount())
                .originAccount(aTransfer.getOriginAccount())
                .transferDate(aTransfer.getTransferDate())
                .destinationAccount(aTransfer.getDestinationAccount())
                .tax(aTransfer.getTax())
                .date(aTransfer.getDate())
                .build();

        return transferRepr;
    }
}
