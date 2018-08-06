package com.vollino.data.analyser.core.processor;

import com.google.common.base.Preconditions;
import com.vollino.data.analyser.core.model.Customer;
import com.vollino.data.analyser.core.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Bruno Vollino
 */
@Component
public class CustomerProcessor implements RegistryProcessor {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerProcessor(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public boolean accepts(List<String> registry) {
        return registry.get(0).equals("002");
    }

    @Override
    public void consume(List<String> registry) {
        Preconditions.checkArgument(registry.size() == 4,
                "Customer registry should have 4 columns: " + registry);
        Customer customer = new Customer();
        customer.setCnpj(registry.get(1));
        customer.setName(registry.get(2));
        customer.setBusinessArea(registry.get(3));

        customerRepository.save(customer);
    }
}
