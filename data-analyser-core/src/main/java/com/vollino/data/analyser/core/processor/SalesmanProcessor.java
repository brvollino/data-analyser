package com.vollino.data.analyser.core.processor;

import com.vollino.data.analyser.core.model.Salesman;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Bruno Vollino
 */
@Component
public class SalesmanProcessor implements RegistryProcessor {

    @Override
    public boolean accepts(List<String> registry) {
        return registry.get(0).equals("001");
    }

    @Override
    public void consume(List<String> line) {
        Salesman salesman = new Salesman();
        salesman.setCpf(line.get(1));
        salesman.setName(line.get(2));
        salesman.setSalary(new BigDecimal(line.get(3)));
    }
}
