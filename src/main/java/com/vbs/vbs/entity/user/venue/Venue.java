package com.vbs.vbs.entity.user.venue;

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
    @SequenceGenerator(name="owner_id_sequence",sequenceName = "owner_id_sequence")
    @GeneratedValue(generator = "owner_id_sequence",strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name="v_name",length=200)
    private String v_name;

    @Column(name="contact",length=10)
    private String contact;

    @Column(name="email",length=200)
    private String email;

    @Column(name="capacity",length=100)
    private String capacity;

    @Column(name="password",length=50)
    private String password;

    @Column(name="city_address",length=45)
    private String city_name;

    @Column(name="street_address",length=34)
    private String street_name;



}
