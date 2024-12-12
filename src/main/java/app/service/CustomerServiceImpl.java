package app.service;

import app.domain.Customer;
import app.domain.Product;
import app.exeptions.*;
import app.repository.CustomerRepository;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;
    private final ProductService productService;

    public CustomerServiceImpl(CustomerRepository repository, ProductService productService) {
        this.repository = repository;
        this.productService = productService;
    }

    @Override
    public Customer save(Customer customer) throws CustomerSaveException {
        if (customer == null) {
            throw new CustomerSaveException("Customer cannot be null");
        }
        if (customer.getName() == null || customer.getName().length() < 2){
            throw new CustomerSaveException("Customers name is incorrect");
        }
        customer.setActive(true);
        return repository.save(customer);
    }

    @Override
    public List<Customer> getAllActiveCustomers() {
        return repository.findAll()
                .stream()
                .filter(x -> x.isActive())
                .toList();
    }

    @Override
    public Customer getActiveCustomerById(Long id) throws CustomerNotFoundException{
        Customer customer = repository.findById(id);
        if (customer == null || !customer.isActive()){
            throw new CustomerNotFoundException(String.format("Customer with Id %d doesn't exist", id));
        }
        return customer;
    }

    @Override
    public void updateActiveCustomer(Customer customer) throws CustomerUpdateException, CustomerNotFoundException {
        if (customer == null) {
            throw new CustomerUpdateException("Customer cannot be null");
        }
        if (customer.getName() == null || customer.getName().length() < 2){
            throw new CustomerUpdateException("Customers name is incorrect");
        }

        Customer existedCustomer = getActiveCustomerById(customer.getId());

        if (existedCustomer == null){
            throw new CustomerUpdateException("Customer inactive or doesn't exist");
        }
        
        repository.update(customer);
    }

    @Override
    public void deleteById(Long id) throws CustomerNotFoundException{
        Customer customer = getActiveCustomerById(id);
        if (customer != null){
            customer.setActive(false);
        }
    }

    @Override
    public void deleteByName(String name) {
        getAllActiveCustomers()
                .stream()
                .filter(x -> x.getName().equals(name))
                .forEach(x -> x.setActive(false));
    }

    @Override
    public void restoreById(Long id) {
        Customer customer = repository.findById(id);
        if (customer != null){
            customer.setActive(true);
        }
    }

    @Override
    public int getActiveCustomersNumber() {
        return getAllActiveCustomers().size();
    }

    @Override
    public double getCustomersCurtTotalPrise(Long id) throws CustomerNotFoundException{
        return getActiveCustomerById(id).getAllActiveProductsTotalCast();

    }

    @Override
    public double getCustomersCartAveragePrise(Long id) throws CustomerNotFoundException{
        return getActiveCustomerById(id).getAllActiveProductsAveragePrise();
    }

    @Override
    public void addProductToCustomersCart(Long customerId, Long productId) throws CustomerNotFoundException, ProductNotFoundException {
        Customer customer = getActiveCustomerById(customerId);
        Product product = productService.getActiveProductById(productId);
        customer.addProduct(product);

    }

    @Override
    public void deleteProductFromCustomersCart(Long customerId, Long productId) throws CustomerNotFoundException {
        Customer customer = getActiveCustomerById(customerId);
        customer.removeProductById(productId);
    }

    @Override
    public void clearCustomersCart(Long id)throws CustomerNotFoundException {
        Customer customer = getActiveCustomerById(id);
        customer.removeAllProduct();
    }
}
