//ADMIN ACCOUNT ADMINSTRATION INTERFACE CONTROLLER (Admin Feature)
package controller.admin;
import model.*;

//JAVA IMPORTS
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//JAVAFX IMPORTS
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableRow;
import javafx.event.EventHandler;

//adminAccountAdminstrationController java
public class adminAccountAdminstrationController implements Initializable{

    private FilteredList<person> personFilteredList;
    private String[] personTypeList = {"All","admin","tenant","property owner"};
    private Admin admin;

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

        TableViewSelectionModel<person> userInfoTableViewSelectionModel = userInfoTableView.getSelectionModel();
        userInfoTableViewSelectionModel.setSelectionMode(SelectionMode.SINGLE);

        userInfoTableView.setRowFactory(new Callback<TableView<person>, TableRow<person>>() {  
            @Override  
            public TableRow<person> call(TableView<person> tableView2) {  
                final TableRow<person> row = new TableRow<>();  
                row.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {  
                    @Override  
                    public void handle(MouseEvent event) {  
                        final int index = row.getIndex();  
                        if (index >= 0 && index < userInfoTableView.getItems().size() && userInfoTableView.getSelectionModel().isSelected(index)  ) {
                            userInfoTableView.getSelectionModel().clearSelection();
                            event.consume();  
                        }  
                    }  
                });  
                return row;  
            }  
        });  

    }

    //Method for passing in the admin object from the previous interface
    public void initialiseAdminInfo(Admin passedIn){
        admin = passedIn;
    }

    public void backButtonHandler() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/admin/adminHomepageScene.fxml"));
        Parent root = loader.load();

        adminHomepageSceneController controller =  loader.getController();
        controller.initUserObejct(admin);

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void addPersonButtonHandler() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/admin/adminAddPersonScene.fxml"));
        Parent root = loader.load();

        adminAddPersonSceneController controller =  loader.getController();
        controller.initialiseAdminInfo(admin);

        Stage stage = (Stage) addPersonButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void deletePersonButtonHandler() throws IOException{
        ObservableList<person> selectedPerson = userInfoTableView.getSelectionModel().getSelectedItems();

        if (selectedPerson.isEmpty()){
            (new Alert(AlertType.ERROR,"Please select a person to delete")).showAndWait();
        }
        else{
            Alert confirmation_Alert = new Alert(AlertType.CONFIRMATION,"Do you wish to delete this person details?",ButtonType.YES,ButtonType.NO,ButtonType.CANCEL);
            confirmation_Alert.showAndWait();
    
            if (confirmation_Alert.getResult() == ButtonType.YES){
                if (selectedPerson.get(0).getUUID() == admin.getUUID()){
                    (new Alert(AlertType.ERROR,"You can't delete your own acount, please select another account!")).showAndWait();
                }
                else{
                    admin.removeSelectedPerson(selectedPerson);
                }
            }
        }
    }

    public void editPersonButtonHandler() throws IOException{
        ObservableList<person> selectedPerson = userInfoTableView.getSelectionModel().getSelectedItems();

        if (selectedPerson.isEmpty()){
            (new Alert(AlertType.ERROR,"Please select a person to edit")).showAndWait();
        }
        else{
            Alert confirmation_Alert = new Alert(AlertType.CONFIRMATION,"Do you wish to edit this person details?",ButtonType.YES,ButtonType.NO,ButtonType.CANCEL);
            confirmation_Alert.showAndWait();

            if (confirmation_Alert.getResult() == ButtonType.YES){

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/admin/adminEditPersonScene.fxml"));
                Parent root = loader.load();

                adminEditPersonSceneController controller =  loader.getController();
                controller.initialiseAdminInfo(admin);
                controller.initialiseSelectedPersonInfo(selectedPerson.get(0));

                Stage stage = (Stage) editPersonButton.getScene().getWindow();
                stage.setScene(new Scene(root));
            }
        }   
    }

}