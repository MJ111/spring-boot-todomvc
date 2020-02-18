package todoapp.web;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import todoapp.commons.domain.Spreadsheet;
import todoapp.core.todos.application.TodoFinder;
import todoapp.core.todos.domain.Todo;
import todoapp.security.UserSession;
import todoapp.web.convert.TodoToSpreadsheetConverter;
import todoapp.web.model.SiteProperties;

@Controller
public class TodoController {
	
	private SiteProperties siteProperties;
	private TodoFinder finder;
	
	public TodoController(TodoFinder finder, SiteProperties siteProperties) {
		this.siteProperties = siteProperties;
		this.finder = finder;
	}
	
	@RequestMapping("/todos")
	public ModelAndView todos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("todos");
		mv.addObject("site", siteProperties);

		return mv;
	}
	
	@RequestMapping(value = "/todos", produces = "text/csv")
	public ModelAndView downloads() {
		List<Todo> todos = finder.getAll();
		Spreadsheet spreadsheet = new TodoToSpreadsheetConverter().convert(todos);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject(spreadsheet);
		
		return mv;
	}
}
