package br.com.sppvc;

import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory con = new ConnectionFactory();
		Connection connection = con.recuperarConexao();

		System.out.println("Testando conexão!");
		connection.close();
	}

}
