package com.kanhu.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String countryCode;
    @Column(nullable = false, unique = true)
    private String cityCode;
    @Column(nullable = false)
    private String countryName;
    @Size(max = 10)
    private String regionId;
    @Column(name="time_zone_id" , length = 50)
    private String timeZoneId;

}
