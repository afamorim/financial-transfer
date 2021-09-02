package br.com.oreri.financialTransfer.domain.service.business;

import br.com.oreri.financialTransfer.domain.entity.Transfer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransferHandlerTest {

    private TransferHandler transferHandler;

    private Date INITIAL_DATE;

    private Date LESS_OR_EQUAL_10_DAYS;

    private Date MORE_THAN_40_DAYS;

    private Date MORE_THAN_10_AND_LESS_20_DAYS;

    private Date MORE_THAN_20_AND_LESS_30_DAYS;

    private Date MORE_THAN_30_AND_LESS_40_DAYS;

    @BeforeAll
    public void basicInstatiation() throws ParseException{
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-mm-dd");
        transferHandler = new TransferSameDay(null);
        Calendar initialDateCalendar = Calendar.getInstance();
        initialDateCalendar.setTime(spf.parse("2021-08-31"));
        INITIAL_DATE = initialDateCalendar.getTime();

        Calendar lessOrEqualThan10DaysCalendar = Calendar.getInstance();
        lessOrEqualThan10DaysCalendar.setTime(spf.parse("2021-08-31"));
        lessOrEqualThan10DaysCalendar.add(Calendar.DATE, 5);
        LESS_OR_EQUAL_10_DAYS = lessOrEqualThan10DaysCalendar.getTime();

        Calendar moreThan10DAndLessThan20DaysCalendar = Calendar.getInstance();
        moreThan10DAndLessThan20DaysCalendar.setTime(spf.parse("2021-08-31"));
        moreThan10DAndLessThan20DaysCalendar.add(Calendar.DATE, 15);
        MORE_THAN_10_AND_LESS_20_DAYS = moreThan10DAndLessThan20DaysCalendar.getTime();

        Calendar moreThan20DAndLessThan30DaysCalendar = Calendar.getInstance();
        moreThan20DAndLessThan30DaysCalendar.setTime(spf.parse("2021-08-31"));
        moreThan20DAndLessThan30DaysCalendar.add(Calendar.DATE, 25);
        MORE_THAN_20_AND_LESS_30_DAYS = moreThan20DAndLessThan30DaysCalendar.getTime();

        Calendar moreThan30DAndLessThan40DaysCalendar = Calendar.getInstance();
        moreThan30DAndLessThan40DaysCalendar.setTime(spf.parse("2021-08-31"));
        moreThan30DAndLessThan40DaysCalendar.add(Calendar.DATE, 35);
        MORE_THAN_30_AND_LESS_40_DAYS = moreThan30DAndLessThan40DaysCalendar.getTime();

        Calendar moreThan40Calendar = Calendar.getInstance();
        moreThan40Calendar.setTime(spf.parse("2021-08-31"));
        moreThan40Calendar.add(Calendar.DATE, 50);
        MORE_THAN_40_DAYS = moreThan40Calendar.getTime();

    }

    @Test
    public void transferSameDayTest(){
        transferHandler = new TransferSameDay(null);
        Transfer transfer = Transfer.builder()
                .transferDate(INITIAL_DATE)
                .transferAmount(100.00)
                .originAccount("Origin")
                .destinationAccount("Destination")
                .date(INITIAL_DATE)
                .build();
        transferHandler.calcuteTax(transfer);
        Assertions.assertTrue(transfer.getTax().equals(6.00));
    }

    @Test
    public void transferLessOrEqual10DaysTest(){
        transferHandler = new TransferLessThanTenDays(null );
        Transfer transfer = Transfer.builder()
                .transferDate(LESS_OR_EQUAL_10_DAYS)
                .transferAmount(100.00)
                .originAccount("Origin")
                .destinationAccount("Destination")
                .date(INITIAL_DATE)
                .build();
        transferHandler.calcuteTax(transfer);
        Assertions.assertTrue(transfer.getTax().equals(45.00));
    }

    @Test
    public void transferMoreThan10DaysAndLessThan20DaysTest(){
        transferHandler = new TransferMoreThanTenLessThanTwentyDays(null );
        Transfer transfer = Transfer.builder()
                .transferDate(MORE_THAN_10_AND_LESS_20_DAYS)
                .transferAmount(100.00)
                .originAccount("Origin")
                .destinationAccount("Destination")
                .date(INITIAL_DATE)
                .build();
        transferHandler.calcuteTax(transfer);
        Assertions.assertTrue(transfer.getTax().equals(8.00));
    }

    @Test
    public void transferMoreThan20DaysAndLessThan30DaysTest(){
        transferHandler = new TransferMoreThanTwentyLessThanThirtyDays(null);
        Transfer transfer = Transfer.builder()
                .transferDate(MORE_THAN_20_AND_LESS_30_DAYS)
                .transferAmount(100.00)
                .originAccount("Origin")
                .destinationAccount("Destination")
                .date(INITIAL_DATE)
                .build();
        transferHandler.calcuteTax(transfer);
        Assertions.assertTrue(transfer.getTax().equals(6.00));
    }

    @Test
    public void transferMoreThan30DaysAndLessThan40DaysTest(){
        transferHandler = new TransferMoreThanThirtyLessThanFortyDays(null);
        Transfer transfer = Transfer.builder()
                .transferDate(MORE_THAN_30_AND_LESS_40_DAYS)
                .transferAmount(100.00)
                .originAccount("Origin")
                .destinationAccount("Destination")
                .date(INITIAL_DATE)
                .build();
        transferHandler.calcuteTax(transfer);
        Assertions.assertTrue(transfer.getTax().equals(4.00));
    }

    @Test
    public void transferMoreThan40DaysTest(){
        transferHandler = new TransferMoreThanFortyDays(null);
        Transfer transfer = Transfer.builder()
                .transferDate(MORE_THAN_40_DAYS)
                .transferAmount(200000.00)
                .originAccount("Origin")
                .destinationAccount("Destination")
                .date(INITIAL_DATE)
                .build();
        transferHandler.calcuteTax(transfer);
        Assertions.assertTrue(transfer.getTax().equals(4000.00));
    }

    @Test
    public void dateDifferenceTest()  {
        transferHandler = new TransferSameDay(null);
        Transfer transfer = Transfer.builder()
                .transferDate(MORE_THAN_40_DAYS)
                .transferAmount(100.00)
                .originAccount("Origin")
                .destinationAccount("Destination")
                .date(INITIAL_DATE)
                .build();
        Assertions.assertTrue(transferHandler.calculateDateDifference(transfer) == 50);
    }

    @Test
    public void handlerInvalidRange(){
        transferHandler = new TransferSameDay(null);
        Transfer transfer = Transfer.builder()
                .transferDate(MORE_THAN_40_DAYS)
                .transferAmount(100.00)
                .originAccount("Origin")
                .destinationAccount("Destination")
                .date(INITIAL_DATE)
                .build();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            transferHandler.calcuteTax(transfer);
        });
    }
}
