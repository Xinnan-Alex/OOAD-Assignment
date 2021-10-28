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

//Property Owner class
public class propertyOwner extends person{

    //secret phrase for creating admin account
    //static String secretPhrase = "No-Brainer";

    //Property Owner constructor
    propertyOwner(String username, String password, String fullName, String phoneNumber, UUID ID, String userType) {
        super(username, password, fullName, phoneNumber, ID, userType);
    }


    //Property Owner overloaded constructor that takes in person object as parameter
    public propertyOwner (person p){
        super(p.getUsername(), p.getPassword(), p.getFullName(), p.getPhoneNumber(), p.getUUID(), p.getUserType());
    }

    //Property Owner Default Constructor
    public propertyOwner() {
    }

    /*==============================================================================================================================================================*/
    //PROPERTY METHODS
    //Method for editing selected property's data
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
}
