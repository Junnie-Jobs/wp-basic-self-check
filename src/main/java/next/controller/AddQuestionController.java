package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;
import next.dao.QuestionDao;
import next.model.Question;

public class AddQuestionController extends AbstractController{

	QuestionDao questionDao = QuestionDao.getInstance();

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String writer = ServletRequestUtils.getStringParameter(request, "writer");
		String title = ServletRequestUtils.getStringParameter(request, "title");
		String contents = ServletRequestUtils.getStringParameter(request, "contents");
		
		questionDao.insert(new Question(writer, title, contents));
		ModelAndView mav = jstlView("redirect:list.next");
		
		return mav;
		
	}

	
	
}
