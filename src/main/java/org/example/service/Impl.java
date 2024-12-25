package org.example.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.example.dto.ModelDTO;
import org.example.map.CarMap;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Impl implements Model {
    private List<ModelDTO> carModelList = new ArrayList<>();
    private final CarMap carMap = CarMap.INSTANCE;

    @Override
    public void load(String fileName) {
        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            String[] values;

            csvReader.readNext();

            while ((values = csvReader.readNext()) != null) {
                if (values.length < 5) {
                    System.err.println("Недостаточно данных: " + String.join(", ", values));
                    continue;
                }

                try {
                    long id = Long.parseLong(values[0]);
                    String brand = values[1];
                    String model = values[2];
                    String countryOrigin = values[3];
                    String countryCode = values[4];

                    ModelDTO carModel = new ModelDTO(id, brand, model, countryOrigin, countryCode);
                    carModelList.add(carModel);
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка формата числа: " + String.join(", ", values));
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ModelDTO> getAllCarDTOs(String brand) {
        return carModelList.stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ModelDTO> findCarById(ModelDTO car) {
        return carModelList.stream()
                .filter(c -> c.getId() == car.getId())
                .findFirst();
    }

    @Override
    public Map<String, Integer> getCarModelGroupByModel(String brand) {
        Map<String, Integer> modelCountMap = new HashMap<>();
        carModelList.stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .forEach(car -> modelCountMap.put(car.getModel(), modelCountMap.getOrDefault(car.getModel(), 0) + 1));
        return modelCountMap;
    }

    @Override
    public Set<String> getUniqueBrands() {
        return carModelList.stream()
                .map(ModelDTO::getBrand)
                .collect(Collectors.toSet());
    }

    @Override
    public List<String> findModelsByBrand(String brand) {
        return carModelList.stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .map(ModelDTO::getModel)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Integer> groupByBrand() {
        Map<String, Integer> brandCountMap = new HashMap<>();
        carModelList.forEach(car -> brandCountMap.put(car.getBrand(), brandCountMap.getOrDefault(car.getBrand(), 0) + 1));
        return brandCountMap;
    }

    public org.example.entity.Model convertToEntity(ModelDTO carModelDTO) {
        return carMap.carModelDTOToCarModelEntity(carModelDTO);
    }

    public ModelDTO convertToDTO(org.example.entity.Model carModelEntity) {
        return carMap.carModelEntityToCarModelDTO(carModelEntity);
    }

    public List<org.example.entity.Model> convertAllToEntities() {
        return carModelList.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    public List<ModelDTO> convertAllToDTOs(List<org.example.entity.Model> carModelEntities) {
        return carModelEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
