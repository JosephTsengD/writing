package ncu.edu.writing.controller;

import java.util.List;

import ncu.edu.writing.model.ForumRecord;

import org.springframework.stereotype.Component;

@Component
public class ViewHelper {

	public ForumRecord getForumRecordByForumId(List<ForumRecord> frList,
			int forumId) {
		for (ForumRecord fr : frList) {
			if (fr != null && fr.forumId == forumId) {
				return fr;
			}
		}
		return null;
	}
}
