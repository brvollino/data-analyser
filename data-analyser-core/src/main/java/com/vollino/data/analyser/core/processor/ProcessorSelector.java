package com.vollino.data.analyser.core.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Bruno Vollino
 */
@Component
public class ProcessorSelector {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessorSelector.class);

    @Autowired
    private List<RegistryProcessor> registryProcessors;

    public void consume(List<String> registry) {
        RegistryProcessor processor = registryProcessors.stream()
                .filter(p -> p.accepts(registry))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unexpected registry type: " + registry));

        processor.consume(registry);
    }
}
