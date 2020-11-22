package Main;
import java.io.IOException;
import View.inicial.ControllerInicial;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application{

	@Override
    public void start(Stage stage) {
    	
    	try {
    		AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("/View/inicial/Inicial.fxml"));
    		stage.setTitle("Inicial");
    		Scene sc = new Scene(pane);
    		stage.setScene(sc);
    		stage.show();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	 
    } 
	
	
	public static void main(String[] args) {
		
		Application.launch(args);
		
	}
	
}
