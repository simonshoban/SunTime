package data;

public class WebAddress {
    private String domain;
    private String country;
    private String city;
    
    public WebAddress(String domain, String country, String city) {
        this.domain = domain;
        this.country = country;
        this.city = city;
    }
    
    public String getDomain() {
        return domain;
    }
    
    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }
}
