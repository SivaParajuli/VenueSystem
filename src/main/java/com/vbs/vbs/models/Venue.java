package com.vbs.vbs.models;

import com.vbs.vbs.enums.VenueStatus;
import com.vbs.vbs.enums.ApplicationUserRole;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_venue",uniqueConstraints = {
        @UniqueConstraint(name="unique_venue_email",columnNames = "email"),
        @UniqueConstraint(name="unique_venue_contact_no",columnNames = "contactNumber"),
        @UniqueConstraint(name="unique_venue_userName",columnNames = "username"),

})
public class Venue implements Serializable {
    @Id
    @SequenceGenerator(name = "owner_id_sequence", sequenceName = "owner_id_sequence")
    @GeneratedValue(generator = "owner_id_sequence", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "v_name", length = 200)
    private String venueName;

    @Column(name = "username")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private ApplicationUserRole applicationUserRole;

    @Column(name = "contactNumber", length = 10)
    private String contactNumber;

    @Column(name = "address", length = 45)
    private String address;

    @Column(name = "venueStatus")
    private VenueStatus venueStatus;

    @Column (columnDefinition = "Text")
    private String description;

    @Column(name="filePath")
    private String filePath;

    @OneToMany(targetEntity = Booking.class,mappedBy = "venue")
    private List<Booking> bookingList;
}