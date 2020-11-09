package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.RegistroEstacionamento;
import Model.Valor;
import db.dbConection;


public class ValorDAO extends dbConection{

		final String SQL_INSERT_VALOR = "INSERT INTO VALOR(ID, VALOR) VALUES(?, ?)";
		final String SQL_SELECT_VALOR_HORARIO = "SELECT * FROM VALOR WHERE ID = ?";
		final String SQL_DELETE_VALOR = "DELETE FROM VALOR WHERE ID = ?";
		final String SQL_UPDATE_VALOR_ID = "UPDATE VALOR SET VALOR = ? WHERE ID = ?";
		
		public int inserir(Valor valor) {
				
				int quantidade = 0;
				
				try(Connection connection = this.conectar();
						PreparedStatement pst = connection.prepareStatement(SQL_INSERT_VALOR);){
					
					pst.setFloat(1, valor.getId());
					pst.setFloat(2, valor.getValor());
					
					
					quantidade = pst.executeUpdate();
					
				}catch(SQLException e) {
					e.printStackTrace();
				}
				
				return quantidade;
			}
		
		
		public Valor findById(float horario) {
			
			Valor valor = null;
			
			try(Connection connection = this.conectar();
					PreparedStatement pst = connection.prepareStatement(SQL_SELECT_VALOR_HORARIO);){
				
				pst.setFloat(1, horario);
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()) {
					valor = new Valor();
					
					valor.setId(rs.getFloat("ID"));
					valor.setValor(rs.getFloat("VALOR"));
					
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return valor;
		}
		
		
		public void excluir(float horario) {
			try(Connection connection = this.conectar();
					PreparedStatement pst = connection.prepareStatement(SQL_DELETE_VALOR);){
				
				pst.setFloat(1, horario);
				pst.execute();
				System.out.println("Sucesso!");
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		public int alterar(Valor valor) {
			int quantidade = 0;
			
			if(valor != null) {
			
			try(Connection connection = this.conectar();
					PreparedStatement pst = connection.prepareStatement(SQL_UPDATE_VALOR_ID);){
				
				pst.setFloat(1, valor.getValor());
				pst.setFloat(2, valor.getId());
				
				quantidade = pst.executeUpdate();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
			return quantidade;
		
		}
	
	
}
