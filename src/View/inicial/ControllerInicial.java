package View.inicial;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;

import DAO.VeiculoDAO;
import Model.RegistroEstacionamento;
import Model.Veiculo;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class ControllerInicial extends Application implements Initializable {

    @FXML
    private Button btnEntrada;

    @FXML
    private Button btnSaida;

    @FXML
    private TextArea txtAreaVeiculos;

    @FXML
    private Button btnFuncionario;

    @FXML
    private Button btnSairAplicacao;

    @FXML
    private Button btnValores;

    @FXML
    private Label lblNumVagas;

    @FXML
    private Label lbldataHora;
    
    
    
    public void execute() {
    	launch();
    }
    
    @Override
    public void start(Stage stage) {
    	
    	try {
    		AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("Inicial.fxml"));
    		stage.setTitle("Inicial");
    		Scene sc = new Scene(pane);
    		stage.setScene(sc);
    		stage.show();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    }
     
    @FXML
    void TelaVeiculoEntrada(ActionEvent event) throws IOException {
    	Pane testPane = FXMLLoader
				.load(getClass().getResource("/View/veiculo/Veiculo.fxml"));

		Scene scene = new Scene(testPane);
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("VeiculosEntrada");
		primaryStage.show();
		
    }
    
    @FXML
    void TelaVeiculoSaida(ActionEvent event) throws IOException {
    	Pane testPane = FXMLLoader
				.load(getClass().getResource("/View/veiculo/VeiculoSaida.fxml"));

		Scene scene = new Scene(testPane);
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("VeiculosSaida");
		primaryStage.show();
		
    }
    
    @FXML
    void SairAplicacao(ActionEvent event) {
    	btnSairAplicacao.setOnAction(e -> Platform.exit());
    }
    

    public void listarVeiculosNoPatio() {
    	txtAreaVeiculos.clear();
    	List<RegistroEstacionamento> listaVeiculo = new VeiculoDAO().listarTodos();
    	
    	listaVeiculo.forEach(veiculo -> {
    		txtAreaVeiculos.appendText(veiculo.toString() + "\n");
    	});
    	
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	listarVeiculosNoPatio();	
	}
    
}
