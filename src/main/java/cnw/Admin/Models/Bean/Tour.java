package cnw.Admin.Models.Bean;

import java.util.ArrayList;
import java.util.Date;

public class Tour {
    private Integer Id;
    private Integer IdIntructor;
    private String Instructor;
    private Integer Price;
    private Integer ToTalTime;
    private Date TimeStart;
    private Boolean Status;
    private String Name;
    private String Description;

    public Tour(Integer id,  String instructor, Integer price, Integer toTalTime, Date timeStart, Boolean status, String name, String description) {
        Id = id;

        Instructor = instructor;
        Price = price;
        ToTalTime = toTalTime;
        TimeStart = timeStart;
        Status = status;
        Name = name;
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    private ArrayList<String > listAddress;


    public Tour() {
    }

    public Tour(Integer id, Integer idIntructor, Integer price, Integer toTalTime, Date timeStart, Boolean status) {
        Id = id;
        IdIntructor = idIntructor;
        Price = price;
        ToTalTime = toTalTime;
        TimeStart = timeStart;
        Status = status;
    }

    public Tour(Integer id, Integer idIntructor, Integer price, Integer toTalTime, Date timeStart, Boolean status, String name, String description) {
        Id = id;
        IdIntructor = idIntructor;
        Price = price;
        ToTalTime = toTalTime;
        TimeStart = timeStart;
        Status = status;
        Name = name;
        Description = description;
    }

    public Tour(Integer id, Integer price, Integer toTalTime, Date timeStart, Boolean status) {
        Id = id;
        Price = price;
        ToTalTime = toTalTime;
        TimeStart = timeStart;
        Status = status;

    }

    public Tour(Integer id, String idInstructor, Integer price, Integer toTalTime, Date timeStart, Boolean status) {
        Id = id;
        Instructor = idInstructor;
        Price = price;
        ToTalTime = toTalTime;
        TimeStart = timeStart;
        Status = status;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getIdIntructor() {
        return IdIntructor;
    }

    public void setIdIntructor(Integer idIntructor) {
        IdIntructor = idIntructor;
    }

    public String getInstructor() {
        return Instructor;
    }

    public void setInstructor(String instructor) {
        Instructor = instructor;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public Integer getToTalTime() {
        return ToTalTime;
    }

    public void setToTalTime(Integer toTalTime) {
        ToTalTime = toTalTime;
    }

    public Date getTimeStart() {
        return TimeStart;
    }

    public void setTimeStart(Date timeStart) {
        TimeStart = timeStart;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }

    public ArrayList<String> getListAddress() {
        return listAddress;
    }

    public void setListAddress(ArrayList<String> listAddress) {
        this.listAddress = listAddress;
    }
}
