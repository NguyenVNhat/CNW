package cnw.Admin.Models.Bean;

public class Admin {
    private Integer Id;
    private String Adminname ;
    private String Password;

    public Admin() {
    }

    public Admin(String adminname, String password) {
        Adminname = adminname;
        Password = password;
    }

    public Admin(Integer id, String adminname, String password) {
        Id = id;
        Adminname = adminname;
        Password = password;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getAdminname() {
        return Adminname;
    }

    public void setAdminname(String adminname) {
        Adminname = adminname;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
