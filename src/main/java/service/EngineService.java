package service;

import model.Engine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class EngineService {
    public EngineService(SessionFactory factory) {
        this.factory = factory;
    }

    SessionFactory factory;

    public Engine saveEngine(Engine engine) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(engine);
        transaction.commit();
        session.close();
        return engine;
    }

    public Engine updateEngine(Engine engine) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE Engine e SET e.cylinder =: cylinder, e.type =: type WHERE e.id =: id", Engine.class);
        query.setParameter(1, engine.getCylinder());
        query.setParameter(2, engine.getType());
        query.setParameter(3, engine.getId());
        transaction.commit();
        session.close();
        if (query.executeUpdate() > 0)
            return engine;
        return null; //can't be updated
    }

    public Engine deleteEngine(Engine engine) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE from Engine e WHERE e.id =: id");
        query.setParameter(1, engine.getId());
        transaction.commit();
        session.close();
        if (query.executeUpdate() > 0)
            return engine;
        return null; //can't be deleted
    }

    public List<Engine> selectAll() {
        Session session = factory.openSession();
        List<Engine> all = session.createQuery("select DISTINCT engine from Engine engine", Engine.class).getResultList();
        session.close();
        return all;
    }

    public Engine getEngineById(Long id) {
        Session session = factory.openSession();
        Query query = session.createQuery("select DISTINCT engine from Engine engine where engine.id=: id", Engine.class);
        query.setParameter(1, id);
        Engine result = (Engine) query.getSingleResultOrNull();
        session.close();
        return result;
    }

    public List<Engine> getEngineByType(char type) {
        Session session = factory.openSession();
        Query query = session.createQuery("select DISTINCT engine from Engine engine where engine.type=: type", Engine.class);
        query.setParameter(1, type);
        List<Engine> engines = query.getResultList();
        session.close();
        return engines;
    }

    public List<Engine> getEngineByCylinder(int cylinders) {
        Session session = factory.openSession();
        Query query = session.createQuery("select DISTINCT engine from Engine engine where engine.cylinder=: cylinders", Engine.class);
        query.setParameter(1, cylinders);
        List<Engine> engines = query.getResultList();
        session.close();
        return engines;
    }

}
