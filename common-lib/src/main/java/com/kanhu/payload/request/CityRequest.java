package com.kanhu.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CityRequest {

    @NotBlank(message = "city name is required")
    @Size(max = 100)
    private String name;
    @NotBlank(message = "city code is required")
    @Size(max = 10)
    private String cityCode;
    @NotBlank(message = "country code is required")
    @Size(max = 5)
    private String countryCode;
    @Size(max = 10)
    private String regionCode;
    @NotBlank(message = "country name is required")
    @Size(max = 100)
    private String countryName;
    @Size(max = 10)
    private String timeZoneOffset;
}
