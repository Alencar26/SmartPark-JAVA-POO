package Model;

public class Valor {

	private float id;
	private float valor;
	
	
	public Valor() {
		
	}
	
	public Valor(float id, float valor) {
		this.id = id;
		this.valor = valor;
	}
	

	public float getId() {
		return id;
	}

	public void setId(float id) {
		this.id = id;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Valor [id=" + id + ", valor=" + valor + "]";
	}

	
	
}
