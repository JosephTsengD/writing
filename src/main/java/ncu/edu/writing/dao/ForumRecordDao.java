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

import ncu.edu.writing.model.ForumRecord;

@Repository
public class ForumRecordDao implements Dao<ForumRecord> {

	// private static final Logger logger =
	// LoggerFactory.getLogger(ForumRecordDao.class);

	@Autowired
	protected JdbcTemplate jdbc;

	public ForumRecord getForumRecordByForumIdAndUserId(int userId, int ForumId) {
		try {
			return jdbc.queryForObject("SELECT * FROM forum_record WHERE user_id=? and forum_id=?", mapper, userId,
					ForumId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<ForumRecord> getForumRecordByUserId(int userId) {
		return jdbc.query("SELECT * FROM forum_record WHERE user_id=?", mapper, userId);

	}

	@Override
	public ForumRecord get(int id) {
		try {
			return jdbc.queryForObject("SELECT * FROM forum_record WHERE id=?", mapper, id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public ForumRecord getForumRecordByUserIdAndForumId(int userId, int forumId) {
		try {
			return jdbc.queryForObject("SELECT * FROM forum_record WHERE user_id=? and forum_id = ?", mapper, userId,
					forumId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	public ForumRecord getByForumId(int forumId) {
		try {
			return jdbc.queryForObject("SELECT * FROM forum_record WHERE forum_id=?", mapper, forumId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<ForumRecord> get(List<Integer> ids) {
		return jdbc.query("SELECT * FROM forum_record WHERE id IN (" + ids + ")", mapper);
	}

	@Override
	public void saveOrUpdate(ForumRecord entity) {
		if (entity.id == 0) {
			String query = "insert into forum_record (answer, user_id,forum_id) values (?,?,?)";
			Object[] args = new Object[] { entity.answer, entity.userId, entity.forumId };
			jdbc.update(query, args);
			entity.id = jdbc.queryForInt("select last_insert_id()");
		} else {
			String query = "update forum_record set answer=?, user_id=?,forum_id=? where id=?";
			Object[] args = new Object[] { entity.answer, entity.userId, entity.forumId, entity.id };
			jdbc.update(query, args);
		}
	}

	@Override
	public void delete(int id) {
		String query = "delete from forum_record where id=?";
		jdbc.update(query, id);
	}

	@Override
	public void delete(List<Integer> ids) {
		String inIds = StringUtils.arrayToCommaDelimitedString(ObjectUtils.toObjectArray(ids));
		String query = "delete from forum_record where id IN (" + inIds + ")";
		jdbc.update(query, ids);
	}

	private static final RowMapper<ForumRecord> mapper = new RowMapper<ForumRecord>() {
		public ForumRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
			ForumRecord obj = new ForumRecord();
			obj.id = rs.getInt("id");
			obj.forumId = rs.getInt("forum_id");
			obj.userId = rs.getInt("user_id");
			obj.answer = rs.getString("answer");
			return obj;
		}
	};

}
