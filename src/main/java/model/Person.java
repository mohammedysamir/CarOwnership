package model;

import jakarta.persistence.*;

@Entity
public class Person {
    String firstName;
    String lastName;
    String nationalId;
    @OneToOne(cascade = CascadeType.ALL)
    Car car;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Person(String firstName, String lastName, String nationalId, Car car) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalId = nationalId;
        this.car = car;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
