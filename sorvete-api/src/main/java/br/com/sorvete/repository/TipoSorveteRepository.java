package br.com.sorvete.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.sorvete.connection.ConnectionManager;
import br.com.sorvete.entity.TipoSorvete;

@Repository
public class TipoSorveteRepository {
	
	public TipoSorvete save(TipoSorvete t) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "insert into tipo_sorvete (codigo, tipo, qtd_bola, peso, descricao, valor)"
				+ "values (?,?,?,?,?,?)";
		
		PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
		
		pstm.setString(1, t.getCodigo());
		pstm.setString(2, t.getTipo());
		pstm.setInt(3, t.getQtdBola());
		pstm.setString(4, t.getPeso());
		pstm.setString(5, t.getDescricao());
		pstm.setDouble(6, t.getValor());
		
		pstm.execute();
		
		return t;
		
	}
	
	public List<TipoSorvete> findAll() {
		List<TipoSorvete> tipoSorveteList = new ArrayList<TipoSorvete>();
		
		String sql = "SELECT * FROM tipo_sorvete";
		
		try {
			Statement statement = ConnectionManager.getConnection().createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()){
				TipoSorvete tipoSorvete = new TipoSorvete();
				tipoSorvete.setId(result.getLong("id"));
				tipoSorvete.setCodigo(result.getString("codigo"));
				tipoSorvete.setTipo(result.getString("tipo"));
				tipoSorvete.setQtdBola(result.getInt("qtd_bola"));
				tipoSorvete.setPeso(result.getString("peso"));
				tipoSorvete.setDescricao(result.getString("descricao"));
				tipoSorvete.setValor(result.getDouble("valor"));
				tipoSorveteList.add(tipoSorvete);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tipoSorveteList;
	}
	
	public TipoSorvete findById(Long id) {
		
		String sql = "SELECT * FROM tipo_sorvete WHERE id = '" + id + "'" ;
		TipoSorvete tipoSorvete = new TipoSorvete();
		
		try {
			Statement statement = ConnectionManager.getConnection().createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()){
				tipoSorvete.setId(result.getLong("id"));
				tipoSorvete.setCodigo(result.getString("codigo"));
				tipoSorvete.setTipo(result.getString("tipo"));
				tipoSorvete.setQtdBola(result.getInt("qtd_bola"));
				tipoSorvete.setPeso(result.getString("peso"));
				tipoSorvete.setDescricao(result.getString("descricao"));
				tipoSorvete.setValor(result.getDouble("valor"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tipoSorvete;
	}
	
	public TipoSorvete update(TipoSorvete t, Long id) throws SQLException {
		String sql = "update tipo_sorvete "
				+ "set codigo=?, tipo=?, qtd_bola=?, peso=?, descricao=?, valor=? "
				+ "where id=?";
		
		PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
		
		pstm.setString(1, t.getCodigo());
		pstm.setString(2, t.getTipo());
		pstm.setInt(3, t.getQtdBola());
		pstm.setString(4, t.getPeso());
		pstm.setString(5, t.getDescricao());
		pstm.setDouble(6, t.getValor());
		
		pstm.setLong(7, t.getId());
		
		pstm.execute();
		return t;
		
	}
	
	public void delete(Long id) throws SQLException {
		
		String sql = "delete from tipo_sorvete where id = ?";
		
		PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);
		
		pstm.setLong(1, id);
		
		pstm.execute();
		
	}

}
