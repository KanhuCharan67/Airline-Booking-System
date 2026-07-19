package com.kanhu.payload.request;

import com.kanhu.enums.AirlineStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirlineRequest {

    @NotBlank(message = "Iata Code is mandatory")
    @Size(min = 2, max = 2, message = "IATA Code must be exactly 2 character")
    private String iataCode;

    @NotBlank(message = "Icao code is mandatory")
    @Size(min = 3, max = 3, message = "ICAO Code must be exactly 3 character")
    private String icaoCOde;

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotBlank
    private String alias;

    private String logoUrl;

    private String website;

    private AirlineStatus status;
    private String alliance;

    private Long headquarterCityId;

    private String supportEmail;
    private String supportPhone;
    private String supportHour;

}
