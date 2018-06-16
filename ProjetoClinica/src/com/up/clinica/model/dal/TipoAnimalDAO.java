package com.up.clinica.model.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.up.clinica.model.TipoAnimal;

public class TipoAnimalDAO extends AbstractDAO<TipoAnimal, Long>{

	@Override
	protected void carregarChavesGeradasNoObjeto(ResultSet generatedKeys, TipoAnimal objeto) throws Exception {
		objeto.setAcronimo(generatedKeys.getString(1));
		
	}

	@Override
	protected PreparedStatement criarStatementPersistir(Connection conexao, TipoAnimal objeto) throws Exception {
		PreparedStatement statement = conexao.prepareStatement(
				"INSERT INTO TIPO_ANIMAL (TIPO_ANIMAL_ACRONIMO,NOME,DESCRICAO) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, objeto.getAcronimo());
		statement.setString(2, objeto.getNome());
		statement.setString(3, objeto.getDescricao());
		return statement;
	}

	@Override
	protected PreparedStatement criarStatementListar(Connection conexao) throws Exception {
		return conexao.prepareStatement("select acronimo,nome,descricao from tipo_amimal");
	}

	@Override
	protected TipoAnimal parseObjeto(ResultSet rs) throws Exception {
		TipoAnimal ta = new TipoAnimal();
		ta.setAcronimo(rs.getString(1));
		ta.setNome(rs.getString(2));
		ta.setDescricao(rs.getString(3));
		
		return ta;
	}

	@Override
	protected PreparedStatement criarStatementBuscar(Connection conexao, Long id) throws Exception {
		PreparedStatement statement = conexao
				.prepareStatement("SELECT ta.ACRONIMO, ta.NOME,ta.DESCRICAO,e.id,e.nome,e.descricao "
						+ " FROM tipo_animal ta INNER JOIN especie e ON ta.tipo_animal_acronimo = e.especie_id WHERE ta.tipo_animal_acronimo = ?");
		statement.setLong(1, id);
		return statement;
	}

	@Override
	protected PreparedStatement criarStatementAtualizar(Connection conexao, TipoAnimal objeto) throws Exception {
		PreparedStatement statement = conexao
				.prepareStatement("UPDATE tipo_animal SET nome=?, descricao=? WHERE tipo_animal_acronimo=?");
		statement.setString(1, objeto.getAcronimo());
		statement.setString(2, objeto.getNome());
		statement.setString(3, objeto.getDescricao());

		return statement;
	}

	@Override
	protected PreparedStatement criarStatementRemover(Connection conexao, Long id) throws Exception {
		
		String sql = "SELECT  tipo_animal_acronimo, nome, descricao FROM TIPO_ANIMAL WHERE tipo_animal_acronimo = ?";
		
		PreparedStatement statement= conexao.prepareStatement(sql);
		statement.setLong(1, id);
		statement.execute();
		ResultSet rs = statement.getResultSet();
		
		TipoAnimal ta = new TipoAnimal();
		while(rs.next()) {
			ta.setAcronimo(rs.getString("acronimo"));
			ta.setNome(rs.getString("nome"));
			ta.setDescricao(rs.getString("descricao"));
		}
		
		sql = "DELETE FROM TIPO_ANIMAL WHERE tipo_animal_acronimo = ?";
		statement = conexao.prepareStatement(sql);
		statement.setLong(1, id);
		statement.execute();

		return statement;
	}
	
}

