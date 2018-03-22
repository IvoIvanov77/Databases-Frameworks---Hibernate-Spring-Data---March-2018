import entities.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("school");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Student student = new Student();
        student.setDescription("notes");
        entityManager.persist(student);
        Student found = entityManager.find(Student.class,1);

        entityManager.detach(found);
        found.setDescription("New notes");
        entityManager.merge(found);
//        entityManager.persist(found);

        /*entityManager.detach(user);
        user.setDescription("some new description");
        entityManager.merge(user);*/

        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();

    }
}
