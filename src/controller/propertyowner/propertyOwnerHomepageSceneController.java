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


    private propertyOwner owner = new propertyOwner();

    @FXML
    Label homepagePropertyOwnerFullname,homepageID;

    @FXML
    Button settingButton,propertyListButton,propertyAdminstrationButton,logoutButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     
    }

    public void initUserObejct(propertyOwner passedIN){
        owner = passedIN;
        homepagePropertyOwnerFullname.setText(owner.getFullName());
        homepageID.setText(owner.getID());

    }

    // public void propertyListButton() throws IOException{

    //     //CHNAGE TO PROPERTYOWNERPROPERTYLISTINGCONTROLLER]
    //     FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/fxml/admin/propertyownerHomepageScene.fxml"));
    //     Parent root = loader.load();

    //     propertyOwnerPropertyListingController propertyListingController =  loader.getController();
    //     propertyListingController.initialiseOwnerInfo(owner);   

    //     Stage stage = (Stage) propertyListButton.getScene().getWindow();
    //     stage.setScene(new Scene(root));
    // }

}
