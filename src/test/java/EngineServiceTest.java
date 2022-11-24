import model.Engine;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import service.EngineService;

public class EngineServiceTest {
    static StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    static SessionFactory factory;
    static EngineService engineService;

    @BeforeClass
    public static void setup() {
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        engineService = new EngineService(factory);
        engineService.saveEngine(new Engine(4, 'w'));
        engineService.saveEngine(new Engine(4, 'v'));
        engineService.saveEngine(new Engine(8, 'w'));
        engineService.saveEngine(new Engine(8, 'v'));
    }

    @Before
    public void setupBeforeTest() {
        factory.openSession();
    }

    @Test
    public void fetchEngineTest() {
        assert engineService.selectAll().size() == 4;
    }

    @Test
    public void fetchEngineOfTypeVTest() {
        assert engineService.getEngineByType('v').size() == 2;
    }

    @Test
    public void fetchEngineOfTypeWTest() {
        assert engineService.getEngineByType('w').size() == 2;
    }

    @Test
    public void fetchEngineOf8CylindersTest() {
        assert engineService.getEngineByCylinder(8).size() == 2;
    }

    @Test
    public void fetchEngineOf4CylindersTest() {
        assert engineService.getEngineByCylinder(4).size() == 2;
    }

    @Test
    public void fetchEngineById() {
        assert engineService.getEngineById(2L).getType() == 'v';
    }

}
