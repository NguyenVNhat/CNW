package cnw.Traveler.Exception;

public class TravelerUsernameAlreadyExists extends Exception{
    String username;
    public TravelerUsernameAlreadyExists(String username) {
        super("Username " + username + " already exists");
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
}
