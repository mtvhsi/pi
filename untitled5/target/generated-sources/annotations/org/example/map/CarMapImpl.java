package org.example.map;

import javax.annotation.processing.Generated;
import org.example.dto.CarDTO;
import org.example.dto.DealershipDTO;
import org.example.dto.ModelDTO;
import org.example.entity.Dealer;
import org.example.entity.Model;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-25T23:26:38+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
public class CarMapImpl implements CarMap {

    @Override
    public CarDTO carEntityToCarDTO(Dealer.Car carEntity) {
        if ( carEntity == null ) {
            return null;
        }

        ModelDTO carModel = null;
        DealershipDTO dealership = null;
        int id = 0;
        String state = null;
        String configuration = null;
        String color = null;
        double price = 0.0d;

        carModel = carToModelDTO( carEntity );
        dealership = carToDealershipDTO( carEntity );
        id = carEntity.getId();
        state = carEntity.getState();
        configuration = carEntity.getConfiguration();
        color = carEntity.getColor();
        price = carEntity.getPrice();

        CarDTO carDTO = new CarDTO( id, carModel, dealership, state, configuration, color, price );

        return carDTO;
    }

    @Override
    public Dealer.Car carDTOToCarEntity(CarDTO carDTO) {
        if ( carDTO == null ) {
            return null;
        }

        int carModelId = 0;
        String dealershipName = null;
        int id = 0;
        String state = null;
        String configuration = null;
        String color = null;
        double price = 0.0d;

        carModelId = (int) carDTOCarModelId( carDTO );
        dealershipName = carDTODealershipName( carDTO );
        id = carDTO.getId();
        state = carDTO.getState();
        configuration = carDTO.getConfiguration();
        color = carDTO.getColor();
        price = carDTO.getPrice();

        Dealer.Car car = new Dealer.Car( id, carModelId, dealershipName, state, configuration, color, price );

        return car;
    }

    @Override
    public ModelDTO carModelEntityToCarModelDTO(Model carModelEntity) {
        if ( carModelEntity == null ) {
            return null;
        }

        long id = 0L;
        String brand = null;
        String model = null;
        String countryOrigin = null;
        String countryCode = null;

        id = carModelEntity.getId();
        brand = carModelEntity.getBrand();
        model = carModelEntity.getModel();
        countryOrigin = carModelEntity.getCountryOrigin();
        countryCode = carModelEntity.getCountryCode();

        ModelDTO modelDTO = new ModelDTO( id, brand, model, countryOrigin, countryCode );

        return modelDTO;
    }

    @Override
    public Model carModelDTOToCarModelEntity(ModelDTO carModelDTO) {
        if ( carModelDTO == null ) {
            return null;
        }

        int id = 0;
        String brand = null;
        String model = null;
        String countryOrigin = null;
        String countryCode = null;

        id = (int) carModelDTO.getId();
        brand = carModelDTO.getBrand();
        model = carModelDTO.getModel();
        countryOrigin = carModelDTO.getCountryOrigin();
        countryCode = carModelDTO.getCountryCode();

        Model model1 = new Model( id, brand, model, countryOrigin, countryCode );

        return model1;
    }

    @Override
    public DealershipDTO dealershipEntityToDealershipDTO(Dealer dealershipEntity) {
        if ( dealershipEntity == null ) {
            return null;
        }

        String name = null;

        name = dealershipEntity.getName();

        DealershipDTO dealershipDTO = new DealershipDTO( name );

        return dealershipDTO;
    }

    @Override
    public Dealer dealershipDTOToDealershipEntity(DealershipDTO dealershipDTO) {
        if ( dealershipDTO == null ) {
            return null;
        }

        String name = null;

        name = dealershipDTO.getName();

        Dealer dealer = new Dealer( name );

        return dealer;
    }

    protected ModelDTO carToModelDTO(Dealer.Car car) {
        if ( car == null ) {
            return null;
        }

        long id = 0L;

        id = car.getCarModelId();

        String brand = null;
        String model = null;
        String countryOrigin = null;
        String countryCode = null;

        ModelDTO modelDTO = new ModelDTO( id, brand, model, countryOrigin, countryCode );

        return modelDTO;
    }

    protected DealershipDTO carToDealershipDTO(Dealer.Car car) {
        if ( car == null ) {
            return null;
        }

        String name = null;

        name = car.getDealershipName();

        DealershipDTO dealershipDTO = new DealershipDTO( name );

        return dealershipDTO;
    }

    private long carDTOCarModelId(CarDTO carDTO) {
        if ( carDTO == null ) {
            return 0L;
        }
        ModelDTO carModel = carDTO.getCarModel();
        if ( carModel == null ) {
            return 0L;
        }
        long id = carModel.getId();
        return id;
    }

    private String carDTODealershipName(CarDTO carDTO) {
        if ( carDTO == null ) {
            return null;
        }
        DealershipDTO dealership = carDTO.getDealership();
        if ( dealership == null ) {
            return null;
        }
        String name = dealership.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
