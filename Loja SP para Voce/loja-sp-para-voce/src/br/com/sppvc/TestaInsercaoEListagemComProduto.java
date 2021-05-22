package br.com.sppvc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.com.sppvc.dao.ProdutoDAO;
import br.com.sppvc.modelo.Produto;

public class TestaInsercaoEListagemComProduto {

	public static void main(String[] args) throws SQLException {
		Produto comoda = new Produto("CÔMODA", "CÔMODA VERTICAL");
		
		try(Connection connection = new ConnectionFactory().recuperarConexao()){
			
			ProdutoDAO ProdutoDao = new ProdutoDAO(connection);
			ProdutoDao.salvar(comoda);
			List<Produto> produtos = ProdutoDao.listar();
			produtos.stream().forEach(produto -> System.out.println(produto));
		}
	}

}
