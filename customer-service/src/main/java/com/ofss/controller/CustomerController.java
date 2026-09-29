package com.ofss.controller;

import com.ofss.dto.CustomerRequest;
import com.ofss.dto.CustomerResponse;
import com.ofss.service.CustomerService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(
            CustomerService customerService) {

        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(
            @Valid @RequestBody CustomerRequest request) {

        CustomerResponse response =
                customerService.createCustomer(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse>
    getCustomerById(
            @PathVariable Long customerId) {

        return ResponseEntity.ok(
                customerService.getCustomerById(
                        customerId));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>>
    getAllCustomers() {

        return ResponseEntity.ok(
                customerService.getAllCustomers());
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResponse>
    updateCustomer(
            @PathVariable Long customerId,
            @Valid @RequestBody CustomerRequest request) {

        return ResponseEntity.ok(
                customerService.updateCustomer(
                        customerId,
                        request));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable Long customerId) {

        customerService.deleteCustomer(customerId);

        return ResponseEntity.noContent().build();
    }
}