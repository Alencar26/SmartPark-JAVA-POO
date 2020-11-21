package View.veiculo;
import java.io.IOException;
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
    	
    	double preco = valor.getValor();
    	
    	DecimalFormat formatador = new DecimalFormat("0.00");
    	String Valor = formatador.format(preco);
    	lblValor.setText(Valor);
    }
    
    @FXML
    void SairVeiculo(ActionEvent event) {
    	String placa = txtBuscaVeiculo.getText();
    	VeiculoDAO veiculoDAO = new VeiculoDAO();
    	veiculoDAO.excluir(placa);
    	ControllerInicial telaInicial = new ControllerInicial();
    	//telaInicial.listarVeiculosNoPatio();
    	
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
