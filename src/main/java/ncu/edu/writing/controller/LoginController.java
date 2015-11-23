package ncu.edu.writing.controller;

import javax.servlet.http.HttpSession;

import ncu.edu.writing.dao.ForumDao;
import ncu.edu.writing.dao.ForumRecordDao;
import ncu.edu.writing.dao.TopicDao;
import ncu.edu.writing.dao.TopicRecordDao;
import ncu.edu.writing.dao.UserDao;
import ncu.edu.writing.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author dough
 *
 */
@Controller
public class LoginController extends AbstractController {

	@Autowired
	TopicRecordDao topicRecordDao;

	@Autowired
	ForumRecordDao ForumRecordDao;

	@Autowired
	TopicDao topicDao;

	@Autowired
	ForumDao forumDao;

	@Autowired
	UserDao userdao;

	@Autowired
	ViewHelper viewHelper;

	/**
	 * 1.如果已有loginValidate使用者進行登入，user不為NULL，則存入userId和account進 Session
	 * 2.如果Session已存有userId(先進行過1.)則開始執行 *
	 * 
	 * @param model
	 *            = 回傳整個頁面內容，包含user.id,account
	 * @param session
	 *            存取使用者登入資料:id, account, password
	 * @param account
	 * @param password
	 * @return 整個model
	 */
	@RequestMapping("/writing_login")
	public String initLogin(Model model, HttpSession session,
			@RequestParam(value = "username", required = false) String account,
			@RequestParam(value = "password", required = false) String password) {
		if (session.getAttribute("userId") != null) {
			User user = new User();
			user.id = Integer.parseInt(session.getAttribute("userId")
					.toString());
			user.account = session.getAttribute("userAccount").toString();

			model.addAttribute("user", user);
			model.addAttribute(AbstractController.WIRTING_CONTENT,
					"modules/writing_login");
			return "layout/base";
		}

		User user = userdao.loginValidate(account, password);
		if (user != null) {

			session.setAttribute("userId", user.id);
			session.setAttribute("userAccount", user.account);
			model.addAttribute("user", user);
		}
		model.addAttribute(AbstractController.WIRTING_CONTENT,
				"modules/writing_login");
		return "layout/base";
	}

	@RequestMapping("/writing_logout")
	public String initLogin(Model model, HttpSession session) {
		session.removeAttribute("userId");
		model.addAttribute(AbstractController.WIRTING_CONTENT,
				"modules/writing_login");
		return "layout/base";
	}

}
