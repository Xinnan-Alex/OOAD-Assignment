//ADMIN CLASS FOR USERTYPE ADMIN, AND ADMIN RELATED METHODS
package model;
import main.*;

//JAVA IMPORTS
import java.io.IOException;
import java.util.UUID;

//JAVAFX IMPORTS
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.ObservableList;

//Admin class
public class Admin extends person{

    //secret phrase for creating admin account
    static String secretPhrase = "No-Brainer";

    //Admin constructor
    Admin(String username, String password, String fullName, String phoneNumber, UUID ID, String userType) {
        super(username, password, fullName, phoneNumber, ID, userType);
    }


    //Admin overloaded constructor that takes in person object as parameter
    public Admin (person p){
        super(p.getUsername(), p.getPassword(), p.getFullName(), p.getPhoneNumber(), p.getUUID(), p.getUserType());
    }

    //Admin Default Constructor
    public Admin() {
    }

    /*==============================================================================================================================================================*/
    //PROPERTY METHODS
    //Method for editting selected property's data
    public void editSelectedPropertyData(Property selectedProperty, String[] selectedPropertyData, String[] selectedPropertyData_TobeValided) throws IOException{

        if(selectedPropertyData_TobeValided[0].isEmpty()){
            (new Alert(AlertType.ERROR,"Property Hidden Status can't be blank")).show();
        }
        else{
            if (Globals.LogicModel.addingPropertyValidation(selectedPropertyData_TobeValided,selectedPropertyData)){

                Property tempProperty = Globals.LogicModel.getPropertyObject(selectedPropertyData);

                selectedProperty.setProjectName(tempProperty.getProjectName());
                selectedProperty.setPropertySize(tempProperty.getPropertySize());
                selectedProperty.setRentalRate(tempProperty.getRentalRate());
                selectedProperty.setPropertyType(tempProperty.getPropertyType());
                selectedProperty.setPropertyOwner(tempProperty.getPropertyOwner());
                selectedProperty.setContactNum(tempProperty.getContactNum());
                selectedProperty.setHiddenStatus(tempProperty.getHiddenStatus());
                selectedProperty.setRentStatus(tempProperty.getRentStatus());

                Globals.LogicModel.WriteToPropertyListCsv();
            }
            else{
                (new Alert(AlertType.ERROR,"Error occured please try again.")).show();
            }

        }
    
    }

    //Method for removing selected property
    public void removeSelectedProperty(ObservableList<Property> selectedProperty) throws IOException{
        Model.propertyList.removeAll(selectedProperty);
        Globals.LogicModel.WriteToPropertyListCsv();
    }

    //Method for adding property
    public void addProperty(Property propertyToBeAdded) throws IOException{
        Globals.LogicModel.WriteToPropertyListCsv(propertyToBeAdded);
    }

    /*==============================================================================================================================================================*/
    //PERSON/ADMIN METHODS
    //Method for editing selected person's data
    public void editSelectedPersonData(person selectedPerson, String[] selectedPersonData) throws IOException{
        //{username.getText(),password.getText(),fullname.getText(),contactnum.getText(),userType.getValue()};
        selectedPerson.setUsername(selectedPersonData[0]);
        selectedPerson.setPassword(selectedPersonData[1]);
        selectedPerson.setFullname(selectedPersonData[2]);
        selectedPerson.setPhonenumber(selectedPersonData[3]);
        selectedPerson.setUsertype(selectedPersonData[4]);

        Globals.LogicModel.writeToUserDataCSV();
    }

    //Method for removing selected person
    public void removeSelectedPerson(ObservableList<person> selectedPerson) throws IOException{
        Model.userInfo.removeAll(selectedPerson);
        Globals.LogicModel.writeToUserDataCSV();
    }

    //Method for adding person
    public void addPerson(String[] addPersonInfo_Write) throws IOException{
        Globals.LogicModel.writeToUserDataCSV(Globals.LogicModel.addUserInfo(addPersonInfo_Write));
    }

    //Method for creating admin account
    public person createAdminAccount(String username, String password, String fullname){
        person admin = new person(username, password, fullname, "", "admin");

        return admin;
    }
}
