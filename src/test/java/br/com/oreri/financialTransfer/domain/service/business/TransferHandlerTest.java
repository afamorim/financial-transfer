package br.com.oreri.financialTransfer.domain.service.business;

import br.com.oreri.financialTransfer.domain.entity.Transfer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class TransferHandlerTest {

    private TransferHandler transferHandler;

    @Test
    public void transferSameDayTest(){
        transferHandler = new TransferSameDay(null);
        Transfer transfer = Transfer.builder()
                .transferDate(new Date())
                .transferAmount(100.00)
                .originAccount("Origin")
                .destinationAccount("Destination")
                .date(new Date())
                .build();
        transferHandler.calcuteTax(transfer);
        Assertions.assertTrue(transfer.getTax().equals(6.00));
    }

    @Test
    public void dateDifferenceTest() throws ParseException {
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-mm-dd");
        transferHandler = new TransferSameDay(null);
        Calendar c = Calendar.getInstance();
        c.setTime(spf.parse("2021-08-31"));
        c.add(Calendar.DATE, 3);

        Transfer transfer = Transfer.builder()
                .transferDate(c.getTime())
                .transferAmount(100.00)
                .originAccount("Origin")
                .destinationAccount("Destination")
                .date(spf.parse("2021-08-31"))
                .build();
        Assertions.assertTrue(transferHandler.dateDiference(transfer) == 3);
    }
}
