package com.vbs.vbs.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

public class EventsCostAndRate implements Serializable {
    @Id
    @SequenceGenerator(name = "function_id_sequence", sequenceName = "function_id_sequence")
    @GeneratedValue(generator = "function_id_sequence", strategy = GenerationType.SEQUENCE)
    private Integer id;

    private Double marriageCost;

    private Double conclaveCost;

    private Double familyFunctionCost;

    private Double collegeEventCost;

    private Double annualMeetCost;

    private Double rate;

    @JsonIgnoreProperties({"password","description","bookingList","functionList","applicationUserRole"})
    @ManyToOne(targetEntity = Venue.class,fetch =FetchType.LAZY)
    @JoinColumn(name="v_id",foreignKey = @ForeignKey(name ="Fk_FR_venueId"))
    private Venue venue1;
}
