package com.vbs.vbs.models;


import com.vbs.vbs.enums.ApplicationUserRole;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="tbl_admin")
public class Admin implements Serializable {
    @Id
    @SequenceGenerator(name = "admin_id_sequence", sequenceName = "admin_id_sequence")
    @GeneratedValue(generator = "admin_id_sequence", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "username", length = 200,nullable = false)
    private String name;

    @Column(name = "email", length = 100,nullable = false)
    private String email;

    @Column(name = "password", length = 100 ,nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private ApplicationUserRole applicationUserRole;

}

