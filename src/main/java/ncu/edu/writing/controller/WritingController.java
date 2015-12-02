//取得瀏覽器request model，進行對應的資料庫操作，回傳操作結果給request頁面
package ncu.edu.writing.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ncu.edu.writing.dao.ForumDao;
import ncu.edu.writing.dao.ForumRecordDao;
import ncu.edu.writing.dao.TopicDao;
import ncu.edu.writing.dao.TopicRecordDao;
import ncu.edu.writing.model.Forum;
import ncu.edu.writing.model.ForumRecord;
import ncu.edu.writing.model.Topic;
import ncu.edu.writing.model.TopicRecord;

@Controller
public class WritingController extends AbstractController {

	@Autowired
	TopicRecordDao topicRecordDao;

	@Autowired
	ForumRecordDao ForumRecordDao;

	@Autowired
	TopicDao topicDao;

	@Autowired
	ForumDao forumDao;

	@Autowired
	ViewHelper viewHelper;

	@RequestMapping("/writing_home")
	public String initIndex(Model model) {
		model.addAttribute(AbstractController.WIRTING_CONTENT, "modules/writing_home");
		return "layout/base";
	}

	@RequestMapping("/writing_about")
	public String initAbout(Model model) {
		model.addAttribute(AbstractController.WIRTING_CONTENT, "modules/writing_about");
		return "layout/base";
	}

	@RequestMapping("/writing_help")
	public String initHelp(Model model) {
		model.addAttribute(AbstractController.WIRTING_CONTENT, "modules/writing_help");
		return "layout/base";
	}

	@RequestMapping("/writing_help1")
	public String initHelp1(Model model) {
		model.addAttribute(AbstractController.WIRTING_CONTENT, "modules/writing_help1");
		return "layout/base";
	}

	@RequestMapping("/writing_help2")
	public String initHelp2(Model model) {
		model.addAttribute(AbstractController.WIRTING_CONTENT, "modules/writing_help2");
		return "layout/base";
	}

	@RequestMapping("/writing_help3")
	public String initHelp3(Model model) {
		model.addAttribute(AbstractController.WIRTING_CONTENT, "modules/writing_help3");
		return "layout/base";
	}

	@RequestMapping("/writing_learn")
	public String initLearn(Model model) {
		model.addAttribute(AbstractController.WIRTING_CONTENT, "modules/writing_learn");
		return "layout/base";
	}

	// 功能頁面-------------------------------------------------
	/**
	 * 文章選擇列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/writing_article_list")
	public String initArticleList(Model model, HttpSession session) {
		List<Topic> tList = topicDao.getAll();

		model.addAttribute("tl", tList);
		model.addAttribute(AbstractController.WIRTING_CONTENT, "modules/writing_article_list");
		return "layout/base";
	}

	/**
	 * 在writing_article_list選擇文章之後進入活動的第一頁 此頁面主要作為閱讀文章用
	 * 
	 * @param model
	 *            存取資料庫參數
	 * @param userId
	 *            依照使用者ID
	 * @param topicId
	 *            依照文章ID
	 * @return
	 */
	@RequestMapping("/writing_start_read")
	public String initStartRead(Model model, HttpSession session, @RequestParam("topicId") int topicId) {
		List<ForumRecord> frList = new ArrayList<ForumRecord>();
		List<Forum> fList = forumDao.getByTopicId(topicId);
		for (Forum f : fList) {
			frList.add(ForumRecordDao.getForumRecordByForumIdAndUserId(
					Integer.parseInt(session.getAttribute("userId").toString()), f.id));
		}
		model.addAttribute("viewHelper", viewHelper);
		model.addAttribute("fr", frList);
		model.addAttribute("fl", fList);
		model.addAttribute("t", topicDao.get(topicId));
		model.addAttribute("tr", topicRecordDao.getTopicRecordByUserIdAndTopicId(
				Integer.parseInt(session.getAttribute("userId").toString()), topicId));
		model.addAttribute(AbstractController.WIRTING_CONTENT, "modules/writing_start_read");
		return "layout/base";
	}

	/**
	 * @param model
	 *            存入forumRecord,topicRecord, topicId, userId,
	 * @param session
	 *            存使用者登入參數id,account
	 * @param topicId
	 * @return 回傳整個model內容
	 */
	@RequestMapping("/writing_step1")
	public String initStep1(Model model, HttpSession session, @RequestParam("topicId") int topicId) {

		List<ForumRecord> frList = new ArrayList<ForumRecord>();
		List<Forum> fList = forumDao.getByTopicId(topicId);
		for (Forum f : fList) {
			frList.add(ForumRecordDao.getForumRecordByForumIdAndUserId(
					Integer.parseInt(session.getAttribute("userId").toString()), f.id));
		}
		model.addAttribute("viewHelper", viewHelper);
		model.addAttribute("fr", frList);
		model.addAttribute("fl", fList);
		model.addAttribute("t", topicDao.get(topicId));
		model.addAttribute("tr", topicRecordDao.getTopicRecordByUserIdAndTopicId(
				Integer.parseInt(session.getAttribute("userId").toString()), topicId));
		model.addAttribute(AbstractController.WIRTING_CONTENT, "modules/writing_step1");
		return "layout/base";
	}

	@RequestMapping("/writing_step2")
	public String initStep2(Model model, HttpSession session, @RequestParam("topicId") int topicId) {

		List<ForumRecord> frList = new ArrayList<ForumRecord>();
		List<Forum> fList = forumDao.getByTopicId(topicId);
		for (Forum f : fList) {
			frList.add(ForumRecordDao.getForumRecordByForumIdAndUserId(
					Integer.parseInt(session.getAttribute("userId").toString()), f.id));
		}
		model.addAttribute("viewHelper", viewHelper);
		model.addAttribute("fr", frList);
		model.addAttribute("fl", fList);
		model.addAttribute("t", topicDao.get(topicId));
		model.addAttribute("tr", topicRecordDao.getTopicRecordByUserIdAndTopicId(
				Integer.parseInt(session.getAttribute("userId").toString()), topicId));
		model.addAttribute(AbstractController.WIRTING_CONTENT, "modules/writing_step2");
		return "layout/base";
	}

	@RequestMapping("/writing_step3")
	public String initStep3(Model model, HttpSession session, @RequestParam("topicId") int topicId) {

		List<ForumRecord> frList = new ArrayList<ForumRecord>();
		List<Forum> fList = forumDao.getByTopicId(topicId);
		for (Forum f : fList) {
			frList.add(ForumRecordDao.getForumRecordByForumIdAndUserId(
					Integer.parseInt(session.getAttribute("userId").toString()), f.id));
		}
		model.addAttribute("viewHelper", viewHelper);
		model.addAttribute("fr", frList);
		model.addAttribute("fl", fList);
		model.addAttribute("t", topicDao.get(topicId));
		model.addAttribute("tr", topicRecordDao.getTopicRecordByUserIdAndTopicId(
				Integer.parseInt(session.getAttribute("userId").toString()), topicId));
		model.addAttribute(AbstractController.WIRTING_CONTENT, "modules/writing_step3");
		return "layout/base";
	}

	@RequestMapping("/writing_step4")
	public String initStep4(Model model, HttpSession session, @RequestParam("topicId") int topicId) {

		List<ForumRecord> frList = new ArrayList<ForumRecord>();
		List<Forum> fList = forumDao.getByTopicId(topicId);
		for (Forum f : fList) {
			frList.add(ForumRecordDao.getForumRecordByForumIdAndUserId(
					Integer.parseInt(session.getAttribute("userId").toString()), f.id));
		}
		model.addAttribute("viewHelper", viewHelper);
		model.addAttribute("fr", frList);
		model.addAttribute("fl", fList);
		model.addAttribute("t", topicDao.get(topicId));
		model.addAttribute("tr", topicRecordDao.getTopicRecordByUserIdAndTopicId(
				Integer.parseInt(session.getAttribute("userId").toString()), topicId));
		model.addAttribute(AbstractController.WIRTING_CONTENT, "modules/writing_step4");
		return "layout/base";
	}

	@RequestMapping("/writing_record_list")
	public String initRecordList(Model model) {
		List<Topic> tList = topicDao.getAll();
		model.addAttribute("tl", tList);
		model.addAttribute(AbstractController.WIRTING_CONTENT, "modules/writing_record_list");
		return "layout/base";
	}

	@RequestMapping("/writing_start")
	public String initWritingPage(Model model, HttpSession session, @RequestParam("topicId") int topicId) {

		List<ForumRecord> frList = new ArrayList<ForumRecord>();
		List<Forum> fList = forumDao.getByTopicId(topicId);
		for (Forum f : fList) {
			frList.add(ForumRecordDao.getForumRecordByForumIdAndUserId(
					Integer.parseInt(session.getAttribute("userId").toString()), f.id));
		}
		model.addAttribute("viewHelper", viewHelper);
		model.addAttribute("fr", frList);
		model.addAttribute("fl", fList);
		model.addAttribute("t", topicDao.get(topicId));
		model.addAttribute("tr", topicRecordDao.getTopicRecordByUserIdAndTopicId(
				Integer.parseInt(session.getAttribute("userId").toString()), topicId));
		model.addAttribute(AbstractController.WIRTING_CONTENT, "modules/writing_start");
		return "layout/base";
	}

	/*
	 * @RequestMapping("/writing_login") public String initLogin(Model model) {
	 * model.addAttribute(AbstractController.WIRTING_CONTENT,
	 * "modules/writing_login"); return "layout/base"; }
	 */

	@RequestMapping("/writing_record")
	public String initRecord(Model model, HttpSession session, @RequestParam("topicId") int topicId) {

		List<ForumRecord> frList = new ArrayList<ForumRecord>();
		List<Forum> fList = forumDao.getByTopicId(topicId);
		for (Forum f : fList) {
			frList.add(ForumRecordDao.getForumRecordByForumIdAndUserId(
					Integer.parseInt(session.getAttribute("userId").toString()), f.id));
		}
		model.addAttribute("viewHelper", viewHelper);
		model.addAttribute("fr", frList);
		model.addAttribute("fl", fList);
		model.addAttribute("t", topicDao.get(topicId));
		model.addAttribute("tr", topicRecordDao.getTopicRecordByUserIdAndTopicId(
				Integer.parseInt(session.getAttribute("userId").toString()), topicId));
		model.addAttribute(AbstractController.WIRTING_CONTENT, "modules/writing_record");
		return "layout/base";
	}

	@RequestMapping("/writing_edit")
	public String initEdit(Model model) {
		model.addAttribute(AbstractController.WIRTING_CONTENT, "modules/writing_edit");
		return "layout/base";
	}

	@RequestMapping("/writing_edit_list")
	public String initEditList(Model model) {
		model.addAttribute(AbstractController.WIRTING_CONTENT, "modules/writing_edit_list");
		return "layout/base";
	}

	@RequestMapping("/writing_regist")
	public String initRegist(Model model) {
		model.addAttribute(AbstractController.WIRTING_CONTENT, "modules/writing_regist");
		return "layout/base";
	}

	@RequestMapping(value = "/saveTopicRecord", method = RequestMethod.POST)
	public @ResponseBody String ProcessTopicRecord(@RequestParam("tid") int topicId,
			@RequestParam("answer") String answer) {

		TopicRecord oldRecord = topicRecordDao.getTopicRecordByUserIdAndTopicId(1, topicId);
		if (oldRecord == null) {
			TopicRecord tr = new TopicRecord();
			tr.answer = answer;
			tr.topicId = topicId;
			tr.userId = 1;
			topicRecordDao.saveOrUpdate(tr);
		} else {
			oldRecord.answer = answer;
			topicRecordDao.saveOrUpdate(oldRecord);
		}
		return "sucess";
	}

	@RequestMapping(value = "/saveForumRecord", method = RequestMethod.POST)
	public @ResponseBody String ProcessForumRecord(HttpSession session, @RequestParam("fid") int forumId,
			@RequestParam("answer") String answer) {

		ForumRecord oldRecord = ForumRecordDao
				.getForumRecordByUserIdAndForumId(Integer.parseInt(session.getAttribute("userId").toString()), forumId);
		if (oldRecord == null) {
			ForumRecord fr = new ForumRecord();
			fr.answer = answer;
			fr.forumId = forumId;
			fr.userId = Integer.parseInt(session.getAttribute("userId").toString());
			ForumRecordDao.saveOrUpdate(fr);
		} else {
			oldRecord.answer = answer;
			oldRecord.forumId = forumId;
			System.out.println(session.getAttribute("userId"));
			oldRecord.userId = Integer.parseInt(session.getAttribute("userId").toString());
			ForumRecordDao.saveOrUpdate(oldRecord);
		}
		return "sucess";
	}
}
