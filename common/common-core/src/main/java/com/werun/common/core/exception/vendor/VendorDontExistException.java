package com.werun.common.core.exception.vendor;

public class VendorDontExistException extends RuntimeException {
    public VendorDontExistException() {
        super("vendor don't exist");
    }
}
