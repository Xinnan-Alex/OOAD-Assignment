//PERSON CLASS IS THE SUPERCLASS OF ALL USERTYPE OBJECT IN OUR PROGRAM

//JAVA IMPORTS
import java.util.UUID;

//person class
public class person {
    private String fullName;
    private UUID ID;
    private String phoneNumber;
    private String username;
    private String password;
    private String userType;

    public person (String username, String password, String fullName , String phoneNumber, String userType) {
        this.username = username;
        this.fullName = fullName;
        ID = UUID.randomUUID();
        this.phoneNumber = phoneNumber;
        this.userType = userType;
        this.password = password;
    }

    public person(String username,String password, String fullName , String phoneNumber, UUID ID,String userType){
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
        this.ID = ID;
    }

    public person() {
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getFullName(){
        return fullName;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getUserType(){
        return userType;
    }

    public String getID(){
        return ID.toString();
    }

    public UUID getUUID(){
        return ID;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setFullname(String fullName){
        this.fullName = fullName;
    }

    public void setPhonenumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setUsertype(String userType){
        this.userType = userType;
    }

    public String toCSVFormat(){
        return (username + "," + password + "," + fullName + "," + phoneNumber + "," +  ID  + "," + userType);
    }

}