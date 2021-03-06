
import Domain.Student;
import Domain.TemaLab;
import Exceptions.ValidatorException;
import Repository.MemoryRepository.StudentRepo;
import Repository.MemoryRepository.TemaLabRepo;
import Validator.StudentValidator;
import Validator.TemaLabValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLOutput;

public class AssignmentTest {
    private static Logger logger = LogManager.getLogger(StudentTest.class);
    private Student student = new Student("1", "Alexandru Kispal", 934, "kispal.alex@gmail.com", "Muresan Alexandra");
    private Student studentInvalid = new Student("*3", (String)null, 67, "aa", "Ki$pal Mondi@lu");
    private StudentValidator studentValidator = new StudentValidator();
    private StudentRepo studentRepo = new StudentRepo(studentValidator);
    private TemaLab temaLabValid = new TemaLab(1," Create a jenkins job",6,5);
    private TemaLab temaLabInvalid = new TemaLab(200,"",18,19);
    private TemaLabValidator temaLabValidator = new TemaLabValidator();
    private TemaLabRepo temaLabRepo = new TemaLabRepo(temaLabValidator);

    @BeforeClass
    public static void setUp() {
        logger.info("set up...");
    }

    @AfterClass
    public static void tearDown() {
        logger.info("tear down...");
    }

    @Test
    public void nrAssigmnentTest() {
        logger.info("Check if an id has only int digits");
        Assert.assertTrue("The assignment cannot be 0 ", this.temaLabInvalid.getId() != 0);
    }

    @Test
    public void validDeadlineTest() {
        logger.info("This is a test for testing the deadline for the homework");
        Assert.assertTrue("The deadline must be greater than 0, smaller than week 14", this.temaLabValid.getTermenLimita() > 0 && this.temaLabValid.getTermenLimita() <= 14);
    }

    @Test
    public void invalidDeadlineTest() {
        logger.info("This is a test for testing the invalid deadline for the homework");
        try {
            Assert.assertFalse("The deadline must be greater than 0, smaller than week 14", this.temaLabInvalid.getTermenLimita() > 0 && this.temaLabInvalid.getTermenLimita() <= 14);
        } catch (Exception ex) { System.out.println("Not a valid deadline"); }
    }

    @Test
    public void validDeliveryWeekTest() {
        logger.info("This is a test for verifying the valid delivery week");
        Assert.assertTrue("",this.temaLabValid.getSaptammanaPredarii() <= 14 &&  this.temaLabValid.getSaptammanaPredarii() >= 1);
    }

    @Test
    public void invalidDeliveryWeekTest() {
        logger.info("This is a test for testing the invalid delivery week for the homework");
        try {
            Assert.assertFalse("The delivery week must be greater than 1, smaller than week 14", this.temaLabInvalid.getTermenLimita() > 1 && this.temaLabInvalid.getTermenLimita() <= 14);
        } catch(Exception ex){ System.out.println("The delivery week is not valid") ;}
    }

    @Test
    public void validDescriptionTest() {
        logger.info("This is a test for verifying the valid description of a lab assignment");
        Assert.assertTrue("The description cannot be an empty string",this.temaLabValid.getDescriere() != "");
    }

    @Test
    public void invalidDescriptionTest(){
        logger.info("This is a test for verifying the behaviour in case an invalid description is entered for a lab assignment");
        try {
            Assert.assertFalse("The descrition cannot be an empty string", this.temaLabInvalid.getDescriere() != "");
        }catch (Exception ex){
            System.out.println("This is not a valid descrption");
        }
    }

    @Test
    public void addAssignmentTest() throws ValidatorException {
        logger.info("This is a test for verifying the add for the assignment feature ");
        Assert.assertEquals("The repo is empty at the beginning", 0, this.temaLabRepo.size());
        this.temaLabRepo.save(this.temaLabValid);
        Assert.assertEquals("The lab assignment was not added", 1, this.temaLabRepo.size());
    }
}
