package com.justin.springboottest.mockitohappyhotel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceTest {

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
    void should_CalculateCorrectPrice_When_CorrectInput() {
        // given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2024, 01, 01),
                LocalDate.of(2024, 01, 05), 2, false);
        double expectedPrice = 4 * 2 * 50.0;

        // when
        double actualPrice = bookingService.calculatePrice(bookingRequest);

        // then
        assertEquals(expectedPrice,actualPrice);

    }






    @Test
    void getAvailablePlaceCount() {
    }
    @Test
    void calculatePriceEuro() {
    }

    @Test
    void makeBooking() {
    }

    @Test
    void cancelBooking() {
    }
}