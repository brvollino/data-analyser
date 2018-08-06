package com.vollino.data.analyser.core;

import com.vollino.data.analyser.core.output.Analyser;
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

    private static final String FROM_ROUTE_PATTERN = "%s?include=.*.dat";
    private static final String TO_ROUTE_PATTERN = "%s?fileName=%s";

    @Autowired
    private DataConfigurationProperties dataConfigurationProperties;

    @Override
    public void configure() {
        String fromRoute = String.format(FROM_ROUTE_PATTERN,
                dataConfigurationProperties.inDirectory().toUri());
        String toRoute = String.format(TO_ROUTE_PATTERN,
                dataConfigurationProperties.outDirectory().toUri(),
                dataConfigurationProperties.outFile().getFileName());
        CsvDataFormat csv = new CsvDataFormat(true);
        csv.setDelimiter("ç");

        from(fromRoute)
                .onCompletion()
                    .bean(Analyser.class, "analyse")
                    .end()
                .unmarshal(csv)
                .split().body()
                .bean(ProcessorSelector.class, "consume");

        from("direct:analyse")
                .marshal(csv)
                .to(toRoute);
    }

}
