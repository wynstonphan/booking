package com.win.booking.Repository;

import com.win.booking.Model.Equipment;
import com.win.booking.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByIdNotIn(List<Long> id);

    List<Room> findByCapacityGreaterThanEqual(int capacity);


    List<Room> findByEquipmentsName(String name);

    List<Room> findByIdNotInAndCapacityGreaterThanEqualAndEquipmentsName(List<Long> id, int capacity, String name);
}
