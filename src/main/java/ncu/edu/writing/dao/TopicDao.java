package ncu.edu.writing.dao;

//取值、新增修改刪除等資料存取介面
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

import ncu.edu.writing.model.Topic;

@Repository
public class TopicDao implements Dao<Topic> {

	// private static final Logger logger =
	// LoggerFactory.getLogger(TopicDao.class);
	// jdbcTemplate模板操作類，把訪問jdbc的模板抽取到template中，使用模板類，可以不用管有關連接管理，關閉等細節
	@Autowired
	protected JdbcTemplate jdbc;

	@Override
	public Topic get(int id) {
		try {
			return jdbc.queryForObject("SELECT * FROM topic WHERE id=?", mapper, id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Topic> get(List<Integer> ids) {
		return jdbc.query("SELECT * FROM topic WHERE id IN (" + ids + ")", mapper);
	}

	/**
	 * @return 取出所有文章列表
	 */
	public List<Topic> getAll() {
		return jdbc.query("SELECT * FROM topic", mapper);
	}

	public List<Topic> getByTopicId(int topicId) {
		return jdbc.query("SELECT * FROM topic WHERE topic_id= ?", mapper, topicId);
	}

	@Override
	public void saveOrUpdate(Topic entity) {
		String query = "insert into topic (id,topic, article1,article2,article3,article4, description, review , admin_id) values (?,?,?,?,?,?,?,?)";
		Object[] args = new Object[] { entity.id, entity.topic, entity.article1, entity.article2, entity.article3,
				entity.article4, entity.description, entity.review, entity.adminId };
		jdbc.update(query, args);
		entity.id = jdbc.queryForInt("select last_insert_id()");
	}

	@Override
	public void delete(int id) {
		String query = "delete from topic where id=?";
		jdbc.update(query, id);
	}

	@Override
	public void delete(List<Integer> ids) {
		String inIds = StringUtils.arrayToCommaDelimitedString(ObjectUtils.toObjectArray(ids));
		String query = "delete from topic where id IN (" + inIds + ")";
		jdbc.update(query, ids);
	}

	/**
	 * 通過建立內部類實現RowMapper接口,可以將數據中的每一行數據封裝成用戶定義的類別,RowMapper中有一個mapRow方法,
	 * 所以實現RowMapper接口一定要實現mapRow方法, 對自定義類的包裝就在mapRow方法中實現
	 */
	private static final RowMapper<Topic> mapper = new RowMapper<Topic>() {
		public Topic mapRow(ResultSet rs, int rowNum) throws SQLException {
			Topic topic = new Topic();
			topic.id = rs.getInt("id");
			topic.topic = rs.getString("topic");
			topic.article1 = "<div class='p-box'>" + rs.getString("article1") + "</div>";
			topic.article2 = "<div class='p-box'>" + rs.getString("article2") + "</div>";
			topic.article3 = "<div class='p-box'>" + rs.getString("article3") + "</div>";
			topic.article4 = "<div class='p-box'>" + rs.getString("article4") + "</div>";
			topic.description = rs.getString("description");
			topic.review = rs.getString("review");
			topic.adminId = rs.getInt("admin_id");

			return topic;
		}
	};

}
