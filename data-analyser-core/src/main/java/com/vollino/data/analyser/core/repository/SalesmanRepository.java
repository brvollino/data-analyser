package com.vollino.data.analyser.core.repository;

import com.vollino.data.analyser.core.model.Salesman;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Bruno Vollino
 */
public interface SalesmanRepository extends CrudRepository<Salesman, String> {

    @Query(value =
            "SELECT m2.* " +
            "FROM salesman m2 " +
            "INNER JOIN " +
                "(SELECT m.cpf, SUM(s.total_value) total " +
                "FROM salesman m " +
                "INNER JOIN sale s " +
                "WHERE m.name = s.salesman_name " +
                "GROUP BY m.cpf " +
                "ORDER BY total ASC " +
                "LIMIT 1) worst " +
            "WHERE m2.cpf = worst.cpf",
            nativeQuery = true)
    Salesman findSalesmanWithTheMinimumValueInSales();
}
