package cnw.utils.viewdto;

import cnw.Admin.Models.Bean.Admin;
import cnw.Traveler.Model.Bean.Traveler;
import cnw.utils.filter.auth.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SummaryProfile {
    private int id;
    private String username;
    private String name;
    private Role role;
    private String avatar;
    public SummaryProfile(Admin admin) {
        this.id = admin.getId();
        this.username = admin.getUsername();
        this.name = admin.getUsername();
        this.role = Role.ADMIN;
    }
    public SummaryProfile(Traveler traveler){
        this.id = traveler.getId();
        this.username = traveler.getUsername();
        this.name = traveler.getName();
        this.role = Role.TRAVELER;
        this.avatar = traveler.getAvatar();
    }
}
