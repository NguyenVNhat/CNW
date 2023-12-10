package cnw.Admin.Models.Bean;

public class AdressDiagram {
    private String addressname;
    private Integer addresscount;

    public AdressDiagram(String addressname, Integer addresscount) {
        this.addressname = addressname;
        this.addresscount = addresscount;
    }

    public String getAddressname() {
        return addressname;
    }

    public void setAddressname(String addressname) {
        this.addressname = addressname;
    }

    public Integer getAddresscount() {
        return addresscount;
    }

    public void setAddresscount(Integer addresscount) {
        this.addresscount = addresscount;
    }
}
