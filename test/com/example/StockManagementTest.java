package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class StockManagementTest {

    @Test
    public void testCanGetACorrectLocatorCode() {

        ExternalISBNDataService testWebService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return new Book("Of Mice and Men", "John Steinbeck", "0582827647" );
            }
        };
        ExternalISBNDataService testDBService = new ExternalISBNDataService() {
            public Book lookup(String isbn) {
                return null;
            }
        };

        StockManager stockManager = new StockManager();
        stockManager.setDbService(testDBService);
        stockManager.setWebService(testWebService);
        String isbn = "0582827647";
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7647S4", locatorCode);
    }
    @Test
    public void databaseIsUsedWhenDataIsPresent(){
        ExternalISBNDataService dbService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);
        when(dbService.lookup("0582827647")).thenReturn(new Book("sample-book", "sample", "1234567890"));
        StockManager stockManager = new StockManager();
        stockManager.setDbService(dbService);
        stockManager.setWebService(webService);
        String isbn = "0582827647";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(dbService, times(1)).lookup("0582827647");
        verify(webService, never()).lookup(anyString());
    }
    @Test
    public void databaseIsNotUsedWhenDataIsNotPresent(){
        ExternalISBNDataService dbService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);
        StockManager stockManager = new StockManager();
        stockManager.setDbService(dbService);
        stockManager.setWebService(webService);
        String isbn = "0582827647";
        when(dbService.lookup(isbn)).thenReturn(null);
        when(webService.lookup(isbn)).thenReturn( new Book("sample-book", "sample", "1234567890"));
        String locatorCode = stockManager.getLocatorCode(isbn);
        verify(dbService, times(1)).lookup(isbn);
        verify(webService, times(1)).lookup(isbn);

    }
}
