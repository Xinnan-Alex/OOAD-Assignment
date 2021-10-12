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

    @FXML
    private Button btn1, btn2;

    @FXML
    void handlebtn() throws IOException {

        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tenentprofile.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("resources//fxml//tenant//tenantProfileScene.fxml"));

        Stage window = (Stage)btn1.getScene().getWindow();
        window.setScene(new Scene(root, 750, 500)); 

    }

    // @FXML
    // void handlebtn2() throws IOException {

    //     //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tenentprofile.fxml"));
    //     Parent root = FXMLLoader.load(getClass().getResource("mainfame.fxml"));

    //     Stage window = (Stage)btn2.getScene().getWindow();
    //     window.setScene(new Scene(root, 750, 500)); 

    // }

}
