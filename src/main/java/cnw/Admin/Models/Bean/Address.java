package cnw.Admin.Models.Bean;

public class Address {
    private Integer Id;
    private String AddressName;

    public Address(Integer id, String addressName) {
        Id = id;
        AddressName = addressName;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getAddressName() {
        return AddressName;
    }

    public void setAddressName(String addressName) {
        AddressName = addressName;
    }
}
