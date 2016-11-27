package br.jsec2.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.data.jdbc.support.oracle.OracleXmlObjectMappingHandler;
import org.springframework.jdbc.core.RowMapper;

import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;
import com.nurkiewicz.jdbcrepository.TableDescription;

import br.jsec2.domain.Jsec2User;

public class Jsec2UserRepository extends JdbcRepository<Jsec2User, Long> {

	public Jsec2UserRepository(RowMapper<Jsec2User> rowMapper, RowUnmapper<Jsec2User> rowUnmapper, TableDescription table) {
		super(rowMapper, rowUnmapper, new TableDescription("JSEC2USER", "ID"));
	}

	public static final RowMapper<Jsec2User> ROW_MAPPER = new RowMapper<Jsec2User>() {

		@SuppressWarnings("unchecked")
		@Override
		public Jsec2User mapRow(ResultSet rs, int rowNum) throws SQLException {
			Jsec2User jsec2User = new Jsec2User();
			jsec2User.setId(rs.getLong("ID"));
			jsec2User.setLogin(rs.getString("LOGIN"));
			jsec2User.setName(rs.getString("NAME"));
			jsec2User.setEnabled(rs.getBoolean("ENABLED"));
			jsec2User.setPasswd(rs.getString("PASSWD"));
			jsec2User.setProperties((Map<String, Object>) new OracleXmlObjectMappingHandler().getXmlAsObject(rs, "USR_PROPERTIES"));
			return jsec2User;
		}
	};

	public static final RowUnmapper<Jsec2User> ROW_UNMAPPER = new RowUnmapper<Jsec2User>() {

		@Override
		public Map<String, Object> mapColumns(Jsec2User jsec2User) {
			Map<String, Object> map = new LinkedHashMap<>();
			return null;
		}
	};
}
