package com.kanhu.payload.response;

import com.kanhu.embedabble.Address;
import com.kanhu.embedabble.GeoCode;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import lombok.*;

import java.time.ZoneId;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirportResponse {
    private Long id;
    private String iataCode;
    private String name;
    private String detailedName;
    private ZoneId timeZone;
    private Address address;
    private GeoCode geoCode;
    private CityResponse city;
}
