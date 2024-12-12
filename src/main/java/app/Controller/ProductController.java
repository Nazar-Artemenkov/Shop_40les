package app.Controller;

import app.domain.Product;
import app.exeptions.ProductNotFoundException;
import app.exeptions.ProductSaveException;
import app.exeptions.ProductUpdateExeption;
import app.service.ProductService;

import java.util.List;

public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }
    public  Product save(String title, double price) throws ProductSaveException{
        Product product = new Product(title, price);
        return service.save(product);
    }
    public List<Product> getAllActiveProduct(){
        return service.getAllActiveProduct();
    }
    public Product getActiveProductById(Long id) throws ProductNotFoundException{
        return service.getActiveProductById(id);
    }
    public void updateActiveProduct(Long id, double price)throws ProductUpdateExeption, ProductNotFoundException{
        Product product = new Product(id, price);
        service.updateActiveProduct(product);
    }
    public void deleteById(Long id)throws ProductNotFoundException{
        service.deleteById(id);
    }
    public void deleteByTitle(String title){
        service.deleteByTitle(title);
    }
    public void restoreById(Long id){
        service.restoreById(id);
    }
    public int getActiveProductsNumber(){
        return service.getActiveProductsNumber();
    }
    public double getActiveProductTotalCoast(){
        return service.getActiveProductTotalCoast();
    }
    public double getActiveProductsAveragePrice(){
        return service.getActiveProductsAveragePrice();
    }
}
