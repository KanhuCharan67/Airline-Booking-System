package com.kanhu.mapper;

import com.kanhu.model.City;
import com.kanhu.payload.request.CityRequest;
import com.kanhu.payload.response.CityResponse;

public class CityMapper {

    public static City toEntity(CityRequest cityRequest){
        if(cityRequest == null)return null;
        return City.builder().name(cityRequest.getName())
                .countryName(cityRequest.getCountryName())
                .countryCode(cityRequest.getCountryCode())
                .cityCode(cityRequest.getCityCode())
                .regionId(cityRequest.getRegionCode())
                .timeZoneId(cityRequest.getTimeZoneOffset())
                .build();

    }
    public static CityResponse toResponse(City city){
        if(city==null)return null;
        return  CityResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .cityCode(city.getCityCode())
                .countryCode(city.getCountryCode())
                .countryName(city.getCountryName())
                .regionCode(city.getRegionId())
                .timezoneOffset(city.getTimeZoneId())
                .build();
    }

    public static City updateEntity(City city, CityRequest cityRequest){
        if(cityRequest.getName() !=null){
            city.setName(cityRequest.getName().trim());
        }
        if(cityRequest.getCityCode() !=null){
            city.setCityCode(cityRequest.getCityCode().trim());
        }
        if(cityRequest.getCountryName() !=null){
            city.setCountryName(cityRequest.getCountryName().trim());
        }
        if(cityRequest.getCountryCode() !=null){
            city.setCountryCode(cityRequest.getCountryCode().trim());
        }
        if(cityRequest.getRegionCode() !=null){
            city.setRegionId(cityRequest.getRegionCode().trim());
        }
        if(cityRequest.getTimeZoneOffset() !=null){
            city.setTimeZoneId(cityRequest.getTimeZoneOffset().trim());
        }
        return city;
    }
}
