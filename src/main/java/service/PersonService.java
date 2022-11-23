package service;

import model.Car;
import model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PersonService {
    SessionFactory factory;

    public PersonService(SessionFactory factory) {
        this.factory = factory;
    }


    public Person savePerson(Person Person) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(Person);
        transaction.commit();
        session.close();
        return Person;
    }

    public Person updatePerson(Person Person) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE Person p SET p.firstName =: first, p.lastName =: last, p.nationalId=:nationalId, p.car=: car WHERE p.id =: id", Person.class);
        query.setParameter("first", Person.getFirstName());
        query.setParameter("last", Person.getLastName());
        query.setParameter("nationalId", Person.getNationalId());
        query.setParameter("car", Person.getCar());
        query.setParameter("id", Person.getId());
        transaction.commit();
        session.close();
        if (query.executeUpdate() > 0)
            return Person;
        return null; //can't be updated
    }

    public Person deletePerson(Person person) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE from Person p WHERE p.id =: id");
        query.setParameter("id", person.getId());
        transaction.commit();
        session.close();
        if (query.executeUpdate() > 0)
            return person;
        return null; //can't be deleted
    }

    public List<Person> selectAll() {
        Session session = factory.openSession();
        List<Person> all = session.createQuery("select DISTINCT person from Person person", Person.class).getResultList();
        session.close();
        return all;
    }

    public Person getPersonById(Long id) {
        Session session = factory.openSession();
        Query query = session.createQuery("select DISTINCT person from Person person where person.id=: id", Person.class);
        query.setParameter("id", id);
        Person person = (Person) query.getSingleResultOrNull();
        session.close();
        return person;
    }

    public List<Person> getPeopleByFirstName(String firstName) {
        Session session = factory.openSession();
        Query query = session.createQuery("select DISTINCT person from Person person where person.firstName=: firstName", Person.class);
        query.setParameter("firstName", firstName);
        List<Person> result = query.getResultList();
        session.close();
        return result;
    }

    public List<Person> getPeopleByLastName(String lastName) {
        Session session = factory.openSession();
        Query query = session.createQuery("select DISTINCT person from Person Person where person.lastName=: lastName", Person.class);
        query.setParameter("lastName", lastName);
        List<Person> result = query.getResultList();
        session.close();
        return result;
    }

    public Person getPersonByNationalId(String nationdalId) {
        Session session = factory.openSession();
        Query query = session.createQuery("select DISTINCT person from Person person where person.nationalId=: nationalId", Person.class);
        query.setParameter("nationalId", nationdalId);
        Person perosn = (Person) query.getSingleResultOrNull();
        session.close();
        return perosn;
    }

    public List<Person> getPeopleByCar(Car car) {
        Session session = factory.openSession();
        Query query = session.createQuery("select DISTINCT person from Person Person where person.car=: car", Person.class);
        query.setParameter("car", car);
        List<Person> result = query.getResultList();
        session.close();
        return result;
    }

}
