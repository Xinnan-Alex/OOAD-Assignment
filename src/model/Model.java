//Model class for mostly doing validation and returning the specific data requested by the interfaces
package model;
import main.*;

//JAVA IMPORTS
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.UUID;
import java.util.Random;

//JAVAFX IMPORTS
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;


//Model Class
public final class Model {
    public static  ObservableList<person>userInfo = FXCollections.observableArrayList();
    public static ObservableList<Property> propertyList = FXCollections.observableArrayList();

    public Model(){
        
    }
    

    //Method for loading the person's info into the program
    //Written by Leong Xin Nan
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
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Creating the first admin account");
            Admin firstAdmin = new Admin("admin","iamnotadmin","Admin_1","",UUID.randomUUID(),"admin");
            writeToUserDataCSV(firstAdmin);
            System.out.println("Created the first admin account");
            
            System.out.println("Preloading the property owner accounts");
            agentxowner preload_PropOwner1 = new agentxowner("hajiwan488", "zdahalan8083", "Haji Wan Luthfi", "0160660488", UUID.randomUUID(), "property owner");
            agentxowner preload_PropOwner2 = new agentxowner("wenchao", "9aef5cd5", "Wen Chew Zao", "0155838897", UUID.randomUUID(), "property owner");
            agentxowner preload_PropOwner3 = new agentxowner("zhonzee", "6bc5f52d", "Zhong Thee Zee", "01112562590", UUID.randomUUID(), "property owner");
            writeToUserDataCSV(preload_PropOwner1);
            writeToUserDataCSV(preload_PropOwner2);
            writeToUserDataCSV(preload_PropOwner3);
            System.out.println("Preloaded the property owner accounts");

            System.out.println("Preloading the tenant accounts");
            tenant preload_Tenant1 = new tenant("chiewpei", "db9612f8", "Chiew Thok Pei", "0152221731", UUID.randomUUID(), "tenant");
            tenant preload_Tenant2 = new tenant("hjhmarzi", "4cf3ae40", "Marlina Robani", "0193086426", UUID.randomUUID(), "tenant");
            tenant preload_Tenant3 = new tenant("rameshan", "4e39290b", "Ramesh Sivanesan", "0188404994", UUID.randomUUID(), "tenant");
            tenant preload_Tenant4 = new tenant("kangpoh", "d01d29a4", "Kang Sui Poh", "0109426448", UUID.randomUUID(), "tenant");
            tenant preload_Tenant5 = new tenant("muhamif", "33f7536e", "Muhamed Zakri Yusseri", "0154945845", UUID.randomUUID(), "tenant");
            writeToUserDataCSV(preload_Tenant1);
            writeToUserDataCSV(preload_Tenant2);
            writeToUserDataCSV(preload_Tenant3);
            writeToUserDataCSV(preload_Tenant4);
            writeToUserDataCSV(preload_Tenant5);
            System.out.println("Preloaded the property owner accounts");

        }

    }

    //Method for creating a person's object
    //Written by Leong Xin Nan
    public person addUserInfo(String[] data){
        return new person(data[0],data[1],data[2],data[3],UUID.fromString(data[4]),data[5]);
    }

    //Method for validating login status
    //Written by Leong Xin Nan
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

    //Method for getting persons's username by passing in the person's fullname
    //Written by Leong Xin Nan
    public String getUsername(String fullname){
        String username = "";

        for (person p: userInfo){
            if (p.getFullName().equals(fullname)){

                username = p.getUsername();
                break;
            }
            else{
                username = "user not found";
            }
        }

        return username;
    }

    //Method for getting person's fullname by passing in the person's username 
    //Written by Leong Xin Nan
    public String getFullname(String username){
        String fullname = "";

        for (person p: userInfo){
            if (p.getUsername().equals(username)){

                fullname = p.getFullName();
                break;
            }
            else{
                fullname = "user not found";
            }
        }

        return fullname;
    }

    //Method for getting person's usertype by passing in the person's username 
    //Written by Leong Xin Nan
    public String getUsertype(String username){
        String usertype = "";

        for (person p: userInfo){
            if (p.getUsername().equals(username)){

                usertype = p.getUserType();
                break;
            }
            else{
                usertype = "user not found";
            }
        }

        return usertype;
    }

    //Method for getting person's ID by passing in the person's username
    //Written by Leong Xin Nan
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

    //Method for getting person's password by passing in the person's username 
    //Written by Leong Xin Nan
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

    //Method for getting person's contact number by passing in the person's username 
    //Written by Leong Xin Nan
    public String getUserContactNum(String username){
        String getUserContactNum = "";

        for (person p: userInfo){
            if (p.getUsername().equals(username)){

                getUserContactNum = p.getPhoneNumber();
                break;
            }
            else{
                getUserContactNum = "user not found";
            }
        }

        return getUserContactNum;
    }

    //Method for getting person object by passing in the person's username 
    //Written by Leong Xin Nan
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

    //Method for getting the list of property owner
    //Written by Leong Xin Nan
    public String[] getListofPropOwnerName(){
        int propOwnerNameListSize = 0;

        for (person p: userInfo){
            if (p.getUserType().equals("property owner")){
                propOwnerNameListSize++;
            }
        }

        String[] propOwnerNameList = new String[propOwnerNameListSize];
        int propOwnerNameListIndex = 0;

        for (person p: userInfo){
            if (p.getUserType().equals("property owner")){
                propOwnerNameList[propOwnerNameListIndex] = p.getFullName();
                propOwnerNameListIndex++;
            }
        }

        return propOwnerNameList;
    }

    public ObservableList<person> getListofPropertyOwner(){
        ObservableList<person> propownerList = FXCollections.observableArrayList();

        for (person p: userInfo){
            if (p.getUserType().equals("property owner")){
                propownerList.add(p);
            }
        }

        return propownerList;
    }

    
    //Method for validating the username for account registration
    //Written by Leong Xin Nan
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

    //Method for validating the fullname for account registration
    //Written by Leong Xin Nan
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
                    if (fullname.equals(userInfo.get(i).getFullName())){
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

    //Method for validating the password for account registration
    //Written by Leong Xin Nan
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

    //Method for validating the contact number for account registration
    //Written by Leong Xin Nan
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

    //Method for validating the infos for account registration
    //Written by Leong Xin Nan
    public boolean registerInfoValidation(String[] registerInfo){
        
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

    //Method 1 for writing person data into the userData csv file
    //Written by Leong Xin Nan
    public void writeToUserDataCSV(person p) throws IOException{
        FileWriter fileWriter = new FileWriter(new File("userData.csv"));
        userInfo.add(p);

        for (person u : userInfo) {
            fileWriter.write(u.toCSVFormat());
            fileWriter.write("\n");
        }
        fileWriter.close();
    }

    //Method 2 for writing person data into the userData csv file
    //Written by Leong Xin Nan
    public void writeToUserDataCSV() throws IOException{
        FileWriter fileWriter = new FileWriter(new File("userData.csv"));

        for (person u : userInfo) {
            fileWriter.write(u.toCSVFormat());
            fileWriter.write("\n");
        }
        fileWriter.close();
    }

    //Method for loading property list into the program
    //Written by Leong Xin Nan
    public void loadPropertyList() throws IOException {
        String file = "propertyList.csv";

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line = br.readLine();
            while (line != null) {

                //projectName,propertySize,rentalRate,propertyType,propertyOwner,contactNum,propertyID,numofRoom,numofBathroom,facilities,hiddenStatus,rentStatus
                String[] stringInfo = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                Property property = getPropertyObjectWithID(stringInfo);
                propertyList.add(property);
                
                line = br.readLine();
            }

            Globals.setCurrentID(propertyList.get(propertyList.size()-1).getPropertyID());
        } catch (FileNotFoundException | IndexOutOfBoundsException e) {

            Random rand = new Random();

            person owner_property1 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty1 = {"\"5, Jalan 4/2S, Bandar Segambut, 78083 Umbai, Melaka\"","2000","300","Terrace",owner_property1.getFullName(),owner_property1.getPhoneNumber(),"5","4","\"Aircond,Pool,Indoor Gym,Washing Machine,Cooking Utensil\"","false","active"};
            person owner_property2 = getListofPropertyOwner().get(rand.nextInt(getListofPropertyOwner().size())); 
            String [] preloadProperty2 = {"\"8-6, Jln 5/43I, Bandar Laksamana, 89335 Kota Kinabalu, Sabah \"","1000","100","Townhouse",owner_property2.getFullName(),owner_property2.getPhoneNumber(),"2","1","\"Frontyard,Garage,Backyard,Train near by\"","false","active"};
            // String [] preloadProperty3 = {projectName,propertySize,rentalRate,propertyType,propertyOwner,contactNum,numofRoom,numofBathroom,facilities,hiddenStatus,rentStatus};
            // String [] preloadProperty4 = {projectName,propertySize,rentalRate,propertyType,propertyOwner,contactNum,numofRoom,numofBathroom,facilities,hiddenStatus,rentStatus};
            // String [] preloadProperty5 = {projectName,propertySize,rentalRate,propertyType,propertyOwner,contactNum,numofRoom,numofBathroom,facilities,hiddenStatus,rentStatus};
            
            WriteToPropertyListCsv(getPropertyObject(preloadProperty1));
            WriteToPropertyListCsv(getPropertyObject(preloadProperty2));
            // WriteToPropertyListCsv(getPropertyObject(preloadProperty3));
            // WriteToPropertyListCsv(getPropertyObject(preloadProperty4));
            // WriteToPropertyListCsv(getPropertyObject(preloadProperty5));

            System.out.println("Preloading property list completed");
        }
    }

    public void printPropertyList(){
        for (Property p: propertyList){
            System.out.println(p.toString());
        }
    }

    //Method for getting a property object
    //Written by Leong Xin Nan
    public Property getPropertyObject(String[] propertyinfo){

        String projectName =  propertyinfo[0].replace("\"", "");

        long propertySize;
        if (propertyinfo[1].equals("")){
            propertySize = 0;
        } 
        else{
            propertySize = Long.parseLong(propertyinfo[1]);
        }

        long rentalRate;
        if (propertyinfo[2].equals("")){
            rentalRate = 0;
        } 
        else{
            rentalRate = Long.parseLong(propertyinfo[2]);
        }

        String propertyType = propertyinfo[3];
        String propertyOwner = propertyinfo[4];
        String contactNum = propertyinfo[5];
        int numofRoom = Integer.parseInt(propertyinfo[6]);
        int numofBathroom = Integer.parseInt(propertyinfo[7]);
        String facilities = propertyinfo[8].replace("\"", "");

        Boolean hiddenStatus = Boolean.parseBoolean(propertyinfo[9]);

        Boolean rentStatus;
        if (propertyinfo[10]==null){
            rentStatus = false;
        } 
        else{
            if (propertyinfo[7].equals("not Active")){
                rentStatus = true;
            }
            else{
                rentStatus = false;
            }
        }
        
        
        Property returnProperty = new Property.propertyBuilder(Globals.generatePropertyID())
                                            .projectName(projectName)
                                            .propertyOwner(propertyOwner)
                                            .contactNum(contactNum)
                                            .propertySize(propertySize)
                                            .rentalRate(rentalRate)
                                            .propertyType(propertyType)
                                            .numofRoom(numofRoom)
                                            .numofBathroom(numofBathroom)
                                            .facilities(facilities)
                                            .rentStatus(rentStatus)
                                            .hiddenStatus(hiddenStatus)
                                            .build();

        return returnProperty;


    }

    //Method for getting the property list 
    //Written by Leong Xin Nan
    public ObservableList<Property> getPropertyList(){
        return propertyList;
    }

    //Method for getting the lsit of property list with the hiddenstatus = false
    //Written by Leong Xin Nan
    public ObservableList<Property> getVisiblePropertyList(){
        ObservableList<Property> visiblePropList = FXCollections.observableArrayList();

        for(Property property:propertyList){
            if (!property.getHiddenStatus()){
                visiblePropList.add(property);
            }
        }

        return visiblePropList;
    }

    //Method for getting a property object with ID
    //Written by Leong Xin Nan
    public Property getPropertyObjectWithID(String[] propertyinfo){
        String projectName =  propertyinfo[0].replace("\"", "");

        long propertySize;
        if (propertyinfo[1].equals("")){
            propertySize = 0;
        } 
        else{
            propertySize = Long.parseLong(propertyinfo[1]);
        }

        long rentalRate;
        if (propertyinfo[2].equals("")){
            rentalRate = 0;
        } 
        else{
            rentalRate = Long.parseLong(propertyinfo[2]);
        }

        String propertyType = propertyinfo[3];
        String propertyOwner = propertyinfo[4];
        String contactNum = propertyinfo[5];
        Long propertyID = Long.parseLong(propertyinfo[6]);
        int numofRoom = Integer.parseInt(propertyinfo[7]);
        int numofBathroom = Integer.parseInt(propertyinfo[8]);
        String facilities = propertyinfo[9].replace("\"", "");

        Boolean hiddenStatus = Boolean.parseBoolean(propertyinfo[10]);

        Boolean rentStatus;
        if (propertyinfo[11]==null){
            rentStatus = false;
        } 
        else{
            if (propertyinfo[7].equals("not Active")){
                rentStatus = true;
            }
            else{
                rentStatus = false;
            }
        }
        
        
        Property returnProperty = new Property.propertyBuilder(propertyID)
                                            .projectName(projectName)
                                            .propertyOwner(propertyOwner)
                                            .contactNum(contactNum)
                                            .propertySize(propertySize)
                                            .rentalRate(rentalRate)
                                            .propertyType(propertyType)
                                            .numofRoom(numofRoom)
                                            .numofBathroom(numofBathroom)
                                            .facilities(facilities)
                                            .rentStatus(rentStatus)
                                            .hiddenStatus(hiddenStatus)
                                            .build();

        return returnProperty;
    }

    //Method for Property ID Validation
    //Written by Leong Xin Nan
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

    

    //Method for Property Name Validation
    //Written by Leong Xin Nan
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

    //Method for Property Rental Rate Validation
    //Written by Leong Xin Nan
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

    //Method for Property Size Validation
    //Written by Leong Xin Nan
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

    //Method for Validating property info that will be added
    //Written by Leong Xin Nan
    public Boolean addingPropertyValidation(String[] propValidationInfo,String[] propInfoList){
        Boolean hiddenStatusValid = false;
        Boolean propownerselected = false;
        Boolean PropertyValidation = false;

        //String[] propInfoList_TobeValided = {propHiddenStatusComboBox.getValue(),propOwnerComboBox.getValue()};
        for (int i=0;i<propInfoList.length;i++){
            if(propInfoList[i].equals("")){
                if (propValidationInfo[0].equals("false") || propValidationInfo[0].isEmpty()){
                    hiddenStatusValid = false;
                    (new Alert(AlertType.ERROR,"Incomplete Property Details can't be displayed on property listing")).show();
                    break;
                }
                else{
                    hiddenStatusValid = true;
                }
            }
            else{
                hiddenStatusValid = true;
            }
        }

        if (propValidationInfo[1].equals("")){
            propownerselected = false;
            (new Alert(AlertType.ERROR,"Property Owner can't be empty, please select a property owner")).show();
        }
        else{
            propownerselected = true;
        }

        if (hiddenStatusValid && propownerselected){
            PropertyValidation = true;
        }
        else{
            PropertyValidation = false;
        }

        return PropertyValidation;
    }

    //Method 1 for writing property into the propertylist csv file (takes a property object as a parameter)
    //Written by Leong Xin Nan
    public void WriteToPropertyListCsv(Property propertytoWrite) throws IOException{
        FileWriter fileWriter = new FileWriter(new File("PropertyList.csv"));
        propertyList.add(propertytoWrite);

        for (Property u : propertyList) {
            fileWriter.write(u.toCSVFormat());
            fileWriter.write("\n");
        }
        fileWriter.close();
    }

    //Method 2 for writing property into the propertylist csv file (takes nothing as parameter)
    //Written by Leong Xin Nan
    public void WriteToPropertyListCsv() throws IOException{
        FileWriter fileWriter = new FileWriter(new File("PropertyList.csv"));

        for (Property u : propertyList) {
            fileWriter.write(u.toCSVFormat());
            fileWriter.write("\n");
        }
        fileWriter.close();
    }

    //Method for validating the edited person's data before commiting the changes
    //Written by Leong Xin Nan
    public boolean editSelectedPersonDataValidation(String[] selectedPersonData_TobeValid){
        Boolean completeData = false;
        Boolean passwordMatch =  registerPasswordValidation(selectedPersonData_TobeValid[2],selectedPersonData_TobeValid[3]);
        Boolean editSelectedPersonDataValid = false;
        Boolean validContactNum = ContactnumValidation(selectedPersonData_TobeValid[4]);

        //Checking whether the edited selected person's data is empty
        for (int i=0;i<selectedPersonData_TobeValid.length;i++){
            if (selectedPersonData_TobeValid[i].isEmpty()){
                completeData = false;
                break;
            }
            else{
                completeData = true;
            }
        }

        //Final validating for edited person's data before committing the edits
        if(completeData && passwordMatch && validContactNum){
            editSelectedPersonDataValid = true;
        }
        else{
            editSelectedPersonDataValid = false;
        }

        //ERROR MESSAGES
        if(!completeData){  
            (new Alert(AlertType.ERROR,"Incomplete data, please fill in the information before committing to the changes!")).showAndWait();
        }
        if(!passwordMatch){
            (new Alert(AlertType.ERROR,"Password does not match, please try again!")).showAndWait();
        }
        if(!validContactNum){
            (new Alert(AlertType.ERROR,"Invalid phone number, please try again!")).showAndWait();
        }

        return editSelectedPersonDataValid;
    }

    //Method for validating the admin account's information before creating the admin account
    //Written by Leong Xin Nan
    public Boolean createAdminAccountValidation(String username,String password, String reenterPassowrd, String adminSecretPhrase, String fullname) throws IOException{
        Boolean validAdminAccountInfo = false;
        Boolean validAdminSecretPhrase = false;
        Boolean validUsername = false;
        Boolean validPassword = false;
        Boolean validFullname = false;

        //Validating secret phrase for creating admin account
        if (adminSecretPhrase.isEmpty()){
            validAdminSecretPhrase = false;
        }
        else{
            if (adminSecretPhrase.equals(Admin.secretPhrase)){
                validAdminSecretPhrase = true;
            }
            else{
                validAdminSecretPhrase = false;
            }
        }

        //Validating duplicate admin username
        if (username.isEmpty()){
            validUsername = false;
        }else{
            for (person u : userInfo){
                if (u.getUserType().equals("admin")){
                    if(u.getUsername().equals(username)){
                        validUsername = false;
                        break;
                    }
                    else{
                        validUsername = true;
                    }
                }
            }
        }

        //Validating duplicate admin fullname
        if (fullname.isEmpty()){
            validFullname = false;
        }
        else{
            for (person u : userInfo){
                if (u.getUserType().equals("admin")){
                    if(u.getFullName().toLowerCase().equals(fullname.toLowerCase())){
                        validFullname = false;
                        break;
                    }
                    else{
                        validFullname = true;
                    }
                }
            }
        }     

        //Validating password
        if (password.isEmpty() || reenterPassowrd.isEmpty()){
            validFullname = false;
        }
        else{
            if(password.equals(reenterPassowrd)){
                validPassword = true;
            }
            else{
                validPassword = false;
            }
        }

        //ERROR MESSAGES
        if(!validAdminSecretPhrase){
            (new Alert(AlertType.ERROR,"Invalid Admin Secret Phrase, please try again")).showAndWait();
        }
        if(!validFullname){
            (new Alert(AlertType.ERROR,"Invalid Fullname, please try again")).showAndWait();
        }
        if(!validPassword){
            (new Alert(AlertType.ERROR,"Invalid Password, please try again")).showAndWait();
        }
        if(!validUsername){
            (new Alert(AlertType.ERROR,"Invalid Username, please try again")).showAndWait();
        }

        //Final Validation for Admin Account Info
        if(validAdminSecretPhrase && validFullname && validPassword && validUsername){
            validAdminAccountInfo = true;
        }
        else{
            validAdminAccountInfo = false;
        }

        return validAdminAccountInfo;
    }

}
