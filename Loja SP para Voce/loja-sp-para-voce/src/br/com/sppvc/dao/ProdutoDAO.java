package br.com.sppvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.sppvc.modelo.Categoria;
import br.com.sppvc.modelo.Produto;

public class ProdutoDAO {

	private Connection connection;

	public ProdutoDAO(Connection connection) {
		this.connection = connection;
	}

	public void salvar(Produto produto) throws SQLException {
		String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES (?, ?)";

		try(PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			pst.setString(1, produto.getNome());
			pst.setString(2, produto.getDescricao());

			pst.execute();

			try(ResultSet rst = pst.getGeneratedKeys()) {
				while(rst.next()) {
					produto.setId(rst.getInt(1));
				}
			}
		}

		produto.toString();
	}

	public List<Produto> listar() throws SQLException {
		List<Produto> produtos = new ArrayList<Produto>();

		String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO";

		try(PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.execute();

			try(ResultSet rst = pst.getResultSet()){
				while(rst.next()) {
					Produto produto = 
							new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));
					produtos.add(produto);
				}
			}
		}

		return produtos;
	}

	public List<Produto> buscarPorCategoria(Categoria categoria) throws SQLException {

		List<Produto> produtos = new ArrayList<Produto>();
		
		System.out.println("Executando a query de buscar produto por categoria.");

		String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO WHERE CATEGORIA_ID = ?";

		try(PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setInt(1, categoria.getId());

			pst.execute();

			try(ResultSet rst = pst.getResultSet()){
				while(rst.next()) {
					Produto produto = 
							new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));
					produtos.add(produto);
				}
			}
		}

		return produtos;
	}
}


