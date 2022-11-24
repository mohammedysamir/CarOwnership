package model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CarAgency {
    String agencyName;
    @OneToMany(cascade = CascadeType.ALL)
    List<Car> cars;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public CarAgency() {
    }

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
