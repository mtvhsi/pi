package org.example.dto;

public class CarDTO {
    private int id;
    private ModelDTO carModel;
    private DealershipDTO dealership;
    private String state;
    private String configuration;
    private String color;
    private double price;
    public CarDTO(int id, ModelDTO carModel, DealershipDTO dealership, String state, String configuration, String color, double price) {
        this.id = id;
        this.carModel = carModel;
        this.dealership = dealership;
        this.state = state;
        this.configuration = configuration;
        this.color = color;
        this.price = price;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public ModelDTO getCarModel() {
        return carModel;
    }
    public void setCarModel(ModelDTO carModel) {
        this.carModel = carModel;
    }
    public DealershipDTO getDealership() {
        return dealership;
    }
    public void setDealership(DealershipDTO dealership) {
        this.dealership = dealership;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getConfiguration() {
        return configuration;
    }
    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "CarDTO{" +
                "id=" + id +
                ", carModel=" + carModel +
                ", dealership=" + dealership.getName() +
                ", state='" + state + '\'' +
                ", configuration='" + configuration + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }
}

