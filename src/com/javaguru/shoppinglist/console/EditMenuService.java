package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.product.Product;
import com.javaguru.shoppinglist.product.service.ProductService;
import com.javaguru.shoppinglist.product.validation.ProductValidationException;

import java.math.BigDecimal;

public class EditMenuService {
    private final UserInput userInput = new UserInput();
    private final ProductService productService = ProductService.getInstance();
    private final Menu menu;

    private Product product;

    public EditMenuService(Menu menu) {
        this.menu = menu;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void editName() {
        String name = userInput.getString("Enter new name");
        product.setName(name);
    }

    public void editCategory() {
        String category = userInput.getString("Enter new category");
        product.setCategory(category);
    }

    public void editPrice() {
        BigDecimal price = userInput.getBigDecimal("Enter new price");
        product.setPrice(price);
    }

    public void editDiscount() {
        BigDecimal discount = userInput.getBigDecimal("Enter new discount");
        product.setDiscount(discount);
    }

    public void editDescription() {
        String description = userInput.getString("Enter new description (optional)");
        product.setDescription(description);
    }

    public void save() {
        try {
            Product updatedProduct = productService.updateProduct(product);
            System.out.printf("Product updated. { ID: %d }%n", updatedProduct.getId());
            menu.setActive(false);
        } catch (ProductValidationException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cancel() {
        menu.setActive(false);
    }
}
