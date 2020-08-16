package com.win.booking.Service.Booking;

import com.win.booking.Model.Booking;
import com.win.booking.Payload.Request.BookingRequest;

import java.util.List;

public interface BookingService {

    Booking makeBooking(BookingRequest bookingRequest);

    List<Booking> findAll();

    Booking approveBooking(Booking booking);

    List<Booking> findByUserId(Long id);
}
