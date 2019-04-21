import Domain.Nota;
import Domain.Student;
import Domain.TemaLab;
import Repository.MemoryRepository.NotaRepo;
import Repository.MemoryRepository.StudentRepo;
import Repository.MemoryRepository.TemaLabRepo;
import Validator.NotaValidator;
import Validator.StudentValidator;
import Validator.TemaLabValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class IncrementalIntegration {
    private static Logger logger = LogManager.getLogger(IncrementalIntegration.class);

    @BeforeClass
    public static void setUp() {
        logger.info("set up...");
    }

    @AfterClass
    public static void tearDown() {
        logger.info("tear down...");
    }

        @Test
        public void testAddStudent(){
            logger.info("This is a test for testing the add student feature");
            StudentValidator studentValidator=new StudentValidator();
            StudentRepo repo = new StudentRepo(studentValidator);
            Student student = new Student(
                    "123",
                    "Pop Mihai",
                    935,
                    "paie2111@scs.ubbcluj.ro",
                    "Pop Ana"
            );

            try{
                assertTrue(repo.size()==0);
                repo.save(student);
                assertTrue(repo.size()==1);
            }
            catch (Exception ex){
                assertTrue(false);
            }
        }

        @Test
        public void testAddAssignment() {
            logger.info("This is a test for testing the add assignment feature");
            TemaLabValidator labValidator=new TemaLabValidator();
            TemaLabRepo repoTema = new TemaLabRepo(labValidator);
            TemaLab tema = new TemaLab(
                    20,
                    "Configure a Jenkins job",
                    10,
                    12

            );

            try{
                assertTrue(repoTema.size()==0);
                repoTema.save(tema);
                assertTrue(repoTema.size()==1);
            }
            catch (Exception ex){
                assertTrue(false);
            }
        }

        @Test
        public void testAddStudentAddAssignment() {
            logger.info("This is a test for testing the add a student with assignment feature");
            StudentValidator studentValidator=new StudentValidator();
            StudentRepo repo = new StudentRepo(studentValidator);
            Student student = new Student(
                    "123",
                    "Pop Mihai",
                    935,
                    "paie2111@scs.ubbcluj.ro",
                    "Pop Ana"
            );


            TemaLabValidator labValidator=new TemaLabValidator();
            TemaLabRepo repoTema = new TemaLabRepo(labValidator);
            TemaLab tema = new TemaLab(
                    20,
                    "Create a Jenkins job",
                    10,
                    12

            );

            try{
                repo.save(student);
                repoTema.save(tema);
                assertTrue(true);
            }
            catch (Exception ex){
                assertTrue(false);
            }
        }

        @Test
        public void shouldAddGradeTest(){
            logger.info("This is a test for testing the add a grade for a specific student feature");
            NotaValidator nv = new NotaValidator();
            NotaRepo notaRepo = new NotaRepo(nv);

            Nota nota = new Nota(1, "123", 1, 10, null);
            try{
                notaRepo.save(nota);
                assertTrue(true);
            }catch(Exception ex){
                assertTrue(false);
            }
        }


        @Test
        public void testAddStudentAddAssignmentAddGrade() {
            logger.info("This is a test for testing the add a student with assignment and a grade feature");
            StudentValidator studentValidator=new StudentValidator();
            StudentRepo repo = new StudentRepo(studentValidator);
            Student student = new Student(
                    "123",
                    "Pop Mihai",
                    935,
                    "paie2111@scs.ubbcluj.ro",
                    "Pop Ana"
            );


            TemaLabValidator labValidator=new TemaLabValidator();
            TemaLabRepo repoTema = new TemaLabRepo(labValidator);
            TemaLab tema = new TemaLab(
                    20,
                    "Create a Jenkins job",
                    10,
                    12

            );

            NotaValidator nv = new NotaValidator();
            NotaRepo notaRepo = new NotaRepo(nv);
            Nota nota = new Nota(1, "123", 1, 10, null);

            try{
                repo.save(student);
                repoTema.save(tema);
                notaRepo.save(nota);
                assertTrue(true);
            }
            catch (Exception ex){
                assertTrue(false);
            }
        }

        @Test
        public void integrationTesting(){
            this.shouldAddGradeTest();
            this.testAddAssignment();
            this.testAddStudent();
        }
}

