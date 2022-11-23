package model;

import jakarta.persistence.*;

@Entity
public class Car {
    String color;
    String model;

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", engine=" + engine.toString() +
                ", numberOfPassengers=" + numberOfPassengers +
                ", id=" + id +
                '}';
    }

    String manufacturer;
    @OneToOne(cascade = CascadeType.ALL)
    Engine engine;
    int numberOfPassengers;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public Car(String color, String model, String manufacturer, Engine engine, int numberOfPassengers) {
        this.color = color;
        this.model = model;
        this.manufacturer = manufacturer;
        this.engine = engine;
        this.numberOfPassengers = numberOfPassengers;
    }

    public Car() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void startEngine(){
        engine.start();
    }
    public void stopEngine(){
        engine.stop();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
