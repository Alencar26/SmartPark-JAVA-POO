package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Estacionamento;
import db.dbConection;


public class EstacionamentoDAO extends dbConection {

	
	
	final String SQL_INSERT_INFO_ESTAC = "INSERT INTO ESTACIONAMENTO(NOME, VAGAS) VALUES(?, ?)";
	final String SQL_UPDATE_ESTACIONAMENTO = "UPDATE ESTACIONAMENTO SET NOME = ?, VAGAS = ? WHERE ID = 1";
	final String SQL_SELECT_ESTACIONAMENTO_ID = "SELECT * FROM ESTACIONAMENTO WHERE ID = 1";
	final String SQL_CONTAGEM = "SELECT COUNT(*) FROM REGISTRO";
	
	public int inserir(Estacionamento estacionamento) {
		
		int quantidade = 0;
		
		try(Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_INSERT_INFO_ESTAC);){
			
			pst.setString(1, estacionamento.getNome());
			pst.setInt(2, estacionamento.getVagas());
			
			quantidade = pst.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		//ESTOU AQUI
		return quantidade;
	}
	
	
	public int alterar(Estacionamento estacionamento) {
		int quantidade = 0;
		
		if(estacionamento != null) {
		
		try(Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_UPDATE_ESTACIONAMENTO);){
			
			pst.setString(1, estacionamento.getNome());
			pst.setInt(2, estacionamento.getVagas());
			
			
			quantidade = pst.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
		return quantidade;
	
	}
	
	
	public Estacionamento findById() {
		
	
		
		Estacionamento estacionamento = null;
		
		try(Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_ESTACIONAMENTO_ID);){
			
	
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				estacionamento = new Estacionamento();
				
				estacionamento.setNome(rs.getString("NOME"));
				estacionamento.setVagas(rs.getInt("VAGAS"));
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return estacionamento;
	}
	
	
	public int Contagem() {
		
		int contagem = 0;
		
		try(Connection connection = this.conectar();
				Statement  st = connection.createStatement();){
			
			ResultSet rs= st.executeQuery(SQL_CONTAGEM);
			if(rs.next()) {
				contagem = rs.getInt(1);
			}
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return contagem;
		
	}
	
	
}
