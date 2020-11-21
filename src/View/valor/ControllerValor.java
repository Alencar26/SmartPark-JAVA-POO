package View.valor;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;
import DAO.ValorDAO;
import Model.Valor;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerValor extends Application implements Initializable {

	 	@FXML
	    private TableView<Valor> tblHorarioValor;

	    @FXML
	    private TableColumn<Valor, String> ColunaHoras;

	    @FXML
	    private TableColumn<Valor, String> ColunaValores;
	    @FXML
	    private Button btnAdiciona;

	    @FXML
	    private TextField txtHorario;

	    @FXML
	    private TextField txtValor;

	    @FXML
	    private Button btnBusca;

	    @FXML
	    private TextField txtBuscaHorario;

	    @FXML
	    private TextField txtNovoValor;

	    @FXML
	    private Button btnAtualizaValor;

	    @FXML
	    private Button btnDeletaValor;

	    @FXML
	    private Label lblMostraHorario;

	    @FXML
	    private Label lblMostraValor;
    
    
	    
	    
	    @FXML
	    void CadastraValor(ActionEvent event) {

	    	Valor valorHora = PegaDados();

	    	limpaCampos();
	    	ListaValores();
	    	ConfirmaSelecao();
	    	int retorno = new ValorDAO().inserir(valorHora);
	    	System.out.println(retorno);
	    	
	    }
	    
	    
	    @FXML
	    void BuscandoValor(ActionEvent event) {

	    	float horario = Float.parseFloat(txtBuscaHorario.getText());
	    	Valor valor = null;
	    	
	    	DecimalFormat formatador = new DecimalFormat("0.00");  
	        
	    	
	    	if(horario != 0) {
	    		try {
	    			valor = new ValorDAO().findById(horario);
	    		}catch(Exception e) {
	    			System.out.println("ALGO NÃO ESTÁ CERTO");
	    		}
	    	
	    		if(valor != null) {
	    			
	    			lblMostraHorario.setText("Horário:  " + valor.getId()+" horas");
	    			String valorAPAgar = formatador.format(valor.getValor());
	    			lblMostraValor.setText("Valor: R$ "+ valorAPAgar);
	    		}else {
	    			System.out.println("ALGO NÃO ESTÁ CERTO");
	    		}
	    	}
	    	
	    }
	    

	    @FXML
	    void Deletar(ActionEvent event) {

	    	float horario = Float.parseFloat(txtBuscaHorario.getText());
	    	ValorDAO valor = new ValorDAO();
	    	valor.excluir(horario);
	    	System.out.println("Excluido");
	    	limpaCampos();
	    }
	    
	    
	    @FXML
	    void AlterarValor(ActionEvent event) {

	    	float horario = Float.parseFloat(txtBuscaHorario.getText());
	    	float novoValor = Float.parseFloat(txtNovoValor.getText());
	    	Valor valor = new Valor();
	    	valor.setId(horario);
	    	valor.setValor(novoValor);
	    	int retorno = new ValorDAO().alterar(valor);
	    	limpaCampos();
	    	System.out.println(retorno);
	    	ListaValores();
	    	limpaCampos();
	    	
	    }
	    
	    private void ListaValores() {

	    	
	    	
	    	
	    	List<Valor> listaValores = new ValorDAO().listarTodos();
	    	System.out.println(listaValores);
	    	ObservableList<Valor> observableListValores;
	    	
	    	observableListValores = FXCollections.observableArrayList(listaValores);
	    	  	
	    	
	    	ColunaHoras = new TableColumn<>("id");
	    	ColunaHoras.setCellValueFactory(Data -> new SimpleStringProperty(Data.getValue().getIdString()));
	    	ColunaValores = new TableColumn<>("valor");
	    	ColunaValores.setCellValueFactory(Data -> new SimpleStringProperty(Data.getValue().getValorString()));
	    	//ColunaHoras.setCellValueFactory(new PropertyValueFactory<Valor, Float>("id"));
	    	//ColunaValores.setCellValueFactory(new PropertyValueFactory<Valor, Float>("valor"));
	    	tblHorarioValor.setItems(observableListValores);
	    }
	    
	    
	    
	    private void ValorSelecionado(Valor v) {
	    	System.out.println("Valor Selecionado: "+ v);
	    	
	    	lblMostraHorario.setText("Horário:  " + v.getId()+" horas");
	    	DecimalFormat formatador = new DecimalFormat("0.00"); 
			String valorAPAgar = formatador.format(v.getValor());
			lblMostraValor.setText("Valor: R$ "+ valorAPAgar);
			txtBuscaHorario.setText(v.getId()+"");
	    }
	    
	    private void ConfirmaSelecao() {
	    	
	    	tblHorarioValor.getSelectionModel().selectedItemProperty().addListener(
	    			(observable, oldValue, newValue) -> ValorSelecionado(newValue));
	    	
	    	
	    }
	    
	    
	    
	    private Valor PegaDados() {
	    	
	    	float horario = Float.parseFloat(txtHorario.getText());
	    	float valor = Float.parseFloat(txtValor.getText());
	    	return new Valor(horario, valor);
	    	
	    }
	    
	    
	    private void limpaCampos() {
	    	txtHorario.clear();
	    	txtValor.clear();
	    	txtBuscaHorario.clear();
	    	lblMostraHorario.setText("Horário:");
	    	lblMostraValor.setText("Valor:");
	    	txtNovoValor.clear();
	    }
    
    public void execute() {
    	launch();
    }
    
    @Override
    public void start(Stage stage) {
    	
    	try {
    		AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("Valor.fxml"));
    		stage.setTitle("Tela de Valores");
    		Scene sc = new Scene(pane);
    		stage.setScene(sc);
    		stage.show();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}

    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	ListaValores();
    	ConfirmaSelecao();
	}
}


