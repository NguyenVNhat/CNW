package cnw.Traveler.Exception;

public class TravelerNotInTourYet extends RuntimeException{
    Integer travelerId;
    Integer tourId;
    public TravelerNotInTourYet(Integer travelerId, Integer tourId) {
        super("Traveler id " + travelerId + " not in tour id " + tourId + " yet");
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
