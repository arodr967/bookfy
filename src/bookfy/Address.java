/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookfy;

/**
 *
 * @author Roger Boza
 */
public class Address {
    private String name;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private int zipcode;
    private String country;

    public Address(String name, String address, String address1, String city, String state, int zipcode, String country) {
        this.name = (name == null) ? "John Smith" : name;
        this.addressLine1 = (address == null) ? "ABC Street" : address;
        this.addressLine2 = (address1 == null) ? "" : address1;
        this.city = (city == null) ? "" : city;
        this.state = (state == null) ? "" : state;
        this.zipcode = zipcode;
        this.country = (country == null) ? "" : country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public String formattedShort(){
        String output = addressLine1 + "\n";
        
        if(addressLine2 != null && !addressLine2.equalsIgnoreCase("")){
            output += addressLine2 + "\n";
        }
        
        output += city + ", " + state.toUpperCase() + ", " + country.toUpperCase();
        
        return output;
    }
    
    public String formattedLong(){
        String output = name + "\n" + addressLine1 + "\n";
        
        if(addressLine2 != null && !addressLine2.equalsIgnoreCase("")){
            output += addressLine2 + "\n";
        }
        
        output += city + ", " + state.toUpperCase() + ", " + country.toUpperCase();
        
        return output;
    }

    @Override
    public String toString() {
        return formattedLong();
    }

    
    
}
