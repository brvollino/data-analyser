package com.vollino.data.analyser.core.processor;

import com.vollino.data.analyser.core.model.Customer;
import com.vollino.data.analyser.core.model.Salesman;
import com.vollino.data.analyser.core.repository.SalesmanRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

/**
 * @author Bruno Vollino
 */
@RunWith(MockitoJUnitRunner.class)
public class SalesmanProcessorTest {

    @Mock
    private SalesmanRepository salesmanRepository;

    @InjectMocks
    private SalesmanProcessor salesmanProcessor;

    @Test
    public void shouldAcceptASalesmanRegistryForProcessing() {
        //given
        String customerType = "001";
        List<String> registry = Lists.newArrayList(customerType);

        //when
        boolean hasAccepted = salesmanProcessor.accepts(registry);

        //then
        assertTrue(hasAccepted);
    }

    @Test
    public void shouldNotAcceptOtherRegistriesForProcessing() {
        //given
        String customerType = "002";
        List<String> registry = Lists.newArrayList(customerType);

        //when
        boolean hasAccepted = salesmanProcessor.accepts(registry);

        //then
        assertFalse(hasAccepted);
    }

    @Test
    public void shouldConsumeRegistry() {
        //given
        List<String> registry = Lists.newArrayList("001", "cpf", "name", "150.3300");
        Salesman expectedSalesman = new Salesman();
        expectedSalesman.setCpf("cpf");
        expectedSalesman.setName("name");
        expectedSalesman.setSalary(new BigDecimal("150.33"));

        //when
        salesmanProcessor.consume(registry);

        //then
        verify(salesmanRepository).save(expectedSalesman);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenThereAreFewerFieldsThanExpected() {
        //given
        List<String> registry = Lists.newArrayList("001", "cpf", "name");

        //when
        salesmanProcessor.consume(registry);
    }
}