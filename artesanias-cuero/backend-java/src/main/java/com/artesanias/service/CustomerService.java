package com.artesanias.service;
import com.artesanias.model.Customer;
import com.artesanias.repository.CustomerRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.artesanias.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer save(Customer customer){
         if (customerRepository.existsByName(customer.getName())) {
            throw new ResourceNotFoundException("el cliente ya existe");
        }
        return customerRepository.save(customer);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
    }

}
