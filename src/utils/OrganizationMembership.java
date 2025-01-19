package utils;
import enums.OrganizationRole;
import java.io.Serializable;


public class OrganizationMembership implements Serializable {
  private Organization organization;
    private OrganizationRole role;

    public OrganizationMembership(Organization organization, OrganizationRole role) {
        this.organization = organization;
        this.role = role;
    }

    @Override
    public String toString() {
        return organization.getName() + " (" + role + ")";
    }

}
