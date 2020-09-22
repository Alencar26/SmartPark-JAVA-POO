package Model;

public class Veiculo {

	
	private String marca;
	private String placa;
	private String cor;
	private String nomeVeiculo;

	public Veiculo() {

	}

	public Veiculo( String marca, String placa, String cor, String nomeVeiculo) {
		
		this.marca = marca;
		this.placa = placa;
		this.cor = cor;
		this.nomeVeiculo = nomeVeiculo;
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

	public String getNomeVeiculo() {
		return nomeVeiculo;
	}

	public void setNomeVeiculo(String nomeVeiculo) {
		this.nomeVeiculo = nomeVeiculo;
	}

	@Override
	public String toString() {
		return "Veiculo [ Marca:" + marca + ", Placa:" + placa + ", Cor:" + cor
				+ ", Nome do Veiculo:" + nomeVeiculo + "]";
	}
	
	
}
