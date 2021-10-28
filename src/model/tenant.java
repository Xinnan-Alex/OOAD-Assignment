//TENANT CLASS FOR TENANTS
package model;

//JAVA IMPORT
import java.util.UUID;

//tenant class
public class Tenant extends person{

    public Tenant(String username, String password, String fullName, String phoneNumber, UUID ID, String userType) {
        super(username, password, fullName, phoneNumber, ID, userType);
    }
    
    public Tenant(person p){
        super(p.getUsername(), p.getPassword(), p.getFullName(), p.getPhoneNumber(), p.getUUID(), p.getUserType());
    }
}
