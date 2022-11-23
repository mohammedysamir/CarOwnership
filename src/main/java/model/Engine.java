package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Engine {
    int cylinder;
    char type; //v | w
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public Engine(int cylinder, char type) {
        this.cylinder = cylinder;
        this.type = type;
    }

    public Engine() {

    }

    @Override
    public String toString() {
        return "Engine{" +
                "cylinder=" + cylinder +
                ", type=" + type +
                ", id=" + id +
                '}';
    }

    public void start() {
        System.out.println("Engine is starting...");
    }

    public int getCylinder() {
        return cylinder;
    }

    public void setCylinder(int cylinder) {
        this.cylinder = cylinder;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public void stop() {
        System.out.println("Engine is stopping...");
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
