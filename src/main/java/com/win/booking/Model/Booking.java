package com.win.booking.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="date_booking")
    @JsonFormat(pattern = "dd-MM-yyyy",timezone = "GMT+10")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateBooking;

    @Column(name ="submit_date")
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT+10")
    private Date submitDate;

    @Column(name="approved")
    private Boolean approved;

    @Column(name="user_id")
    private Long userId;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,targetEntity = Room.class)
    @JoinColumn(name="room_id",referencedColumnName = "id")
    private Room room;

}
