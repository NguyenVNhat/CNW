package cnw.Traveler.Exception;

public class TravelerAlreadyInTour extends RuntimeException{
    Integer travelerId;
    Integer tourId;
    public TravelerAlreadyInTour(Integer travelerId, Integer tourId) {
        super("Traveler id " + travelerId + " already in tour id " + tourId);
        this.travelerId = travelerId;
        this.tourId = tourId;
    }
    public Integer getTravelerId() {
        return travelerId;
    }
    public Integer getTourId() {
        return tourId;
    }
}
