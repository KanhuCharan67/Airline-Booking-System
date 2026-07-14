package com.kanhu.payload.request;

import com.kanhu.embedabble.Address;
import com.kanhu.embedabble.GeoCode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirportRequest {

    @NotBlank(message = "IATA code is Mandatory")
    @Size(min = 3, max = 3 ,message = "IATA code must be 3 characters")
    private String iataCode;
    @NotBlank(message = "Airport is mandatory")
    private String name;
    private ZoneId timeZone;
    @Valid
    private Address address;
    @NotNull(message = "City Id is mandatory")
    private Long cityId;
    @Valid
    private GeoCode geoCode;
}
