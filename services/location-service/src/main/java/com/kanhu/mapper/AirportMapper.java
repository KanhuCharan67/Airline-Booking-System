package com.kanhu.mapper;

import com.kanhu.model.Airport;
import com.kanhu.payload.request.AirportRequest;
import com.kanhu.payload.response.AirportResponse;
import com.kanhu.repository.AirportRepository;

public class AirportMapper {

    public static Airport toEntity(AirportRequest airportRequest){
        if(airportRequest==null) return null;
        return  Airport.builder()
                .iataCode(airportRequest.getIataCode())
                .name(airportRequest.getName())
//                .timeZone(airportRequest.getTimeZone())
                .address(airportRequest.getAddress())
                .geoCode(airportRequest.getGeoCode())
                .build();
    }
    public static AirportResponse toResponse(Airport airport){
        if(airport == null) return  null;
        return  AirportResponse.builder()
                .id(airport.getId())
                .iataCode(airport.getIataCode())
                .name(airport.getName())
                .detailedName(airport.getDetailedName())
//                .timeZone(airport.getTimeZone())
                .address(airport.getAddress())
                .city(CityMapper.toResponse(airport.getCity()))
                .geoCode(airport.getGeoCode())
                .build();
    }
    public static void updateEntity(AirportRequest airportRequest,  Airport existingAirport){
        if(airportRequest == null || existingAirport == null) return;
        if(airportRequest.getIataCode() !=null){
            existingAirport.setIataCode(airportRequest.getIataCode());
        }
        if(airportRequest.getName() !=null){
            existingAirport.setName(airportRequest.getName());
        }
        if(airportRequest.getAddress()!=null){
            existingAirport.setAddress(airportRequest.getAddress());
        }
        if(airportRequest.getGeoCode()!=null){
            existingAirport.setGeoCode(airportRequest.getGeoCode());
        }
    }
}
