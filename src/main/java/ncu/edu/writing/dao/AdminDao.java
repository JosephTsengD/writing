//JDBC資料庫存取實作

package ncu.edu.writing.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import ncu.edu.writing.model.Admin;

@Repository
public class AdminDao implements Dao<Admin> {

	// private static final Logger logger =
	// LoggerFactory.getLogger(AdminDao.class);

	@Autowired
	protected JdbcTemplate jdbc;

	@Override
	public Admin get(int id) {
		try {
			return jdbc.queryForObject("SELECT * FROM admin WHERE id=?", mapper, id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Admin> get(List<Integer> ids) {
		return jdbc.query("SELECT * FROM admin WHERE id IN (" + ids + ")", mapper);
	}

	@Override
	public void saveOrUpdate(Admin entity) {
		String query = "insert into admin (id, account, password) values (?,?,?)";
		Object[] args = new Object[] { entity.id, entity.account, entity.password };
		jdbc.update(query, args);
		entity.id = jdbc.queryForInt("select last_insert_id()");
	}

	@Override
	public void delete(int id) {
		String query = "delete from admin where id=?";
		jdbc.update(query, id);
	}

	@Override
	public void delete(List<Integer> ids) {
		String inIds = StringUtils.arrayToCommaDelimitedString(ObjectUtils.toObjectArray(ids));
		String query = "delete from admin where id IN (" + inIds + ")";
		jdbc.update(query, ids);
	}

	private static final RowMapper<Admin> mapper = new RowMapper<Admin>() {
		public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
			Admin obj = new Admin();
			obj.id = rs.getInt("id");
			obj.account = rs.getString("account");
			obj.password = rs.getString("password");
			return obj;
		}
	};

}
