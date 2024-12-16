package app.Controller;

import app.domain.Customer;
import app.exeptions.CustomerNotFoundException;
import app.exeptions.CustomerSaveException;
import app.exeptions.CustomerUpdateException;
import app.exeptions.ProductNotFoundException;
import app.service.CustomerService;

import java.util.List;

public class CustomerController {
    private CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

        public Customer save(String name) throws CustomerSaveException{
        Customer customer = new Customer(name);
        return service.save(customer);
        }
    public List<Customer> getAllActiveCustomers(){
        return service.getAllActiveCustomers();
    }
    public Customer getActiveCustomerById(Long id)throws CustomerNotFoundException{
        return service.getActiveCustomerById(id);
    }
   public void updateActiveCustomer(Long id, String name)throws CustomerUpdateException, CustomerNotFoundException{
        Customer customer = new Customer(id, name);
        service.updateActiveCustomer(customer);
   }
    public void deleteById(Long id)throws CustomerNotFoundException{
        service.deleteById(id);
    }
    public void deleteByName(String name){
        service.deleteByName(name);
    }
    public void restoreById(Long id){
        service.restoreById(id);
    }
    public int getActiveCustomersNumber(){
        return service.getActiveCustomersNumber();
    }
    public double getCustomersCurtTotalPrise(Long id)throws CustomerNotFoundException{
        return service.getCustomersCurtTotalPrise(id);
    }
    public double getCustomersCartAveragePrise(Long id)throws CustomerNotFoundException{
        return service.getCustomersCartAveragePrise(id);
    }
    public void addProductToCustomersCart(Long customerId, Long productId) throws CustomerNotFoundException, ProductNotFoundException{
        service.addProductToCustomersCart(customerId, productId);
    }
    public void deleteProductFromCustomersCart(Long customerId, Long productId) throws CustomerNotFoundException{
        service.deleteProductFromCustomersCart(customerId, productId);
    }
    public void clearCustomersCart(Long id)throws CustomerNotFoundException{
        service.clearCustomersCart(id);
    }
}
