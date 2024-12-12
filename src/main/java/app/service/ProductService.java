package app.service;

import app.domain.Product;
import app.exeptions.ProductNotFoundException;
import app.exeptions.ProductSaveException;
import app.exeptions.ProductUpdateExeption;

import java.util.List;

public interface ProductService {

    Product save(Product product) throws ProductSaveException;
    List<Product> getAllActiveProduct();
    Product getActiveProductById(Long id) throws ProductNotFoundException;
    void updateActiveProduct(Product product)throws ProductUpdateExeption, ProductNotFoundException;
    void deleteById(Long id)throws ProductNotFoundException;
    void deleteByTitle(String title);
    void restoreById(Long id);
    int getActiveProductsNumber();
    double getActiveProductTotalCoast();
    double getActiveProductsAveragePrice();
}
