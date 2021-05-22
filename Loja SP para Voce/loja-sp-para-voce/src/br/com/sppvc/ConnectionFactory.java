package br.com.sppvc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	public DataSource dataSource;
	
	public ConnectionFactory() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("30121996@480851Hao");
		
		comboPooledDataSource.setMaxPoolSize(15);
		
		this.dataSource = comboPooledDataSource;
	}
	
	public Connection recuperarConexao() throws SQLException {
		return this.dataSource.getConnection();
		
	}
/**
 * 	
 * @return Connection
 * @throws SQLException
 * 
 * Intuito apenas para documentacao
 */
	private Connection metodoParaConexaoDiretoComDriver() throws SQLException {
		return DriverManager
				.getConnection("\"jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC", 
						"root", 
						"30121996@480851Hao");
	}
}
