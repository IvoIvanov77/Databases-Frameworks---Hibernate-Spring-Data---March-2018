package app.repositories;

import app.domain.models.Customer;
import app.domain.xml_dto.p01_ordered_customers.XmlOrderedCustomerDto;
import app.domain.xml_dto.p05_total_sales_by_customer.XMLCustomerStatisticDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


//    @Query("select c.id, c.name, c.birthDate, c.youngDriver, s " +
//            "from Customer c " +
//            "inner join c.sales as s " +
//            "group by c.id " +
//            "order by c.birthDate, c.youngDriver desc")
//    List<Object[]> orderedCustomers();

    @Query("select new app.domain.xml_dto.p01_ordered_customers.XmlOrderedCustomerDto(" +
            "c.id, c.name, c.birthDate, c.youngDriver) " +
            "from Customer c " +
            "order by c.birthDate, c.youngDriver desc")
    List<XmlOrderedCustomerDto> orderedCustomers();


    /**
     * Get all customers that have bought at least 1 car and get their names,
     * bought cars count and total spent money on cars. Order the result list by
     * total spent money descending then by total bought cars again in descending order.
     * @return List of CustomerStatisticDto
     */
    @Query("select new app.domain.xml_dto.p05_total_sales_by_customer.XMLCustomerStatisticDto(" +
            "customer.name, count(distinct sale), sum(part.price)) from Customer customer " +
            "inner join  customer.sales as sale " +
            "inner join sale.car as car " +
            "inner join car.parts as part " +
            "group by customer " +
            "order by sum(part.price) desc, count(distinct sale) desc ")
    public List<XMLCustomerStatisticDto> totalSalesByCustomer();

}
