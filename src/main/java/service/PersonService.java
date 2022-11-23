package service;

import model.Car;
import model.Person;
import model.Engine;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PersonService {
    Session session;
    Transaction transaction;

    public PersonService(Session session) {
        this.session = session;
        transaction = session.beginTransaction();
    }


    public Person savePerson(Person Person) {
        this.session.persist(Person);
        transaction.commit();
        return Person;
    }

    public Person updatePerson(Person Person) {
        Query query = session.createQuery("UPDATE Person p SET p.firstName =: first, p.lastName =: last, p.nationalId=:nationalId, p.car=: car WHERE p.id =: id", Person.class);
        query.setParameter(1, Person.getFirstName());
        query.setParameter(2, Person.getLastName());
        query.setParameter(3, Person.getNationalId());
        query.setParameter(4, Person.getCar());
        query.setParameter(5, Person.getId());
        transaction.commit();
        if (query.executeUpdate() > 0)
            return Person;
        return null; //can't be updated
    }

    public Person deletePerson(Person person) {
        Query query = session.createQuery("DELETE from Person p WHERE p.id =: id");
        query.setParameter(1, person.getId());
        transaction.commit();
        if (query.executeUpdate() > 0)
            return person;
        return null; //can't be deleted
    }

    public List<Person> selectAll() {
        return session.createQuery("select DISTINCT person from Person person", Person.class).getResultList();
    }

    public Person getPersonById(Long id) {
        Query query = session.createQuery("select DISTINCT person from Person person where person.id=: id", Person.class);
        query.setParameter(1, id);
        return (Person) query.getSingleResultOrNull();
    }

    public List<Person> getPeopleByFirstName(String firstName) {
        Query query = session.createQuery("select DISTINCT person from Person person where person.firstName=: firstName", Person.class);
        query.setParameter(1, firstName);
        return query.getResultList();
    }

    public List<Person> getPeopleByLastName(String lastName) {
        Query query = session.createQuery("select DISTINCT person from Person Person where person.lastName=: lastName", Person.class);
        query.setParameter(1, lastName);
        return query.getResultList();
    }

    public Person getPersonByNationalId(String nationdalId) {
        Query query = session.createQuery("select DISTINCT person from Person person where person.nationalId=: nationalId", Person.class);
        query.setParameter(1, nationdalId);
        return (Person) query.getSingleResultOrNull();
    }

    public List<Person> getPeopleByCar(Car car) {
        Query query = session.createQuery("select DISTINCT person from Person Person where person.car=: car", Person.class);
        query.setParameter(1, car);
        return query.getResultList();
    }

}
