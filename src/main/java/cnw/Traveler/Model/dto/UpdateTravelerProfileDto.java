package cnw.Traveler.Model.dto;

import javax.servlet.http.Part;
import java.sql.Date;
public class UpdateTravelerProfileDto {
    private Integer Id;
    private String name;
    private String address;
    private Date dayBorn;
    private String email;
    private String phone;
    private Part avatar;
    private String fileName;

    public UpdateTravelerProfileDto() {
    }

    public UpdateTravelerProfileDto(Integer id, String name, String address, Date dayBorn, String email, String phone, Part avatar , String fileName) {
        Id = id;
        this.name = name;
        this.address = address;
        this.dayBorn = dayBorn;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.fileName = fileName;
    }

    public UpdateTravelerProfileDto(String name, String address, Date dayBorn, String email, String phone, Part avatar , String fileName) {
        this.name = name;
        this.address = address;
        this.dayBorn = dayBorn;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.fileName = fileName;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
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

    public Part getAvatar() {
        return avatar;
    }

    public void setAvatar(Part avatar) {
        this.avatar = avatar;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
