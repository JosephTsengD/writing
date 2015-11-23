package ncu.edu.writing.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import ncu.edu.writing.model.Forum;

@Repository
public class ForumDao implements Dao<Forum> {

	private static final Logger logger = LoggerFactory.getLogger(ForumDao.class);

	@Autowired
	protected JdbcTemplate jdbc;

	@Override
	public Forum get(int id) {
		try {
			return jdbc.queryForObject("SELECT * FROM forum WHERE id=?", mapper, id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Forum> get(List<Integer> ids) {
		return jdbc.query("SELECT * FROM forum WHERE id IN (" + ids + ")", mapper);
	}

	public List<Forum> getByTopicId(int topicId) {
		return jdbc.query("SELECT * FROM forum WHERE topic_id= ?", mapper, topicId);
	}

	@Override
	public void saveOrUpdate(Forum entity) {
		String query = "insert into forum (id, title, type, topic_id,answer,answer_tip,title_tip) values (?,?,?,?,?,?,?)";
		Object[] args = new Object[] { entity.id, entity.title, entity.type, entity.topicId, entity.answer,
				entity.answerTip, entity.titleTip };
		jdbc.update(query, args);
		entity.id = jdbc.queryForInt("select last_insert_id()");
	}

	@Override
	public void delete(int id) {
		String query = "delete from forum where id=?";
		jdbc.update(query, id);
	}

	@Override
	public void delete(List<Integer> ids) {
		String inIds = StringUtils.arrayToCommaDelimitedString(ObjectUtils.toObjectArray(ids));
		String query = "delete from forum where id IN (" + inIds + ")";
		jdbc.update(query, ids);
	}

	private static final RowMapper<Forum> mapper = new RowMapper<Forum>() {
		public Forum mapRow(ResultSet rs, int rowNum) throws SQLException {
			Forum obj = new Forum();
			obj.id = rs.getInt("id");
			obj.title = rs.getString("title");
			obj.topicId = rs.getInt("topic_id");
			obj.type = rs.getString("type");
			obj.answerTip = rs.getString("answer_tip");
			obj.titleTip = rs.getString("title_tip");
			obj.answer = rs.getString("answer");
			return obj;
		}
	};

}
