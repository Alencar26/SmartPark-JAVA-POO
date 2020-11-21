package View.inicial;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import DAO.EstacionamentoDAO;
import DAO.VeiculoDAO;
import Model.Estacionamento;
import Model.RegistroEstacionamento;
import Model.Valor;
import View.veiculo.ControllerVeiculoSaida;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import testes.TesteTabela;


public class ControllerInicial extends Application implements Initializable {

	@FXML
    private Button btnEntrada;

    @FXML
    private Button btnSaida;

    @FXML
    private Button btnFuncionario;

    @FXML
    private Button btnSairAplicacao;

    @FXML
    private Button btnValores;

    @FXML
    private Button btnEstac;

    @FXML
    private Label lblNumVagas;

    @FXML
    private Label lbldataHora;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Label lblTotalVagas;

    @FXML
    private TableView<RegistroEstacionamento> Tabela;

    @FXML
    private TableColumn<RegistroEstacionamento, String> ColunaPlaca;

    @FXML
    private TableColumn<RegistroEstacionamento, String> ColunaMarca;

    @FXML
    private TableColumn<RegistroEstacionamento, String> ColunaModelo;

    @FXML
    private TableColumn<RegistroEstacionamento, String> ColunaCor;

    @FXML
    private TableColumn<RegistroEstacionamento, String> ColunaEntrada;

    private ObservableList<RegistroEstacionamento> observableListVeiculos;
    
    
  public void carregaTabela() {
    	
    	ColunaPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
    	ColunaMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
    	ColunaModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
    	ColunaCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
    	ColunaEntrada.setCellValueFactory(new PropertyValueFactory<>("entrada")); 	
    	
    	
    	List<RegistroEstacionamento> listaVeiculo = new VeiculoDAO().listarTodos();
    	observableListVeiculos = FXCollections.observableArrayList(listaVeiculo);
    	
    	Tabela.setItems(observableListVeiculos);
    }

	  private void ConfirmaSelecao(RegistroEstacionamento v) {
	  	System.out.println("Veiculo Selecionado: "+ v);
	  	
	  	new ControllerVeiculoSaida().execute();
	  }
	  
	  private void VeicuoSelecionado() {
	  	
		  Tabela.getSelectionModel().selectedItemProperty().addListener(
	  			(observable, oldValue, newValue) -> ConfirmaSelecao(newValue));
	  	
	  	
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
    void TelaEstacionamento(ActionEvent event) throws IOException{
    	Pane testPane = FXMLLoader
				.load(getClass().getResource("/View/estacionamento/Estacionamento.fxml"));

		Scene scene = new Scene(testPane);
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("TelaEstacionamento");
		primaryStage.show();
    }
    
    
    @FXML
    void atualizarLista(ActionEvent event) {
    	carregaTabela();
    	//listarVeiculosNoPatio();
    	ContaVagas();
    	int contagem = new EstacionamentoDAO().Contagem();
    	System.out.println(contagem);
    	
    }
    
    
    @FXML
    void SairAplicacao(ActionEvent event) {
    	btnSairAplicacao.setOnAction(e -> Platform.exit());
    }
    
    
    @FXML
    void TelaValores(ActionEvent event) throws IOException{

    	Pane testPane = FXMLLoader
				.load(getClass().getResource("/View/valor/Valor.fxml"));

		Scene scene = new Scene(testPane);
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("TelaValores");
		primaryStage.show();
    	
    }

    
    private void ContaVagas() {
    	
    	Estacionamento estac = new Estacionamento();
    	estac = new EstacionamentoDAO().findById();
    	
    	int totalVagas = estac.getVagas();
    	int vagasOcupdas = new EstacionamentoDAO().Contagem();
    	
    	int vagasDisponiveis = totalVagas - vagasOcupdas;
    	
    	lblTotalVagas.setText(totalVagas+"");
    	lblNumVagas.setText(vagasDisponiveis+"");
    	
    }
   
    private void Hora() {
        lbldataHora.setText("Horário de entrada: "+LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
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
    
    public void execute() {
    	launch();
    }

    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	carregaTabela();
    	VeicuoSelecionado();
    	Hora();
    	ContaVagas();
	}
    
}
