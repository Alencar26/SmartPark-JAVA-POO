package View.veiculo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import DAO.VeiculoDAO;
import Model.RegistroEstacionamento;
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

    
    private String caminhoCupom = "ENTRADA.txt";
    
    @FXML
    void CadastrarVeiculo(ActionEvent event) throws IOException {
    	RegistroEstacionamento veiculo = pegaDados();
    	limpaCampo();
    	int qntd = new VeiculoDAO().inserir(veiculo);
    	System.out.println(qntd);
    	
    	GeraCupom(veiculo);
    	
    	Stage stage = (Stage) btnCadastro.getScene().getWindow();
    	stage.close();
        
    }
    

     
    private RegistroEstacionamento pegaDados() {
    	LocalDateTime entrada;
    	LocalDateTime saida = null;
    	
    	entrada = LocalDateTime.now();
    	saida = null;
    	
    	return new RegistroEstacionamento(txtPlaca.getText(), txtMarca.getText(), txtModelo.getText(), txtCor.getText(), entrada, saida);
    }
    
    private void GeraCupom(RegistroEstacionamento veiculo) throws IOException {
    	//Gera cupom
    	String data = veiculo.getEntrada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
    	String hora = veiculo.getEntrada().format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString();
    	Cupom(caminhoCupom, CorpoCupom(veiculo.getPlaca(),veiculo.getMarca(), veiculo.getModelo(), veiculo.getCor(), data, hora));
    	//ABRE ARQUIVO
    	java.awt.Desktop.getDesktop().open( new File( "ENTRADA.txt" ) );
    }
    
    private void Cupom(String caminhaArquivo, String textoArquivo) {
    	
    	try(
    			FileWriter criadorArquivo = new FileWriter(caminhaArquivo, false);
    			BufferedWriter buffer = new BufferedWriter(criadorArquivo);
    			PrintWriter escritorArquivo = new PrintWriter(buffer);
    			
    			){
    		
    		escritorArquivo.append(textoArquivo);
    		
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    }
    
    private String CorpoCupom(String placa,String marca, String modelo, String cor, String data, String hora) {
    	
    	return "=Smart Park====================================\r\n" + 
    			"	SMART-PARCK ESTACIONAMENTOS LTDA\r\n" + 
    			"	    CNPJ:00.000.000/0001-00\r\n" + 
    			"===============================================\r\n" + 
    			"\r\n" + 
    			"DATA   : "+data+"\r\n" + 
    			"HORA   : "+hora+"\r\n" + 
    			"\r\n" + 
    			"PLACA  : "+placa+"\r\n" + 
    			"MARCA  : "+marca+"\r\n" + 
    			"MODELO : "+modelo+"\r\n" + 
    			"COR    : "+cor+"\r\n" + 
    			"\r\n" + 
    			"ENDEREÇO - MARECHAL FLORIANO PEIXOTO \r\n" + 
    			"NÚMERO - 15\r\n" + 
    			"\r\n" + 
    			"\r\n" + 
    			"HORÁRIO DE FUNCIONAMENTO\r\n" + 
    			"07:30 ATÉ 20:00\r\n" + 
    			"OBRIGADO PELA PREFERÊNCIA\r\n" + 
    			"\r\n" + 
    			"===============================================\r\n";
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