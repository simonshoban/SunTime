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
    private int radixLocation;
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
    public WebAddress(String domain, String city, String country) {
        capitalizedVersions = new String[2];
        this.domain = domain;
        this.city = formatString(city, 0);
        this.country = formatString(country, 1);
    }
    
    /**
     * Formats the WebAddress strings into something the Parser can use.
     * 
     * @param string - The string to format
     * @param version - 0 for city, 1 for country
     * @return - The formatted string
     */
    private String formatString(String string, int version) {
        radixLocation = -1;
        string = string.trim().toLowerCase();
        string = checkForRadix(string);
        capitalizedVersions[version] = Toolkit.capitalize(string);
        
        string = parseAndRejoin(string, version, " ");
        string = parseAndRejoin(string, version, "-");
        returnRadix(version);
        
        return string;
    }
    
    /**
     * Checks if the string contains a radix point.
     * 
     * @param string - The string to be checked
     * @return - The string without a radix point
     */
    private String checkForRadix(String string) {
        if (string.contains(".")) {
            radixLocation = string.indexOf(".");
            string = string.replaceAll("\\.", "");
        }
        
        return string;
    }
    
    /**
     * Parses the string by the parsing agent and rejoins it after creating a 
     * capitalized version of the string.
     * 
     * @param string - The string to be parsed and rejoined
     * @param version - 0 for city, 1 for country
     * @param parsingAgent - The token to parse by
     * @return - The rejoined version of the string
     */
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
     * Returns the radix point to its original position in the capitalized versions of the web address strings.
     * 
     * @param version - The capitalized version to return the radix point to
     */
    private void returnRadix(int version) {
        if (radixLocation != -1) {
            capitalizedVersions[version] = Toolkit.insertIntoStringAt(capitalizedVersions[version], ".", radixLocation);
        }
    }
    
    /**
     * Checks if this WebAddress contains data for Saint Petersburg, Russia.
     * 
     * @return true if Saint Petersburg, false otherwise
     */
    public boolean isSaintPetersburg() {
        return getCapitalizedCity().equals("Saint Petersburg") && getCapitalizedCountry().equals("Russia");
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
    
    /**
     * Gets the capitalized string of the city name.
     * 
     * @return capitalizedVersions[0]
     */
    public String getCapitalizedCity() {
        return capitalizedVersions[0];
    }
    
    /**
     * Gets the capitalized string of the country name.
     * 
     * @return capitalizedVersions[1]
     */
    public String getCapitalizedCountry() {
        return capitalizedVersions[1];
    }
}
