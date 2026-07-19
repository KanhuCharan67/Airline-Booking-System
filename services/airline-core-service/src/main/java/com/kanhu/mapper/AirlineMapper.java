package com.kanhu.mapper;

import com.kanhu.embedabble.Support;
import com.kanhu.model.Airline;
import com.kanhu.payload.request.AirlineRequest;
import com.kanhu.payload.response.AirlineResponse;

public class AirlineMapper {
    public static Airline toEntity(AirlineRequest airlineRequest, Long ownerId){
        if(airlineRequest==null)return null;

        Airline airline = Airline.builder()
                .iataCode(airlineRequest.getIataCode())
                .icaoCode(airlineRequest.getIcaoCOde())
                .name(airlineRequest.getName())
                .alias(airlineRequest.getAlias())
                .logoUrl(airlineRequest.getLogoUrl())
                .website(airlineRequest.getWebsite())
                .status(airlineRequest.getStatus())
                .alliance(airlineRequest.getAlliance())
                .headquarterCityId(airlineRequest.getHeadquarterCityId())
                .ownerId(ownerId)
                .build();
        if(airlineRequest.getSupportEmail()!=null ||
        airlineRequest.getSupportPhone()!=null||
        airlineRequest.getSupportHour()!=null){
            airline.setSupport(
                    Support.builder()
                            .email(airlineRequest.getSupportEmail())
                            .phone(airlineRequest.getSupportPhone())
                            .hour(airlineRequest.getSupportHour())
                            .build()
            );
        }
        return airline;
    }

    public static AirlineResponse toResponse(Airline airline){
        if(airline==null)return null;

        return AirlineResponse.builder()
                .id(airline.getId())
                .iataCode(airline.getIataCode())
                .iacoCode(airline.getIcaoCode())
                .name(airline.getName())
                .alias(airline.getAlias())
                .logoUrl(airline.getLogoUrl())
                .website(airline.getWebsite())
                .status(airline.getStatus())
                .alliance(airline.getAlliance())
                .support(airline.getSupport())
                .createdAt(airline.getCreatedAt())
                .updatedAt(airline.getUpdatedAt())
                .ownerId(airline.getOwnerId())
                .updatedById(airline.getUpdateById())
                .build();
    }

    public static void updateEntity(Airline airline, AirlineRequest request){
        if(airline==null || request == null)return;

        airline.setIataCode(request.getIataCode());
        airline.setIcaoCode(request.getIcaoCOde());
        airline.setName(request.getName());
        airline.setAlias(request.getAlias());
        airline.setLogoUrl(request.getLogoUrl());
        airline.setWebsite(request.getWebsite());
        airline.setStatus(request.getStatus());
        airline.setAlliance(request.getAlliance());
        airline.setHeadquarterCityId(request.getHeadquarterCityId());
        if(airline.getSupport()==null){
            airline.setSupport(new Support());
        }
        airline.getSupport().setEmail(request.getSupportEmail());
        airline.getSupport().setPhone(request.getSupportPhone());
        airline.getSupport().setHour(request.getSupportHour());

    }

}
