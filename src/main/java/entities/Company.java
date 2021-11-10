package entities;

public class Company {

    private String name;
    private String email;
    private String address;
    private String country;
    private String city;
    private String state;
    private String zipCode;
    private String prefixNumber;
    private String telNumber;

    public Company(String name, String email, String address, String country, String city, String state, String zipCode, String telNumber) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.country = country;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.prefixNumber = "48";
        this.telNumber = telNumber;
    }

    public Company() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPrefixNumber() {
        return prefixNumber;
    }

    public void setPrefixNumber(String prefixNumber) {
        this.prefixNumber = prefixNumber;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

}
