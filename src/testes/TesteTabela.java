package testes;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import DAO.VeiculoDAO;
import Model.RegistroEstacionamento;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TesteTabela extends Application implements Initializable {

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

    @FXML
    private TableColumn<RegistroEstacionamento, String> ColunaSaida;

    private ObservableList<RegistroEstacionamento> observableListVeiculos;
	
   
    public void carregaTabela() {
    	
    	ColunaPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
    	ColunaMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
    	ColunaModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
    	ColunaCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
    	ColunaEntrada.setCellValueFactory(new PropertyValueFactory<>("entrada"));
    	ColunaSaida.setCellValueFactory(new PropertyValueFactory<>("saida"));    	
    	
    	
    	List<RegistroEstacionamento> listaVeiculo = new VeiculoDAO().listarTodos();
    	observableListVeiculos = FXCollections.observableArrayList(listaVeiculo);
    	
    	Tabela.setItems(observableListVeiculos);
    }
    
    public void executa() {
    	launch();
    }
    
    @Override
    public void start(Stage stage) {
    	
    	try {
    		AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("TesteTabelaView.fxml"));
    		stage.setTitle("Tabela");
    		Scene sc = new Scene(pane);
    		stage.setScene(sc);
    		stage.show();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}

    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	carregaTabela();
	}
    
}
