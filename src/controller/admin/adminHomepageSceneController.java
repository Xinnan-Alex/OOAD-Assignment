//ADMIN HOMEPAGE INTERFACE CONTROLLER (Admin Feature)
package controller.admin;

//JAVA IMPORTS
import java.io.IOException;

//JAVAFX IMPORTS
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
public class adminHomepageSceneController{

    private Admin admin = new Admin();

    @FXML
    Label homepageAdminFullname,homepageID;
    @FXML
    Button logoutButton,profileSettingButton,propertyListButton,accountAdminstrationButton,createaAdminButton;

    public void initUserObejct(Admin passedIN){
        admin = passedIN;
        homepageAdminFullname.setText(admin.getFullName());
        homepageID.setText(admin.getID());

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

    public void propertyListButton() throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/admin/adminPropertyListing.fxml"));
        Parent root = loader.load();

        adminPropertyListingController propertyListingController =  loader.getController();
        propertyListingController.initialiseAdminInfo(admin);   

        Stage stage = (Stage) propertyListButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void accountAdminstrationButtonHandler() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/admin/adminAccountAdminstrationScene.fxml"));
        Parent root = loader.load();

        adminAccountAdminstrationController accountAdminstrationController =  loader.getController();
        accountAdminstrationController.initialiseAdminInfo(admin);

        Stage stage = (Stage) accountAdminstrationButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        
    }

    public void createAdminButtonHandler() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/admin/adminCreateAdmin.fxml"));
        Parent root = loader.load();

        adminCreateAdminController controller =  loader.getController();
        controller.initialiseAdminInfo(admin);

        Stage stage = (Stage) createaAdminButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void profileSettingButtonHandler() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/admin/adminProfileScene.fxml"));
        Parent root = fxmlLoader.load();
        
        adminProfileSceneController controller = fxmlLoader.getController();
        controller.initUserObejct(admin);
        
        Stage window = (Stage)profileSettingButton.getScene().getWindow();
        window.setScene(new Scene(root)); 
    }
}
