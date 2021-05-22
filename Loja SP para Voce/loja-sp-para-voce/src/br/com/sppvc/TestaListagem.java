package br.com.sppvc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestaListagem {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory criaCon = new ConnectionFactory();
		Connection con = criaCon.recuperarConexao();
		
		PreparedStatement stm = con.prepareStatement("SELECT id, nome, descricao FROM PRODUTO");
		// o que devolve como true e' listagem; falso significa que update, delete ou insert
		stm.execute();
		
		// se for listagem, e' possivel guardar em um resultset
		ResultSet rst = stm.getResultSet();
		while(rst.next()) {
			Integer id = rst.getInt("id");
			String nome = rst.getString("nome");
			String descricao = rst.getString("descricao");
			System.out.println(id + " "+ nome + " "+ descricao);
		}
		
		stm.close();
		con.close();
	}

}
