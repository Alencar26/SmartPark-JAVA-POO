package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class RegistroEstacionamento extends Veiculo {

	private LocalDateTime entrada;
	private LocalDateTime saida;
	
	
	public RegistroEstacionamento() {
	}
	
	public RegistroEstacionamento(String placa, String marca, String modelo, String cor, LocalDateTime entrada, LocalDateTime saida) {
		super(placa, marca, modelo, cor);
		this.entrada = entrada;
		this.saida = saida;
	}

	public LocalDateTime getEntrada() {
		return entrada;
	}

	public void setEntrada(LocalDateTime entrada) {
		this.entrada = entrada;
	}

	public LocalDateTime getSaida() {
		return saida;
	}

	public void setSaida(LocalDateTime saida) {
		this.saida = saida;
	}

	@Override
	public String toString() {
		return " REGISTRO: [ PLACA: "+ super.getPlaca() +" - MARCA: "+ super.getMarca() +" - MODELO: "+ super.getModelo() +" - COR: "+ super.getCor() +" - ENTRADA: "+ this.entrada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +" ]";
	}

	
	
	
	
	
	
}
