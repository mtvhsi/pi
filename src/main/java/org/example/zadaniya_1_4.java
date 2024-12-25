package org.example;

import org.example.dto.ModelDTO;
import org.example.map.CarMap;
import org.example.service.Model;
import org.example.service.Impl;
import org.example.service.Car;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
public class zadaniya_1_4 {
    public static void main(String[] args) {
        Model modelService = new Impl();
        CarMap carMap = CarMap.INSTANCE;
        Car carService = new Car(carMap);
        String filePath = "src\\main\\resources\\cars.csv";
        modelService.load(filePath);

        System.out.println("1: Показать все автомобили марки Honda:");
        List<ModelDTO> toyotaCars = modelService.getAllCarDTOs("Honda");
        if (toyotaCars.isEmpty()) {
            System.out.println("Автомобили марки Honda отсутствуют.");
        } else {
            toyotaCars.forEach(car -> System.out.println(car));
        }

        System.out.println("\n2: Найти автомобиль по его идентификатору:");
        ModelDTO searchById = new ModelDTO(11L, null, null, null, null); // Ищем по ID = 3
        Optional<ModelDTO> foundById = modelService.findCarById(searchById);
        foundById.ifPresent(car -> System.out.println("Обнаруженный автомобиль: " + car));
        if (!foundById.isPresent()) {
            System.out.println("Автомобиль с идентификатором 3 не найден.");
        }

        System.out.println("\n3: Группировка автомобилей марки Lexus по моделям:");
        Map<String, Integer> modelCountMap = modelService.getCarModelGroupByModel("BMW");
        if (modelCountMap.isEmpty()) {
            System.out.println("Автомобили марки BMW отсутствуют.");
        } else {
            modelCountMap.forEach((model, count) ->
                    System.out.println("Модель: " + model + ", Количество: " + count));
        }

        System.out.println("\n4: Список уникальных марок автомобилей:");
        Set<String> uniqueBrands = modelService.getUniqueBrands();
        if (uniqueBrands.isEmpty()) {
            System.out.println("Уникальные марки автомобилей отсутствуют.");
        } else {
            uniqueBrands.forEach(System.out::println);
        }

        String brandToSearch = "Kia";
        System.out.println("\n5: Список моделей автомобилей марки " + brandToSearch + ":");
        List<String> toyotaModels = modelService.findModelsByBrand(brandToSearch);
        if (toyotaModels.isEmpty()) {
            System.out.println("Модели для марки " + brandToSearch + " отсутствуют.");
        } else {
            toyotaModels.forEach(System.out::println);
        }

        System.out.println("\n6: Группировка автомобилей по маркам:");
        Map<String, Integer> brandCountMap = modelService.groupByBrand();
        if (brandCountMap.isEmpty()) {
            System.out.println("Нет автомобилей для группировки по маркам.");
        } else {
            brandCountMap.forEach((brand, count) ->
                    System.out.println("Марка: " + brand + ", Количество: " + count));
        }
    }
}
