package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import Model.RegistroEstacionamento;
import db.dbConection;

public class VeiculoDAO extends dbConection {

	final String SQL_INSERT_VEICULO = "INSERT INTO REGISTRO(PLACA, MARCA, MODELO, COR, ENTRADA) VALUES(?, ?, ?, ?, ?)";
	final String SQL_SELECT_VEICULO = "SELECT * FROM REGISTRO";
	final String SQL_SELECT_VEICULO_PLACA = "SELECT * FROM REGISTRO WHERE PLACA = ?";
	final String SQL_DELETE_VEICULO = "DELETE FROM REGISTRO WHERE PLACA = ?";
	
	public int inserir(RegistroEstacionamento veiculo) {
		
		int quantidade = 0;
		
		try(Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_INSERT_VEICULO);){
			
			pst.setString(1, veiculo.getPlaca());
			pst.setString(2, veiculo.getMarca());
			pst.setString(3, veiculo.getModelo());
			pst.setString(4, veiculo.getCor());
			pst.setTimestamp(5, java.sql.Timestamp.valueOf(
												veiculo.getEntrada().format(
																DateTimeFormatter.ofPattern(
																		"yyyy-MM-dd HH:mm:ss.SSS")).toString()));
			/*java.sql.Timestamp.valueOf("2012-03-22 08:49:51.784")*/
			quantidade = pst.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return quantidade;
	}
	
	public List<RegistroEstacionamento> listarTodos() {
		List<RegistroEstacionamento> listaVeiculos = new ArrayList<RegistroEstacionamento>();
		
		
		try(Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_VEICULO);){
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				RegistroEstacionamento veiculo = new RegistroEstacionamento();
				
				veiculo.setPlaca(rs.getString("PLACA"));
				veiculo.setMarca(rs.getString("MARCA"));
				veiculo.setModelo(rs.getString("MODELO"));
				veiculo.setCor(rs.getString("COR"));
				veiculo.setEntrada(rs.getTimestamp("ENTRADA").toLocalDateTime());
				
				listaVeiculos.add(veiculo);
			
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return listaVeiculos;
		
	}
	
	public RegistroEstacionamento findByPLACA(String PLACA) {
		
		RegistroEstacionamento veiculo = null;
		
		try(Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_VEICULO_PLACA);){
			
			pst.setString(1, PLACA);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				veiculo = new RegistroEstacionamento();
				
				veiculo.setPlaca(rs.getString("PLACA"));
				veiculo.setMarca(rs.getString("MARCA"));
				veiculo.setModelo(rs.getString("MODELO"));
				veiculo.setCor(rs.getString("COR"));
				veiculo.setEntrada(rs.getTimestamp("ENTRADA").toLocalDateTime());
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return veiculo;
	}
	
	
	public void excluir(String PLACA) {
		try(Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_DELETE_VEICULO);){
			
			pst.setString(1, PLACA);
			pst.execute();
			System.out.println("Sucesso!");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
