package View.veiculo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import DAO.VeiculoDAO;
import Model.RegistroEstacionamento;
import Model.Veiculo;
import View.inicial.ControllerInicial;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerVeiculo extends Application {

    @FXML
    private Button btnCadastro;

    @FXML
    private TextField txtCor;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtPlaca;

    @FXML
    private TextField txtMarca;

    
    @FXML
    void CadastrarVeiculo(ActionEvent event) {
    	RegistroEstacionamento veiculo = pegaDados();
    	limpaCampo();
    	int qntd = new VeiculoDAO().inserir(veiculo);
    	System.out.println(qntd);
    	
        ControllerInicial inicial = new ControllerInicial();
        inicial.listarVeiculosNoPatio();
    }
    

     
    private RegistroEstacionamento pegaDados() {
    	LocalDateTime entrada;
    	LocalDateTime saida = null;
    	
    	entrada = LocalDateTime.now();
    	saida = null;
    	
    	return new RegistroEstacionamento(txtPlaca.getText(), txtMarca.getText(), txtModelo.getText(), txtCor.getText(), entrada, saida);
    }
    
    private void limpaCampo() {
    	txtPlaca.clear();
    	txtMarca.clear();
    	txtCor.clear();
    	txtModelo.clear();
    	
    	txtPlaca.requestFocus();
    }
    
    public void execute() {
    	launch();
    }
    
    @Override
    public void start(Stage stage) {
    	
    	try {
    		AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("Veiculo.fxml"));
    		stage.setTitle("Cadastro Veiculo");
    		Scene sc = new Scene(pane);
    		stage.setScene(sc);
    		stage.show();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    }

}