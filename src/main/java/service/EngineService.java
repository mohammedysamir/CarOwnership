package service;

import model.Engine;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class EngineService {
    public EngineService(Session session) {
        this.session = session;
        transaction = session.beginTransaction();
    }

    Session session;
    Transaction transaction;

    public Engine saveEngine(Engine engine) {
        this.session.persist(engine);
        transaction.commit();
        return engine;
    }

    public Engine updateEngine(Engine engine) {
        Query query = session.createQuery("UPDATE Engine e SET e.cylinder =: cylinder, e.type =: type WHERE e.id =: id", Engine.class);
        query.setParameter(1, engine.getCylinder());
        query.setParameter(2, engine.getType());
        query.setParameter(3, engine.getId());
        transaction.commit();
        if (query.executeUpdate() > 0)
            return engine;
        return null; //can't be updated
    }

    public Engine deleteEngine(Engine engine) {
        Query query = session.createQuery("DELETE from Engine e WHERE e.id =: id");
        query.setParameter(1, engine.getId());
        transaction.commit();
        if (query.executeUpdate() > 0)
            return engine;
        return null; //can't be deleted
    }

    public List<Engine> selectAll() {
        return session.createQuery("select DISTINCT engine from Engine engine", Engine.class).getResultList();
    }

    public Engine getEngineById(Long id) {
        Query query = session.createQuery("select DISTINCT engine from Engine engine where engine.id=: id", Engine.class);
        query.setParameter(1, id);
        return (Engine) query.getSingleResultOrNull();
    }

    public List<Engine> getEngineByType(String type) {
        Query query = session.createQuery("select DISTINCT engine from Engine engine where engine.type=: type", Engine.class);
        query.setParameter(1, type);
        return query.getResultList();
    }

    public List<Engine> getEngineByCylinder(int cylinders) {
        Query query = session.createQuery("select DISTINCT engine from Engine engine where engine.cylinder=: cylinders", Engine.class);
        query.setParameter(1, cylinders);
        return query.getResultList();
    }

}
