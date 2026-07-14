package com.kanhu.repository;

import com.kanhu.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CityRepository extends JpaRepository<City, Long> {
    boolean existsByCityCode(String cityCode);
    boolean existsByCityCodeAndIdNot(String cityCode, Long id);
    Page<City> findByCountryCodeIgnoreCase(String countryCode,Pageable pageable);

    @Query("""
    select c from City c
    where lower(c.name) like lower(concat('%', :keyWord, '%'))
       or lower(c.cityCode) like lower(concat('%', :keyWord, '%'))
       or lower(c.countryCode) like lower(concat('%', :keyWord, '%'))
       or lower(c.countryName) like lower(concat('%', :keyWord, '%'))
       or lower(c.regionId) like lower(concat('%', :keyWord, '%'))
    """)
    Page<City> searchByKeyword(@Param("keyWord") String keyWord, Pageable pageable);
}
