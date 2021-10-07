import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class adminHomepageSceneController {

    private Admin admin = new Admin();
    private String usertype;
    private Model logicModel = new Model();

    @FXML
    Label homepageAdminFullname,homepageID;
    @FXML
    Button logoutButton,settingButton,propertyListButton,accountAdminstrationButton,createaAdminButton;

    private Stage stage;

    public void displayName(String name){
        homepageAdminFullname.setText(name);
    }

    public void displayID(String ID){
        homepageID.setText(ID);
    }

    public void initUserObejct(String username){
        usertype = logicModel.getPersonObeject(username).getUsertype();
        person user = logicModel.getPersonObeject(username);

        admin = new Admin(user);

    }

    public void logoutButtonHandler() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("resources/fxml/loginScene.fxml"));
        stage = (Stage) logoutButton.getScene().getWindow();
        stage.setScene(new Scene(root));
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
        logicModel.loadPropertyList();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/fxml/admin/adminPropertyListing.fxml"));
        Parent root = loader.load();

        stage = (Stage) propertyListButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void accountAdminstrationButton() throws IOException{
    }

    public void createaAdminButton() throws IOException{
    }
    
}
