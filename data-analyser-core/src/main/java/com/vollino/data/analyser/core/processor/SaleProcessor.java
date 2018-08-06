package com.vollino.data.analyser.core.processor;

import com.google.common.base.Preconditions;
import com.google.common.collect.Streams;
import com.vollino.data.analyser.core.model.Sale;
import com.vollino.data.analyser.core.model.SaleItem;
import com.vollino.data.analyser.core.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Bruno Vollino
 */
@Component
public class SaleProcessor implements RegistryProcessor {

    private static final String SALE_ITEM_REGEX = "(\\d+)-(\\d+)-([0-9]+\\.?[0-9]+),?";
    private static final Pattern SALE_LIST_PATTERN = Pattern.compile("\\[(" + SALE_ITEM_REGEX + ")+]");
    private static final Pattern SALE_ITEM_PATTERN = Pattern.compile(SALE_ITEM_REGEX);

    private SaleRepository saleRepository;

    @Autowired
    public SaleProcessor(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public boolean accepts(List<String> registry) {
        return registry.get(0).equals("003");
    }

    @Override
    public void consume(List<String> registry) {
        Sale sale = new Sale();
        sale.setId(Long.parseLong(registry.get(1)));

        List<SaleItem> items = parseSaleItems(sale.getId(), registry.get(2));
        BigDecimal totalValue = items.stream()
                .map(item -> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO.setScale(2), BigDecimal::add);

        sale.setSaleItems(items);
        sale.setSalesmanName(registry.get(3));
        sale.setTotalValue(totalValue);

        saleRepository.save(sale);
    }

    private List<SaleItem> parseSaleItems(Long saleId, String saleItemList) {
        Preconditions.checkArgument(SALE_LIST_PATTERN.matcher(saleItemList).matches(),
                "Invalid sale item list: " + saleItemList);

        return Arrays.stream(saleItemList.substring(1, saleItemList.length()-1).split(","))
                .map(item -> {
                    Matcher matcher = SALE_ITEM_PATTERN.matcher(item);
                    Preconditions.checkArgument(matcher.matches(),
                            "Invalid sale item list: " + saleItemList);
                    SaleItem saleItem = new SaleItem();
                    saleItem.setSaleId(saleId);
                    saleItem.setItemId(Long.parseLong(matcher.group(1)));
                    saleItem.setQuantity(Integer.parseInt(matcher.group(2)));
                    saleItem.setUnitPrice(new BigDecimal(matcher.group(3)).setScale(2));

                    return saleItem;
                })
                .collect(Collectors.toList());
    }
}
