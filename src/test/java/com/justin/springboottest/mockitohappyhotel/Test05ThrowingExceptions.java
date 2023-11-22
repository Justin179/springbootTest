package com.justin.springboottest.mockitohappyhotel;

import io.micrometer.observation.Observation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Test05ThrowingExceptions {

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
    void should_ThrowException_When_NoRoomAvailable() {
        // given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2024, 01, 01),
                LocalDate.of(2024, 01, 05), 2, false);
        when(roomServiceMock.findAvailableRoomId(bookingRequest))
                .thenThrow(BusinessException.class);

        // when
        Executable callable = () -> bookingService.makeBooking(bookingRequest);

        // then
        assertThrows(BusinessException.class, callable);
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