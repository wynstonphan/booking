package com.win.booking.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="equipment")
public class Equipment {

    @Id
    private Long id;

    @Column(name="name")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "equipments")
    private Set<Room> room;

}
