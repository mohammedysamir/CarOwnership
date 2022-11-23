import model.Car;
import model.Engine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import service.CarService;
import service.EngineService;
import service.PersonService;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        factory.openSession();
        EngineService engineService = new EngineService(factory);
        System.out.println("----------------------------Engine----------------------------");
        System.out.println("Create some engines...");
        System.out.println(engineService.saveEngine(new Engine(4, 'w')).toString());
        System.out.println(engineService.saveEngine(new Engine(4, 'v')).toString());
        System.out.println(engineService.saveEngine(new Engine(8, 'w')).toString());
        System.out.println(engineService.saveEngine(new Engine(8, 'v')).toString());
        System.out.println("Fetching created engines...");
        for (Engine engine : engineService.selectAll())
            System.out.println(engine.toString());
        System.out.println("Fetch engine with type 'v'");
        for (Engine engine : engineService.getEngineByType('v'))
            System.out.println(engine.toString());
        System.out.println("Fetch engine with type 'w'");
        for (Engine engine : engineService.getEngineByType('w'))
            System.out.println(engine.toString());
        System.out.println("Fetch engine with 4 cylinders");
        for (Engine engine : engineService.getEngineByCylinder(4))
            System.out.println(engine.toString());
        System.out.println("Fetch engine with 8 cylinders");
        for (Engine engine : engineService.getEngineByCylinder(8))
            System.out.println(engine.toString());

        System.out.println("Test get by id: 1");
        System.out.println(engineService.getEngineById(1L).toString());
        System.out.println("----------------------------------------------------");
    }
}