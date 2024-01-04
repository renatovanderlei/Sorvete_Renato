package br.com.sorvete.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.sorvete.connection.ConnectionManager;
import br.com.sorvete.entity.Sabor;

//Crud de acordo com a implementação da Rifa
@Repository
public class SaborRepository {

	public Sabor save(Sabor t) throws SQLException {
		// TODO Auto-generated method stub

		String sql = "insert into sabor (codigo, nome, descricao)" + "values (?,?,?)";

		PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);

		pstm.setString(1, t.getCodigo());
		pstm.setString(2, t.getNome());
		pstm.setString(3, t.getDescricao());

		pstm.execute();

		return t;

	}

	//implementei um método pra achar pelo id porque uso o id para preparar o relatório
	public Sabor findById(Long id) {

		String sql = "SELECT * FROM sabor WHERE id = '" + id + "'";
		Sabor sabor = new Sabor();

		try {
			Statement statement = ConnectionManager.getConnection().createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				sabor.setId(result.getLong("id"));
				sabor.setCodigo(result.getString("codigo"));
				sabor.setNome(result.getString("nome"));
				sabor.setDescricao(result.getString("descricao"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sabor;
	}

	
	public List<Sabor> findAll() {
		List<Sabor> saborList = new ArrayList<Sabor>();

		String sql = "SELECT * FROM sabor";

		try {
			Statement statement = ConnectionManager.getConnection().createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				Sabor sabor = new Sabor();
				sabor.setId(result.getLong("id"));
				sabor.setCodigo(result.getString("codigo"));
				sabor.setNome(result.getString("nome"));
				sabor.setDescricao(result.getString("descricao"));
				saborList.add(sabor);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return saborList;
	}

	public Sabor update(Sabor t, Long id) throws SQLException {
		String sql = "update sabor " + "set codigo=?, nome=?, descricao=? " + "where id=?";

		PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);

		pstm.setString(1, t.getCodigo());
		pstm.setString(2, t.getNome());
		pstm.setString(3, t.getDescricao());

		pstm.setLong(4, t.getId());

		pstm.execute();
		return t;

	}

	public void delete(Long id) throws SQLException {

		String sql = "delete from sabor where id = ?";

		PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);

		pstm.setLong(1, id);

		pstm.execute();

	}

}
