package com.example.demo.terminal;


import com.example.demo.dtos.EmployeeDto;
import com.example.demo.dtos.ManagerDto;
import com.example.demo.entities.Address;
import com.example.demo.entities.Employee;
import com.example.demo.repositories.AddressRepository;
import com.example.demo.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class ConsoleRunner implements CommandLineRunner {

    private EmployeeRepository employeeRepository;
    private AddressRepository addressRepository;

    @Autowired
    public ConsoleRunner(EmployeeRepository employeeRepository, AddressRepository addressRepository) {
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void run(String... strings) throws Exception {


    }

    private void advancedMapping(){
        List<Employee> employeeList = this.employeeRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();


        List<ManagerDto> result = employeeList.stream().map(employee ->
            modelMapper.map(employee, ManagerDto.class)).collect(Collectors.toList());

        result.forEach(managerDto -> {
            System.out.println(String.format("%s %s | %d %s",
                    managerDto.getFirstName(),
                    managerDto.getLastName(),
                    managerDto.getEmployees().size(),
                    managerDto.getClass().getSimpleName()));
            managerDto.getEmployees().forEach(employeeDto -> {
                System.out.println(String.format("  - %s %s %f %s",
                        employeeDto.getFirstName(),
                        employeeDto.getLastName(),
                        employeeDto.getSalary().floatValue(),
                        employeeDto.getClass().getSimpleName()));
            });

        });

    }

//    Use Hibernate to create and insert into a database list of employees that each employee
// has first name, last name, salary, birthday, address and manager (another employee).
// Transform to EmployeeDto (first name, last name, salary, manager’s last name) those employees
// who are born before 1990. Order them by salary descending and print them on the console.

    private void projection() throws ParseException {
        List<Employee> employeeList = this.employeeRepository.findAllByBirthdayBeforeOrderBySalaryDesc(
                new SimpleDateFormat("yyyy").parse("1990")
        );

        ModelMapper modelMapper = new ModelMapper();
        TypeMap<Employee, EmployeeDto> typeMap = modelMapper.createTypeMap(Employee.class, EmployeeDto.class);
        typeMap.addMappings(m -> m.map(s -> s.getManager().getLastName(), EmployeeDto::setManagerLastName));

        List<EmployeeDto> employeeDtos = employeeList.stream().
                map(typeMap::map).collect(Collectors.toList());

        employeeDtos.forEach(employeeDto ->
                System.out.println(String.format("%s %s %.2f – Manager: %s",
                        employeeDto.getFirstName(),
                        employeeDto.getLastName(),
                        employeeDto.getSalary().floatValue(),
                        employeeDto.getManagerLastName()== null ? "[no manager]"
                                : employeeDto.getManagerLastName())));
    }

    private void simpleMapping(){
        ModelMapper mapper = new ModelMapper();
        Employee employee = this.employeeRepository.findOne(2L);
        TypeMap<Employee, EmployeeDto> typeMap = mapper.createTypeMap(Employee.class, EmployeeDto.class);
        typeMap.addMappings(m -> m.map(s -> s.getAddress().getCity(), EmployeeDto::setCity));

        EmployeeDto employeeDto = typeMap.map(employee);

        System.out.println(employeeDto.getCity());
    }


    private void seedDatabase() throws ParseException {
        Employee e = new Employee();
        Address address = new Address();
        address.setCountry("Bulgaria");
        address.setCity("Varna");
        e.setFirstName("Ivaylo");
        e.setLastName("Ivanov");
        e.setAddress(address);
        e.setBirthday(new SimpleDateFormat("yy-MM-dd").parse("77-05-07"));

        this.addressRepository.save(address);
        this.employeeRepository.save(e);

    }



}
