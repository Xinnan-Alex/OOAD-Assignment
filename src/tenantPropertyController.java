//JAVA IMPORTS
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
//JAVAFX IMPORTS
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class tenantPropertyController implements Initializable{

    tenant loggedinPerson;
    FilteredList<Property> propertyFilter;

    @FXML
    ListView<Property> propertyListing;
    
    @FXML
    private Button backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        propertyListing.setCellFactory(new Callback<ListView<Property>, ListCell<Property>>() {
            @Override
            public ListCell<Property> call(ListView<Property> listView) {
                return new CustomListCell();
            }
        });

        //propertyFilter = new FilteredList<>(Model.propertyList, b -> true);

        ObservableList<Property> propertyView = Model.propertyList;
        propertyView.removeIf(p -> p.getHiddenStatus() == true); //hidden status
        propertyListing.setItems(propertyView);
    }

    public void initUserObejct(tenant passedIN){
        loggedinPerson = passedIN;
    }

    public void backButtonHandler() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/fxml/tenant/tenantHomepageScene.fxml"));
        Parent root = fxmlLoader.load();
        
        tenantHomepageSceneController controller = fxmlLoader.getController();
        controller.initUserObejct(loggedinPerson);
        
        Stage window = (Stage)backButton.getScene().getWindow();
        window.setScene(new Scene(root, 750, 500)); 
    }

    private class CustomListCell extends ListCell<Property> {
        private HBox content;
        private Text name;
        private Text price;

        public CustomListCell() {
            super();
            name = new Text();
            price = new Text();
            VBox vBox = new VBox(name, price);
            content = new HBox(new Label("[Graphic]"), vBox);
            content.setSpacing(10);
        }

        @Override
        protected void updateItem(Property property, boolean empty) {
            super.updateItem(property, empty);
            if (property != null && !empty) { // <== test for null item and empty parameter
                name.setText(property.getProjectName());
                price.setText(String.format("%d $", property.getRentalRate()));
                setGraphic(content);
            } else {
                setGraphic(null);
            }
        }
    } 
}