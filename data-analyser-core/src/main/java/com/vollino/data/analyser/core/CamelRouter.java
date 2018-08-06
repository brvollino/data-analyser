package com.vollino.data.analyser.core;

import com.vollino.data.analyser.core.DataConfigurationProperties;
import com.vollino.data.analyser.core.processor.ProcessorSelector;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.CsvDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Bruno Vollino
 */
@Component
public class CamelRouter extends RouteBuilder {

    private static final String FROM_ROUTE_PATTERN = "%s?noop=true&include=.*.dat";
    private static final String TO_ROUTE_PATTERN = "%s?doneFileName=${file:name.noext}.done.dat";

    @Autowired
    private DataConfigurationProperties dataConfigurationProperties;

    @Override
    public void configure() {
        String fromRoute = String.format(FROM_ROUTE_PATTERN,
                dataConfigurationProperties.inDirectory().toUri());
        String toRoute = String.format(TO_ROUTE_PATTERN,
                dataConfigurationProperties.outDirectory().toUri());
        CsvDataFormat csv = new CsvDataFormat(true);
        csv.setDelimiter("ç");

        from(fromRoute)
                .unmarshal(csv)
                .split().body()
                .bean(ProcessorSelector.class, "consume");
    }

}
