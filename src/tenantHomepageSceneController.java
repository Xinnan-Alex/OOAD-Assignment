//TENANT HOMEPAGE INTERFACE CONTROLLER

//JAVA IMPORTS
import java.io.IOException;

//JAVAFX IMPORTS
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

//tenantHomepageSceneController class
public class tenantHomepageSceneController {

    tenant loggedinPerson;

    @FXML
    private Button btn1;

    @FXML
    public void handlebtn() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/fxml/tenant/tenantProfileScene.fxml"));
        Parent root = fxmlLoader.load();
        
        tenantProfileSceneController controller = fxmlLoader.getController();
        controller.initUserObejct(loggedinPerson);
        
        Stage window = (Stage)btn1.getScene().getWindow();
        window.setScene(new Scene(root, 750, 500)); 
    }

    public void initUserObejct(tenant passedIN){
        loggedinPerson = passedIN;
        
    }
}



// public class tenantHomepageSceneController {

//     @FXML
//     private Button btn1;

//     @FXML
//     public void handlebtn() throws IOException {

//         //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tenentprofile.fxml"));
//         Parent root = FXMLLoader.load(getClass().getResource("resources/fxml/tenant/tenantProfileScene.fxml"));

//         Stage window = (Stage)btn1.getScene().getWindow();
//         window.setScene(new Scene(root, 750, 500)); 

        
//     }
// }