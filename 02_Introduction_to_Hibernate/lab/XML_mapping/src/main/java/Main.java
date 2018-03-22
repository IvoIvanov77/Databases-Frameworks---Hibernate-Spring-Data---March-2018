import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.Collection;

public class Main {

    public static final String FILTERED_STUDENTS = "FROM Student WHERE name like 'P%'";

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        saveData(session);

        Student student = retrieveDataByGet(session, 2);
        System.out.println(student.getName());

        Collection<Student> students = retrieveDataByQuery(session);
        for (Student s : students) {
            System.out.println(s.getName());
        }

        Collection<Student> studentsWithCriteria = retrieveDataByCriteria(session,
                Restrictions.like("name", "%o"));
        for (Student s : studentsWithCriteria) {
            System.out.println(s.getName());
        }


        session.getTransaction().commit();

        session.close();
    }

    private static void saveData(Session session){


        Student student = new Student();
        Student studentWithName = new Student("Pesho");
        session.save(student);
        session.save(studentWithName);
        student.setName("Ivo");
        session.persist(new Student("Penka"));
    }

    private static Student retrieveDataByGet(Session session, Integer id){
        Student student = session.get(Student.class, id);
        return student;
    }

    @SuppressWarnings("unchecked")
    private static Collection<Student> retrieveDataByQuery(Session session){
        Collection<Student> filteredStudent = session.createQuery(FILTERED_STUDENTS).list();
        return filteredStudent;
    }

    @SuppressWarnings("unchecked")
    private static Collection<Student> retrieveDataByCriteria(Session session, Criterion criterion){
        Collection<Student> filteredStudent = session.createCriteria(Student.class)
                .add(criterion).list();
        return filteredStudent;
    }

}
