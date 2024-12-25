package org.example.service;

import org.example.dto.DealershipDTO;
import org.example.map.CarMap;
import org.example.repository.Dealer;

import java.util.List;
import java.util.stream.Collectors;

public class Dealersh {

    private final Dealer dealer;
    private final CarMap carMap;

    public Dealersh(Dealer dealer, CarMap carMap) {
        this.dealer = dealer;
        this.carMap = carMap;
    }

    public List<DealershipDTO> getAllDealerships() {
        List<org.example.entity.Dealer> dealershipEntities = dealer.findAll();
        return dealershipEntities.stream()
                .map(carMap::dealershipEntityToDealershipDTO)
                .collect(Collectors.toList());
    }

}
