package com.win.booking.Controller;

import com.win.booking.Model.Booking;
import com.win.booking.Repository.UserRepository;
import com.win.booking.Service.Booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingService bookingService;

    @GetMapping("/booking")
    ResponseEntity<?> findAll(){
        return new ResponseEntity<>(bookingService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/booking/approve")
    ResponseEntity<?> approveBooking(@RequestBody Booking booking){
        System.out.println(booking);
        return new ResponseEntity<>(bookingService.approveBooking(booking),HttpStatus.OK);
    }

    @GetMapping("/user")
    ResponseEntity<?> findAllUser() {
        return new ResponseEntity<>(userRepository.findAll(),HttpStatus.OK);
    }
}
