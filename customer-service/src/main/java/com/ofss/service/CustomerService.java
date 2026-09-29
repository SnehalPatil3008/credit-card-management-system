package com.ofss.service;

import com.ofss.dto.CustomerRequest;
import com.ofss.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest request);

    CustomerResponse getCustomerById(Long customerId);

    List<CustomerResponse> getAllCustomers();

    CustomerResponse updateCustomer(
            Long customerId,
            CustomerRequest request);

    void deleteCustomer(Long customerId);
}