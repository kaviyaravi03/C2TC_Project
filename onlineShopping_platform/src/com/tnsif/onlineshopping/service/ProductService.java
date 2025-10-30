package com.tnsif.onlineshopping.service;

import com.tnsif.onlineshopping.entity.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private static List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(int id) {
        products.removeIf(p -> p.getProductId() == id);
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product findProductById(int id) {
        for (Product p : products) {
            if (p.getProductId() == id)
                return p;
        }
        return null;
    }
}
//Test commit