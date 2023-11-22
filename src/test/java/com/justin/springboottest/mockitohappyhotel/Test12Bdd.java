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
class Test12Bdd {

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
    void should_CountAvailablePlaces_When_OneRoomAvailable() {
        // given
        given(roomServiceMock.getAvailableRooms())
                .willReturn(Collections.singletonList(new Room("Room 1",2)));
        int expected = 2;
        // when
        int actual = bookingService.getAvailablePlaceCount();

        // then
        assertEquals(expected,actual);
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
        then(paymentServiceMock).should(times(1)).pay(bookingRequest,400.0);

        // verify "paymentService.pay(bookingRequest, price);" is called only once
//        verifyNoMoreInteractions(paymentServiceMock);

    }

}