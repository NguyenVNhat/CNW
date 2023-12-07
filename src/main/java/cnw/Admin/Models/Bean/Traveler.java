package cnw.Admin.Models.Bean;

import java.util.Date;

public class Traveler {
    private Integer Id;
    private String Username;
    private String Password;
    private String Name;
    private String Address;
    private Date DayBorn;
    private String Email;
    private String Phone;

    public Traveler(Integer id, String name, String address, Date dayBorn, String email, String phone) {
        Id = id;
        Name = name;
        Address = address;
        DayBorn = dayBorn;
        Email = email;
        Phone = phone;
    }

    public Traveler(Integer id, String username, String password, String name, String address, Date dayBorn, String email, String phone) {
        Id = id;
        Username = username;
        Password = password;
        Name = name;
        Address = address;
        DayBorn = dayBorn;
        Email = email;
        Phone = phone;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Date getDayBorn() {
        return DayBorn;
    }

    public void setDayBorn(Date dayBorn) {
        DayBorn = dayBorn;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
