package com.askie01.accounts.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseMessage {
    public static final String OK = "Request processed successfully";
    public static final String CREATED = "Account created successfully";
    public static final String BAD_REQUEST = "Bad request";
    public static final String NOT_FOUND = "Resource not found";
    public static final String EXPECTATION_FAILED = "Update operation failed. Please try again or contact Dev team";
    public static final String INTERNAL_SERVER_ERROR = "An error occurred. Please try again or contact Dev team";
}
