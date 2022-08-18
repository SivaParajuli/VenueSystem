package com.vbs.vbs.models;

import com.vbs.vbs.enums.BookingStatus;
import com.vbs.vbs.models.Client;
import com.vbs.vbs.models.Venue;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="VenueBookingRequest")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking implements Serializable {

    @Id
    @SequenceGenerator(name="BookingRequest_SEG_GEN",sequenceName = "BookingRequest_SEG_GEN")
    @GeneratedValue(generator = "BookingRequest_SEG_GEN",strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Temporal(TemporalType.DATE)
    private Date bookingDate;

    private String calculatedPayment;

    @Column(name="functionType")
    private String functionType;

    @Column(name="requiredCapacity")
    private String requiredCapacity;

    private BookingStatus bookingStatus;

    @Column(name="contactNumber")
    private String contactNumber;

    @ManyToOne(targetEntity = Client.class,fetch =FetchType.LAZY)
    @JoinColumn(name="client_id",foreignKey = @ForeignKey(name ="Fk_BR_clientId"))
    private Client client;

    @ManyToOne(targetEntity = Venue.class,fetch =FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="venue_id",foreignKey = @ForeignKey(name ="Fk_BR_venueId"))
    private Venue venue;

}
