package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidateISBNTest {

    @Test
    public void checkValidateISBN() {
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkISBN("0735211299");
        assertTrue(result);
    }
    @Test
    public void checkInValidateISBN() {
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkISBN("073e5211299");
        assertFalse(result);
    }
    @Test
    public void checkValidateISBN2() {
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkISBN2("978030406157");
        assertTrue(result);
    }
    @Test
    public void checkInvalidISBN2(){
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkISBN2("123456789012f");
                assertFalse(result);
    }


}
