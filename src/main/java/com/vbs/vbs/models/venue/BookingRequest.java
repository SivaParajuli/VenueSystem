package com.vbs.vbs.models.venue;

import com.vbs.vbs.enums.BookingStatus;
import com.vbs.vbs.models.client.Client;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "VenueBookingRequest")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest implements Serializable {

    @Id
    @SequenceGenerator(name="BookingRequest_SEG_GEN",sequenceName = "BookingRequest_SEG_GEN")
    @GeneratedValue(generator = "BookingRequest_SEG_GEN",strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Temporal(TemporalType.DATE)
    private Date bookingDate;

    private Double offeredPayment;

    @Column(name="functionType")
    private String functionType;

    @Column(name="requiredCapacity")
    private Integer requiredCapacity;

    private BookingStatus bookingStatus;

    @Column(name="contactNumber")
    private Integer contactNumber;

    @ManyToOne(targetEntity = Client.class,fetch =FetchType.LAZY)
    @JoinColumn(name="client_id",foreignKey = @ForeignKey(name ="Fk_BR_clientId"))
    private Client client;

    @ManyToOne(targetEntity = Venue.class,fetch =FetchType.LAZY)
    @JoinColumn(name="venue_id",foreignKey = @ForeignKey(name ="Fk_BR_venueId"))
    private Venue venue;

}