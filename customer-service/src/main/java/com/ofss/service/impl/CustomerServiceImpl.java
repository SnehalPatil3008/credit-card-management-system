package com.ofss.service.impl;

import com.ofss.dto.CustomerRequest;
import com.ofss.dto.CustomerResponse;
import com.ofss.entity.Customer;
import com.ofss.exception.CustomerNotFoundException;
import com.ofss.repository.CustomerRepository;
import com.ofss.service.CustomerService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(
            CustomerRepository customerRepository) {

        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse createCustomer(
            CustomerRequest request) {

        validateUniqueCustomerData(request);

        Customer customer = new Customer();

        customer.setCustomerName(request.getCustomerName());
        customer.setEmail(request.getEmail());
        customer.setMobileNumber(request.getMobileNumber());
        customer.setPanNumber(request.getPanNumber());

        Customer savedCustomer =
                customerRepository.save(customer);

        return mapToResponse(savedCustomer);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponse getCustomerById(
            Long customerId) {

        Customer customer =
                customerRepository.findById(customerId)
                        .orElseThrow(() ->
                                new CustomerNotFoundException(
                                        "Customer not found with ID: "
                                                + customerId));

        return mapToResponse(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponse> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public CustomerResponse updateCustomer(
            Long customerId,
            CustomerRequest request) {

        Customer existingCustomer =
                customerRepository.findById(customerId)
                        .orElseThrow(() ->
                                new CustomerNotFoundException(
                                        "Customer not found with ID: "
                                                + customerId));

        validateUniqueCustomerDataForUpdate(
                customerId,
                request);

        existingCustomer.setCustomerName(
                request.getCustomerName());

        existingCustomer.setEmail(
                request.getEmail());

        existingCustomer.setMobileNumber(
                request.getMobileNumber());

        existingCustomer.setPanNumber(
                request.getPanNumber());

        Customer updatedCustomer =
                customerRepository.save(existingCustomer);

        return mapToResponse(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long customerId) {

        Customer customer =
                customerRepository.findById(customerId)
                        .orElseThrow(() ->
                                new CustomerNotFoundException(
                                        "Customer not found with ID: "
                                                + customerId));

        customerRepository.delete(customer);
    }

    private void validateUniqueCustomerData(
            CustomerRequest request) {

        if (customerRepository.existsByEmail(
                request.getEmail())) {

            throw new IllegalArgumentException(
                    "Email already exists");
        }

        if (customerRepository.existsByMobileNumber(
                request.getMobileNumber())) {

            throw new IllegalArgumentException(
                    "Mobile number already exists");
        }

        if (customerRepository.existsByPanNumber(
                request.getPanNumber())) {

            throw new IllegalArgumentException(
                    "PAN number already exists");
        }
    }

    private void validateUniqueCustomerDataForUpdate(
            Long customerId,
            CustomerRequest request) {

        customerRepository.findById(customerId);

        customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new CustomerNotFoundException(
                                "Customer not found with ID: "
                                        + customerId));

        Customer emailOwner =
                customerRepository
                        .findAll()
                        .stream()
                        .filter(customer ->
                                customer.getEmail()
                                        .equalsIgnoreCase(
                                                request.getEmail())
                                        &&
                                !customer.getCustomerId()
                                        .equals(customerId))
                        .findFirst()
                        .orElse(null);

        if (emailOwner != null) {
            throw new IllegalArgumentException(
                    "Email already exists");
        }

        Customer mobileOwner =
                customerRepository
                        .findAll()
                        .stream()
                        .filter(customer ->
                                customer.getMobileNumber()
                                        .equals(
                                                request.getMobileNumber())
                                        &&
                                !customer.getCustomerId()
                                        .equals(customerId))
                        .findFirst()
                        .orElse(null);

        if (mobileOwner != null) {
            throw new IllegalArgumentException(
                    "Mobile number already exists");
        }

        Customer panOwner =
                customerRepository
                        .findAll()
                        .stream()
                        .filter(customer ->
                                customer.getPanNumber()
                                        .equalsIgnoreCase(
                                                request.getPanNumber())
                                        &&
                                !customer.getCustomerId()
                                        .equals(customerId))
                        .findFirst()
                        .orElse(null);

        if (panOwner != null) {
            throw new IllegalArgumentException(
                    "PAN number already exists");
        }
    }

    private CustomerResponse mapToResponse(
            Customer customer) {

        return new CustomerResponse(
                customer.getCustomerId(),
                customer.getCustomerName(),
                customer.getEmail(),
                customer.getMobileNumber(),
                customer.getPanNumber()
        );
    }
}