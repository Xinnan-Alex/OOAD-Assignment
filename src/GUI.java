//GUI CLASS, THE MAIN EXECUTABLE FILE FOR OUR ASSIGNMENT.

//JAVA IMPORTS
import java.io.IOException;

//JAVAFX IMPORTS
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.Parent;

//Gui class
public class GUI extends Application {

    private Scene scene;
    private Parent root;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        root = FXMLLoader.load(getClass().getResource("resources/fxml/loginScene.fxml"));

        scene = new Scene(root);

        primaryStage.setScene(scene);

        //importing mmu logo into the program
        Image stageicon = new Image("resources/images/mmulogo_2.png");

        //adding icon to the window
        primaryStage.getIcons().add(stageicon);
        //adding a title to the window
        primaryStage.setTitle("Cyberjaya Online Rental Managment System");

        //the window is 500x500
        //primaryStage.setWidth(500);
        //primaryStage.setWidth(500);

        //the primarystage is unresizable
        primaryStage.setResizable(false);
        primaryStage.show();

    }
    public void switchScene(String s) throws IOException{
            root = FXMLLoader.load(getClass().getResource(s));
            scene = new Scene(root,Color.LIGHTGREY);
            primaryStage.setScene(scene);
            primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        Model logicModel = new Model();
        logicModel.loadUserInfo();
        logicModel.loadPropertyList();
        launch(args); //launching the GUI
    }
}