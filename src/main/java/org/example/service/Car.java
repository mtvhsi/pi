package org.example.service;

import org.example.dto.CarDTO;
import org.example.dto.ModelDTO;
import org.example.dto.DealershipDTO;
import org.example.entity.Dealer;
import org.example.map.CarMap;

import java.util.Random;

public class Car {

    private final Random random = new Random();
    private final CarMap carMap;

    // Конструктор для инициализации CarMap
    public Car(CarMap carMap) {
        this.carMap = carMap;
    }

    public CarDTO createCarWithRandomValues(int id, DealershipDTO dealership) {
        long startTime = System.nanoTime();

        String[] states = {"Не занят", "В пути", "В наличии", "Продан", "Забронирован"};
        String[] configurations = {"Базовая", "Специальная", "Спортивная"};
        String[] colors = {"Красный", "Синий", "Черный", "Белый", "Серый"};

        ModelDTO carModel = new ModelDTO(
                id,
                "Brand " + (random.nextInt(10) + 1),
                "Model " + (random.nextInt(10) + 1),
                "Country " + (random.nextInt(5) + 1),
                "Code " + (random.nextInt(5) + 1)
        );

        String state = states[random.nextInt(states.length)];
        String configuration = configurations[random.nextInt(configurations.length)];
        String color = colors[random.nextInt(colors.length)];
        double price = 50000 + (random.nextDouble() * 50000);

        CarDTO car = new CarDTO(id, carModel, dealership, state, configuration, color, price);

        long endTime = System.nanoTime();
        System.out.println("Время выполнения createCarWithRandomValues: " + (endTime - startTime) + " нс");

        return car;
    }

    public void saveCar(CarDTO carDTO) {
        Dealer.Car carEntity = carMap.carDTOToCarEntity(carDTO);
        System.out.println("Сохранен автомобиль: " + carEntity);
    }

    public void measurePerformanceForMultipleCreations(int numberOfCreations, DealershipDTO dealership) {
        long startTime = System.nanoTime();
        for (int i = 0; i < numberOfCreations; i++) {
            CarDTO car = createCarWithRandomValues(i, dealership);
            saveCar(car); // Сохраняем каждый созданный автомобиль
        }
        long endTime = System.nanoTime();
        System.out.println("Время выполнения measurePerformanceForMultipleCreations: " + (endTime - startTime) + " нс");
    }
}
