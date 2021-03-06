package todoapp.web;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import todoapp.core.user.application.UserJoinder;
import todoapp.core.user.application.UserPasswordVerifier;
import todoapp.core.user.domain.User;
import todoapp.core.user.domain.UserEntityNotFoundException;
import todoapp.core.user.domain.UserPasswordNotMatchedException;
import todoapp.security.UserSession;
import todoapp.security.UserSessionRepository;

@Controller
public class LoginController {
	private UserPasswordVerifier verifier;
	private UserJoinder joinder;
	private UserSessionRepository sessionRepository;
	
    private final Logger log = LoggerFactory.getLogger(this.getClass());

	public LoginController(UserPasswordVerifier verifier, UserJoinder joinder, UserSessionRepository sessionRepository) {
		this.verifier = verifier;
		this.joinder = joinder;
		this.sessionRepository = sessionRepository;
	}
	
	@GetMapping("/login")
	public void loginForm() {}
	
	@PostMapping("/login")
	public String loginProcess(@Valid UserCommand command
			, BindingResult bindingResult
			, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("bindingResult", bindingResult);
			model.addAttribute("message", "사용자 입력값이 올바르지 않습니다.");
			return "login";
		}
		
		User user;
		try {
			user = verifier.verify(command.getUsername(), command.getPassword());
			log.debug("loggined", command);
		} catch (UserEntityNotFoundException e) {
			user = joinder.join(command.getUsername(), command.getPassword());
			log.debug("joined", command);
		}
		sessionRepository.set(new UserSession(user));
		
		return "redirect:/todos";
	}
	
	@ExceptionHandler(UserPasswordNotMatchedException.class)
	public String handleUserPasswordNotMatchedException(UserPasswordNotMatchedException error, Model model) {
		model.addAttribute("message", "사용자 정보가 올바르지 않습니다.");
		return "login";
	}
	
	static class UserCommand {
		@Size(min = 4, max = 120)
		private String username;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		private String password;
		
		
	}
}
