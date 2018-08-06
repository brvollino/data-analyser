package com.vollino.data.analyser.core.repository;

import com.vollino.data.analyser.core.model.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Bruno Vollino
 */
public interface CustomerRepository extends CrudRepository<Customer, String> {
}
