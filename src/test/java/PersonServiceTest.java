import model.Car;
import model.Engine;
import model.Person;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import service.PersonService;

import java.util.Objects;

public class PersonServiceTest {
    static StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    static SessionFactory factory;
    static PersonService personService;

    @BeforeClass
    public static void setup() {
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        personService = new PersonService(factory);
        personService.savePerson(new Person("Mohammed","Yasser","1234",new Car("Black","2019","BMW",new Engine(8,'w'),4)));
        personService.savePerson(new Person("Leonel","Messi","10102",new Car("Black","2019","BMW",new Engine(8,'w'),4)));
        personService.savePerson(new Person("Mohammed","Mahmoud","15221",new Car("Navy","2021","Tesla",new Engine(8,'v'),2)));
    }

    @Before
    public void setupBeforeTest() {
        factory.openSession();
    }

    @Test
    public void fetchPeopleTest() {
        assert personService.selectAll().size() == 3;
    }

    @Test
    public void fetchPersonByFirstName(){
        assert personService.getPeopleByFirstName("Mohammed").size() == 2;
    }
    @Test
    public void fetchPersonByLastName(){
        assert personService.getPeopleByLastName("Messi").size() == 1;
    }
    //enable to test by car
//    @Test
//    public void fetchPersonByCar(){
//        Car car = new Car("Black","2019","BMW",new Engine(8,'w'),4);
//        assert personService.getPeopleByCar(car).size() == 2; //Mohammed and Leonel
//    }

    @Test
    public void fetchPersonByNationalId(){
        Person person = personService.getPersonByNationalId("1234");
        assert Objects.equals(person.getFirstName(), "Mohammed");
    }
}
