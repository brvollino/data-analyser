package com.vollino.data.analyser.core.processor;

import java.util.List;

/**
 * @author Bruno Vollino
 */
public interface RegistryProcessor {
    boolean accepts(List<String> registry);

    void consume(List<String> registry);
}
