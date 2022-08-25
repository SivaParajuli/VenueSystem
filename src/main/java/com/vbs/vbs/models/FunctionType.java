package com.vbs.vbs.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vbs.vbs.enums.EventType;
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
    private Integer id;

    @Column(name = "eventType")
    private EventType event;

    private Double rate;

    private Double baseCost;

    @JsonIgnoreProperties({"password","description","bookingList","functionList","applicationUserRole"})
    @ManyToOne(targetEntity = Venue.class,fetch =FetchType.LAZY)
    @JoinColumn(name="v_id",foreignKey = @ForeignKey(name ="Fk_FR_venueId"))
    private Venue venue1;

}
