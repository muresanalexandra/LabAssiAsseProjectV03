import Domain.Nota;
import Domain.Student;
import Domain.TemaLab;
import Exceptions.ValidatorException;
import Repository.MemoryRepository.NotaRepo;
import Repository.MemoryRepository.StudentRepo;
import Repository.MemoryRepository.TemaLabRepo;
import Validator.NotaValidator;
import Validator.StudentValidator;
import Validator.TemaLabValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;


public class BigBang{
    private static Logger logger = LogManager.getLogger(StudentTest.class);
    private Student student = new Student("1", "Alexandru Kispal", 934, "kispal.alex@gmail.com", "Muresan Alexandra");
    private Student studentInvalid = new Student("*3", (String)null, 67, "aa", "Ki$pal Mondi@lu");
    private StudentValidator studentValidator = new StudentValidator();
    private StudentRepo studentRepo = new StudentRepo(studentValidator);
    private TemaLab temaLabValid = new TemaLab(1," Create a jenkins job",6,5);
    private TemaLab temaLabInvalid = new TemaLab(200,"",18,19);
    private TemaLabValidator temaLabValidator = new TemaLabValidator();
    private TemaLabRepo temaLabRepo = new TemaLabRepo(temaLabValidator);
    private Nota notaValida = new Nota(1,"1234",2,10, LocalDateTime.parse("2019-01-09T11:44:44"));
    private Nota notaInvalida = new Nota(-1,"8*^",-1,-10,LocalDateTime.parse("2020-01-09T11:44:44"));
    private NotaValidator notaValidator = new NotaValidator();
    private NotaRepo notaRepo = new NotaRepo(notaValidator);


    @BeforeClass
    public static void setUp() {
        logger.info("set up...");
    }

    @AfterClass
    public static void tearDown() {
        logger.info("tear down...");
    }

    @Test
    public void addGradeTest() throws ValidatorException {
        logger.info("This is a test for verifying the add of a grade  ");
        Assert.assertEquals("The repo is empty at the beginning", 0, this.notaRepo.size());
        this.notaRepo.save(this.notaValida);
        Assert.assertEquals("The lab grade was added", 1, this.notaRepo.size());
    }

    @Test
    public void addAssignmentTest() throws ValidatorException {
        logger.info("This is a test for verifying the add for the assignment feature ");
        Assert.assertEquals("The repo is empty at the beginning", 0, this.temaLabRepo.size());
        this.temaLabRepo.save(this.temaLabValid);
        Assert.assertEquals("The lab assignment was not added", 1, this.temaLabRepo.size());
    }

    @Test
    public void addStudentTest() throws ValidatorException {
        logger.info("This is a test for testing the add feature");
        Assert.assertEquals("The repo is empty at the beginning", 0, studentRepo.size());
        studentRepo.save(student);
        Assert.assertEquals("The student was not added", 1, studentRepo.size());
    }

    @Test
    public void testAll(){
        logger.info("Test all");
        try  {
            addStudentTest();
        }catch( Exception ex) {}
        try{
            addGradeTest();
        }catch (Exception ex){}
        try{
            addAssignmentTest();
        }catch (Exception ex) {}
    }

}