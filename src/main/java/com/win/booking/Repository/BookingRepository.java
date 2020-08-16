package com.win.booking.Repository;

import com.win.booking.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {

    List<Booking> findByUserId(Long id);

    List<Booking> findByDateBooking(Date date);
}
