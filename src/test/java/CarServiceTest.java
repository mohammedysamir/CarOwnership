import model.Car;
import model.Engine;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import service.CarService;

import java.util.Objects;

public class CarServiceTest {
    static StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    static SessionFactory factory;
    static CarService carService;

    @BeforeClass
    public static void setup() {
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        carService = new CarService(factory);
        carService.saveCar(new Car("Black", "2021", "BMW", new Engine(4, 'w'), 4));
        carService.saveCar(new Car("Navy", "2015", "Tesla", new Engine(8, 'v'), 2));
        carService.saveCar(new Car("White", "2021", "Tesla", new Engine(8, 'w'), 4));
    }

    @Before
    public void setupBeforeTest() {
        factory.openSession();
    }

    @Test
    public void fetchCarTest() {
        assert carService.selectAll().size() == 3;
    }

    @Test
    public void fetchCarByColor() {
        assert carService.getCarByColor("White").size() == 1;
        assert carService.getCarByColor("Black").size() == 1;
        assert carService.getCarByColor("Navy").size() == 1;
    }

    @Test
    public void fetchCarByModel() {
        assert carService.getCarByModel("2021").size() == 2;
        assert carService.getCarByModel("2015").size() == 1;
    }

    @Test
    public void fetchCarByManufacturer() {
        assert carService.getCarByManufacturer("BMW").size() == 1;
        assert carService.getCarByManufacturer("Tesla").size() == 2;
    }

    @Test
    public void fetchCarByPassengers() {
        assert carService.getCarByPassengerNumber(2).size() == 1;
        assert carService.getCarByPassengerNumber(4).size() == 2;
    }

    @Test
    public void fetchCarById(){
        assert Objects.equals(carService.getCarById(1L).getManufacturer(), "BMW");
        assert Objects.equals(carService.getCarById(2L).getManufacturer(), "Tesla");
        assert Objects.equals(carService.getCarById(3L).getManufacturer(), "Tesla");
    }
//  enable to test engine with a car
//    @Test
//    public void fetchCarByEngine(){
//        assert carService.getCarByEngine(new Engine(4, 'w')).get(0).getId() == 1;
//    }
}
