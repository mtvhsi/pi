package org.example.service;

import org.example.dto.CarDTO;
import org.example.dto.DealerDTO;
import org.example.dto.DealershipDTO;
import org.example.dto.ModelDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DealerServiceTest {

    private Dealer dealer;
    private DealerDTO dealerCenter;
    private DealershipDTO dealership;

    @BeforeEach
    public void setup() {
        dealer = new Dealer();
        dealerCenter = new DealerDTO(1L);
        dealership = new DealershipDTO("BMW Auto");
    }

    @Test
    public void testMultithreads() throws InterruptedException {
        // Создаем список автомобилей для теста
        List<CarDTO> carList = createCars(3000);

        List<CarDTO> carList1 = carList.subList(0, 1000);
        List<CarDTO> carList2 = carList.subList(1000, 2000);
        List<CarDTO> carList3 = carList.subList(2000, 3000);

        Thread thread1 = new Thread(() -> {
            dealer.processCars(dealerCenter, carList1);
        });
        Thread thread2 = new Thread(() -> {
            dealer.processCars(dealerCenter, carList2);
        });
        Thread thread3 = new Thread(() -> {
            dealer.processCars(dealerCenter, carList3);
        });

        long startTime = System.currentTimeMillis();

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        long endTime = System.currentTimeMillis();

        System.out.println("Время обработки: " + (endTime - startTime) + " ms");

        int countInShowroom = dealerCenter.getCountShowroomCars();
        System.out.println("Количество автомобилей в шоуруме: " + countInShowroom);

        assertEquals(3000, countInShowroom, "Количество автомобилей в шоуруме должно быть 3000");
    }


    private List<CarDTO> createCars(int count) {
        List<CarDTO> carList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ModelDTO carModel = new ModelDTO(i, "BMW", "X5", "GERMANY", "Year " + (2020 + (i % 5)));
            String color = "Black";
            CarDTO car = new CarDTO(i, carModel, dealership, "New", "SuperPremium", color, 10000.0);
            carList.add(car);
        }
        return carList;
    }

}
