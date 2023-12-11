package cnw.Traveler.Model.Bean;

import java.sql.Date;

public class Traveler {
    private Integer Id;
    private String username;
    private String password;
    private String name;
    private String address;
    private Date dayBorn;
    private String email;
    private String phone;
    private String avatar;

    public Traveler() {
    }

    public Traveler(Integer id, String username, String password, String name, String address, Date dayBorn, String email, String phone , String avatar) {
        Id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.dayBorn = dayBorn;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
    }

    public Traveler(String username, String password, String name, String address, Date dayBorn, String email, String phone , String avatar) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.dayBorn = dayBorn;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDayBorn() {
        return dayBorn;
    }

    public void setDayBorn(Date dayBorn) {
        this.dayBorn = dayBorn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
