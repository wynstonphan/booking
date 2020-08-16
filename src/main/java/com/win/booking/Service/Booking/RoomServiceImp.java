package com.win.booking.Service.Booking;

import com.win.booking.Model.Booking;
import com.win.booking.Model.Room;
import com.win.booking.Repository.BookingRepository;
import com.win.booking.Repository.RoomRepository;
import com.win.booking.Service.Booking.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class RoomServiceImp implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Collection<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public Collection<Room> findByDate(Date date) {
        return roomRepository.findByIdNotIn( getEmptyRoomId(date));
    }

    @Override
    public Collection<Room> findByCapacity(int capacity) {
        return roomRepository.findByCapacityGreaterThanEqual(capacity);
    }

    @Override
    public Collection<Room> findByEquipment(String name) {
        return roomRepository.findByEquipmentsName(name);
    }

    @Override
    public Collection<Room> findByCustom(Date date, int capacity, String name) {
        return roomRepository.findByIdNotInAndCapacityGreaterThanEqualAndEquipmentsName(getEmptyRoomId(date),capacity,name);
    }

    private List<Long> getEmptyRoomId(Date date) {
        List<Booking> bookingList = bookingRepository.findByDateBooking(date);
        List<Long> roomId = new ArrayList<>();
        for(Booking bookedRoom: bookingList){
            Long id = bookedRoom.getRoom().getId();
            roomId.add(id);
        }
        System.out.println(roomId);
        return roomId;
    }
}
