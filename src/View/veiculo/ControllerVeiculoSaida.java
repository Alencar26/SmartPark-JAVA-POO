package View.veiculo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import DAO.ValorDAO;
import DAO.VeiculoDAO;
import Model.RegistroEstacionamento;
import Model.Valor;
import View.inicial.ControllerInicial;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ControllerVeiculoSaida extends Application {

    @FXML
    private TextField txtBuscaVeiculo;

    @FXML
    private Button btnBuscaVeiculo;

    @FXML
    private Button btnConfirmaSaida;

    @FXML
    private Label lblPlaca;

    @FXML
    private Label lblValor;

    @FXML
    private Label lblMarca;

    @FXML
    private Label lblModelo;

    @FXML
    private Label lblCor;

    @FXML
    private Label lblEntrada;
    
    @FXML
    private Label lblSaida;
    
    @FXML
    private Label lblEstadia;
    
    private String caminhoCupom = "SAIDA.txt";
    private String valorAPagar = "";
    
    public void execute() {
    	launch();
    }
    
    @FXML
    void BuscaVeiculo(ActionEvent event) throws ParseException {

    	String placa = txtBuscaVeiculo.getText();
    	RegistroEstacionamento veiculo = null;
    	if(!placa.equals("")) {
    		try {
    			veiculo = new VeiculoDAO().findByPLACA(placa);
    		}catch(Exception e) {
    			
    		
    		}
    		
    		if(veiculo != null) {
    			lblPlaca.setText(veiculo.getPlaca());
    			lblMarca.setText(veiculo.getMarca());
    			lblModelo.setText(veiculo.getModelo());
    			lblCor.setText(veiculo.getCor());
    			lblEntrada.setText(veiculo.getEntrada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")).toString());
    			String entrada = veiculo.getEntrada().format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString();
    			String saida = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    			String saidaD = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    			System.out.println(saida + " AQUI");
    			lblSaida.setText(saida);
    			CalculaTempoDePermanencia(entrada, saidaD);
    			//CUPOM
    			String data = veiculo.getEntrada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
    			Cupom(caminhoCupom, CorpoCupom(veiculo.getPlaca(), veiculo.getMarca(), veiculo.getModelo(), veiculo.getCor(), data, entrada, saidaD, valorAPagar));
    		}
    	}
    }
    
    
    private void CalculaTempoDePermanencia(String Hentrada, String Hsaida) throws ParseException {
    	
    
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		Date horaIni = sdf.parse(Hentrada);
        Date horaFim = sdf.parse(Hsaida);
		
        double horaI = horaIni.getTime();
        double horaF = horaFim.getTime(); 
		 
        double diferencaHoras = horaF - horaI;
		 
        diferencaHoras = diferencaHoras / 1000 / 60 / 60; 
        DecimalFormat formatador = new DecimalFormat("0.00");
        
        String difHoras = formatador.format(diferencaHoras);
        lblEstadia.setText(difHoras);
        CalculoValoraAPagar(diferencaHoras);

    }
    
    private void CalculoValoraAPagar(double diferencaHoras) {
    	
    	
    	Valor valor = new ValorDAO().findByHours(diferencaHoras);
    	System.out.println(valor.getValor());
    	double preco = valor.getValor();
    	
    	DecimalFormat formatador = new DecimalFormat("0.00");
    	String Valor = formatador.format(preco);
    	valorAPagar = Valor;
    	lblValor.setText(Valor);
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
    
    private String CorpoCupom(String placa,String marca, String modelo, String cor, String data, String horaEntrada, String horaSaida, String valor) {
    	
    	return "=Smart Park====================================\r\n" + 
    			"	SMART-PARCK ESTACIONAMENTOS LTDA\r\n" + 
    			"	    CNPJ:00.000.000/0001-00\r\n" + 
    			"===============================================\r\n" + 
    			"\r\n" + 
    			"DATA      : "+data+"      PLACA  : "+placa+"\r\n" + 
    			"ENTRADA   : "+horaEntrada+"	    MARCA  : "+marca+"\r\n" + 
    			"SAÍDA     : "+horaSaida+"	    MODELO : "+modelo+"\r\n" + 
    			"			    COR    : "+cor+"\r\n" + 
    			"\r\n" + 
    			"\r\n" + 
    			"\r\n" + 
    			"	    VALOR PAGO: R$"+valor+"\r\n" + 
    			"\r\n" + 
    			"\r\n" + 
    			"\r\n" + 
    			"ENDEREÇO - MARECHAL FLORIANO PEIXOTO \r\n" + 
    			"NÚMERO - 15\r\n" + 
    			"\r\n" + 
    			"\r\n" + 
    			"HORÁRIO DE FUNCIONAMENTO\r\n" + 
    			"07:30 ATÉ 20:00\r\n" + 
    			"OBRIGADO PELA PREFERÊNCIA\r\n" + 
    			"\r\n" + 
    			"===============================================\r\n" + 
    			"		Volte Sempre!";
    }
    
    @FXML
    void SairVeiculo(ActionEvent event) throws IOException {
    	String placa = txtBuscaVeiculo.getText();
    	VeiculoDAO veiculoDAO = new VeiculoDAO();
    	veiculoDAO.excluir(placa);
    	ControllerInicial telaInicial = new ControllerInicial();
    	//telaInicial.listarVeiculosNoPatio();
    	
    	java.awt.Desktop.getDesktop().open( new File( "SAIDA.txt" ) );
    	
    	Stage stage = (Stage) btnConfirmaSaida.getScene().getWindow();
    	stage.close();
    }
    
    @Override
    public void start(Stage stage) {
    	
    	try {
    		AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("VeiculoSaida.fxml"));
    		stage.setTitle("Saída Veículo");
    		Scene sc = new Scene(pane);
    		stage.setScene(sc);
    		stage.show();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    }
    
   
    
    
    
}
