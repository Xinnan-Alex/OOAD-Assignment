import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class adminAccountAdminstrationController implements Initializable{

    FilteredList<person> personFilteredList;
    private String[] personTypeList = {"All","admin","tenant","property owner"};
    Admin admin;

    @FXML
    Button deletePersonButton,addPersonButton,editPersonButton,backButton;

    @FXML
    TableView<person> userInfoTableView;

    @FXML
    TableColumn<person,String> noColumn,personIDColumn,personFullnameColumn,personUsernameColumn,personPhoneNumberColumn,personUserTypeColumn;

    @FXML
    TextField personInfoSearchBar;

    @FXML
    ComboBox<String> personTypeFilter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userInfoTableView.setStyle("-fx-selection-bar: #87CEFA;");
        personFilteredList = new FilteredList<>(Model.userInfo,b->true);

        personTypeFilter.getItems().addAll(personTypeList);
        personTypeFilter.setValue("All");

        noColumn.setCellValueFactory(new Callback<CellDataFeatures<person, String>, ObservableValue<String>>() {
            @Override 
            public ObservableValue<String> call(CellDataFeatures<person, String> p) {
                return new ReadOnlyObjectWrapper(userInfoTableView.getItems().indexOf(p.getValue()) + 1);
            }
            });
        noColumn.setSortable(false);

        personIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        personFullnameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        personUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        personPhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        personUserTypeColumn.setCellValueFactory(new PropertyValueFactory<>("userType"));

        personInfoSearchBar.textProperty().addListener((Observable,oldValue,newValue) -> {
            personFilteredList.setPredicate(person ->{
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }

                String personInfoSearchBarToLowerCase = newValue.toLowerCase();

                if (person.getFullName().toLowerCase().contains(personInfoSearchBarToLowerCase)){
                    return true;
                }
                else if(person.getUsername().toLowerCase().contains(personInfoSearchBarToLowerCase)){
                    return true;
                }
                else{
                    return false;
                }
            });

        });

        personTypeFilter.valueProperty().addListener((Observable,oldValue,newValue) -> {
            personFilteredList.setPredicate(person ->{
                if (newValue.equals("All") || newValue.isEmpty()){
                    return true;
                }

                String personTypeFilterToLowerCase = newValue.toLowerCase();

                if (person.getUserType().toLowerCase().equals(personTypeFilterToLowerCase)){
                    return true;
                }
                else{
                    return false;
                }
            });

        });


        userInfoTableView.setItems(personFilteredList);

        TableViewSelectionModel userInfoTableViewSelectionModel = userInfoTableView.getSelectionModel();
        userInfoTableViewSelectionModel.setSelectionMode(SelectionMode.SINGLE);

    }

    public void initialiseAdminInfo(Admin passedIn){
        admin = passedIn;
    }

    public void backButtonHandler() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/fxml/admin/adminHomepageScene.fxml"));
        Parent root = loader.load();

        adminHomepageSceneController controller =  loader.getController();
        controller.initUserObejct(admin);

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

}