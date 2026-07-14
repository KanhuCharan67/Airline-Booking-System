package com.kanhu.controller;

import com.kanhu.payload.request.AirportRequest;
import com.kanhu.payload.response.AirportResponse;
import com.kanhu.payload.response.ApiResponse;
import com.kanhu.service.impl.AirportServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/airports")
public class AirportController {
    private final AirportServiceImpl airportService;

    @PostMapping
    public ResponseEntity<AirportResponse> createairPort(@Valid  @RequestBody AirportRequest airportRequest) throws Exception {
        AirportResponse airport = airportService.createAirport(airportRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(airport);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportResponse> getAirportById(@PathVariable Long id) throws Exception {
        AirportResponse airportById = airportService.getAirportById(id);
        return ResponseEntity.ok(airportById);
    }
    @GetMapping
    public ResponseEntity<List<AirportResponse>> getAirports(){
        return ResponseEntity.ok(airportService.getAllAirport());
    }
    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<AirportResponse>> getAirportsByCityId(@PathVariable Long cityId){
        return ResponseEntity.ok(airportService.getAirportByCityId(cityId));
    }
    @PutMapping("/{id}")
    public ResponseEntity<AirportResponse> updateAirport(@PathVariable Long id, @Valid @RequestBody AirportRequest airportRequest) throws Exception {
        AirportResponse airportResponse = airportService.updateAirport(id, airportRequest);
        return ResponseEntity.ok(airportResponse);
    }
    public ResponseEntity<ApiResponse> deleteAirport(@PathVariable Long id) throws Exception {
        airportService.deleteAirport(id);
        return ResponseEntity.ok(new ApiResponse("Airport deleted Successfully"));
    }
}
