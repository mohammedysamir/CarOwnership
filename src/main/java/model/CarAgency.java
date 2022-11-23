package model;

import jakarta.persistence.OneToMany;

import java.util.List;

public class CarAgency {
    String agencyName;
    @OneToMany
    List<Car> cars;

    public void addCar(Car car) {
        cars.add(car);
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public CarAgency(String agencyName, List<Car> cars) {
        this.agencyName = agencyName;
        this.cars = cars;
    }
}
