package testes;

import java.sql.Connection;

import db.dbConection;

public class testeConexao {

	public static void main(String[] args) {
		
		
	dbConection conn = new dbConection();
	
	Connection connection = conn.conectar();
	
	System.out.println(connection);
	
	
	//inserir dados
	// parei em 56:40
	
	
	}
}
