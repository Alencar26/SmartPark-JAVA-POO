package testes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import DAO.VeiculoDAO;
import Model.Veiculo;
import db.dbConection;

public class testeConexao {

	public static void main(String[] args) {
		
/*	
	dbConection conn = new dbConection();
	
	Connection connection = conn.conectar();
	
	System.out.println(connection);
	
	
	//inserir dados
	
	final String SQL_INSERT_VEICULO = "INSERT INTO VEICULO(PLACA, MARCA, MODELO, COR) VALUES(?, ?, ?, ?)";
	
		PreparedStatement pst;
		try {
			
			pst = connection.prepareStatement(SQL_INSERT_VEICULO);
			
			pst.setString(1, "GHI789");
			pst.setString(2, "FORD");
			pst.setString(3, "RANGER");
			pst.setString(4, "BRANCA");
			
			int quantidade = pst.executeUpdate();
			System.out.println("Quantidade inserido: "+ quantidade);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		//listar
		
		final String SQL_SELECT_VEICULO = "SELECT * FROM VEICULO";
		
		try {
			pst = connection.prepareStatement(SQL_SELECT_VEICULO);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				String placa = rs.getString("PLACA");
				String marca = rs.getString("MARCA");
				String modelo = rs.getString("MODELO");
				String cor = rs.getString("COR");
				
				System.out.println(placa +" "+ marca +" "+ modelo +" "+ cor);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	
	
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
*/		
		
		
		//TESTE INSERIR VEICULO DAO
		
		
		VeiculoDAO veiculoDAO = new VeiculoDAO();
		
		Veiculo veiculo = new Veiculo("OAE3325", "HONDA", "CITY", "PRATA");
		int quantidade = veiculoDAO.inserir(veiculo);
		System.out.println(quantidade);
		
		//LISTAR
	/*		
		List<Veiculo> listaVeiculos = new VeiculoDAO().listarTodos();
		
		for(Veiculo veiculo : listaVeiculos) {
			System.out.println(veiculo);
		}
		
*/		
		
		
	}
}
