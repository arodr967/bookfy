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
public class CreditCard {
    private String name;
    private String number;
    private String expiration;
    private String cvv;
    
    private Address billing;

    public CreditCard(String name, String number, String expiration, String cvv, Address billing) {
        this.name = name;
        this.number = number;
        this.expiration = expiration;
        this.cvv = cvv;
        this.billing = billing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Address getBilling() {
        return billing;
    }

    public void setBilling(Address billing) {
        this.billing = billing;
    }
}
