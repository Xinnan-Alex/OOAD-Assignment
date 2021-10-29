package controller.propertyowner;
import model.Property;
import model.propertyOwner;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class propertyOwnerPropertyListingDetailsController {

    propertyOwner owner;
    Property property;

    @FXML
    private Label propertyAddress, propertySize, rentalRate, propertyOwner, propertyType, numofRoom, facilities, contactNum;
    
    @FXML
    private Button backButton;

    public void backButtonHandler() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/propertyowner/propertyOwnerPropertyListingScene.fxml"));
        Parent root = loader.load();

        propertyOwnerPropertyListingController propertyListingController =  loader.getController();
        propertyListingController.passedInOwnerObject(owner);   

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void initUserObejct(propertyOwner passedIN){
        owner = passedIN;
    }
    

    public void passPropertyObject(Property p) {
        property = p;
        setPropertyInfo();
        
    } 

    public void setPropertyInfo() {
        propertyAddress.setText(property.getProjectName());
        propertySize.setText(String.valueOf(property.getPropertySize()));
        rentalRate.setText(String.valueOf(property.getRentalRate()));
        propertyOwner.setText(property.getPropertyOwner());
        propertyType.setText(property.getPropertyType());
        numofRoom.setText(String.valueOf(property.getNumofRoom()));
        facilities.setText(String.valueOf(property.getFacilities()));
        contactNum.setText(property.getContactNum());
    } 
}