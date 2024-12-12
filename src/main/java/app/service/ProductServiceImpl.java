package app.service;

import app.domain.Product;
import app.exeptions.ProductNotFoundException;
import app.exeptions.ProductSaveException;
import app.exeptions.ProductUpdateExeption;
import app.repository.ProductRepository;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository){
        this.repository = repository;
    }

    @Override
    public Product save(Product product) throws ProductSaveException {
        if (product == null){
            throw new ProductSaveException("Product cannot be null");
        }
        if (product.getTitle() == null ||product.getTitle().length() < 3){
            throw new ProductSaveException("Product title is incorrect");
        }
        if (product.getPrise() <= 0){
            throw new ProductSaveException("Product prise is incorrect");
        }
        product.setActive(true);
        return repository.save(product);
    }

    @Override
    public List<Product> getAllActiveProduct() {
        return repository.findAll().stream()
                .filter(x -> x.isActive())
                .toList();
    }

    @Override
    public Product getActiveProductById(Long id) throws ProductNotFoundException{
        Product product = repository.findById(id);

        if (product == null|| !product.isActive()){

            throw new ProductNotFoundException(String.format("Product with ID %d doesn't exist", id));
        }
        return product;
    }

    @Override
    public void updateActiveProduct(Product product) throws ProductUpdateExeption, ProductNotFoundException{
        if (product == null){
            throw new ProductUpdateExeption("Product cannot be null");
        }
        if (product.getPrise() <= 0){
            throw new ProductUpdateExeption("Product prise is incorrect");
        }
        Product existedProduct = getActiveProductById(product.getId());

        if (existedProduct == null){
            throw new ProductUpdateExeption("Product inactive or doesn't exist");
        }

        repository.update(product);
    }

    @Override
    public void deleteById(Long id) throws ProductNotFoundException{
        Product product = getActiveProductById(id);
        if (product != null){
            product.setActive(false);
        }
    }

    @Override
    public void deleteByTitle(String title) {
        getAllActiveProduct()
                .stream()
                .filter(x -> x.getTitle().equals(title))
                .forEach(x -> x.setActive(false));
    }

    @Override
    public void restoreById(Long id) {
        Product product = repository.findById(id);
        if (product != null){
            product.setActive(true);
        }
    }

    @Override
    public int getActiveProductsNumber() {
        return getAllActiveProduct().size();
    }

    @Override
    public double getActiveProductTotalCoast() {
        return getAllActiveProduct()
                .stream()
                .mapToDouble(x -> x.getPrise())
                .sum();
    }

    @Override
    public double getActiveProductsAveragePrice() {
        int productNumber = getActiveProductsNumber();
        if (productNumber == 0){
            return 0;
        }
        return getActiveProductTotalCoast() / productNumber;
    }
}
