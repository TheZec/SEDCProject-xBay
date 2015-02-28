package sptest.zec.com.xbay.Models;

/**
 * Created by Aleksandar on 10/27/2014.
 */
public class ProfileModel {

    private String Address;
    private String City;
    private boolean ConfirmedEmail;
    private String Country;
    private String Description;
    private String Email;
    private String Gender;
    private boolean IsAdmin;
    private String LastName;
    private String PhotoUrl;
    private int UserId;
    private String FirstName;

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public boolean isConfirmedEmail() {
        return ConfirmedEmail;
    }

    public void setConfirmedEmail(boolean confirmedEmail) {
        ConfirmedEmail = confirmedEmail;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public boolean isAdmin() {
        return IsAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        IsAdmin = isAdmin;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhotoUrl() {
        return PhotoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        PhotoUrl = photoUrl;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }
}

