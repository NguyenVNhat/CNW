package cnw.Admin.Models.Bean;

import java.util.Date;

public class Tour {
    private Integer Id;
    private Integer IdTraveler;
    private Integer IdInstructor;
    private Integer IdAddress;
    private Integer Price;
    private Integer ToTalTime;
    private Date TimeStart;
    private Date TimeEnd;
    private Boolean Status;

    public Integer getIdTraveler() {
        return IdTraveler;
    }

    public void setIdTraveler(Integer idTraveler) {
        IdTraveler = idTraveler;
    }

    public Tour() {
    }

    public Tour(Integer id, Integer idTraveler, Integer idInstructor, Integer idAddress, Integer price, Integer toTalTime, Date timeStart, Date timeEnd, Boolean status) {
        Id = id;
        IdTraveler = idTraveler;
        IdInstructor = idInstructor;
        IdAddress = idAddress;
        Price = price;
        ToTalTime = toTalTime;
        TimeStart = timeStart;
        TimeEnd = timeEnd;
        Status = status;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getIdInstructor() {
        return IdInstructor;
    }

    public void setIdInstructor(Integer idInstructor) {
        IdInstructor = idInstructor;
    }

    public Integer getIdAddress() {
        return IdAddress;
    }

    public void setIdAddress(Integer idAddress) {
        IdAddress = idAddress;
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

    public Date getTimeEnd() {
        return TimeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        TimeEnd = timeEnd;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }
}
