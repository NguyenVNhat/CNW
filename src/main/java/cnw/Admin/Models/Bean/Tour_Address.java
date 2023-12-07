package cnw.Admin.Models.Bean;

public class Tour_Address {
    private Integer IdTour;
    private  Integer IdAddress;

    public Tour_Address(Integer idTour, Integer idAddress) {
        IdTour = idTour;
        IdAddress = idAddress;
    }

    public Integer getIdTour() {
        return IdTour;
    }

    public void setIdTour(Integer idTour) {
        IdTour = idTour;
    }

    public Integer getIdAddress() {
        return IdAddress;
    }

    public void setIdAddress(Integer idAddress) {
        IdAddress = idAddress;
    }
}
