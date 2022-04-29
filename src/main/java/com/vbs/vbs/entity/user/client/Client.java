package com.vbs.vbs.entity.user.client;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tbl_client")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client implements Serializable {
    @Id
    @SequenceGenerator(name="client_id_sequence",sequenceName="client_id_sequence")
    @GeneratedValue(generator="client_id_sequence",strategy=GenerationType.SEQUENCE)
    private Integer id;

    @Column(name="name",length=200)
    private String name;

    @Column(name="moblie_no",length=10)
    private String mobile_no;

    @Column(name="mail",length=255)
    private String email;

    @Column(name="password",length=50)
    private String password;

    @Column(name="city_address",length=45)
    private String city_name;

    @Column(name="street_address",length=34)
    private String street_name;
}
