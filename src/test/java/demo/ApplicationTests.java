package demo;

import ncu.edu.writing.Application;
import ncu.edu.writing.constants.ForumType;
import ncu.edu.writing.dao.AdminDao;
import ncu.edu.writing.dao.ForumDao;
import ncu.edu.writing.dao.ForumRecordDao;
import ncu.edu.writing.dao.TopicDao;
import ncu.edu.writing.dao.TopicRecordDao;
import ncu.edu.writing.dao.UserDao;
import ncu.edu.writing.model.Admin;
import ncu.edu.writing.model.Forum;
import ncu.edu.writing.model.ForumRecord;
import ncu.edu.writing.model.Topic;
import ncu.edu.writing.model.TopicRecord;
import ncu.edu.writing.model.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ApplicationTests {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ForumDao forumDao;

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private TopicDao topicDao;

	@Autowired
	private ForumRecordDao forumRecordDao;

	@Autowired
	private TopicRecordDao topicRecordDao;

	@Test
	public void saveUser() {
		User user = new User();
		user.account = "joseph";
		user.password = "123456";
		userDao.saveOrUpdate(user);
	}

	@Test
	public void testAlot() {
		User user = new User();
		user.account = "joseph";
		user.password = "123456";
		userDao.saveOrUpdate(user);

		Admin admin = new Admin();
		admin.account = "qwrewr";
		admin.password = "123";
		adminDao.saveOrUpdate(admin);

		Topic topic = new Topic();
		topic.article1 = "werewr";
		topic.adminId = admin.id;
		topic.description = "EWrwer";
		topic.review = "EWret";
		topicDao.saveOrUpdate(topic);

		TopicRecord tr = new TopicRecord();
		tr.topicId = topic.id;
		tr.userId = user.id;
		tr.answer = "EWrwerwer";
		topicRecordDao.saveOrUpdate(tr);

		Forum forum = new Forum();
		forum.title = "hi";
		forum.type = ForumType.LAYOUT.toString();
		forum.topicId = topic.id;
		forumDao.saveOrUpdate(forum);

		ForumRecord fr = new ForumRecord();
		fr.forumId = forum.id;
		fr.answer = "WErewr";
		fr.userId = user.id;
		forumRecordDao.saveOrUpdate(fr);

	}

}
