package com.kanhu.service;

import com.kanhu.enums.AirlineStatus;
import com.kanhu.payload.request.AirlineRequest;
import com.kanhu.payload.response.AirlineDropdownItem;
import com.kanhu.payload.response.AirlineResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AirlineService {

    AirlineResponse createAirline(AirlineRequest airlineRequest,Long ownerId);
    AirlineResponse getAirlineByOwner(Long ownerId) throws Exception;
    AirlineResponse getAirlineById(Long id) throws Exception;
    Page<AirlineResponse> getAllAirLines(Pageable pageable);
    AirlineResponse updateAirline(AirlineRequest airlineRequest, Long ownerId) throws Exception;
    void deleteAirline(Long id, Long ownerId) throws Exception;
    AirlineResponse changeStatusByAdmin(Long airlineId, AirlineStatus status) throws Exception;

    List<AirlineDropdownItem> getAirlineDropdown();
}
