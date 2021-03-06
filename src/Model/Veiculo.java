package Model;

public class Veiculo {

	private String placa;
	private String marca;
	private String modelo;
	private String cor;
	
	
	public Veiculo() {

	}

	public Veiculo( String placa, String marca, String modelo, String cor ) {
		
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.cor = cor;
		
	}

	
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@Override
	public String toString() {
		
		return "Ve�culo: [ PLACA: "+ placa +" - MARCA: "+ marca +" - MODELO: "+ modelo +" - COR: "+ cor +" ]";
	}
	
	
}
