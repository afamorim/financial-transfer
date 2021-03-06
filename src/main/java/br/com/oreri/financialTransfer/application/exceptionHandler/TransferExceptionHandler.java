package br.com.oreri.financialTransfer.application.exceptionHandler;

import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TransferExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request){
        String mensagemUsuario = "{\"msg\": \"Does not exist any tax to this condition\"}";

        return handleExceptionInternal(ex, mensagemUsuario, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
