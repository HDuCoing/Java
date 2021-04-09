package assignment1;

import java.util.Objects;

public class Address implements Cloneable{
    private String town;
    private String street;
    private String postcode;
    private String housenumber;


    public void Location(String town, String street,String postcode,String housenumber) {
        this.town = town;
        this.street = street;
        this.postcode = postcode;
        this.housenumber = housenumber;
    }
    public String getTown() {
        return town;
    }
    public String getStreet() {
        return street;
    }
    public String getPostcode() {
        return postcode;
    }
    public String getHousenumber() {
        return housenumber;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return street;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(getTown(), address.getTown()) && Objects.equals(getStreet(), address.getStreet()) && Objects.equals(getPostcode(), address.getPostcode()) && Objects.equals(getHousenumber(), address.getHousenumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTown(), getStreet(), getPostcode(), getHousenumber());
    }

    // deep clone
    // Overriding clone() method to create a deep copy of an object.

    @Override
    public Address clone() {
        Address clone = new Address();
        this.town = clone.town;
        this.postcode = clone.postcode;
        this.housenumber = clone.housenumber;
        return clone();
    }
    }



