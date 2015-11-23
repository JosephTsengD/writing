package ncu.edu.writing.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ncu.edu.writing.model.TopicRecord;

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
public class TopicRecordDao implements Dao<TopicRecord> {

	private static final Logger logger = LoggerFactory
			.getLogger(TopicRecordDao.class);

	@Autowired
	protected JdbcTemplate jdbc;

	/**
	 * 取得TOPICRECORD
	 * 
	 * @param userId
	 * @param topicId
	 * @return
	 */
	public TopicRecord getTopicRecordByUserIdAndTopicId(int userId, int topicId) {
		try {
			return jdbc
					.queryForObject(
							"SELECT * FROM topic_record WHERE user_id=? and topic_id = ?",
							mapper, userId, topicId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	@Override
	public TopicRecord get(int id) {
		try {
			return jdbc.queryForObject("SELECT * FROM topic_record WHERE id=?",
					mapper, id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<TopicRecord> get(List<Integer> ids) {
		return jdbc.query("SELECT * FROM topic_record WHERE id IN (" + ids
				+ ")", mapper);
	}

	@Override
	public void saveOrUpdate(TopicRecord entity) {
		if (entity.id == 0) {
			String query = "insert into topic_record (id, answer, user_id,topic_id) values (?,?,?,?)";
			Object[] args = new Object[] { entity.id, entity.answer,
					entity.userId, entity.topicId };
			jdbc.update(query, args);
			entity.id = jdbc.queryForInt("select last_insert_id()");
		} else {
			String query = "update topic_record set answer=?, user_id=?,topic_id=? where id=?";
			Object[] args = new Object[] { entity.answer, entity.userId,
					entity.topicId, entity.id };
			jdbc.update(query, args);
		}
	}

	@Override
	public void delete(int id) {
		String query = "delete from topic_record where id=?";
		jdbc.update(query, id);
	}

	@Override
	public void delete(List<Integer> ids) {
		String inIds = StringUtils.arrayToCommaDelimitedString(ObjectUtils
				.toObjectArray(ids));
		String query = "delete from topic_record where id IN (" + inIds + ")";
		jdbc.update(query, ids);
	}

	private static final RowMapper<TopicRecord> mapper = new RowMapper<TopicRecord>() {
		public TopicRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
			TopicRecord obj = new TopicRecord();
			obj.id = rs.getInt("id");
			obj.topicId = rs.getInt("topic_id");
			obj.userId = rs.getInt("user_id");
			obj.answer = rs.getString("answer");
			return obj;
		}
	};

}
