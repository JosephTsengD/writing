package ncu.edu.writing.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ncu.edu.writing.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Repository
public class UserDao implements Dao<User> {

	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

	@Autowired
	protected JdbcTemplate jdbc;

	@Override
	public User get(int id) {
		try {
			return jdbc.queryForObject("SELECT * FROM user WHERE id=?", mapper,
					id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<User> get(List<Integer> ids) {
		String inIds = StringUtils.arrayToCommaDelimitedString(ObjectUtils
				.toObjectArray(ids));
		return jdbc.query("SELECT * FROM user WHERE id IN (" + inIds + ")",
				mapper);
	}

	public User loginValidate(String account, String password) {

		List<User> userlist = jdbc
				.query("SELECT id, account, password FROM user WHERE account='"
						+ account + "' AND password='" + password + "'", mapper);

		// System.out.println(!userlist.isEmpty());
		if (!userlist.isEmpty()) {
			return userlist.get(0);
		} else {
			return null;
		}

	}

	@Override
	public void saveOrUpdate(User entity) {
		String query = "insert into user (id, account, password) values (?,?,?)";
		Object[] args = new Object[] { entity.id, entity.account,
				entity.password };
		jdbc.update(query, args);
		entity.id = jdbc.queryForInt("select last_insert_id()");
	}

	@Override
	public void delete(int id) {
		String query = "delete from user where id=?";
		jdbc.update(query, id);
	}

	@Override
	public void delete(List<Integer> ids) {
		String inIds = StringUtils.arrayToCommaDelimitedString(ObjectUtils
				.toObjectArray(ids));
		String query = "delete from user where id IN (" + inIds + ")";
		jdbc.update(query, ids);
	}

	private static final RowMapper<User> mapper = new RowMapper<User>() {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.id = rs.getInt("id");
			user.account = rs.getString("account");
			user.password = rs.getString("password");
			return user;
		}
	};

}
