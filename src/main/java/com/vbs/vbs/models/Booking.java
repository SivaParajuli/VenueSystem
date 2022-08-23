package com.vbs.vbs.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vbs.vbs.enums.BookingStatus;
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

    @JsonIgnoreProperties({"password","bookingList","id","applicationUserRole"})
    @ManyToOne(targetEntity = Client.class,fetch =FetchType.EAGER)
    @JoinColumn(name="client_id",foreignKey = @ForeignKey(name ="Fk_BR_clientId"))
    private Client client;

    @JsonIgnoreProperties({"password","description","bookingList","applicationUserRole"})
    @ManyToOne(targetEntity = Venue.class,fetch =FetchType.EAGER)
    @JoinColumn(name="venue_id",foreignKey = @ForeignKey(name ="Fk_BR_venueId"))
    private Venue venue;
}
