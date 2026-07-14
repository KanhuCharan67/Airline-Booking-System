package com.kanhu.service;

import com.kanhu.payload.request.AirportRequest;
import com.kanhu.payload.response.AirportResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AirportService {
    AirportResponse createAirport(AirportRequest airPortRequest) throws Exception;

    AirportResponse getAirportById(Long id) throws Exception;
    List<AirportResponse> getAllAirport();
    AirportResponse updateAirport(Long id, AirportRequest airportRequest) throws Exception;
    void deleteAirport(Long id) throws Exception;

    List<AirportResponse> getAirportByCityId(Long cityId);

}
