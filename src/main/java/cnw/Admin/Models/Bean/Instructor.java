package cnw.Admin.Models.Bean;

import java.util.ArrayList;

public class Instructor {
    private Integer Id;
    private String Name;
    private String Age;
    private String Email;
    private String Phone;
    private ArrayList<String> listAddress;

    public Instructor(Integer id, String name, String age, String email, String phone) {
        Id = id;
        Name = name;
        Age = age;
        Email = email;
        Phone = phone;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
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

    public ArrayList<String> getListAddress() {
        return listAddress;
    }

    public void setListAddress(ArrayList<String> listAddress) {
        this.listAddress = listAddress;
    }
}
