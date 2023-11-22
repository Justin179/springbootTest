package com.justin.springboottest.mockitohappyhotel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class Test13StrickStubbing {

    @InjectMocks // real obj
    private BookingService bookingService;

    @Mock
    private PaymentService paymentServiceMock;
    @Mock
    private RoomService roomServiceMock;
    @Mock
    private BookingDAO bookingDAOMock;
    @Mock
    private MailSender mailSenderMock;
    @Captor
    private ArgumentCaptor<Double> doubleArgumentCaptor;



    @Test
    void should_InvokePayment_When_Prepaid() {
        // given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2024, 01, 01),
                LocalDate.of(2024, 01, 05), 2, false);
        // to keep this UnnecessaryStubbing, add lenient() in the front
        lenient().when(paymentServiceMock.pay(any(),anyDouble())).thenReturn("1");

        // when
        bookingService.makeBooking(bookingRequest);

        // then
        // no exception is thrown

    }

}