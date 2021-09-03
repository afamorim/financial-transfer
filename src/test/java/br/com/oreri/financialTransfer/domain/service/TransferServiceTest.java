package br.com.oreri.financialTransfer.domain.service;

import br.com.oreri.financialTransfer.domain.entity.Transfer;
import br.com.oreri.financialTransfer.domain.repository.TransferRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransferServiceTest {

    private Date MORE_THAN_30_AND_LESS_40_DAYS;
    private Date INITIAL_DATE;

    private TransferService transferService;

    private TransferRepository transferRepository;

    @BeforeAll
    public void setup() throws ParseException {
        transferRepository = mock(TransferRepository.class);
        transferService = new TransferService(transferRepository);

        when(transferRepository.insert(any())).thenAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) {
                return invocation.getArguments()[0];
            }
        });

        List<Transfer> ts = new ArrayList<>();
        ts.add(Transfer.builder().build());
        Mockito.when(transferRepository.findAll()).thenReturn(ts);

        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar initialDateCalendar = Calendar.getInstance();
        initialDateCalendar.setTime(spf.parse("2021-08-31"));
        INITIAL_DATE = initialDateCalendar.getTime();

        Calendar moreThan30DAndLessThan40DaysCalendar = Calendar.getInstance();
        moreThan30DAndLessThan40DaysCalendar.setTime(spf.parse("2021-08-31"));
        moreThan30DAndLessThan40DaysCalendar.add(Calendar.DATE, 35);
        MORE_THAN_30_AND_LESS_40_DAYS = moreThan30DAndLessThan40DaysCalendar.getTime();
    }

    @Test
    public void createTest(){
        Transfer transfer = Transfer.builder()
                .transferDate(MORE_THAN_30_AND_LESS_40_DAYS)
                .transferAmount(100.00)
                .originAccount("Origin")
                .destinationAccount("Destination")
                .date(INITIAL_DATE)
                .build();
        transfer = transferService.create(transfer);

        Assertions.assertTrue(transfer.getTax().equals(4.00));
    }

    @Test
    public void findAllTest(){
        Assertions.assertTrue(transferService.findAll().size() == 1);
    }
}
