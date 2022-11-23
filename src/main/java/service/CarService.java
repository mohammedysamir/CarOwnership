package service;

import model.Car;
import model.Engine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CarService {
    public CarService(SessionFactory factory) {
        this.factory = factory;
    }

    SessionFactory factory;

    public Car saveCar(Car car) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(car);
        transaction.commit();
        return car;
    }

    public Car updateCar(Car car) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE Car c SET c.color =: color, c.model =: model, c.engine=:engine WHERE c.id =: id", Car.class);
        query.setParameter(1, car.getColor());
        query.setParameter(2, car.getModel());
        query.setParameter(3, car.getEngine());
        query.setParameter(4, car.getId());
        transaction.commit();
        session.close();
        if (query.executeUpdate() > 0)
            return car;
        return null; //can't be updated
    }

    public Car deleteCar(Car car) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE from Car c WHERE c.id =: id");
        query.setParameter(1, car.getId());
        transaction.commit();
        session.close();
        if (query.executeUpdate() > 0)
            return car;
        return null; //can't be deleted
    }

    public List<Car> selectAll() {
        Session session = factory.openSession();
        List<Car> all = session.createQuery("select DISTINCT car from Car car", Car.class).getResultList();
        session.close();
        return all;
    }

    public Car getCarById(Long id) {
        Session session = factory.openSession();
        Query query = session.createQuery("select DISTINCT car from Car car where car.id=: id", Car.class);
        query.setParameter(1, id);
        Car result = (Car) query.getSingleResultOrNull();
        session.close();
        return result;
    }

    public List<Car> getCarByModel(String model) {
        Session session = factory.openSession();
        Query query = session.createQuery("select DISTINCT car from Car car where car.model=: model", Car.class);
        query.setParameter(1, model);
        List<Car> cars = query.getResultList();
        session.close();
        return cars;
    }

    public List<Car> getCarByEngine(Engine engine) {
        Session session = factory.openSession();
        Query query = session.createQuery("select DISTINCT car from Car car where car.engine=: engine", Car.class);
        query.setParameter(1, engine);
        List<Car> cars = query.getResultList();
        session.close();
        return cars;
    }

    public List<Car> getCarByManufacturer(String manufacturer) {
        Session session = factory.openSession();
        Query query = session.createQuery("select DISTINCT car from Car car where car.manufacturer=: manufacturer", Car.class);
        query.setParameter(1, manufacturer);
        List<Car> cars = query.getResultList();
        session.close();
        return cars;
    }
}
