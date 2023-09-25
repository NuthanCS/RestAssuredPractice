package pojo.createUser;

public class UserMainPojo {

    private String name;
    private  String username;
    private  String email;
    AddressPojo addressPojo;

    public UserMainPojo(String name, String username, String email, AddressPojo addressPojo)
    {
        this.name = name;
        this.username = username;
        this.email = email;
        this.addressPojo = addressPojo;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressPojo getAddressPojo() {
        return addressPojo;
    }

    public void setAddressPojo(AddressPojo addressPojo) {
        this.addressPojo = addressPojo;
    }

}
