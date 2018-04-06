package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.domain.models.Customer;
import app.domain.models.Sale;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {


    Long countByCustomer(Customer customer);

    List<Sale> findAllByCustomer(Customer customer);


}
