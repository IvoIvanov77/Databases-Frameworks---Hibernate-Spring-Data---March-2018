import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private final static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = managerFactory.createEntityManager();

//        p02_removeObject(entityManager);
//        p3_containsEmployee(entityManager);
//        p4_employeesWithSalaryOver50K(entityManager);
//        p5_employeesFromDepartment(entityManager);
        p6_addingNewAddressAndUpdatingEmployee(entityManager);
//        p7_getEmployeeWithProject(entityManager);
//        p8_FindLatest10Projects(entityManager);
//        p9_increaseSalaries(entityManager);
//        p10removeTowns(entityManager);
//        p11_findEmployeesByFirstName(entityManager);
//        p12_employeesMaximumSalaries(entityManager);


        entityManager.close();
        managerFactory.close();

    }

    @SuppressWarnings("unchecked")
    public static void p02_removeObject(EntityManager entityManager) {

        entityManager.getTransaction().begin();

        //entityManager.createQuery("from Town t where length(t.name) > 5");

        List<Town> townList = entityManager.createQuery("from Town t").getResultList();
        for (Town town : townList) {
            if (town.getName().length() > 5) {
                entityManager.detach(town);
                town.setName(town.getName().toUpperCase());
                entityManager.merge(town);
            }
        }

//        for (Town town : townList) {
//            entityManager.merge(town);//        }

        entityManager.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    public static void p3_containsEmployee(EntityManager em) throws IOException {

        String name = reader.readLine();

        List<String> employeeNames = em.createQuery("select concat(e.firstName ,' ', e.lastName) " +
                "as full_mame from  Employee as e")
                .getResultList();

        if(employeeNames.contains(name)){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }

    }

    @SuppressWarnings("unchecked")
    public static void p4_employeesWithSalaryOver50K(EntityManager em){

        List<String> employeeNames = em.createQuery("select e.firstName " +
                "from  Employee as e where e.salary > 50000")
                .getResultList();

        System.out.println(String.join("\n", employeeNames));
    }

//    Extract all employees from the Research and Development department. Order them by salary (in ascending order),
//    then by id (in asc order). Print only their first name, last name, department name and salary.
    @SuppressWarnings("unchecked")
    public static void p5_employeesFromDepartment(EntityManager em){

        List<Employee> employees = em.createQuery("from Employee as e " +
                "where e.department.name = 'Research and Development' " +
                "order by e.salary").getResultList();

        for (Employee employee : employees) {
            System.out.println(String.format("%s %s from %s - %.2f",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getDepartment().getName(),
                    employee.getSalary()));
        }
    }

//    Create a new address with text "Vitoshka 15". Set that address to an employee with last name from user input.

    public static void p6_addingNewAddressAndUpdatingEmployee(EntityManager em) throws IOException {

        String lastName = reader.readLine();
        Address address = new Address();
        address.setText("Vitoshka 15");

        Employee employee = null;
        try {
             employee = (Employee) em.createQuery("from Employee as e where e.lastName = :name")
                    .setParameter("name", lastName)
                    .getSingleResult();
        }catch (NoResultException nre){
            em.getTransaction().rollback();
            System.out.println("No such User");
            return;
        }

//        Optional optinal = em.createQuery("from Employee as e where e.lastName = :name")
//                .setParameter("name", lastName)
//                .getResultList()
//                .stream()
//                .findFirst();
//        if(!optinal.isPresent()){
//            em.getTransaction().rollback();
//            System.out.println("No such User");
//            return;
//        }else {
//            employee = (Employee) optinal.get();
//        }


        em.getTransaction().begin();
        em.persist(address);

        em.detach(employee);
        employee.setAddress(address);
        em.merge(employee);
        em.getTransaction().commit();
    }

    public static void p7_getEmployeeWithProject(EntityManager em) throws IOException {

        int id = Integer.parseInt(reader.readLine());

        em.getTransaction().begin();
        Employee employee = em.find(Employee.class, id);

        Set<Project> projectSet = employee.getProjects();

        List<String> projectNames = projectSet.stream().
                map(Project::getName)
                .sorted()
                .collect(Collectors.toList());


        System.out.println(String.format("%s %s - %s", employee.getFirstName(), 
                employee.getLastName(), employee.getJobTitle()));
        for (String projectName : projectNames) {
            System.out.println("\t" + projectName);
        }


        em.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    public static void p8_FindLatest10Projects(EntityManager em){

        List<Project> last10Projects = em.createQuery("from Project p " +
                "order by p.startDate desc").setMaxResults(10).getResultList();

        last10Projects.sort(Comparator.comparing(Project::getName));


        for (Project project : last10Projects) {
            System.out.println(String.format("Project name: %s\n" +
                    " \tProject Description: %s\n" +
                    " \tProject Start Date: %s\n" +
                    " \tProject End Date: %s",
                    project.getName(),
                    project.getDescription(),
                    project.getStartDate(),
                    project.getEndDate()
            ));
        }
    }

    @SuppressWarnings("unchecked")
    public static void p9_increaseSalaries(EntityManager em){

        List<Employee> employees = em.createQuery("from Employee e " +
                "WHERE e.department.name in(?, ?, ?, ?)")
                .setParameter(0,"Engineering")
                .setParameter(1,"Tool Design")
                .setParameter(2,"Marketing")
                .setParameter(3,"Information Services")
                .getResultList();

//        List<Employee> employees2 = em.createQuery("from Employee e " +
//                "WHERE e.department.name = ? " +
//                "OR e.department.name = ? " +
//                "OR e.department.name = ? " +
//                "OR e.department.name = ?")
//                .setParameter(0,"Engineering")
//                .setParameter(1,"Tool Design")
//                .setParameter(2,"Marketing")
//                .setParameter(3,"Information Services")
//                .getResultList();

        em.getTransaction().begin();
        for (Employee employee : employees) {
            em.detach(employee);
            employee.setSalary(employee.getSalary().multiply(new BigDecimal(1.12)));
            em.merge(employee);
        }
        em.getTransaction().commit();

        for (Employee employee : employees) {
            Employee employeeWithNewSalary = em.find(Employee.class, employee.getId());
            System.out.println(String.format("%s %s ($%.2f)",
                    employeeWithNewSalary.getFirstName(),
                    employeeWithNewSalary.getLastName(),
                    employeeWithNewSalary.getSalary().floatValue()
            ));
        }
    }

    @SuppressWarnings("unchecked")
    public static void p10removeTowns(EntityManager em) throws IOException {

        String townName = reader.readLine();
        List<Address> addresses = em.createQuery("from Address a where a.town.name = :name")
                .setParameter("name", townName)
                .getResultList();

        em.getTransaction().begin();

        for (Address address : addresses) {
            em.createQuery("update Employee e set e.address = null  where e.address.id = :id")
                    .setParameter("id", address.getId())
                    .executeUpdate();

            em.remove(address);
            em.flush();
        }
        System.out.println(String.format("%d addresses in %s deleted", addresses.size(), townName));
        em.createQuery("delete from Town t where t.name = :name")
                .setParameter("name", townName)
                .executeUpdate();
        em.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    public static void p11_findEmployeesByFirstName(EntityManager em) throws IOException {
        String namePattern = reader.readLine();

        Query query = em.createQuery("from Employee e where e.firstName like :pattern");
        query.setParameter("pattern",  namePattern + '%');
        List<Employee> employees = query.getResultList();
        String debug = "";
        for (Employee e : employees) {
            System.out.println(String.format("%s %s - %s - ($%.2f)",
                    e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary().floatValue()
            ));
        }
    }

    @SuppressWarnings("unchecked")
    public static void p12_employeesMaximumSalaries(EntityManager em){

        Query query = em.createQuery("select e.department.name as department_name, max(e.salary) as max_salary  " +
                "from Employee e " +
                "group by e.department.name " +
                "having max(e.salary) not between 30000 and 70000");

        List<Object[]> employees = query.getResultList();
        String debug = "";
        for (Object[] o : employees) {
            System.out.println(String.format("%s - ($%s)",
                o[0], o[1]
            ));
        }

    }
}