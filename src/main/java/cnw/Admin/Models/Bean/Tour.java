package cnw.Admin.Models.Bean;

import java.util.ArrayList;
import java.util.Date;

public class Tour {
    private Integer Id;
    private String Instructor;
    private Integer Price;
    private Integer ToTalTime;
    private Date TimeStart;
    private Boolean Status;

    private ArrayList<String > listAddress;

    public String getInstructor() {
        return Instructor;
    }

    public void setInstructor(String instructor) {
        Instructor = instructor;
    }

    public ArrayList<String> getListAddress() {
        return listAddress;
    }

    public void setListAddress(ArrayList<String> listAddress) {
        this.listAddress = listAddress;
    }

    public Tour() {
    }

    public Tour(Integer id, String idInstructor, Integer price, Integer toTalTime, Date timeStart,  Boolean status) {
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

    public String getIdInstructor() {
        return Instructor;
    }

    public void setIdInstructor(String idInstructor) {
        Instructor = idInstructor;
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
}
