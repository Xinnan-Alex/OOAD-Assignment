import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

public class adminHomepageSceneController implements Initializable{

    private Admin admin = new Admin();
    private String usertype;

    @FXML
    Label homepageAdminFullname,homepageID;
    @FXML
    Button logoutButton,settingButton,propertyListButton,accountAdminstrationButton,createaAdminButton;

    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     
    }

    public void displayName(String name){
        homepageAdminFullname.setText(name);
    }

    public void displayID(String ID){
        homepageID.setText(ID);
    }

    public void initUserObejct(Admin passedIN){
        admin = passedIN;
        homepageAdminFullname.setText(admin.getFullName());
        homepageID.setText(admin.getID());
        usertype = admin.getUserType();

    }

    public void logoutButtonHandler() throws IOException{
        Alert confirmation_Alert = new Alert(AlertType.CONFIRMATION,"Do you wish to update in this property?",ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        confirmation_Alert.showAndWait();

        if (confirmation_Alert.getResult() == ButtonType.YES){
            Parent root = FXMLLoader.load(getClass().getResource("resources/fxml/loginScene.fxml"));
            stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }

    public void settingButtonHandler() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/fxml/settingScene.fxml"));
        Parent root = loader.load();

        settingSceneController settingController =  loader.getController();

        settingController.setUserInfo(admin);
        
        stage = (Stage) logoutButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void propertyListButton() throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/fxml/admin/adminPropertyListing.fxml"));
        Parent root = loader.load();

        adminPropertyListingController propertyListingController =  loader.getController();
        propertyListingController.initialiseAdminInfo(admin);

        stage = (Stage) propertyListButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void accountAdminstrationButtonHandler() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/fxml/admin/adminAccountAdminstrationScene.fxml"));
        Parent root = loader.load();

        adminAccountAdminstrationController accountAdminstrationController =  loader.getController();
        accountAdminstrationController.initialiseAdminInfo(admin);

        stage = (Stage) accountAdminstrationButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        
    }

    public void createaAdminButtonHandler() throws IOException{
    }
}
