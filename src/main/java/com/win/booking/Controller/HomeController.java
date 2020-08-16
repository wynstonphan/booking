package com.win.booking.Controller;


import com.win.booking.Model.Booking;
import com.win.booking.Payload.Request.BookingRequest;
import com.win.booking.Payload.Request.SearchRequest;
import com.win.booking.Repository.BookingRepository;
import com.win.booking.Repository.RoomRepository;
import com.win.booking.Service.Booking.BookingService;
import com.win.booking.Service.Booking.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private BookingService bookingService;


    @GetMapping("/all")
    ResponseEntity<?> findAll(){
        return new ResponseEntity<>(roomService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/user/{id}/booking")
    ResponseEntity<?> findById(@PathVariable Long id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        System.out.println(username);
        return new ResponseEntity<>(bookingService.findByUserId(id),HttpStatus.OK);
    }


    @GetMapping("/room")
    ResponseEntity<?> searchingRoom(@RequestBody SearchRequest searchRequest) {
        String type = searchRequest.getSearchBy().toLowerCase();

        switch (type) {
            case  "date" :
                return new ResponseEntity<>(roomService.findByDate(searchRequest.getDateBooking()),HttpStatus.OK);

            case "equipment" :
                if(searchRequest.getEquipment() == null){
                    return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(roomService.findByEquipment(searchRequest.getEquipment()),HttpStatus.OK);

            case "capacity" :
                if(searchRequest.getCapacity() < 50 || searchRequest.getCapacity()>100){
                    return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(roomService.findByCapacity(searchRequest.getCapacity()),HttpStatus.OK);

            case "custom":
                return new ResponseEntity<>(roomService.findByCustom(searchRequest.getDateBooking(),searchRequest.getCapacity(),searchRequest.getEquipment()),HttpStatus.OK);
            default:
                return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/booking")
    ResponseEntity<?> makeBooking(@RequestBody BookingRequest bookingRequest){
        if(bookingRequest.getDate() == null || bookingRequest.getRoomID() == null){
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bookingService.makeBooking(bookingRequest),HttpStatus.ACCEPTED);
    }
}
