package com.kanhu.service.impl;

import com.kanhu.enums.AirlineStatus;
import com.kanhu.mapper.AirlineMapper;
import com.kanhu.model.Airline;
import com.kanhu.payload.request.AirlineRequest;
import com.kanhu.payload.response.AirlineDropdownItem;
import com.kanhu.payload.response.AirlineResponse;
import com.kanhu.repository.AirlineRepository;
import com.kanhu.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;
    @Override
    public AirlineResponse createAirline(AirlineRequest airlineRequest, Long ownerId) {
        Airline airlineEntity = AirlineMapper.toEntity(airlineRequest, ownerId);
        Airline airline = airlineRepository.save(airlineEntity);
        return AirlineMapper.toResponse(airline);
    }

    @Override
    public AirlineResponse getAirlineByOwner(Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(()->new Exception("Airline not found by the given owner id"));
        return AirlineMapper.toResponse(airline);
    }

    @Override
    public AirlineResponse getAirlineById(Long id) throws Exception {
        Airline airline = airlineRepository.findById(id).orElseThrow(() -> new Exception("Airline not found by the given  id"));
        return AirlineMapper.toResponse(airline);
    }

    @Override
    public Page<AirlineResponse> getAllAirLines(Pageable pageable) {
        return airlineRepository.findAll(pageable).map(
                AirlineMapper::toResponse
        );
    }

    @Override
    public AirlineResponse updateAirline(AirlineRequest airlineRequest, Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(()->new Exception("Airline not found by the given owner id"));
        AirlineMapper.updateEntity(airline,airlineRequest);
        Airline savedAirline = airlineRepository.save(airline);
        return AirlineMapper.toResponse(savedAirline);
    }

    @Override
    public void deleteAirline(Long id, Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(()->new Exception("Airline not found by the given owner id"));
        airlineRepository.delete(airline);
    }

    @Override
    public AirlineResponse changeStatusByAdmin(Long airlineId, AirlineStatus status) throws Exception {
        Airline airline = airlineRepository.findById(airlineId).orElseThrow(()->new Exception("Airline not found by the given airlineid"));
        airline.setStatus(status);
        Airline updateStatus = airlineRepository.save(airline);
        return AirlineMapper.toResponse(updateStatus);
    }

    @Override
    public List<AirlineDropdownItem> getAirlineDropdown() {
       return airlineRepository.findByStatus(AirlineStatus.ACTIVE).stream()
                .map(a->AirlineDropdownItem.builder()
                        .id(a.getId())
                        .iataCode(a.getIataCode())
                        .icaoCode(a.getIcaoCode())
                        .name(a.getName())
                        .logoUrl(a.getLogoUrl())
                        .build()).toList();
    }
}
