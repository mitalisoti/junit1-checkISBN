package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class ValidateISBN {
    public boolean checkISBN(String isbn) {
        int total = 0;
        for(int i = 0; i < 10; i++) {
            // total += isbn.charAt(i) - '0';
            total += Character.getNumericValue(isbn.charAt(i)) * (10 - i);

        }
        if(total % 11 == 0){
            return true;
        } else{
            return false;
        }
    }
    public boolean checkISBN2(String isbn) {
        int total = 0;
        for(int i = 0; i < 13; i++) {
            int number = Integer.parseInt(isbn.substring(i, i + 1));
            total += (i%2 == 0) ? number : number * 3;

        }
        int sum = total/ 10;
        if(total % 10 == 0){
            return true;
        }
        return false;


    }
}




