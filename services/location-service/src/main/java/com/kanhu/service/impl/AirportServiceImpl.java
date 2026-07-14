package com.kanhu.service.impl;

import com.kanhu.mapper.AirportMapper;
import com.kanhu.model.Airport;
import com.kanhu.model.City;
import com.kanhu.payload.request.AirportRequest;
import com.kanhu.payload.response.AirportResponse;
import com.kanhu.repository.AirportRepository;
import com.kanhu.repository.CityRepository;
import com.kanhu.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {
    private  final AirportRepository airportRepository;
    private  final CityRepository cityRepository;
    @Override
    public AirportResponse createAirport(AirportRequest airPortRequest) throws Exception {
        if(airportRepository.findByIataCode(airPortRequest.getIataCode()).isPresent()){
            throw  new Exception("Airport with Iata code already present");
        }
        City city = cityRepository.findById(airPortRequest.getCityId()).orElseThrow(() -> new Exception("City not found"));
        Airport airport = AirportMapper.toEntity(airPortRequest);
        airport.setCity(city);
        Airport save = airportRepository.save(airport);
        AirportResponse airportResponse = AirportMapper.toResponse(save);
        return airportResponse;
    }

    @Override
    public AirportResponse getAirportById(Long id) throws Exception {
        Airport airport = airportRepository.findById(id).orElseThrow(() -> new Exception("The Given Id Airport not found"));
        return AirportMapper.toResponse(airport);
    }

    @Override
    public List<AirportResponse> getAllAirport() {
        return airportRepository.findAll().stream()
                .map(AirportMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AirportResponse updateAirport(Long id, AirportRequest airportRequest) throws Exception {
        Airport existingAirport = airportRepository.findById(id).orElseThrow(() -> new Exception("The Given Id Airport not found"));
        if(airportRequest.getIataCode()!=null
        && !existingAirport.getIataCode().equals(airportRequest.getIataCode())
        && airportRepository.findByIataCode(airportRequest.getIataCode()).isPresent()){
            throw  new Exception("Airport with Iata code already present");
        }
        AirportMapper.updateEntity(airportRequest,existingAirport);
        Airport airport = airportRepository.save(existingAirport);
        return AirportMapper.toResponse(airport);
    }

    @Override
    public void deleteAirport(Long id) throws Exception {
        Airport airport = airportRepository.findById(id).orElseThrow(() -> new Exception("The Given Id Airport not found"));
        airportRepository.delete(airport);
    }

    @Override
    public List<AirportResponse> getAirportByCityId(Long cityId) {
        return airportRepository.findByCityId(cityId).stream()
                .map(AirportMapper::toResponse)
                .collect(Collectors.toList());
    }
}
