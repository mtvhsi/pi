package org.example;

import org.example.database.DB;
import org.example.repository.Model;
import org.example.repository.Car;
import org.example.repository.Dealer;

import java.util.List;
import java.util.Optional;

public class zadanie_7 {
    public static void main(String[] args) {

        DB.setupDatabase();

        Model carModelRepository = new Model();
        Car carRepository = new Car();
        Dealer dealer = new Dealer();

        org.example.entity.Model toyotaModel = new org.example.entity.Model(1, "Toyota", "Camry", "Japan", "JP");
        carModelRepository.create(toyotaModel);
        System.out.println("Модель добавлена: " + toyotaModel);

        org.example.entity.Dealer dealership = new org.example.entity.Dealer("GRAND_AUTO");
        dealer.create(dealership);
        System.out.println("Дилерский центр создан: " + dealership);

        org.example.entity.Dealer.Car newCar = new org.example.entity.Dealer.Car(1, 1, "GRAND_AUTO", "новый", "Седан", "Красный", 20000.00);
        carRepository.create(newCar);
        System.out.println("Автомобиль добавлен: " + newCar);

        List<org.example.entity.Dealer.Car> cars = carRepository.findAll();
        System.out.println("\nВсе автомобили в базе данных:");
        cars.forEach(car -> System.out.println(car));

        List<org.example.entity.Model> carModels = carModelRepository.findAll();
        System.out.println("\nВсе модели автомобилей в базе данных:");
        carModels.forEach(model -> System.out.println(model));

        int searchCarId = 1;
        Optional<org.example.entity.Dealer.Car> foundCar = carRepository.findById(searchCarId);
        foundCar.ifPresent(car -> System.out.println("\nОбнаруженный автомобиль: " + car));

        if (foundCar.isPresent()) {
            org.example.entity.Dealer.Car carToUpdate = foundCar.get();
            carToUpdate.setColor("Синий");
            carToUpdate.setPrice(21000.00);
            carRepository.update(carToUpdate);
            System.out.println("\nДанные автомобиля обновлены: " + carToUpdate);
        }

        int deleteCarId = 1;
        carRepository.delete(deleteCarId);
        System.out.println("\nАвтомобиль с ID " + deleteCarId + " удалён.");

        Optional<org.example.entity.Dealer.Car> checkDeletedCar = carRepository.findById(deleteCarId);
        if (checkDeletedCar.isEmpty()) {
            System.out.println("Автомобиль с ID " + deleteCarId + " отсутствует в базе данных.");
        }

        String dealershipToDelete = "GRAND_AUTO";
        dealer.delete(dealershipToDelete);
        System.out.println("\nДилерский центр " + dealershipToDelete + " удалён.");
    }
}
