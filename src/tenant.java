//TENANT CLASS FOR TENANTS

//JAVA IMPORT
import java.util.UUID;

//tenant class
public class tenant extends person{

    public tenant(String username, String password, String fullName, String phoneNumber, UUID ID, String userType) {
        super(username, password, fullName, phoneNumber, ID, userType);
    }
    
}
