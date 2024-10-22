package com.example;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;

public class StockManager {
    private ExternalISBNDataService webService;
    private ExternalISBNDataService dbService;

    public void setWebService(ExternalISBNDataService webService) {
        this.webService = webService;
    }
    public void setDbService(ExternalISBNDataService dbService) {
        this.dbService = dbService;
    }

    public String getLocatorCode(String isbn) {

        Book book = dbService.lookup(isbn);
       if(book == null){
           book = webService.lookup(isbn);
       }
       StringBuilder locator = new StringBuilder();
       locator.append(isbn.substring(isbn.length() - 4));
      String[] nameParts = book.getAuthor().split(" ");
      char lastName = nameParts[nameParts.length - 1].charAt(0);
     // char firstLetter = lastName.charAt(0);
      locator.append(lastName);

        locator.append(book.getTitle().split(" ").length);

        return locator.toString();
    }
}
