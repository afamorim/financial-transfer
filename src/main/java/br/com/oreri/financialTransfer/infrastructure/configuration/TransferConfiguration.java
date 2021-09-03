package br.com.oreri.financialTransfer.infrastructure.configuration;

import br.com.oreri.financialTransfer.domain.repository.TransferRepository;
import br.com.oreri.financialTransfer.domain.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransferConfiguration {

    @Bean
    @Autowired
    public TransferService getTransactionService(TransferRepository transferRepository) {
        return new TransferService(transferRepository);
    }
}
