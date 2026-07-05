package com.kanhu.service.impl;

import com.kanhu.mapper.CityMapper;
import com.kanhu.model.City;
import com.kanhu.payload.request.CityRequest;
import com.kanhu.payload.response.CityResponse;
import com.kanhu.repository.CityRepository;
import com.kanhu.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityServiceImpl  implements CityService {
    private final CityRepository cityRepository;
    @Override
    public CityResponse createCity(CityRequest cityRequest) throws Exception {
        if(cityRepository.existsByCityCode(cityRequest.getCityCode()))
        {
            throw new Exception("city with given code already exsit");
        }
        City city= CityMapper.toEntity(cityRequest);
        City result = cityRepository.save(city);
        return CityMapper.toResponse(result);
    }

    @Override
    public CityResponse getCityById(Long id) throws Exception {
        City city = cityRepository.findById(id).orElseThrow(()-> new Exception("city not exist with given id"));
        return CityMapper.toResponse(city);
    }

    @Override
    public CityResponse updateCity(Long id, CityRequest cityRequest) throws Exception {
        City city = cityRepository.findById(id).orElseThrow(()-> new Exception("city not exist with given id"));
        if(cityRepository.existsByCityCode(cityRequest.getCityCode())){
            throw new Exception("city with given code already exist");
        }
        City updateCity = cityRepository.save(CityMapper.updateEntity(city, cityRequest));
        return CityMapper.toResponse(updateCity);
    }

    @Override
    public void deleteCity(Long id) throws Exception {
        City city = cityRepository.findById(id).orElseThrow(()-> new Exception("city not exist with given id"));
        cityRepository.delete(city);
    }

    @Override
    public Page<CityResponse> getAllCity(Pageable pageable) {
        return cityRepository.findAll(pageable).map(CityMapper::toResponse);
    }

    @Override
    public Page<CityResponse> searchCities(String keyword, Pageable pageable) {
        return cityRepository.searchByKeyword(keyword,pageable).map(CityMapper :: toResponse);
    }

    @Override
    public Page<CityResponse> getCityByCountryCode(String countryCode, Pageable pageable) {
        return cityRepository.findByCountryCodeIgnoreCase(countryCode,pageable).map(CityMapper::toResponse);
    }

    @Override
    public boolean cityExists(String cityCode) {
        return cityRepository.existsByCityCode(cityCode);
    }

}
