package com.vollino.data.analyser.core.output;

import com.vollino.data.analyser.core.model.Sale;
import com.vollino.data.analyser.core.model.Salesman;
import com.vollino.data.analyser.core.repository.CustomerRepository;
import com.vollino.data.analyser.core.repository.SaleRepository;
import com.vollino.data.analyser.core.repository.SalesmanRepository;
import org.apache.camel.ProducerTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * @author Bruno Vollino
 */
@RunWith(MockitoJUnitRunner.class)
public class AnalyserTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private SalesmanRepository salesmanRepository;
    @Mock
    private SaleRepository saleRepository;
    @Mock
    private ProducerTemplate producerTemplate;

    @InjectMocks
    private Analyser analyser;

    @Test
    public void shouldQueryRepositoriesAndSendResultToCamelEndpoint() {
        //given
        Map<String, Object> expectedResult = new LinkedHashMap<>();
        expectedResult.put("CustomerCount", 1L);
        expectedResult.put("SalesmanCount", 2L);
        expectedResult.put("MostExpensiveSale", 3L);
        expectedResult.put("WorstSalesman", "name");
        Sale sale = new Sale();
        sale.setId(3L);
        Salesman salesman = new Salesman();
        salesman.setName("name");

        given(customerRepository.count()).willReturn(1L);
        given(salesmanRepository.count()).willReturn(2L);
        given(saleRepository.findFirstByOrderByTotalValueDesc()).willReturn(sale);
        given(salesmanRepository.findSalesmanWithTheMinimumValueInSales()).willReturn(salesman);

        //when
        analyser.analyse();

        //then
        verify(producerTemplate).sendBody("direct:analyse", expectedResult);
    }
}