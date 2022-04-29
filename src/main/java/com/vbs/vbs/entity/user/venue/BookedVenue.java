package com.vbs.vbs.entity.user.venue;

import com.vbs.vbs.entity.user.client.Client;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="tbl_bookedVenue")
public class BookedVenue implements Serializable {
    @Id
    @SequenceGenerator(name="bookedVenue_id",sequenceName = "bookedVenue_id")
    @GeneratedValue(generator = "bookedVenue_id",strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name="booking_date")
    private Timestamp date;

    @Column(name="function_name")
    private String function;

    @Column(name="booked_date")
    private Date bookedDate;

    @Column(name="requiredCapacity")
    private Number requiredCapacity;



}
