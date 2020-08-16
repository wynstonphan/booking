package com.win.booking.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="room")
public class Room {

    @Id
    @Column(name="id")
    private Long id;

    @Column(name="number")
    private String number;

    @Column(name="capacity")
    private int capacity;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="room_equipment", joinColumns = @JoinColumn(name="room_id"), inverseJoinColumns = @JoinColumn(name="equipment_id"))
    private Collection<Equipment> equipments;
}
