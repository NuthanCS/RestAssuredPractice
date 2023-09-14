package pojoExample.createUserPojo;

public class AddressPojo {

    private String street;
    private String suite;
    private String city;
    private String zipcode;

    GeoPojo geoPojo;

    public AddressPojo(String street, String suite, String city, String zipcode, GeoPojo geoPojo)
    {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geoPojo = geoPojo;

    }
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public GeoPojo getGeoPojo() {
        return geoPojo;
    }

    public void setGeoPojo(GeoPojo geoPojo) {
        this.geoPojo = geoPojo;
    }




}
