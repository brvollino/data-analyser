package com.vollino.data.analyser.core.processor;

import com.vollino.data.analyser.core.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Bruno Vollino
 */
@Component
public class CustomerProcessor implements RegistryProcessor {

    @Override
    public boolean accepts(List<String> registry) {
        return registry.get(0).equals("002");
    }

    @Override
    public void consume(List<String> registry) {
        Customer customer = new Customer();
        customer.setCnpj(registry.get(1));
        customer.setName(registry.get(2));
        customer.setBusinessArea(registry.get(3));
    }
}
