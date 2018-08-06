package com.vollino.data.analyser.core.processor;

import com.google.common.base.Preconditions;
import com.vollino.data.analyser.core.model.Salesman;
import com.vollino.data.analyser.core.repository.SalesmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Bruno Vollino
 */
@Component
public class SalesmanProcessor implements RegistryProcessor {

    private final SalesmanRepository salesmanRepository;

    @Autowired
    public SalesmanProcessor(SalesmanRepository salesmanRepository) {
        this.salesmanRepository = salesmanRepository;
    }

    @Override
    public boolean accepts(List<String> registry) {
        return registry.get(0).equals("001");
    }

    @Override
    public void consume(List<String> registry) {
        Preconditions.checkArgument(registry.size() == 4,
                "Salesman registry should have 4 columns: " + registry);

        Salesman salesman = new Salesman();
        salesman.setCpf(registry.get(1));
        salesman.setName(registry.get(2));
        salesman.setSalary(new BigDecimal(registry.get(3)).setScale(2));

        salesmanRepository.save(salesman);
    }
}
