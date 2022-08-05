package com.vbs.vbs.models;

import com.vbs.vbs.security.ApplicationUserRole;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_user")
public class User implements Serializable {
    @Id
    @SequenceGenerator(name = "user_id_sequence", sequenceName = "user_id_sequence")
    @GeneratedValue(generator = "user_id_sequence", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "password", length = 200)
    private String password;

    @Enumerated(EnumType.STRING)
    private ApplicationUserRole applicationUserRole;
}
