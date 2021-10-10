import java.util.UUID;
import java.util.ArrayList;

public class Admin extends person{
    //Scanner input = new Scanner(System.in);

    // ADMIN CONSTRUCTOR
    Admin(String username, String password, String fullName, String phoneNumber, UUID ID, String userType) {
        super(username, password, fullName, phoneNumber, ID, userType);
    }

    public Admin (person p){
        super(p.getUsername(), p.getPassword(), p.getFullName(), p.getPhoneNumber(), p.getUUID(), p.getUserType());
    }

    public Admin() {
    }

    //CREATE MORE ADMIN ACCOUNTS - creates admin accounts and 
    //returns it to be stored in arryalist in main.java
    public Admin createAdmin(String username, String password, String fullName, String phoneNumber, UUID ID, String userType) {
        Admin admin = new Admin(username, password, fullName, phoneNumber, ID, userType);

        return admin;
    }
        

    //REMOVE PROPERTIES
    public void removeProperties(ArrayList<Property> properties) {
        for (int i=0; i<properties.size(); i++) {
            System.out.println("Property: " + properties.get(i).toString());
        }
    }

    //ADD PROPERTIES

    //VIEW ALL PROPERTIES

    //VIEW PROPERTY BY PROPERTY TYPE

    //VIEW PROPERTY BY OWNER

    //VIEW PROPERTY BY RENTAL STATUS

    //LIST OF FACILITIES

}
