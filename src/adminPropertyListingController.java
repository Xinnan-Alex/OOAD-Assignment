import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.util.Callback;
import javafx.fxml.FXML;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

public class adminPropertyListingController implements Initializable{

    private Model logicModel = new Model();
    private Admin admin;
    FilteredList<Property> propertyFilteredList;

    @FXML
    TableView<Property> propTableView;

    @FXML
    TableColumn<Property,String> noColumn,propAdressColumn,propSizeColumn,propRentanRateColumn,propOwnerColumn,propOwnerContactNumColumn,propRentalStatus,propTypeColumn,propIDColumn;

    @FXML
    TextField propertyNameSearch;

    @FXML
    Button addPropertyButton,deletePropertyButton,backButton;

    @FXML
    ComboBox<String> propertyTypeFilter;

    @FXML
    ImageView clearSearchIcon;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        propertyFilteredList = new FilteredList<>(Model.propertyList,b->true);

        propertyTypeFilter.getItems().addAll(Globals.propertyType);
        noColumn.setCellValueFactory(new Callback<CellDataFeatures<Property, String>, ObservableValue<String>>() {
            @Override 
            public ObservableValue<String> call(CellDataFeatures<Property, String> p) {
                return new ReadOnlyObjectWrapper(propTableView.getItems().indexOf(p.getValue()) + 1);
            }
            });
        noColumn.setSortable(false);

        propAdressColumn.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        propSizeColumn.setCellValueFactory(new PropertyValueFactory<>("propertySize"));
        propRentanRateColumn.setCellValueFactory(new PropertyValueFactory<>("rentalRate"));
        propOwnerColumn.setCellValueFactory(new PropertyValueFactory<>("propertyOwner"));
        propOwnerContactNumColumn.setCellValueFactory(new PropertyValueFactory<>("contactNum"));
        propRentalStatus.setCellValueFactory(new PropertyValueFactory<>("rentStatus"));
        propTypeColumn.setCellValueFactory(new PropertyValueFactory<>("propertyType"));
        propIDColumn.setCellValueFactory(new PropertyValueFactory<>("propertyID"));

        propertyNameSearch.textProperty().addListener((Observable,oldValue,newValue) -> {
            propertyFilteredList.setPredicate(Property ->{
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }

                String propertyNameSearchToLowerCase = newValue.toLowerCase();

                if (Property.getProjectName().toLowerCase().contains(propertyNameSearchToLowerCase)){
                    return true;
                }
                else if(Long.toString(Property.getPropertyID()).toLowerCase().contains(propertyNameSearchToLowerCase)){
                    return true;
                }
                else{
                    return false;
                }
            });

        });

        propertyTypeFilter.valueProperty().addListener((Observable,oldValue,newValue) -> {
            propertyFilteredList.setPredicate(Property ->{
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }

                String propertyTypeToLowerCase = newValue.toLowerCase();

                if (Property.getPropertyType().toLowerCase().equals(propertyTypeToLowerCase)){
                    return true;
                }
                else{
                    return false;
                }
            });

        });


        propTableView.setItems(propertyFilteredList);

        TableViewSelectionModel propTableViewSelectionModel = propTableView.getSelectionModel();
        propTableViewSelectionModel.setSelectionMode(SelectionMode.SINGLE);

        
    }

    public void addPropertyButtonHandler() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/fxml/admin/adminAddPropertyScene.fxml"));
        Parent root = loader.load();

        adminAddPropertySceneController controller = loader.getController();
        controller.passedInAdminObject(admin);

        Stage stage = (Stage) addPropertyButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void backButtonHandler() throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/fxml/admin/adminHomepageScene.fxml"));
        Parent root = loader.load();

        adminHomepageSceneController controller =  loader.getController();
        controller.initUserObejct(admin);

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void initialiseAdminInfo(Admin passedIn){
        admin = passedIn;
    }
    
    public void deletePropertyButtonHandler() throws IOException{
        ObservableList<Property> propertyToBeDeleted = propTableView.getSelectionModel().getSelectedItems();

        logicModel.removeSelectedProperty(propertyToBeDeleted);
        propertyFilteredList.removeAll(propertyToBeDeleted);
        
    }

    public void clearSearchIconHandler(){
        propertyTypeFilter.getSelectionModel().clearSelection();
    }

    public void editPropertyButton(){
        
    }
    
}
