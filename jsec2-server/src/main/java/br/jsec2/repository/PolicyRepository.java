package br.jsec2.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;
import com.nurkiewicz.jdbcrepository.TableDescription;

import br.jsec2.domain.Policy;

@Repository
public class PolicyRepository extends JdbcRepository<Policy, Long> {

	public PolicyRepository() {
		super(ROW_MAPPER, ROW_UNMAPPER, new TableDescription("POLICY", "ID"));
	}

	/**
	 * 
	 */
	public static final RowMapper<Policy> ROW_MAPPER = new RowMapper<Policy>() {

		@Override
		public Policy mapRow(ResultSet rs, int arg1) throws SQLException {
			Policy policy = new Policy();
			policy.setId(rs.getLong("ID"));
			policy.setApplicationId(rs.getLong("APPLICATION_ID"));
			policy.setRoleId(rs.getLong("ROLE_ID"));
			policy.setRuleId(rs.getLong("RULE_ID"));
			policy.setEnabled(rs.getBoolean("ENABLED"));
			return policy;
		}
	};

	/**
	 * 
	 */
	public static final RowUnmapper<Policy> ROW_UNMAPPER = new RowUnmapper<Policy>() {

		@Override
		public Map<String, Object> mapColumns(Policy entity) {
			Map<String, Object> columns = new LinkedHashMap<>();
			columns.put("ID", entity.getId());
			columns.put("ENABLED", entity.getEnabled());
			return columns;
		}
	};

	/**
	 * Retorna todas as policies ativas da application.
	 * 
	 * @param applicationId
	 * @return
	 */
	public List<Policy> findAllByApplicationId(Long applicationId) {
		// @formatter:off
		String query = "SELECT PO.* "
				+ "		FROM POLICY PO "
				+ "		WHERE "
				+ "			PO.APPLICATION_ID = ?1 "
				+ "			AND PO.ENABLED = 1";
		// @formatter:on
		Object[] params = new Object[] { applicationId };
		return this.getJdbcOperations().query(query, params, ROW_MAPPER);
	}

	@Override
	protected Map<String, Object> preCreate(Map<String, Object> columns, Policy entity) {
		columns.put("ID", this.getJdbcOperations().queryForObject(entity.getNextValSQL(), Long.class));
		return columns;
	}

}
