package Model;

public class Funcionario extends Pessoa {

	private String login;
	private String senha;

	public Funcionario() {
		this("admin", "123");
	}

	public Funcionario(String nome, Long cpf, String telefone, String login, String senha) {
		super(nome, cpf, telefone);
		this.login = login;
		this.senha = senha;
	}

	public Funcionario(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Funcionario [Login: " + this.login + ", Senha: " + this.senha+ ", Nome: " + super.getNome() + ", CPF: "
				+ super.getCpf() + ", Telefone: " + super.getTelefone() + "]";
	}
	
	
}
