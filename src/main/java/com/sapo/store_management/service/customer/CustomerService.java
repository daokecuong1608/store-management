package com.sapo.store_management.service.customer;

import com.sapo.store_management.dto.customer.CustomerDTO;
import com.sapo.store_management.mapper.CustomerMapper;
import com.sapo.store_management.model.Customer;
import com.sapo.store_management.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        if (customerRepository.existsByPhone(customerDTO.getPhone())) {
            throw new IllegalArgumentException("Phone number already exists");
        }
        Customer customer = customerMapper.toEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDTO(savedCustomer);
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO updateCustomer(Integer id, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Customer updatedCustomer = customerMapper.toEntity(customerDTO);
        updatedCustomer.setId(existingCustomer.getId()); // giữ nguyên ID cũ
        Customer savedCustomer = customerRepository.save(updatedCustomer);
        return customerMapper.toDTO(savedCustomer);
    }
    //
    // public void deleteCustomer(Long id) {
    // Customer customer = customerRepository.findById(id)
    // .orElseThrow(() -> new RuntimeException("Customer not found"));
    // customerRepository.delete(customer);
    // }

    public List<CustomerDTO> searchCustomers(String keyword) {
        return customerRepository.findByFullnameContainingOrPhoneContaining(keyword, keyword)
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteCustomer(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customerRepository.delete(customer);
    }

}
