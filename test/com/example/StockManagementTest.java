package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class StockManagementTest {
    ExternalISBNDataService testWebService;
    ExternalISBNDataService testDbService;
    StockManager stockManager;


    @BeforeEach

    public void setup(){
        testWebService = mock(ExternalISBNDataService.class);
        testDbService = mock(ExternalISBNDataService.class);

        stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDbService(testDbService);
    }

    @Test
    public void testCanGetACorrectLocatorCode() {


        when(testDbService.lookup(anyString())).thenReturn(null);
        when(testWebService.lookup(anyString())).thenReturn(new Book("Of Mice and Men", "John Steinbeck", "0582827647" ));

        String isbn = "0582827647";
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7647S4", locatorCode);


    }
    @Test
    public void databaseIsUsedWhenDataIsPresent(){

        when(testDbService.lookup("0582827647")).thenReturn(new Book("sample-book", "sample", "1234567890"));

        String isbn = "0582827647";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(testDbService, times(1)).lookup("0582827647");
        verify(testWebService, never()).lookup(anyString());
    }
    @Test
    public void databaseIsNotUsedWhenDataIsNotPresent(){

        String isbn = "0582827647";
        when(testDbService.lookup(isbn)).thenReturn(null);
        when(testWebService.lookup(isbn)).thenReturn( new Book("sample-book", "sample", "1234567890"));
        String locatorCode = stockManager.getLocatorCode(isbn);
        verify(testDbService, times(1)).lookup(isbn);
        verify(testWebService, times(1)).lookup(isbn);

    }
}
