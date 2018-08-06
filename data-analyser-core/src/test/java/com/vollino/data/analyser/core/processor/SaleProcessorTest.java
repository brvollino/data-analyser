package com.vollino.data.analyser.core.processor;

import com.vollino.data.analyser.core.model.Sale;
import com.vollino.data.analyser.core.model.SaleItem;
import com.vollino.data.analyser.core.repository.SaleRepository;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * @author Bruno Vollino
 */
@RunWith(MockitoJUnitRunner.class)
public class SaleProcessorTest {

    @Mock
    private SaleRepository saleRepository;

    @InjectMocks
    private SaleProcessor saleProcessor;

    @Test
    public void shouldConsume() {
        //given
        List<String> saleRegistry = Lists.newArrayList(
                "003", "10", "[1-10-100,2-30-2.50,3-40-3.10]", "Diego");
        Sale expectedSale = new Sale();
        expectedSale.setId(10L);
        expectedSale.setSaleItems(Lists.newArrayList(
                new SaleItem(10L, 1L, 10, BigDecimal.valueOf(100).setScale(2)),
                new SaleItem(10L, 2L, 30, BigDecimal.valueOf(2.5).setScale(2)),
                new SaleItem(10L, 3L, 40, BigDecimal.valueOf(3.10).setScale(2))));
        expectedSale.setSalesmanName("Diego");
        expectedSale.setTotalValue(BigDecimal.valueOf(1199).setScale(2));

        //when
        saleProcessor.consume(saleRegistry);

        //then
        verify(saleRepository).save(expectedSale);
    }
}