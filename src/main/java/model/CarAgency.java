package model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

public class CarAgency {
    String agencyName;
    @OneToMany(cascade = CascadeType.ALL)
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
