package service;

import model.Car;
import model.CarAgency;
import model.CarAgency;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CarAgencyService {
    SessionFactory factory;

    public CarAgencyService(SessionFactory factory) {
        this.factory = factory;
    }

    public CarAgency saveCarAgency(CarAgency carAgency) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(carAgency);
        transaction.commit();
        session.close();
        return carAgency;
    }

    public CarAgency updateCarAgency(CarAgency carAgency) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE CarAgency ca SET ca.agencyName =: agencyName, ca.cars =: cars WHERE ca.id =: id", CarAgency.class);
        query.setParameter("agencyName", carAgency.getAgencyName());
        query.setParameter("cars", carAgency.getCars());
        query.setParameter(3, carAgency.getId());
        transaction.commit();
        session.close();
        if (query.executeUpdate() > 0)
            return carAgency;
        return null; //can't be updated
    }

    public CarAgency deleteCar(CarAgency carAgency) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE from CarAgency ca WHERE ca.id =: id");
        query.setParameter(1, carAgency.getId());
        transaction.commit();
        session.close();
        if (query.executeUpdate() > 0)
            return carAgency;
        return null; //can't be deleted
    }

    public List<CarAgency> selectAll() {
        Session session = factory.openSession();
        List<CarAgency> all = session.createQuery("select DISTINCT ca from CarAgency ca", CarAgency.class).getResultList();
        session.close();
        return all;
    }

    public CarAgency getCarAgencyById(Long id) {
        Session session = factory.openSession();
        Query query = session.createQuery("select DISTINCT ca from CarAgency ca where ca.id=: id", CarAgency.class);
        query.setParameter(1, id);
        CarAgency agency = (CarAgency) query.getSingleResultOrNull();
        session.close();
        return agency;
    }

    public List<CarAgency> getCarAgencyByName(String name) {
        Session session = factory.openSession();
        Query query = session.createQuery("select DISTINCT ca from CarAgency ca where ca.agencyName=: type", CarAgency.class);
        query.setParameter(1, name);
        List<CarAgency> agencies = query.getResultList();
        session.close();
        return agencies;
    }

    public List<Car> getAgencyCars(Long id) {
        return getCarAgencyById(id).getCars();
    }
}
