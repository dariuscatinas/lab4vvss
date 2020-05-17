import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Before;
import org.junit.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import java.time.LocalDate;
import java.util.UUID;

import static junit.framework.TestCase.assertTrue;

public class TopDown {
    Service service;

    @Test
    public  void  answerWithTrue(){assertTrue(true); }

    @Before
    public void setUp(){
        StudentValidator studentValidator=new StudentValidator();
        TemaValidator temaValidator=new TemaValidator();
        String fStudent="fisiere/Studenti.xml";
        String fTema = "fisiere/Teme.xml";
        String fNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepo= new StudentXMLRepo(fStudent);
        TemaXMLRepo temaXMLRepo=new TemaXMLRepo(fTema);
        NotaValidator notaValidator=new NotaValidator(studentXMLRepo,temaXMLRepo);
        NotaXMLRepo notaXMLRepo=new NotaXMLRepo(fNota);
        this.service=new Service(studentXMLRepo,studentValidator,temaXMLRepo,temaValidator,notaXMLRepo,notaValidator);

    }

    @Test
    public void addStudentTopDown(){
        Student student=new Student(UUID.randomUUID().toString(),"Cimpean Denis",931,"cimpeandenis@gmail.com");
        int studentCount=0;
        for(Student i:service.getAllStudenti()){
            studentCount++;
        }
        service.addStudent(student);

        int resultCount=0;
        for(Student i: service.getAllStudenti()){
            resultCount++;
        }
        assert resultCount==studentCount+1;
    }
    @Test
    public void addAssigmentTopDown(){
        Student student=new Student(UUID.randomUUID().toString(),"Catinas Darius",931,"cimpeandenis@gmail.com");
        int studentCount=0;
        for(Student i:service.getAllStudenti()){
            studentCount++;
        }
        service.addStudent(student);

        int resultCount=0;
        for(Student i: service.getAllStudenti()){
            resultCount++;
        }
        assert resultCount == studentCount + 1;


        Tema tema=new Tema(UUID.randomUUID().toString(),"Descriere valida",13,10);
        int count=0;
        for(Tema i:service.getAllTeme()){
            count++;
        }
        service.addTema(tema);

        int rCount=0;
        for(Tema i:service.getAllTeme()){
            rCount++;
        }
        assert rCount==count+1;

    }

    @Test
    public void addGradeTopDown(){
        Student student=new Student(UUID.randomUUID().toString(),"Catinas Darius",931,"cimpeandenis@gmail.com");
        int studentCount=0;
        for(Student i:service.getAllStudenti()){
            studentCount++;
        }
        service.addStudent(student);

        int resultCount=0;
        for(Student i: service.getAllStudenti()){
            resultCount++;
        }
        assert resultCount == studentCount + 1;


        Tema tema=new Tema(UUID.randomUUID().toString(),"Descriere valida",13,10);
        int count=0;
        for(Tema i:service.getAllTeme()){
            count++;
        }
        service.addTema(tema);

        int rCount=0;
        for(Tema i:service.getAllTeme()){
            rCount++;
        }
        assert rCount==count+1;

        Nota nota =new Nota(UUID.randomUUID().toString(),student.getID(),tema.getID(),8, LocalDate.of(2020,5,15));
        int gradeCount=0;
        for(Nota i:service.getAllNote()){
            gradeCount++;
        }

        service.addNota(nota,"notiza");
        long rgradeCount=0;
        for (Nota i:service.getAllNote()){
            rgradeCount++;
        }
        assert rgradeCount==gradeCount+1;
    }
}
