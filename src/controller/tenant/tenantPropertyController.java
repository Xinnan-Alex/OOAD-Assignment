package controller.tenant;
import model.*;

//JAVA IMPORTS
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.transformation.FilteredList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class tenantPropertyController implements Initializable{

    Tenant loggedinPerson;
    FilteredList<Property> propertyFilter;
    public static String[] propertyTypeFilterList = {"All","Bungalow","Semi-D","Terrace","Townhouse","Penthouse","Condominium","Duplex","Apartment","Unspecified"};

    @FXML
    ListView<Property> propertyListing;
    
    @FXML
    private Button backButton;

    @FXML
    TextField searchBarInput;

    @FXML
    ComboBox<String> propertyTypeFilter;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        propertyFilter = new FilteredList<>(Model.propertyList,b->true);

        searchBarInput.textProperty().addListener((Observable,oldValue,newValue) -> {
            propertyFilter.setPredicate(Property ->{
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

        propertyTypeFilter.getItems().addAll(propertyTypeFilterList);
        propertyTypeFilter.setValue(propertyTypeFilterList[0]);

        propertyTypeFilter.valueProperty().addListener((Observable,oldValue,newValue) -> {
            propertyFilter.setPredicate(Property ->{
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
        
        propertyListing.setCellFactory(new Callback<ListView<Property>, ListCell<Property>>() {
            @Override
            public ListCell<Property> call(ListView<Property> listView) {
                return new CustomListCell();
            }
        });

        //propertyFilter = new FilteredList<>(Model.propertyList, b -> true);

        //ObservableList<Property> propertyView = propertyFilter;
        propertyFilter.removeIf(p -> p.getHiddenStatus() == true); //hidden status
        propertyListing.setItems(propertyFilter);
    }

    public void initUserObejct(Tenant passedIN){
        loggedinPerson = passedIN;
    }

    public void backButtonHandler() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/tenant/tenantHomepageScene.fxml"));
        Parent root = fxmlLoader.load();
        
        tenantHomepageSceneController controller = fxmlLoader.getController();
        controller.initUserObejct(loggedinPerson);
        
        Stage window = (Stage)backButton.getScene().getWindow();
        window.setScene(new Scene(root)); 
    }

    private class CustomListCell extends ListCell<Property> {
        private HBox content;
        private Text name;
        private Text price;
        private Text propertyType;
        private Property property;
        

        private Button tenantPropertyInfo = new Button("More Info"); //button for addreess pop out
        // protected Stage primaryStage;
        
        public CustomListCell() {
            super();
            name = new Text();
            price = new Text();
            propertyType = new Text();

            VBox vBox = new VBox(name, price);
            content = new HBox(propertyType, vBox, tenantPropertyInfo);
            content.setSpacing(30);
            tenantPropertyInfo.setOnAction(new EventHandler() {

                @Override
                public void handle(Event arg0) {

                    try{                        
                    System.out.println(name.getText());
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/tenant/tenantPropertyInfoScene.fxml"));
                    Parent root = fxmlLoader.load();
                    tenantPropertyInfoController controller = fxmlLoader.getController();
                    controller.initUserObejct(loggedinPerson);
                    controller.passPropertyObject(property);
                    
                    Stage window = (Stage)backButton.getScene().getWindow();
                    window.setScene(new Scene(root, 750, 500)); 

                    }catch(Exception e){
                        e.printStackTrace();
                    } 
                }  
                });
        }

        @Override
        protected void updateItem(Property p, boolean empty) {
            super.updateItem(p, empty);
            if (p != null && !empty) { // <== test for null item and empty parameter
                name.setText(p.getProjectName());
                propertyType.setText(p.getPropertyType());
                property = p;
                price.setText(String.format("%d $", p.getRentalRate()));
                setGraphic(content);
                
                
            } else {
                setGraphic(null);
            }
        }
    } 
}