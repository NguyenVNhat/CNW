package cnw.Admin.Models.Bean;

import java.util.ArrayList;
import java.util.Date;

public class Travel_Tour {

    private Integer IdTour;
    private String Instructor;
    private Integer Price;
    private Integer ToTalTime;
    private Date TimeStart;
    private Boolean Status;

    private ArrayList<String > listAddress;
    private ArrayList<Traveler > listTraveler;

    public Travel_Tour(Integer idTour, String instructor, Integer price, Integer toTalTime, Date timeStart, Boolean status) {
        IdTour = idTour;
        Instructor = instructor;
        Price = price;
        ToTalTime = toTalTime;
        TimeStart = timeStart;
        Status = status;
    }

    public Integer getIdTour() {
        return IdTour;
    }

    public void setIdTour(Integer idTour) {
        IdTour = idTour;
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

    public ArrayList<Traveler> getListTraveler() {
        return listTraveler;
    }

    public void setListTraveler(ArrayList<Traveler> listTraveler) {
        this.listTraveler = listTraveler;
    }
}
