package cnw.Traveler.Model.dto;

import cnw.Admin.Models.Bean.Tour;

import java.util.List;

public class TourDto {
    private List<String> address;
    private Tour tour;

    public TourDto(List<String> address, Tour tour) {
        this.address = address;
        this.tour = tour;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
}
