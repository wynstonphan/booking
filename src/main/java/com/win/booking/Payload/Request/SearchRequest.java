package com.win.booking.Payload.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class SearchRequest {

    private String searchBy;

    private int capacity;

    private String equipment;

    @JsonFormat(pattern = "dd-MM-yyyy",timezone = "GMT+10")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateBooking;
}
