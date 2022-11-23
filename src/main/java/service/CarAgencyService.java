package service;

import model.Car;
import model.CarAgency;
import model.CarAgency;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CarAgencyService {
    Session session;
    Transaction transaction;

    public CarAgencyService(Session session) {
        this.session = session;
        transaction = session.beginTransaction();
    }

    public CarAgency saveCarAgency(CarAgency carAgency) {
        session.persist(carAgency);
        transaction.commit();
        return carAgency;
    }

    public List<CarAgency> selectAll() {
        return session.createQuery("select DISTINCT ca from CarAgency ca", CarAgency.class).getResultList();
    }

    public CarAgency getCarAgencyById(Long id) {
        Query query = session.createQuery("select DISTINCT ca from CarAgency ca where ca.id=: id", CarAgency.class);
        query.setParameter(1, id);
        return (CarAgency) query.getSingleResultOrNull();
    }

    public List<CarAgency> getCarAgencyByName(String name) {
        Query query = session.createQuery("select DISTINCT ca from CarAgency ca where ca.agencyName=: type", CarAgency.class);
        query.setParameter(1, name);
        return query.getResultList();
    }

    public List<Car> getAgencyCars(Long id) {
        return getCarAgencyById(id).getCars();
    }
}
