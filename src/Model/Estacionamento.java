package Model;


public class Estacionamento {

	
	private String nome;
	private int vagas;
	
	

	public Estacionamento(String nome, int vagas) {
		super();
		this.nome = nome;
		this.vagas = vagas;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getVagas() {
		return vagas;
	}


	public void setVagas(int vagas) {
		this.vagas = vagas;
	}


	@Override
	public String toString() {
		return "Estacionamento: " + nome;
	}
	
}
