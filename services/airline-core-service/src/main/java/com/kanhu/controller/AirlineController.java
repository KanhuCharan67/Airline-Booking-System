package com.kanhu.controller;

import com.kanhu.enums.AirlineStatus;
import com.kanhu.payload.request.AirlineRequest;
import com.kanhu.payload.response.AirlineDropdownItem;
import com.kanhu.payload.response.AirlineResponse;
import com.kanhu.payload.response.ApiResponse;
import com.kanhu.service.AirlineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airlines")
@RequiredArgsConstructor
public class AirlineController {
    private final AirlineService airlineService;

    @PostMapping
    public ResponseEntity<AirlineResponse> createAirline(@RequestBody @Valid AirlineRequest request, @RequestHeader("X-User-Id") Long userId){
        AirlineResponse airline = airlineService.createAirline(request, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(airline);
    }
    @GetMapping("/admin")
    public ResponseEntity<AirlineResponse> getAirlineByOwner(@RequestHeader("X-User-Id") Long userId) throws Exception {
        AirlineResponse airlineByOwner = airlineService.getAirlineByOwner(userId);
        return ResponseEntity.ok(airlineByOwner);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AirlineResponse> getAirlineById(@PathVariable Long id) throws Exception {
        AirlineResponse airlineById = airlineService.getAirlineById(id);
        return ResponseEntity.ok(airlineById);
    }
    @GetMapping
    public ResponseEntity<Page<AirlineResponse>> getAllAirlines(Pageable pageable){
        return ResponseEntity.ok(airlineService.getAllAirLines(pageable));
    }
    @GetMapping("/dropdown")
    public ResponseEntity<List<AirlineDropdownItem>> getAirlinesForDropdown(){
        return ResponseEntity.ok(airlineService.getAirlineDropdown());
    }
    @PutMapping
    public ResponseEntity<AirlineResponse> updateAirline(@RequestBody @Valid AirlineRequest request,@RequestHeader("X-User-Id") Long userId) throws Exception {
        AirlineResponse airlineResponse = airlineService.updateAirline(request, userId);
        return ResponseEntity.ok(airlineResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAirline(@PathVariable Long id, @RequestHeader("X-User-Id") Long userId) throws Exception {
        airlineService.deleteAirline(id,userId);
        ApiResponse apiResponse = new ApiResponse("Airline deleted Successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<AirlineResponse> approveAirline(@PathVariable Long id) throws Exception {
        AirlineResponse airlineResponse = airlineService.changeStatusByAdmin(id, AirlineStatus.ACTIVE);
        return ResponseEntity.ok(airlineResponse);
    }
    @PutMapping("/{id}/suspend")
    public ResponseEntity<AirlineResponse> suspendAirline(@PathVariable Long id) throws Exception {
        AirlineResponse airlineResponse = airlineService.changeStatusByAdmin(id, AirlineStatus.INACTIVE);
        return ResponseEntity.ok(airlineResponse);
    }
    @PutMapping("/{id}/ban")
    public ResponseEntity<AirlineResponse> banAirline(@PathVariable Long id) throws Exception {
        AirlineResponse airlineResponse = airlineService.changeStatusByAdmin(id, AirlineStatus.BANNED);
        return ResponseEntity.ok(airlineResponse);
    }
}
