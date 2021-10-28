//TENANT HOMEPAGE INTERFACE CONTROLLER
package controller.tenant;

//JAVA IMPORTS
import java.io.IOException;

//JAVAFX IMPORTS
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.*;

//tenantHomepageSceneController class
public class tenantHomepageSceneController {

    Tenant loggedinPerson;

    @FXML
    private Button tenantViewProfileBtn, propertyBtn, logoutButton ;

    @FXML
    Label fullnameLabel;

    public void setUserInfo(){
        fullnameLabel.setText(loggedinPerson.getFullName());
    }

    public void tenantViewProfileBtnHandler() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/fxml/tenant/tenantProfileScene.fxml"));
        Parent root = fxmlLoader.load();
        
        tenantProfileSceneController controller = fxmlLoader.getController();
        controller.initUserObejct(loggedinPerson);
        
        Stage window = (Stage)tenantViewProfileBtn.getScene().getWindow();
        window.setScene(new Scene(root, 750, 500)); 
    }

    public void initUserObejct(Tenant passedIN){
        loggedinPerson = passedIN;
        setUserInfo();
    }

    public void propertyBtnHandler() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/fxml/tenant/tenantPropertyScene.fxml"));
        Parent root = fxmlLoader.load();
        
        tenantPropertyController controller = fxmlLoader.getController();
        controller.initUserObejct(loggedinPerson);
        
        Stage window = (Stage)propertyBtn.getScene().getWindow();
        window.setScene(new Scene(root, 750, 500)); 
    }

    public void logoutButtonHandler() throws IOException{
        Alert confirmation_Alert = new Alert(AlertType.CONFIRMATION,"Do you wish to logout?",ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        confirmation_Alert.showAndWait();

        if (confirmation_Alert.getResult() == ButtonType.YES){
            Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/loginScene.fxml"));
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }

}