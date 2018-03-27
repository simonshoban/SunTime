package data;

import toolkit.Toolkit;

/**
 * A DTO containing the domain, country, and city of the website
 * to scrape data from.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
public class WebAddress {
    private String domain;
    private String country;
    private String city;
    private String[] capitalizedVersions;
    
    /**
     * Constructs a WebAddress object.
     * 
     * @param domain - Website domain
     * @param country - Country
     * @param city - City
     */
    public WebAddress(String domain, String country, String city) {
        capitalizedVersions = new String[2];
        this.domain = domain;
        this.country = formatString(country, 0);
        this.city = formatString(city, 1);
    }
    
    private String formatString(String string, int version) {
        string = string.trim().toLowerCase();
        capitalizedVersions[version] = Toolkit.capitalize(string);
        
        string = parseAndRejoin(string, version, " ");
        string = parseAndRejoin(string, version, "-");
        
        return string;
    }
    
    private String parseAndRejoin(String string, int version, String parsingAgent) {
        if (string.contains(parsingAgent)) {
            String[] words = string.split(parsingAgent);
            int index = 0;
            capitalizedVersions[version] = "";
            string = "";
            
            for (String word : words) {
                capitalizedVersions[version] += Toolkit.capitalize(word);
                string += word;
                if (++index != words.length) {
                    capitalizedVersions[version] += " ";
                    string += "-";
                }
            }   
        }
        
        return string;
    }
    
    /**
     * Gets the website domain.
     * 
     * @return domain as a String
     */
    public String getDomain() {
        return domain;
    }
    
    /**
     * Gets the country.
     * 
     * @return country as a String
     */
    public String getCountry() {
        return country;
    }

    /**
     * Gets the city.
     * 
     * @return city as a String
     */
    public String getCity() {
        return city;
    }
    
    public String getCapitalizedCountry() {
        return capitalizedVersions[0];
    }
    
    public String getCapitalizedCity() {
        return capitalizedVersions[1];
    }
}
