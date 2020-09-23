package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Veiculo;
import db.dbConection;

public class VeiculoDAO extends dbConection {

	final String SQL_INSERT_VEICULO = "INSERT INTO VEICULO(PLACA, MARCA, MODELO, COR) VALUES(?, ?, ?, ?)";
	final String SQL_SELECT_VEICULO = "SELECT * FROM VEICULO";
	
	
	
	public int inserir(Veiculo veiculo) {
		
		int quantidade = 0;
		
		try(Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_INSERT_VEICULO);){
			
			pst.setString(1, veiculo.getPlaca());
			pst.setString(2, veiculo.getMarca());
			pst.setString(3, veiculo.getModelo());
			pst.setString(4, veiculo.getCor());
			
			quantidade = pst.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return quantidade;
	}
	
	public List<Veiculo> listarTodos() {
		List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
		
		
		try(Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_VEICULO);){
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				Veiculo veiculo = new Veiculo();
				
				veiculo.setPlaca(rs.getString("PLACA"));
				veiculo.setMarca(rs.getString("MARCA"));
				veiculo.setModelo(rs.getString("MODELO"));
				veiculo.setCor(rs.getString("COR"));
				
				listaVeiculos.add(veiculo);
			
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return listaVeiculos;
		
	}
	
	
	
}
