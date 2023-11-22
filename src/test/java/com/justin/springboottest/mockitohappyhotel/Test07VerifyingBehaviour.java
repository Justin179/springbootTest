package com.justin.springboottest.mockitohappyhotel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class Test07VerifyingBehaviour {

    private BookingService bookingService;
    private PaymentService paymentServiceMock;
    private RoomService roomServiceMock;
    private BookingDAO bookingDAOMock;
    private MailSender mailSenderMock;

    @BeforeEach
    void setup(){
        paymentServiceMock = mock(PaymentService.class);
        roomServiceMock = mock(RoomService.class);
        bookingDAOMock = mock(BookingDAO.class);
        mailSenderMock = mock(MailSender.class);
        bookingService = new BookingService(paymentServiceMock,roomServiceMock,bookingDAOMock,mailSenderMock);
    }

    @Test
    void should_InvokePayment_When_Prepaid() {
        // given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2024, 01, 01),
                LocalDate.of(2024, 01, 05), 2, true);

        // when
        bookingService.makeBooking(bookingRequest);

        // verify "paymentService.pay(bookingRequest, price);" is called only once
        // is called and the params are "bookingRequest,400.0"
        verify(paymentServiceMock,times(1)).pay(bookingRequest,400.0);

        // verify "paymentService.pay(bookingRequest, price);" is called only once
//        verifyNoMoreInteractions(paymentServiceMock);

    }

    @Test
    void should_InvokePayment_When_NotPrepaid() {
        // given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2024, 01, 01),
                LocalDate.of(2024, 01, 05), 2, false);

        // when
        bookingService.makeBooking(bookingRequest);

        // then
        verify(paymentServiceMock, never()).pay(any(),anyDouble());
    }





}