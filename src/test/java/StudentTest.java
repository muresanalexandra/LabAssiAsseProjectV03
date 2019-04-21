import Domain.Student;
import Domain.TemaLab;
import Exceptions.ValidatorException;
import Repository.MemoryRepository.StudentRepo;
import Repository.MemoryRepository.TemaLabRepo;
import Validator.IValidator;
import Validator.StudentValidator;
import Validator.TemaLabValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class StudentTest {

    private static Logger logger = LogManager.getLogger(StudentTest.class);
    private Student student = new Student("1", "Alexandru Kispal", 934, "kispal.alex@gmail.com", "Muresan Alexandra");
    private Student studentInvalid = new Student("*3", null, 67, "aa", "Ki$pal Mondi@lu");
    private StudentValidator studentValidator = new StudentValidator();
    private StudentRepo studentRepo = new StudentRepo(studentValidator);

    @BeforeClass
    public static void setUp() {
        logger.info("set up...");
    }

    @AfterClass
    public static void tearDown() {
        logger.info("tear down...");
    }

    @Test
    public void validNameTest() {
        logger.info("This is valid name test");
        String validName = "Alexandru Kispal";
        Assert.assertEquals("Test failed because of:", student.getNume(), validName);
    }

    @Test
    public void invalidNameTest() {
        logger.info("Test if a name isn't null");
        try {
            Assert.assertNull("Invalid name because empty", studentInvalid.getNume());
        }catch (Exception ex) {
            System.out.println("not a valid name");
        }
    }

    @Test
    public void idTest() {
        logger.info("Check if an id has only int digits");
        Assert.assertTrue("The id contains a char value", student.getId().matches("[*0-9]"));
    }

    @Test
    public void addStudentTest() throws ValidatorException {
        logger.info("This is a test for testing the add feature");
        Assert.assertEquals("The repo is empty at the beginning", 0, studentRepo.size());
        studentRepo.save(student);
        Assert.assertEquals("The student was not added", 1, studentRepo.size());
    }

    @Test
    public void removeStudentTest() throws ValidatorException {
        logger.info("This is a test for testing the remove feature");
        addStudentTest();
        studentRepo.delete(student.getId());
        Assert.assertEquals("The repo is not empty", 0, studentRepo.size());

    }
}
