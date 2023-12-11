package cnw.Traveler.Exception;

public class TravelerIdNotExists extends Exception {
    Integer travelerId;

    public TravelerIdNotExists(Integer travelerId) {
        super("Traveler id " + travelerId + " not exists");
        this.travelerId = travelerId;
    }

    public Integer getTravelerId() {
        return travelerId;
    }
}
