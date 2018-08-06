package com.vollino.data.analyser.core.output;

import com.vollino.data.analyser.core.repository.CustomerRepository;
import com.vollino.data.analyser.core.repository.SaleRepository;
import com.vollino.data.analyser.core.repository.SalesmanRepository;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Bruno Vollino
 */
@Component
public class Analyser {

    private static final Logger LOGGER = LoggerFactory.getLogger(Analyser.class);

    private CustomerRepository customerRepository;
    private SalesmanRepository salesmanRepository;
    private SaleRepository saleRepository;
    private ProducerTemplate producerTemplate;

    @Autowired
    public Analyser(
            CustomerRepository customerRepository,
            SalesmanRepository salesmanRepository,
            SaleRepository saleRepository,
            ProducerTemplate producerTemplate) {
        this.customerRepository = customerRepository;
        this.salesmanRepository = salesmanRepository;
        this.saleRepository = saleRepository;
        this.producerTemplate = producerTemplate;
    }


    public Map<String, Object> analyse() {
        LOGGER.debug("analysing");
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("CustomerCount", customerRepository.count());
        result.put("SalesmanCount", salesmanRepository.count());
        result.put("MostExpensiveSale", saleRepository.findFirstByOrderByTotalValueDesc().getId());
        result.put("WorstSalesman", salesmanRepository.findSalesmanWithTheMinimumValueInSales().getName());

        producerTemplate.sendBody("direct:analyse", result);

        return result;
    }
}
