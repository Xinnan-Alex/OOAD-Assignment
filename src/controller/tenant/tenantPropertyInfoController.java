package controller.tenant;
import model.Property;
import model.Tenant;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class tenantPropertyInfoController {

    Tenant loggedinPerson;
    Property property;

    @FXML
    private Label propertyAddress, propertySize, rentalRate, propertyOwner, propertyType, numofRoom, facilities, contactNum;
    
    @FXML
    private Button backButton;

    public void backButtonHandler() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/fxml/tenant/tenantPropertyScene.fxml"));
        Parent root = fxmlLoader.load();
        
        tenantPropertyController controller = fxmlLoader.getController();
        controller.initUserObejct(loggedinPerson);
        
        Stage window = (Stage)backButton.getScene().getWindow();
        window.setScene(new Scene(root, 750, 500)); 
    }

    public void initUserObejct(Tenant passedIN){
        loggedinPerson = passedIN;
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