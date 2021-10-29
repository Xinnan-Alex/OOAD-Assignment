//ADMIN HOMEPAGE INTERFACE CONTROLLER (Admin Feature)
package controller.propertyowner;

//JAVA IMPORTS
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//JAVAFX IMPORTS
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import model.*;

//adminHomepageSceneController class
public class propertyOwnerHomepageSceneController implements Initializable{


    private propertyOwner owner;

    @FXML
    Label homepagePropertyOwnerFullname,homepageID;

    @FXML
    Button propertyOwnerProfileButton,propertyListButton,propertyAdminstrationButton,logoutButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     
    }

    public void initUserObejct(propertyOwner passedIN){
        owner = passedIN;
        homepagePropertyOwnerFullname.setText(owner.getFullName());
        homepageID.setText(owner.getID());

    }

    public void logoutButtonHandler() throws IOException{
        Alert confirmation_Alert = new Alert(AlertType.CONFIRMATION,"Do you wish to logout?",ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        confirmation_Alert.showAndWait();

        if (confirmation_Alert.getResult() == ButtonType.YES){
            Parent root = FXMLLoader.load(getClass().getResource("../../resources/fxml/loginScene.fxml"));
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }

    public void propertyListButtonHandler() throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/propertyowner/propertyOwnerPropertyListingScene.fxml"));
        Parent root = loader.load();

        propertyOwnerPropertyListingController propertyListingController =  loader.getController();
        propertyListingController.passedInOwnerObject(owner);   

        Stage stage = (Stage) propertyListButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
    public void propertyAdminstrationButtonHandler() throws IOException{

        //CHNAGE TO PROPERTYOWNERPROPERTYLISTINGCONTROLLER]
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/propertyowner/propertyOwnerPropertyConfigurationScene.fxml"));
        Parent root = loader.load();

        propertyOwnerPropertyConfigurationSceneController propertyListingController =  loader.getController();
        propertyListingController.initialiseOwnerInfo(owner);   

        Stage stage = (Stage) propertyAdminstrationButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void propertyOwnerProfileButtonHandler() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/propertyowner/propertyOwnerProfileScene.fxml"));
        Parent root = fxmlLoader.load();
        
        propertyOwnerProfileSceneController controller = fxmlLoader.getController();
        controller.initUserObejct(owner);
        
        Stage window = (Stage)propertyOwnerProfileButton.getScene().getWindow();
        window.setScene(new Scene(root, 750, 500)); 
    }

}

