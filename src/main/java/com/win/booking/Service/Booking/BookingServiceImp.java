package com.win.booking.Service.Booking;

import com.win.booking.Model.Booking;
import com.win.booking.Payload.Request.BookingRequest;
import com.win.booking.Model.Room;
import com.win.booking.Repository.BookingRepository;
import com.win.booking.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BookingServiceImp implements BookingService {

    @Autowired
    private BookingRepository repo;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Booking makeBooking(BookingRequest bookingRequest) {
        Date today= Calendar.getInstance().getTime();
        Booking booking = new Booking();
        Room room = roomRepository.getOne(bookingRequest.getRoomID());
        booking.setSubmitDate(today);
        booking.setDateBooking(bookingRequest.getDate());
        booking.setApproved(false);
        booking.setRoom(room);
        booking.setUserId(bookingRequest.getUserId());
        return repo.save(booking);
    }

    @Override
    public List<Booking> findAll() {
        return repo.findAll();
    }

    @Override
    public Booking approveBooking(Booking booking) {
        Booking approveBooking = repo.getOne(booking.getId());
        approveBooking.setApproved(true);
        if(repo.findById(booking.getId()) !=null){
            repo.save(approveBooking);
        }
        return null;
    }

    @Override
    public List<Booking> findByUserId(Long id) {
        return repo.findByUserId(id);
    }
}
