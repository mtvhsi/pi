package org.example.service;


import org.example.dto.ModelDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface Model {
    void load(String fileName);
    List<ModelDTO> getAllCarDTOs(String brand);
    Optional<ModelDTO> findCarById(ModelDTO car);
    Map<String, Integer> getCarModelGroupByModel(String brand);
    Set<String> getUniqueBrands();
    List<String> findModelsByBrand(String brand);
    Map<String, Integer> groupByBrand();
}