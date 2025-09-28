package org.askie01.accounts.mapper;

import lombok.NoArgsConstructor;
import org.askie01.accounts.dto.CustomerDTO;
import org.askie01.accounts.entity.Customer;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class CustomerMapper {
    public static CustomerDTO mapToCustomerDTO(Customer customer) {
        final CustomerDTO customerDTO = new CustomerDTO();
        map(customer, customerDTO);
        return customerDTO;
    }

    public static void map(Customer source, CustomerDTO target) {
        target.setName(source.getName());
        target.setEmail(source.getEmail());
        target.setMobileNumber(source.getMobileNumber());
    }

    public static Customer mapToCustomer(CustomerDTO customerDTO) {
        final Customer customer = new Customer();
        map(customerDTO, customer);
        return customer;
    }

    public static void map(CustomerDTO source, Customer target) {
        target.setName(source.getName());
        target.setEmail(source.getEmail());
        target.setMobileNumber(source.getMobileNumber());
    }
}
