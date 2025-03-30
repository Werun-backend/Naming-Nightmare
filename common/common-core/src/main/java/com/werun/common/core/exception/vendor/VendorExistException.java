package com.werun.common.core.exception.vendor;

public class VendorExistException extends RuntimeException {
    public VendorExistException(String name) {
        super("vendor "+name+" already exist");
    }
}
