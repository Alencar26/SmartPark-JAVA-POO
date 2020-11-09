package View.estacionamento;

import java.io.IOException;

import DAO.EstacionamentoDAO;
import Model.Estacionamento;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerEstacionamento extends Application {

    @FXML
    private TextField txtNomeEstacionamento;

    @FXML
    private TextField txtQntVagas;

    @FXML
    private Button btnAtualiza;

    @FXML
    void AtualizaInformacoes(ActionEvent event) {
    	Controlador();
    	limpaDados();
    }

    @FXML
    void NomeEstacionamento(ActionEvent event) {
    	Estacionamento estac = new Estacionamento();
    	estac = ProcuraInfo();
    	
    	txtNomeEstacionamento.setText(estac.getNome());
    	txtQntVagas.setText(estac.getVagas()+"");
    }

    @FXML
    void QuantidadeVagas(ActionEvent event) {

    }

    private void Controlador() {

    	Estacionamento estac = new Estacionamento();
    	estac = ProcuraInfo();
    	
    	if(estac == null) {
    		Cadastro();
    	}else if(estac != null) {
    		Atualizar();
    	}
    	
    }
    
    private void Cadastro() {
    	
    	Estacionamento estac = PegaDados();
    	int qnt = new EstacionamentoDAO().inserir(estac);
    	System.out.println(qnt);
    	
    }
    
    private void Atualizar() {
    	
    	Estacionamento estac = PegaDados();
    	int qnt = new EstacionamentoDAO().alterar(estac);
    	System.out.println(qnt);
    	
    }
    
    private Estacionamento ProcuraInfo() {
    	
    	Estacionamento estac = new Estacionamento();
    	estac = new EstacionamentoDAO().findById();
    	return estac;
    }

    
    private Estacionamento PegaDados() {
    	
    	int vaga = 0;
    	return new Estacionamento(txtNomeEstacionamento.getText(), vaga = Integer.parseInt(txtQntVagas.getText()));
    	
    }
    
    
    private void limpaDados() {
    	txtNomeEstacionamento.clear();
    	txtQntVagas.clear();
    }
    
    public void execute() {
    	launch();
    }
    
    @Override
    public void start(Stage stage) {
    	
    	try {
    		AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("Estacionamento.fxml"));
    		stage.setTitle("Tela Estacionamento");
    		Scene sc = new Scene(pane);
    		stage.setScene(sc);
    		stage.show();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    }
    
}
