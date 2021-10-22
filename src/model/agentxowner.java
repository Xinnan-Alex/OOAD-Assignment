//AGENT AND OWNER CLASS
package model;

//JAVA IMPORTS
import java.util.ArrayList;
import java.util.UUID;

//agentxowner class
public class agentxowner extends person{
    ArrayList<Property> properties = new ArrayList<Property>();

    public agentxowner(String username,String password, String fullName , String phoneNumber, UUID id,String userType) {
        super(username, password, fullName, phoneNumber , id, userType);
    }

    //ADVERTISE NEW PROPERTY
    public void advertise() { }

    //VIEW ADVERTISED PROPERTY BY OWNER
    public void viewProperties() { }

    //EDIT ADVERTISED PROPERTIES
    public void editProperty() { }

    
    
}
