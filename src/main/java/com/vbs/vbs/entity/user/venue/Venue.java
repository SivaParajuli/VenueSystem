package com.vbs.vbs.entity.user.venue;

import com.vbs.vbs.enums.VenueStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tbl_venue")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Venue implements Serializable {
    @Id
    @SequenceGenerator(name = "owner_id_sequence", sequenceName = "owner_id_sequence")
    @GeneratedValue(generator = "owner_id_sequence", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "v_name", length = 200)
    private String venueName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "contact", length = 10)
    private String contactNumber;

    @Column(name = "address", length = 45)
    private String address;

    @Column(name = "venueStatus")
    private VenueStatus venueStatus;

}