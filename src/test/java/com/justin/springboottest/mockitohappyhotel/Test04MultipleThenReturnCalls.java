package com.justin.springboottest.mockitohappyhotel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Test04MultipleThenReturnCalls {

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
    void should_CountAvailablePlaces_When_CalledMultipleTimes() {
        // given
        when(roomServiceMock.getAvailableRooms())
                .thenReturn(Collections.singletonList(new Room("Room 1",5)))
                .thenReturn(Collections.emptyList());
        int expectedFirstCall = 5;
        int expectedSeconeCall = 0;
        // when
        int actualFirst = bookingService.getAvailablePlaceCount();
        int actualSecond = bookingService.getAvailablePlaceCount();

        // then
        assertAll(()->assertEquals(expectedFirstCall,actualFirst),
                ()->assertEquals(expectedSeconeCall,actualSecond));


    }





}