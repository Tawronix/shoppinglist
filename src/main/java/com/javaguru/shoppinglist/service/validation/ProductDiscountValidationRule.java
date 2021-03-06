package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductDiscountValidationRule implements ValidationRule<Product> {
    public static final BigDecimal MIN_DISCOUNT = BigDecimal.ZERO;
    public static final BigDecimal MAX_DISCOUNT = new BigDecimal("100.0");
    public static final BigDecimal MIN_PRICE_FOR_DISCOUNT = new BigDecimal("20.00");

    @Override
    public void validate(Product product) {
        BigDecimal discount = product.getDiscount();
        if (discount == null) {
            throw new ValidationException("Product discount can't be empty.");
        }

        if (discount.compareTo(MIN_DISCOUNT) < 0 || discount.compareTo(MAX_DISCOUNT) > 0) {
            throw new ValidationException(
                    String.format("Product discount can't be less than %.1f%% or greater than %.1f%%.", MIN_DISCOUNT, MAX_DISCOUNT));
        }

        BigDecimal price = product.getPrice();
        if (price.compareTo(MIN_PRICE_FOR_DISCOUNT) < 0 && discount.compareTo(BigDecimal.ZERO) > 0) {
            throw new ValidationException(
                    String.format("Discount is not applicable for products with price less than %.2f", MIN_PRICE_FOR_DISCOUNT));
        }
    }
}
