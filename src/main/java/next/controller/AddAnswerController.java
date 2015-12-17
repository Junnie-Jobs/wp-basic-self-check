package next.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

public class AddAnswerController extends AbstractController {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AnswerDao answerDao = AnswerDao.getInstance();
		QuestionDao questionDao = QuestionDao.getInstance();
		
		String questionIdString = ServletRequestUtils.getRequiredStringParameter(request, "questionId");
		String writer = ServletRequestUtils.getRequiredStringParameter(request, "writer");
		String contents = ServletRequestUtils.getRequiredStringParameter(request, "contents");
		long questionId = Long.parseLong(questionIdString);
		Answer answer = new Answer(writer, contents, questionId);
		
		answerDao.insert(answer);
		answerDao.CountOfComment(questionId); 
		
		ModelAndView mav = jsonView();		
		return mav;

		

	}

}
