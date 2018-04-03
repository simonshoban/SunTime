package exceptions;

import data.WebAddress;

public class InvalidURLException extends Exception {
    public InvalidURLException(WebAddress webAddress, String extension) {
        System.err.println("Invalid URL: " + webAddress.getDomain() + extension);
        System.err.println();
        System.err.println("Domain: " + webAddress.getDomain());
        System.err.println("Country: " + webAddress.getCountry());
        System.err.println("City: " + webAddress.getCity());
        System.err.println("Extension: " + extension);
        System.err.println();
    }
}
