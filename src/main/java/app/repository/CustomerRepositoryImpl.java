package app.repository;

import app.domain.Customer;

import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public Customer save(Customer customer) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return List.of();
    }

    @Override
    public Customer findById(Long id) {
        return null;
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void removeById(Long id) {

    }
}
