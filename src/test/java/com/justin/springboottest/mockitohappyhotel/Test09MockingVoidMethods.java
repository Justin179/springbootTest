package com.justin.springboottest.mockitohappyhotel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class Test09MockingVoidMethods {

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

        // this pattern is used when return is not void
//        when(roomServiceMock.findAvailableRoomId(bookingRequest))
//                .thenThrow(BusinessException.class);

        // this pattern is used when return is void (doThrow first)
        doThrow(new BusinessException()).when(mailSenderMock)
                .sendBookingConfirmation(any());

        // when
        Executable callable = () -> bookingService.makeBooking(bookingRequest);

        // then
        assertThrows(BusinessException.class, callable);
    }

    @Test
    void should_NotThrowException_When_MailNotReady() {
        // given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2024, 01, 01),
                LocalDate.of(2024, 01, 05), 2, false);

        // this pattern is used when return is void (doThrow first)
        // doNothing is the default behavior, so this line is not necessary
        doNothing().when(mailSenderMock)
                .sendBookingConfirmation(any());

        // when
//        Executable callable = () ->
                bookingService.makeBooking(bookingRequest);

        // then
//        assertThrows(BusinessException.class, callable);
    }




}