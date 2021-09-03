package br.com.oreri.financialTransfer.application.controller;

import br.com.oreri.financialTransfer.application.mapper.TranslatorJsonMapper;
import br.com.oreri.financialTransfer.application.representation.TransferRepresentation;
import br.com.oreri.financialTransfer.domain.entity.Transfer;
import br.com.oreri.financialTransfer.domain.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transfer/v1")
public class TransferV1Controller {

    @Autowired
    private TranslatorJsonMapper translatorJsonMapper;

    @Autowired
    private TransferService transferService;

    @GetMapping()
    public List<TransferRepresentation> findAll(){
        List<TransferRepresentation> trs = new ArrayList<>();
        transferService.findAll().forEach(transfer -> {
            trs.add(translatorJsonMapper.transformObjectToJson(transfer));
        });

        return trs;
    }

    @PostMapping()
    public ResponseEntity<TransferRepresentation> create(@Valid @RequestBody TransferRepresentation transferRepr, HttpServletResponse response){
        Transfer t = translatorJsonMapper.transformJsonToObject(transferRepr);
        t = transferService.create(t);

        return ResponseEntity.status(HttpStatus.CREATED).body(translatorJsonMapper.transformObjectToJson(t));
    }

}
