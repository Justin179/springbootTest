package com.justin.springboottest.mockitohappyhotel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class Test10ArgumentCaptors {

    private BookingService bookingService;
    private PaymentService paymentServiceMock;
    private RoomService roomServiceMock;
    private BookingDAO bookingDAOMock;
    private MailSender mailSenderMock;
    private ArgumentCaptor<Double> doubleArgumentCaptor;
//    private ArgumentCaptor<BookingRequest> bookingRequestArgumentCaptor;

    @BeforeEach
    void setup(){
        paymentServiceMock = mock(PaymentService.class);
        roomServiceMock = mock(RoomService.class);
        bookingDAOMock = mock(BookingDAO.class);
        mailSenderMock = mock(MailSender.class);
        bookingService = new BookingService(paymentServiceMock,roomServiceMock,bookingDAOMock,mailSenderMock);

        doubleArgumentCaptor = ArgumentCaptor.forClass(Double.class);
    }

    @Test
    void should_PayCorrectPrice_When_InputOK() {
        // given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2024, 01, 01),
                LocalDate.of(2024, 01, 05), 2, true);

        // when
        bookingService.makeBooking(bookingRequest);

        // verify "paymentService.pay(bookingRequest, price);" is called only once
        // is called and the params are "bookingRequest,400.0"
        verify(paymentServiceMock,times(1))
                .pay(eq(bookingRequest),doubleArgumentCaptor.capture());

        Double capturedValue = doubleArgumentCaptor.getValue();
        System.out.println(capturedValue);

        assertEquals(400.0,capturedValue);
    }


    @Test
    void should_PayCorrectPrice_When_MultipleCalls() {
        // given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2024, 01, 01),
                LocalDate.of(2024, 01, 05), 2, true);
        BookingRequest bookingRequest2 = new BookingRequest("1", LocalDate.of(2024, 01, 01),
                LocalDate.of(2024, 01, 02), 2, true);
        List<Double> expectedValues = Arrays.asList(400.0,100.0);

        // when
        bookingService.makeBooking(bookingRequest);
        bookingService.makeBooking(bookingRequest2);

        // verify "paymentService.pay(bookingRequest, price);" is called only once
        // is called and the params are "bookingRequest,400.0"
        verify(paymentServiceMock,times(2))
                .pay(any(),doubleArgumentCaptor.capture());

        List<Double> actualValues = doubleArgumentCaptor.getAllValues();
        assertEquals(expectedValues,actualValues);

//        assertEquals(400.0,actualValues.get(0));
//        assertEquals(400.0,actualValues.get(1));
    }


}