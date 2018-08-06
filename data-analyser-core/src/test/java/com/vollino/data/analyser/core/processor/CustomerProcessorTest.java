package com.vollino.data.analyser.core.processor;

import com.vollino.data.analyser.core.model.Customer;
import com.vollino.data.analyser.core.repository.CustomerRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * @author Bruno Vollino
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerProcessorTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerProcessor customerProcessor;

    @Test
    public void shouldAcceptACostumerRegistryForProcessing() {
        //given
        String customerType = "002";
        List<String> registry = Lists.newArrayList(customerType);

        //when
        boolean hasAccepted = customerProcessor.accepts(registry);

        //then
        assertTrue(hasAccepted);
    }

    @Test
    public void shouldNotAcceptOtherRegistriesForProcessing() {
        //given
        String customerType = "001";
        List<String> registry = Lists.newArrayList(customerType);

        //when
        boolean hasAccepted = customerProcessor.accepts(registry);

        //then
        assertFalse(hasAccepted);
    }

    @Test
    public void shouldConsumeRegistry() {
        //given
        List<String> registry = Lists.newArrayList("002", "cnpj", "name", "businessArea");
        Customer expectedCustomer = new Customer();
        expectedCustomer.setCnpj("cnpj");
        expectedCustomer.setName("name");
        expectedCustomer.setBusinessArea("businessArea");

        //when
        customerProcessor.consume(registry);

        //then
        verify(customerRepository).save(expectedCustomer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenThereAreFewerFieldsThanExpected() {
        //given
        List<String> registry = Lists.newArrayList("002", "cnpj", "name");

        //when
        customerProcessor.consume(registry);
    }
}