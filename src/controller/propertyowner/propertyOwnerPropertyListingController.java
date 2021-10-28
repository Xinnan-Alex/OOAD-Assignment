//ADMIN PROPERTY LISTING INTERFACE CONTROLLER
package controller.propertyowner;

//JAVA IMPORTS
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.io.IOException;

//JAVAFX IMPORTS
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.RadioButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TableRow;
import javafx.event.EventHandler;
import model.*;

//adminPropertyListingController class
public class propertyOwnerPropertyListingController implements Initializable{

    propertyOwner owner;
    FilteredList<Property> propertyFilteredList;
    SortedList<Property> propertySortedList;
    public static String[] propertyTypeFilterList = {"All","Bungalow","Semi-D","Terrace","Townhouse","Penthouse","Condominium","Duplex","Apartment","Unspecified"};
    private String[] rentalRateSortComboBoxList = {"Low to High","High to Low"};

    @FXML
    TableView<Property> propTableView;

    @FXML
    TableColumn<Property,String> noColumn,propAdressColumn,propSizeColumn,propRentalRateColumn,propOwnerColumn,propOwnerContactNumColumn,propRentalStatus,propTypeColumn,propIDColumn;

    @FXML
    TableColumn<Property,Integer> numofRoomColumn,numofBathroomColumn;

    @FXML
    TextField propertyNameSearch,propertyFacilitySearch;

    @FXML
    Button addPropertyButton,deletePropertyButton,editPropertyButton,backButton;

    @FXML
    ComboBox<String> propertyTypeFilter,rentalRateSortComboBox;

    @FXML
    RadioButton activePropertyRadioButton,inactivePropertyRadioButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        propTableView.setStyle("-fx-selection-bar: #87CEFA;");
        propertyFilteredList = new FilteredList<>(Model.propertyList,b->true);
        propertySortedList = new SortedList<>(propertyFilteredList,Comparator.comparing(Property::getRentalRate));

        rentalRateSortComboBox.getItems().addAll(rentalRateSortComboBoxList);
        propertyTypeFilter.getItems().addAll(propertyTypeFilterList);
        propertyTypeFilter.setValue(propertyTypeFilterList[0]);
        rentalRateSortComboBox.setValue(rentalRateSortComboBoxList[0]);

        noColumn.setCellValueFactory(new Callback<CellDataFeatures<Property, String>, ObservableValue<String>>() {
            @Override 
            public ObservableValue<String> call(CellDataFeatures<Property, String> p) {
                return new ReadOnlyObjectWrapper(propTableView.getItems().indexOf(p.getValue()) + 1);
            }
            });
        noColumn.setSortable(false);
        
        propAdressColumn.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        propSizeColumn.setCellValueFactory(new PropertyValueFactory<>("propertySize"));
        propRentalRateColumn.setCellValueFactory(new PropertyValueFactory<>("rentalRate"));
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

        propertyFacilitySearch.textProperty().addListener((Observable,oldValue,newValue) -> {
            propertyFilteredList.setPredicate(Property ->{
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }

                String propertyNameSearchToLowerCase = newValue.toLowerCase();

                if (Property.getFacilities().toLowerCase().contains(propertyNameSearchToLowerCase)){
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

        propertySortedList.comparatorProperty().bind(propTableView.comparatorProperty());

        propTableView.setItems(propertySortedList);

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

        rentalRateSortComboBoxHandler();
    }

    public void addPropertyButtonHandler() throws IOException{
        //CHANGE TO PROPERTYOWNERADDPROPERTYSCENE.FXML 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/admin/adminAddPropertyScene.fxml"));
        Parent root = loader.load();

        propertyOwnerAddPropertySceneController controller = loader.getController();
        controller.passedInOwnerObject(owner);

        Stage stage = (Stage) addPropertyButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void backButtonHandler() throws IOException{
        //CHANGE TO PROPERTYOWNERHOMEPAGESCENE.FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/admin/adminHomepageScene.fxml"));
        Parent root = loader.load();

        propertyOwnerHomepageSceneController controller =  loader.getController();
        controller.initUserObejct(owner);

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void initialiseOwnerInfo(propertyOwner passedIn){
        owner = passedIn;
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
                owner.removeSelectedProperty(propertyToBeDeleted);
            }
        }
    }

    public void editPropertyButtonHandler() throws IOException{
        ObservableList<Property> propertyToBeEdited = propTableView.getSelectionModel().getSelectedItems();

        if (propertyToBeEdited.isEmpty()){
            (new Alert(AlertType.ERROR,"Please select a property to edit")).show();
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/admin/adminEditPropertyScene.fxml"));
            Parent parent = loader.load();

            propertyOwnerEditPropertySceneController controller = loader.getController();
            controller.passPropertyToBeEdited(propertyToBeEdited.get(0));
            controller.passedInOwnerObject(owner);
            
            Stage stage = (Stage) editPropertyButton.getScene().getWindow();
            stage.setScene(new Scene(parent));
        }
        

    }

    public void activePropertyRadioButtonHandler(){
        if(activePropertyRadioButton.isSelected()){
            propertyFilteredList.setPredicate(Property ->{

                if (Property.getRentStatus().equals("active")){
                    return true;
                }
                else{
                    return false;
                }
            });
        }
        else{
            propertyFilteredList.setPredicate(Property->{return true;});
        }
    }

    public void inactivePropertyRadioButtonHandler(){
        if(inactivePropertyRadioButton.isSelected()){
            propertyFilteredList.setPredicate(Property ->{

                if (Property.getRentStatus().equals("inactive")){
                    return true;
                }
                else{
                    return false;
                }
            });
        }
        else{
            propertyFilteredList.setPredicate(Property->{return true;});
        }
    }

    public void rentalRateSortComboBoxHandler(){
        String selectedComboBoxString = rentalRateSortComboBox.getSelectionModel().getSelectedItem().toLowerCase();

        if(selectedComboBoxString.equals("low to high")){
            
            propRentalRateColumn.setSortType(TableColumn.SortType.ASCENDING);
            propTableView.getSortOrder().add(propRentalRateColumn);
            propTableView.sort();
        }
        else if(selectedComboBoxString.equals("high to low")){

            propRentalRateColumn.setSortType(TableColumn.SortType.DESCENDING);
            propTableView.getSortOrder().add(propRentalRateColumn);
            propTableView.sort();
        }
    }

    class rentralRateAscendingComaprator implements Comparator<Property>{
        public int compare(Property p1, Property p2) {
            //first come
        if (p1.getRentalRate() < p2.getRentalRate()){
            return -1;
        }
        else
            return 1;

        }
    }

}

