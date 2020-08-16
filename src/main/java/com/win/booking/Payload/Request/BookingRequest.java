package com.win.booking.Payload.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class BookingRequest {

    @JsonFormat(pattern = "dd-MM-yyyy",timezone = "GMT+10")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;


    private Long userId;

    private Long roomID;
}
