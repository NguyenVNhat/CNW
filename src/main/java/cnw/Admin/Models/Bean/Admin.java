package cnw.Admin.Models.Bean;

public class Admin {
    private Integer Id;
    private String Username ;
    private String Password;

    public Admin() {
    }

    public Admin(String username, String password) {
        Username = username;
        Password = password;
    }

    public Admin(Integer id, String adminname, String password) {
        Id = id;
        Username = adminname;
        Password = password;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getAdminname() {
        return Username;
    }

    public void setAdminname(String adminname) {
        Username = adminname;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
