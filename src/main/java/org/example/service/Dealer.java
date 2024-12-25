package org.example.service;

import org.example.dto.CarDTO;
import org.example.dto.ModelDTO;
import org.example.dto.DealerDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dealer {

    // Критерии для добавления в шоурум
    private Set<String> validColors = new HashSet<>();
    private Set<String> validBrands = new HashSet<>();
    private Set<String> validModels = new HashSet<>();
    private Set<String> validConfigurations = new HashSet<>();

    public Dealer() {
        validColors.add("Black");
        validBrands.add("BMW");
        validModels.add("X5");
        validConfigurations.add("SuperPremium");
    }

    public void processCars(DealerDTO dealer, List<CarDTO> carList) {
        for (CarDTO car : carList) {
            heavyProcessing(car); // Имитация тяжелой обработки автомобиля

            // Определяем, нужно ли добавлять автомобиль в шоурум
            if (isNeedAddToShowroom(car)) {
                dealer.addCarToShowroom(car); // Добавляем автомобиль в шоурум
            }
        }
    }

    // Метод для имитации тяжелой обработки автомобиля
    private void heavyProcessing(CarDTO car) {
        try {
            // Имитация тяжелой работы (например, задержка)
            Thread.sleep(10); // 10 миллисекунд
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Восстанавливаем состояние прерывания
        }
    }

    // Определяем, нужно ли добавлять автомобиль в шоурум
    private boolean isNeedAddToShowroom(CarDTO car) {
        ModelDTO carModel = car.getCarModel();
        return validColors.contains(car.getColor()) &&
                validBrands.contains(carModel.getBrand()) &&
                validModels.contains(carModel.getModel()) &&
                validConfigurations.contains(car.getConfiguration());
    }
}
