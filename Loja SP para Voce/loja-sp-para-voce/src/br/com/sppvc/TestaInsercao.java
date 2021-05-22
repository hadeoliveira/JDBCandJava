package br.com.sppvc;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {
	public static void main(String[] args) throws SQLException {
		ConnectionFactory con = new ConnectionFactory();
		Connection connection = con.recuperarConexao();
		
		Statement stm = connection.createStatement();
		
		boolean resultado = 
				stm.execute("INSERT INTO PRODUTO(nome,descricao) VALUES ('CELULAR','XIAOMI MI 8')", Statement.RETURN_GENERATED_KEYS);
		
		ResultSet rst = stm.getGeneratedKeys();
		while(rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println("O id gerado foi " + id);
		}
		
		stm.close();
		connection.close();
	}
}
