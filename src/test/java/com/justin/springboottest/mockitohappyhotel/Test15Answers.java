package com.justin.springboottest.mockitohappyhotel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.anyDouble;
import static org.mockito.BDDMockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class Test15Answers {

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
    void should_CalculateCorrectPrice() {

        try(MockedStatic<CurrencyConverter> mockedConverter = mockStatic(CurrencyConverter.class)){

            // given
            BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2024, 01, 01),
                    LocalDate.of(2024, 01, 05), 2, false);
            double expected = 400.0 * 0.8;
            mockedConverter.when(()-> CurrencyConverter.toEuro(anyDouble()))
                    .thenAnswer(invocationOnMock -> (double)invocationOnMock.getArgument(0)*0.8);

            // when
            double actual = bookingService.calculatePriceEuro(bookingRequest);

            // then
            assertEquals(expected,actual);
        }


    }

}