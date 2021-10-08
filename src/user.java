import java.util.UUID;

public class user {
    private String username;
    private String password;    
    private String fullName;
    private String phoneNumber;
    private String userType;
    private UUID ID;

    //User constructor
    public user(String username,String password, String fullName , String phoneNumber, String userType){
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
        ID = UUID.randomUUID();
    }

    public user(String username,String password, String fullName , String phoneNumber, UUID ID,String userType){
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
        ID = this.ID;
    }
        
    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getFullname(){
        return fullName;
    }

    public String getPhonenumber(){
        return phoneNumber;
    }

    public String getUsertype(){
        return userType;
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
        return (username + "," + password + "," + fullName + "," + phoneNumber + "," + userType);
    }
}
