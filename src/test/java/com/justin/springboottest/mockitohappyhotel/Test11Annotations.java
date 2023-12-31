package com.justin.springboottest.mockitohappyhotel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class Test11Annotations {

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