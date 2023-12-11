package cnw.Traveler.Exception;

public class TravelerCredentialNotCorrect extends Exception{
    String username;
    public TravelerCredentialNotCorrect(String username) {
        super("Username " + username + " or password is not correct");
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
}
