package com.win.booking.Service.Booking;

import com.win.booking.Model.Room;

import java.util.Collection;
import java.util.Date;

import java.util.Optional;


public interface RoomService {

    Collection<Room> findAll();



    Optional<Room> findById(Long id);

    Collection<Room> findByDate(Date date);

    Collection<Room> findByCapacity(int capacity);

    Collection<Room> findByEquipment(String name);

    Collection<Room> findByCustom(Date date, int capacity, String name);
}
