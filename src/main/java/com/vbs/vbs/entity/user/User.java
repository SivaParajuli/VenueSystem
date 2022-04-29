package com.vbs.vbs.entity.user;

import com.vbs.vbs.enums.UserStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_user", uniqueConstraints = {
        @UniqueConstraint(name = "unique_User_email", columnNames = "email")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "User_seq_gen")
    @SequenceGenerator(name = "User_seq_gen", sequenceName = "User_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(nullable = false)
    private UserStatus userStatus;
}

