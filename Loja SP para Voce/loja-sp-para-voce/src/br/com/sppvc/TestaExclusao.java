package br.com.sppvc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestaExclusao {
	public static void main(String[] args) throws SQLException {
		ConnectionFactory con = new ConnectionFactory();
		Connection connection = con.recuperarConexao();
		
		PreparedStatement stm = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID > ?");
		stm.setInt(1, 2);
		
		stm.execute();
		Integer linhasModificadas = stm.getUpdateCount();
		
		System.out.println("Quantidade de linhas modificadas: " + linhasModificadas);
		
		stm.close();
		connection.close();
	}
}
