import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.UUID;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;

public class Model {
    private static ArrayList<person> userInfo = new ArrayList<>();
    private static ArrayList<Property> propertyList = new ArrayList<>();

    public Model(){
        
    }
    
    public void loadUserInfo() throws IOException { 

        String file = "userData.csv";

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line = br.readLine();
            while (line != null) {

                String[] stringInfo = line.split(",");

                person tempUser = addUserInfo(stringInfo);

                userInfo.add(tempUser);
                
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            String[] firstAdminAccountDetails = { "admin" ,"iamnotadmin","Admin_1" ," ",UUID.randomUUID().toString() ,"admin"};
            Admin firstAdmin = new Admin(firstAdminAccountDetails[0],firstAdminAccountDetails[1],firstAdminAccountDetails[2],firstAdminAccountDetails[3],UUID.fromString(firstAdminAccountDetails[4]),firstAdminAccountDetails[5]);
            System.out.println("Creating the first admin account");
            
            writeToUserDataCSV(firstAdmin);
            System.out.println("Created the first admin account");
        }

    }

    public person addUserInfo(String[] data){
        return new person(data[0],data[1],data[2],data[3],UUID.fromString(data[4]),data[5]);
    }

    public boolean loginStatus(String loginUsername, String loginPass){
        boolean login = false;
        for (person p: userInfo){
            if (p.getUsername().equals(loginUsername) && p.getPassword().equals(loginPass)){

                login = true;

                break;
            }
            else{
                login = false;
            }
        }

        return login;
    }

    public String getFullname(String username){
        String fullname = "";

        for (person p: userInfo){
            if (p.getUsername().equals(username)){

                fullname = p.getFullname();
                break;
            }
            else{
                fullname = "user not found";
            }
        }

        return fullname;
    }

    public String getUsertype(String username){
        String usertype = "";

        for (person p: userInfo){
            if (p.getUsername().equals(username)){

                usertype = p.getUsertype();
                break;
            }
            else{
                usertype = "user not found";
            }
        }

        return usertype;
    }

    public String getUserID(String username){
        String userID = "";

        for (person p: userInfo){
            if (p.getUsername().equals(username)){

                userID = p.getID();
                break;
            }
            else{
                userID = "user not found";
            }
        }

        return userID;
    }

    public String getUserPassword (String username){
        String userPassword = "";

        for (person p: userInfo){
            if (p.getUsername().equals(username)){

                userPassword = p.getPassword();
                break;
            }
            else{
                userPassword = "user not found";
            }
        }

        return userPassword;
    }

    public String getUserContactNum(String username){
        String getUserContactNum = "";

        for (person p: userInfo){
            if (p.getUsername().equals(username)){

                getUserContactNum = p.getPhonenumber();
                break;
            }
            else{
                getUserContactNum = "user not found";
            }
        }

        return getUserContactNum;
    }

    public person getPersonObeject(String username){

        person tempP = new person();

        for (person p: userInfo){
            if (p.getUsername().equals(username)){
                tempP = p;
                break;
            }
        }

        return tempP;
    }

    public String[] getListofPropOwnerName(){
        String[] propOwnerNameList = new String[userInfo.size()];
        int propOwnerNameListIndex = 0;

        for(int i=0;i<userInfo.size();i++){
            if (userInfo.get(0).getUsertype().equals("property owner")){
                propOwnerNameList[propOwnerNameListIndex] = userInfo.get(i).getFullname();
                propOwnerNameListIndex++;
            }
        }

        return propOwnerNameList;
    }

    public void registerUser(String username, String password, String fullname, String contactnumber,String userType){
        if (userType.equals("tenant")){
            String[] data = {username,password,fullname,contactnumber,"tenant"};
            person tempUser = addUserInfo(data);
            userInfo.add(tempUser);
        }

        else if(userType.equals("property owner")){
            String[] data = {username,password,fullname,contactnumber,"property owner"};
            person tempUser = addUserInfo(data);
            userInfo.add(tempUser);
        }
        
    }
    
    public boolean registerUsernameValidation(String username){
        boolean registerusernameValid = false;

        if (username.length() == 0){
            registerusernameValid = false;
        }
        else{
            if (userInfo.size() == 0){
                registerusernameValid = true;
            }
            else{
                for (int i=0;i<userInfo.size();i++){
                    if (username.equals(userInfo.get(i).getUsername())){
                         registerusernameValid = false;
                         break;
                    }
                    else{
                         registerusernameValid = true;
                    }
                }
            }
        }
        
        return registerusernameValid;
    }

    public boolean registerFullnameValidation(String fullname){
        boolean registerFullnameValid = false;

        if (fullname.length() == 0){
            registerFullnameValid = false;
        }
        else{
            if (userInfo.size() == 0){
                registerFullnameValid = true;
            }
            else{
                for (int i=0;i<userInfo.size();i++){
                    if (fullname.equals(userInfo.get(i).getFullname())){
                         registerFullnameValid = false;
                         break;
                    }
                    else{
                         registerFullnameValid = true;
                    }
                }
            }
        }
        
        return registerFullnameValid;
    }

    public boolean registerPasswordValidation(String password, String rePassword){
        boolean registerPasswordValid = false;
        
        if (password.length() == 0 || rePassword.length() == 0){
            registerPasswordValid = false;
        }
        else{
            if (password.equals(rePassword)){
                registerPasswordValid = true;
            }
            else{
                registerPasswordValid = false;
            }
        }

        return registerPasswordValid;
    }

    public boolean ContactnumValidation(String contactnum){
        boolean registerContactNumValid = false;

        if (contactnum.length() == 0){
            registerContactNumValid = false;
        }
        else{
            if ((contactnum.charAt(0)) == ('0') && (contactnum.charAt(1)) == ('1')){
                if (contactnum.length() == 10 || contactnum.length() == 11){
                    registerContactNumValid = true;
                }
                else{
                    registerContactNumValid = false;
                }
            }
            else{
                registerContactNumValid = false;
            }
        }

        return registerContactNumValid;
    }

    public boolean registerInfoValidation(String[] registerInfo){
        //{registerUsername.getText(),registerFullname.getText(),registerPass.getText(),registerRePass.getText(),registerContactNum.getText()}
        boolean registerInfoValid = false;
        boolean registerusernameValid = registerUsernameValidation(registerInfo[0]);
        boolean registerFullnameValid= registerFullnameValidation(registerInfo[1]);
        boolean registerPasswordValid = registerPasswordValidation(registerInfo[2],registerInfo[3]);
        boolean registerContactnumValid = ContactnumValidation(registerInfo[4]);

        if (registerusernameValid && registerFullnameValid && registerPasswordValid && registerContactnumValid){
            registerInfoValid = true;
        }
        else{
            registerInfoValid = false;

            if (registerusernameValid == false){
                Alert alert = new Alert(AlertType.ERROR,"Invalid Username, Please try again!");
                alert.show();
            }
            
            if(registerFullnameValid == false){
                Alert alert = new Alert(AlertType.ERROR,"Invalid Fullname, Please try again!");
                alert.show();
            }

            if(registerPasswordValid == false){
                Alert alert = new Alert(AlertType.ERROR,"Invalid Password, Please try again!");
                alert.show();
            }

            if(registerContactnumValid == false){
                Alert alert = new Alert(AlertType.ERROR,"Invalid Contact Number, Please try again!");
                alert.show();
            }
        }

        return registerInfoValid;
    }

    public void writeToUserDataCSV(person p) throws IOException{
        FileWriter fileWriter = new FileWriter(new File("userData.csv"));
        userInfo.add(p);

        for (person u : userInfo) {
            fileWriter.write(u.toCSVFormat());
            fileWriter.write("\n");
        }
        fileWriter.close();
    }

    public void loadPropertyList() throws IOException {
        String file = "propertyList.csv";

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line = br.readLine();
            while (line != null) {

                String[] stringInfo = line.split(",");
                //7 Jalan Durian,2000,0,Apartment,Leong Xin Nan,0102529375,800000001,false,false
                Property property = new Property.propertyBuilder(Long.parseLong(stringInfo[6])).projectName(stringInfo[0]).propertySize(Long.parseLong(stringInfo[1])).rentalRate(Long.parseLong(stringInfo[2]))
                                    .propertyType(stringInfo[3]).propertyOwner(stringInfo[4]).contactNum(stringInfo[5]).hiddenStatus(Boolean.parseBoolean(stringInfo[7])).rentStatus(Boolean.parseBoolean(stringInfo[8]))
                                    .build();
                propertyList.add(property);
                
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Preloading property list");
            Property propertytoWrite1 = new Property.propertyBuilder(Globals.idGen.getAndIncrement()).projectName("7 Jalan Durian").propertySize(2000)
            .propertyType("Apartment").propertyOwner("Leong Xin Nan").contactNum("0102529375").hiddenStatus(false).rentStatus(false).build();
            Property propertytoWrite2 = new Property.propertyBuilder(Globals.idGen.getAndIncrement()).projectName("30 Jalan Durian").propertySize(2000)
            .propertyType("Terrace").propertyOwner("Leong Xin Nan").contactNum("0102529375").hiddenStatus(false).rentStatus(false).build();
            
            WriteToPropertyListCsv(propertytoWrite1);
            WriteToPropertyListCsv(propertytoWrite2);
            System.out.println("Preloading property list completed");
        }
    }

    public void printPropertyList(){
        for (Property p: propertyList){
            System.out.println(p.toString());
        }
    }

    public ArrayList<Property> getPropertyList(){
        return propertyList;
    }

    public static boolean isNumeric(String str) { 

        try {  
            Long.parseLong(str);  
            return true;
        } catch(NumberFormatException e){  
            return false;  
        }  
      }

    // Property ID Validation
    public Boolean propertyIDValidation(String propertyID){
        Boolean validPropertyID = false;

        for (Property p: propertyList){
            if (p.getPropertyID() == Long.parseLong(propertyID)){
                validPropertyID = false;
                break;
            }
            else{
                validPropertyID = true;
            }
        }

        return validPropertyID;
    }

    // Property Name Validation
    public Boolean propertyNameValidation(String propertyName){
        Boolean validPropertyName = false;
        
        //Checking if property name is duplicated
        for (Property p: propertyList){
            System.out.println(p.getProjectName().replaceAll("\\s+","") + " compare " + propertyName.replaceAll("\\s+",""));
            if (p.getProjectName().replaceAll("\\s+","").equals(propertyName.replaceAll("\\s+",""))){
                validPropertyName = false;
                break;
            }
            else{
                validPropertyName = true;
            }
        }
        
        //Checking if the property name is blank
        if (propertyName.length() == 0){
            validPropertyName = true;
        }

        return validPropertyName;
    }

    // Property Rental Rate Validation
    public Boolean propertyRentalRateValidation(String propertyRentalRate){
        Boolean validRentalRate = false;

        if (Long.parseLong(propertyRentalRate) <= 0 ){
                    validRentalRate = false;
            }
        else{
            validRentalRate = true;
        }



        return validRentalRate;
    }

    public Boolean propertySizeValidation(String propertySize){
        Boolean validPropertySize = false;

        if (Long.parseLong(propertySize) < 0 ){
                validPropertySize = false;
        }
        else{
            validPropertySize = true;
        }     

        return validPropertySize;
    }

    public Boolean addingPropertyValidation(String[] propInfoList){
        Boolean PropertyValidation = false;

        // Property Details Validation
        //      0          1           2           3           4           5            6           7           8
        //projectName,propertySize,rentalRate,propertyType, propertyOwner,contactNum,propertyID,hiddenStatus,rentStatus
        for (int i=0;i<propInfoList.length;i++){
            if(propInfoList[i] == null){
                if (propInfoList[7].equals("false")){
                    PropertyValidation = false;
                    (new Alert(AlertType.ERROR,"Incomplete Property Details can't be displayed on property listing")).show();
                }
                else{
                    PropertyValidation = true;
                }
            }
            else{
                PropertyValidation = true;
            }
        }

        return PropertyValidation;
    }

    public void WriteToPropertyListCsv(Property propertytoWrite) throws IOException{
        FileWriter fileWriter = new FileWriter(new File("PropertyList.csv"));
        propertyList.add(propertytoWrite);

        for (Property u : propertyList) {
            fileWriter.write(u.toCSVFormat());
            fileWriter.write("\n");
        }
        fileWriter.close();
    }


}
