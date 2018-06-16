package com.up.clinica.model.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.up.clinica.model.Especie;

public class EspecieDAO extends AbstractDAO<Especie, Long>{

	@Override
	protected void carregarChavesGeradasNoObjeto(ResultSet generatedKeys, Especie objeto) throws Exception {
		objeto.setId(generatedKeys.getLong(1));
		
	}

	@Override
	protected PreparedStatement criarStatementPersistir(Connection conexao, Especie objeto) throws Exception {
		PreparedStatement statement = conexao.prepareStatement(
				"INSERT INTO ESPECIE (NOME,DESCRICAO,TIPO_ANIMAL_ACRONIMO) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, objeto.getNome());
		statement.setString(2, objeto.getDescricao());
		statement.setString(3, objeto.getTipoAnimal().getAcronimo());
		return statement;
	}

	@Override
	protected PreparedStatement criarStatementListar(Connection conexao) throws Exception {
		return conexao.prepareStatement("select id,nome,descricao from especie");
	}

	@Override
	protected Especie parseObjeto(ResultSet rs) throws Exception {
		Especie e = new Especie();
		e.setId(rs.getLong(1));
		e.setNome(rs.getString(2));
		e.setDescricao(rs.getString(3));
		
		return e;
	}

	@Override
	protected PreparedStatement criarStatementBuscar(Connection conexao, Long id) throws Exception {
		PreparedStatement statement = conexao
				.prepareStatement("SELECT e.ID, e.NOME,e.DESCRICAO,ta.tipo_animal_acronimo,ta.nome,ta.descricao "
						+ " FROM especie e INNER JOIN tipo_animal ta ON e.especie_id = ta.tipo_animal_acronimo WHERE e.id = ?");
		statement.setLong(1, id);
		return statement;
	}

	@Override
	protected PreparedStatement criarStatementAtualizar(Connection conexao, Especie objeto) throws Exception {
		PreparedStatement statement = conexao
				.prepareStatement("UPDATE especie SET nome=?, descricao=?,tipo_animal_acronimo=? WHERE id=?");
		statement.setString(1, objeto.getNome());
		statement.setString(2, objeto.getDescricao());
		statement.setString(3, objeto.getTipoAnimal().getAcronimo());
		statement.setLong(4, objeto.getId());

		return statement;
	}

	@Override
	protected PreparedStatement criarStatementRemover(Connection conexao, Long id) throws Exception {
		
		String sql = "SELECT  id, nome, descricao FROM ESPECIE WHERE id = ?";
		
		PreparedStatement statement= conexao.prepareStatement(sql);
		statement.setLong(1, id);
		statement.execute();
		ResultSet rs = statement.getResultSet();
		
		Especie esp = new Especie();
		while(rs.next()) {
			esp.setId(rs.getLong("id"));
			esp.setNome(rs.getString("nome"));
			esp.setDescricao(rs.getString("descricao"));
		}
		
		sql = "DELETE FROM ESPECIE WHERE id = ?";
		statement = conexao.prepareStatement(sql);
		statement.setLong(1, id);
		statement.execute();

		return statement;
	}
	
}
