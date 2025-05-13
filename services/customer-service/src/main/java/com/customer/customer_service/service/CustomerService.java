package com.customer.customer_service.service;

import com.customer.customer_service.dto.CustomerRequestDTO;
import com.customer.customer_service.dto.CustomerResponseDTO;
import com.customer.customer_service.entity.Customer;
import com.customer.customer_service.exception.CustomerNotFoundException;
import com.customer.customer_service.mapper.CustomerMapper;
import com.customer.customer_service.repository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) {

        Customer customer = customerRepository.save(customerMapper.toCustomer(customerRequestDTO));
        return customerMapper.toCustomerResponseDTO(customer);

    }


    public CustomerResponseDTO updateCustomer(String id, @Valid CustomerRequestDTO customerRequestDTO) {

        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        customerMapper.fromCustomerToDTO(customerRequestDTO, customer);
        Customer updatedCustomer = customerRepository.save(customer);
        return customerMapper.toCustomerResponseDTO(updatedCustomer);
    }

    public Page<CustomerResponseDTO> getAllCustomers(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> customers = customerRepository.findAll(pageable);
        return customers.map(customerMapper::toCustomerResponseDTO);
    }

    public Boolean isCustomerExists(String id) {
        return customerRepository.findById(id).isPresent();
    }

    public CustomerResponseDTO findCustomer(String id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        return customerMapper.toCustomerResponseDTO(customer);
    }

    public void deleteCustomer(String id){
        customerRepository.deleteById(id);
    }
}
