package br.com.sorvete.repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.sorvete.connection.ConnectionManager;
import br.com.sorvete.entity.Sorvete;
import br.com.sorvete.service.SaborService;
import br.com.sorvete.service.TipoSorveteService;

@Repository
public class SorveteRepository {
	
	@Autowired
	private TipoSorveteService tipoSorveteService;
	
	@Autowired
	private SaborService saborService;
	
	// Como vou pegar meu sorvete e jogar no banco de dados
	public Sorvete save(Sorvete t) throws SQLException {

		String sql = "insert into sorvete (codigo, data, tipo_sorvete_id, sabor_id)" + "values (?,?,?,?)";

		PreparedStatement pstm = ConnectionManager.getConnection().prepareStatement(sql);

		pstm.setInt(1, t.getCodigo());
		pstm.setDate(2, Date.valueOf(t.getData()));
		pstm.setLong(3, t.getTipoSorveteId().getId());
		pstm.setLong(4, t.getSaborId().getId());

		pstm.execute();

		return t;

	}
	
	// uso um método para obter o último valor de código do sorvete salvo e,
	// na hora que for criar a próxima venda, ele consiga identificar qual é o próximo número
	public Integer findLastCodigo() {

		String sql = "select codigo from sorvete order by codigo desc limit 1";
		Integer lastCodigo = 0;

		try {
			Statement statement = ConnectionManager.getConnection().createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				lastCodigo = result.getInt("codigo");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lastCodigo;
	}
	
	//Vou usar esse método pra retornar a lista de sorvetes e também vou usar no Proxy
	public List<Sorvete> findAll() {
		List<Sorvete> sorveteList = new ArrayList<Sorvete>();

		String sql = "SELECT * FROM sorvete";

		try {
			Statement statement = ConnectionManager.getConnection().createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				Sorvete sorvete = new Sorvete();
				sorvete.setId(result.getLong("id"));
				sorvete.setCodigo(result.getInt("codigo"));
				sorvete.setData(LocalDate.parse(result.getDate("data").toString()));
				sorvete.setTipoSorveteId(tipoSorveteService.findById(result.getLong("tipo_sorvete_id")));
				sorvete.setSaborId(saborService.findById(result.getLong("sabor_id")));
				sorveteList.add(sorvete);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sorveteList;
	}

	
	//Vou criar minha lista de sorvetes criados pela data 
	public List<Sorvete> findAllByData(LocalDate data) {
		List<Sorvete> sorveteList = new ArrayList<Sorvete>();

		String sql = "SELECT * FROM sorvete where data = '" + data + "'";

		try {
			
			Statement statement = ConnectionManager.getConnection().createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				Sorvete sorvete = new Sorvete();
				sorvete.setId(result.getLong("id"));
				sorvete.setCodigo(result.getInt("codigo"));
				sorvete.setData(LocalDate.parse(result.getDate("data").toString()));
				sorvete.setTipoSorveteId(tipoSorveteService.findById(result.getLong("tipo_sorvete_id")));
				sorvete.setSaborId(saborService.findById(result.getLong("sabor_id")));
				sorveteList.add(sorvete);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sorveteList;
	}

}
