package cnw.Admin.Models;

public class AdminNotFoundException extends Exception{
    String username;
    public AdminNotFoundException(String username) {
        super("Admin with username " + username + " not found");
        this.username = username;
    }
}
