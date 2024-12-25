package org.example.map;

import org.example.dto.CarDTO;
import org.example.dto.ModelDTO;
import org.example.dto.DealershipDTO;
import org.example.entity.Model;
import org.example.entity.Dealer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMap {
    CarMap INSTANCE = Mappers.getMapper(CarMap.class);

    @Mappings({
            @Mapping(source = "carModelId", target = "carModel.id"),
            @Mapping(source = "dealershipName", target = "dealership.name"),
            @Mapping(target = "dealership.cars", ignore = true)
    })
    CarDTO carEntityToCarDTO(Dealer.Car carEntity);

    @Mappings({
            @Mapping(source = "carModel.id", target = "carModelId"),
            @Mapping(source = "dealership.name", target = "dealershipName")
    })
    Dealer.Car carDTOToCarEntity(CarDTO carDTO);

    ModelDTO carModelEntityToCarModelDTO(Model carModelEntity);

    Model carModelDTOToCarModelEntity(ModelDTO carModelDTO);

    DealershipDTO dealershipEntityToDealershipDTO(Dealer dealershipEntity);

    Dealer dealershipDTOToDealershipEntity(DealershipDTO dealershipDTO);
}
