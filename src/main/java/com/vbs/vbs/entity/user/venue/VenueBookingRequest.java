package com.vbs.vbs.entity.user.venue;

import com.vbs.vbs.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "VenueBookingRequest")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VenueBookingRequest{

    @Id
    @SequenceGenerator(name="BookingRequest_SEG_GEN",sequenceName = "BookingRequest_SEG_GEN")
    @GeneratedValue(generator = "BookingRequest_SEG_GEN",strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Temporal(TemporalType.DATE)
    private Date BookingDate;

    @Column(nullable = false)
    private Double payment;

    @Column(nullable = false,name="functionType")
    private String functionType;

    @Column(nullable = false,name="requiredCapacity")
    private Integer requiredCapacity;

    @Column(nullable = false)
    private BookingStatus bookingStatus;

    @Column(nullable = false,name="clientName")
    private String clientName;

    @Column(nullable = false,name="contactNumber")
    private Integer contactNumber;

    @OneToOne
    private Venue venue;

}
