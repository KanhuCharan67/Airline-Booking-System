package com.kanhu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kanhu.embedabble.Address;
import com.kanhu.embedabble.GeoCode;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZoneId;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = true)
    private String iataCode;

    @Column(nullable = false)
    private String name;
    @Embedded
    private Address address;
    @Embedded
    private GeoCode geoCode;
    @Column(name="time_zone_id" , length = 50)
    private ZoneId timeZone;
    @ManyToOne
    @JsonIgnore
    private City city;

    @JsonIgnore
    @Transient
    public String getDetailedName(){
        if(city !=null && city.getCountryCode()!=null){
            return name.toUpperCase() + "/" + city.getCityCode();
        }
        return name.toUpperCase();
    }
}
