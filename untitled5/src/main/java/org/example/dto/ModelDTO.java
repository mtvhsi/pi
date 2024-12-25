package org.example.dto;

import java.util.Objects;

public class ModelDTO {
    private long id;
    private String brand;
    private String model;
    private String countryOrigin;
    private String countryCode;

    public ModelDTO(long id, String brand, String model, String countryOrigin, String countryCode) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.countryOrigin = countryOrigin;
        this.countryCode = countryCode;
    }

    public long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    public String getCountryCode() {
        return countryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ModelDTO)) return false;

        ModelDTO that = (ModelDTO) o;

        return id == that.id ||
                (Objects.equals(brand, that.brand) &&
                        Objects.equals(model, that.model));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model);
    }

    @Override
    public String toString() {
        return id + " " + (brand != null ? brand : "N/A") + " " + (model != null ? model : "N/A") + " " +
                (countryOrigin != null ? countryOrigin : "N/A") + " " + (countryCode != null ? countryCode : "N/A");
    }
}