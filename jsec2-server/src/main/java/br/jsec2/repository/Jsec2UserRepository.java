package br.jsec2.repository;

import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.data.jdbc.support.oracle.OracleXmlObjectMappingHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Repository;

import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;
import com.nurkiewicz.jdbcrepository.TableDescription;

import br.jsec2.domain.Jsec2User;
import br.jsec2.domain.Property;

/**
 * Jsec2User Repository.
 * 
 * @author jcruz
 *
 */
@Repository
public class Jsec2UserRepository extends JdbcRepository<Jsec2User, Long> {

	public Jsec2UserRepository() {
		super(ROW_MAPPER, ROW_UNMAPPER, new TableDescription("JSEC2USER", "ID"));
	}

	/**
	 * 
	 */
	public static final RowMapper<Jsec2User> ROW_MAPPER = new RowMapper<Jsec2User>() {

		@SuppressWarnings("unchecked")
		@Override
		public Jsec2User mapRow(ResultSet rs, int rowNum) throws SQLException {
			Jsec2User jsec2User = new Jsec2User();
			jsec2User.setId(rs.getLong("ID"));
			jsec2User.setLogin(rs.getString("LOGIN"));
			jsec2User.setName(rs.getString("NAME"));
			jsec2User.setEnabled(rs.getInt("ENABLED"));
			jsec2User.setPasswd(rs.getString("PASSWD"));
			/*
			 * OracleXmlObjectMappingHandler mappingHandler = new
			 * OracleXmlObjectMappingHandler();
			 * mappingHandler.setUnmarshaller(new Jaxb2Marshaller());
			 * jsec2User.setProperties((Property)
			 * mappingHandler.getXmlAsObject(rs, "USR_PROPERTIES"));
			 */
			return jsec2User;
		}
	};

	/**
	 * 
	 */
	public static final RowUnmapper<Jsec2User> ROW_UNMAPPER = new RowUnmapper<Jsec2User>() {

		@Override
		public Map<String, Object> mapColumns(Jsec2User jsec2User) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("ID", jsec2User.getId());
			map.put("LOGIN", jsec2User.getLogin());
			map.put("NAME", jsec2User.getName());
			map.put("ENABLED", jsec2User.getEnabled());
			map.put("PASSWD", jsec2User.getPasswd());
			OracleXmlObjectMappingHandler mappingHandler = new OracleXmlObjectMappingHandler();
			Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
			marshaller.setClassesToBeBound(Property.class);
			mappingHandler.setMarshaller(marshaller);
			map.put("USR_PROPERTIES", mappingHandler.newMarshallingSqlXmlValue(jsec2User.getProperties()));

			return map;
		}
	};

	@Override
	protected Map<String, Object> preCreate(Map<String, Object> columns, Jsec2User entity) {
		entity.setId(this.getJdbcOperations().queryForObject(entity.getNextValSQL(), Long.class));
		System.out.println("ID: " + entity.getId());
		columns.put("ID", entity.getId());
		return columns;
	}

	@Override
	protected <S extends Jsec2User> S create(S entity) {
		System.out.println("CREATE!!!!!!!!!!!!");
		return super.create(entity);
	}

}
