package com.etim.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer value;

    private Boolean raining;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
//    @JsonIgnore
    private Sensor sensor;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
}
