//ADMIN PROPERTY LISTING INTERFACE CONTROLLER
package controller;

//JAVA IMPORTS
import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;

//JAVAFX IMPORTS
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.beans.value.ObservableValue;
import javafx.util.Callback;
import javafx.fxml.FXML;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TableRow;
import javafx.event.EventHandler;
import model.*;

//adminPropertyListingController class
public class adminPropertyListingController implements Initializable{

    private Admin admin;
    FilteredList<Property> propertyFilteredList;
    public static String[] propertyTypeFilterList = {"All","Bungalow","Semi-D","Terrace","Townhouse","Penthouse","Condominium","Duplex","Apartment","Unspecified"};

    @FXML
    TableView<Property> propTableView;

    @FXML
    TableColumn<Property,String> noColumn,propAdressColumn,propSizeColumn,propRentanRateColumn,propOwnerColumn,propOwnerContactNumColumn,propRentalStatus,propTypeColumn,propIDColumn;

    @FXML
    TableColumn<Property,Integer> numofRoomColumn,numofBathroomColumn;

    @FXML
    TextField propertyNameSearch;

    @FXML
    Button addPropertyButton,deletePropertyButton,editPropertyButton,backButton;

    @FXML
    ComboBox<String> propertyTypeFilter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        propTableView.setStyle("-fx-selection-bar: #87CEFA;");
        propertyFilteredList = new FilteredList<>(Model.propertyList,b->true);

        propertyTypeFilter.getItems().addAll(propertyTypeFilterList);
        propertyTypeFilter.setValue("All");
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
        numofRoomColumn.setCellValueFactory(new PropertyValueFactory<>("numofRoom"));
        numofBathroomColumn.setCellValueFactory(new PropertyValueFactory<>("numofBathroom"));

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
                else if(Property.getFacilities().toLowerCase().contains(propertyNameSearchToLowerCase)){
                    return true;
                }
                else{
                    return false;
                }
            });

        });

        propertyTypeFilter.valueProperty().addListener((Observable,oldValue,newValue) -> {
            propertyFilteredList.setPredicate(Property ->{
                if (newValue.equals("All") || newValue.isEmpty()){
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

        TableViewSelectionModel<Property> propTableViewSelectionModel = propTableView.getSelectionModel();
        propTableViewSelectionModel.setSelectionMode(SelectionMode.SINGLE);

        propTableView.setRowFactory(new Callback<TableView<Property>, TableRow<Property>>() {  
            @Override  
            public TableRow<Property> call(TableView<Property> tableView2) {  
                final TableRow<Property> row = new TableRow<>();  
                row.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {  
                    @Override  
                    public void handle(MouseEvent event) {  
                        final int index = row.getIndex();  
                        if (index >= 0 && index < propTableView.getItems().size() && propTableView.getSelectionModel().isSelected(index)  ) {
                            propTableView.getSelectionModel().clearSelection();
                            event.consume();  
                        }  
                    }  
                });  
                return row;  
            }  
        });  

        
    }

    public void addPropertyButtonHandler() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/fxml/admin/adminAddPropertyScene.fxml"));
        Parent root = loader.load();

        adminAddPropertySceneController controller = loader.getController();
        controller.passedInAdminObject(admin);

        Stage stage = (Stage) addPropertyButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void backButtonHandler() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/fxml/admin/adminHomepageScene.fxml"));
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

        if (propertyToBeDeleted.isEmpty()){
            (new Alert(AlertType.ERROR,"Please select a property to delete")).show();
        }
        else{
            Alert confirmation_Alert = new Alert(AlertType.CONFIRMATION,"Do you wish to delete in this property?",ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            confirmation_Alert.showAndWait();
            
            if (confirmation_Alert.getResult() == ButtonType.YES){
                admin.removeSelectedProperty(propertyToBeDeleted);
            }
        }
    }

    public void editPropertyButtonHandler() throws IOException{
        ObservableList<Property> propertyToBeEdited = propTableView.getSelectionModel().getSelectedItems();

        if (propertyToBeEdited.isEmpty()){
            (new Alert(AlertType.ERROR,"Please select a property to edit")).show();
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/fxml/admin/adminEditPropertyScene.fxml"));
            Parent parent = loader.load();

            adminEditPropertySceneController controller = loader.getController();
            controller.passPropertyToBeEdited(propertyToBeEdited.get(0));
            controller.passedInAdminObject(admin);
            
            Stage stage = (Stage) editPropertyButton.getScene().getWindow();
            stage.setScene(new Scene(parent));
        }
        

    }
    
}
