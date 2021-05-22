package br.com.sppvc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestaInsercaoComParametro {
	public static void main(String[] args) throws SQLException {
		ConnectionFactory con = new ConnectionFactory();

		try(Connection connection = con.recuperarConexao()){
			connection.setAutoCommit(false);

			//PreparedStatement: deixa o codigo mais legivel, protege contra SQL Injection
			// e trata tudo como string 

			try (PreparedStatement stm = 
					connection.prepareStatement("INSERT INTO PRODUTO (nome,descricao) VALUES (?, ?)", 
							PreparedStatement.RETURN_GENERATED_KEYS)){

				adicionarVariavel("TELEVISAO", "LG 4K", stm);
				adicionarVariavel("CHUTEIRA", "NIKE MERCURIAL", stm);

				connection.commit();

			} catch (Exception e) {
				e.printStackTrace();
				connection.rollback();
				System.out.println("ROLLBACK EXECUTADO! NÃO FOI POSSIVEL INCLUIR!");
			}
		}
	}

	private static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
		stm.setString(1, nome);
		stm.setString(2, descricao);

		stm.execute();

		try(ResultSet rst = stm.getGeneratedKeys()){
			while(rst.next()) {
				Integer id = rst.getInt(1);
				System.out.println("O id gerado foi " + id);
			}
		}
	}
}
