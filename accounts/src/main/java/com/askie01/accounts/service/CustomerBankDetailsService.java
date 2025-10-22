package com.askie01.accounts.service;

import com.askie01.accounts.dto.CustomerBankDetailsDTO;

public interface CustomerBankDetailsService {
    CustomerBankDetailsDTO getCustomerBankDetails(String mobileNumber);
}
