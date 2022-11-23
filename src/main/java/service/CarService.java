package service;

import model.Car;
import model.Engine;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CarService {
    public CarService(Session session) {
        this.session = session;
        transaction = session.beginTransaction();
    }

    Session session;
    Transaction transaction;

    public Car saveCar(Car car) {
        this.session.persist(car);
        transaction.commit();
        return car;
    }

    public Car updateCar(Car car) {
        Query query = session.createQuery("UPDATE Car c SET c.color =: color, c.model =: model, c.engine=:engine WHERE c.id =: id");
        query.setParameter(1, car.getColor());
        query.setParameter(2, car.getModel());
        query.setParameter(3, car.getEngine());
        query.setParameter(4, car.getId());
        transaction.commit();
        if (query.executeUpdate() > 0)
            return car;
        return null; //can't be updated
    }

    public Car deleteCar(Car car) {
        Query query = session.createQuery("DELETE from Car c WHERE c.id =: id");
        query.setParameter(1, car.getId());
        transaction.commit();
        if (query.executeUpdate() > 0)
            return car;
        return null; //can't be deleted
    }

    public List<Car> selectAll() {
        return session.createQuery("select DISTINCT car from Car car", Car.class).getResultList();
    }

    public Car getCarById(Long id) {
        Query query = session.createQuery("select DISTINCT car from Car car where car.id=: id", Car.class);
        query.setParameter(1, id);
        return (Car) query.getSingleResultOrNull();
    }

    public List<Car> getCarByModel(String model) {
        Query query = session.createQuery("select DISTINCT car from Car car where car.model=: model", Car.class);
        query.setParameter(1, model);
        return query.getResultList();
    }

    public List<Car> getCarByEngine(Engine engine) {
        Query query = session.createQuery("select DISTINCT car from Car car where car.engine=: engine", Car.class);
        query.setParameter(1, engine);
        return query.getResultList();
    }

    public List<Car> getCarByManufacturer(String manufacturer) {
        Query query = session.createQuery("select DISTINCT car from Car car where car.manufacturer=: manufacturer", Car.class);
        query.setParameter(1, manufacturer);
        return query.getResultList();
    }
}
