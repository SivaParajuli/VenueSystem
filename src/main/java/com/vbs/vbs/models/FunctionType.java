package com.vbs.vbs.models;

import com.vbs.vbs.enums.Functions;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_function")

public class FunctionType implements Serializable {
    @Id
    @SequenceGenerator(name="function_id_sequence",sequenceName="function_id_sequence")
    @GeneratedValue(generator="function_id_sequence",strategy=GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "eventType")
    private Functions functions;
}
