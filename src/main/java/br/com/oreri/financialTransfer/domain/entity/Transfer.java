package br.com.oreri.financialTransfer.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@AllArgsConstructor
@Getter
public class Transfer {

    @NotBlank
    private String  originAccount;
    @NotBlank
    private String  destinationAccount;
    @NotNull
    private Double  transferAmount;
    @Setter
    private Double  tax;
    @FutureOrPresent
    private Date    transferDate;
    private Date    date;
}
