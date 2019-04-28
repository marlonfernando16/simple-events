package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import model.User;
import repository.UserRepository;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@RequestMapping("/form")
	public String showForm() {
		return "form-login";
	}
	
	@RequestMapping("/valide")
	public String valide(String login, String senha, Model model) {
		User user = UserRepository.findByEmail(login);
		if(user != null && user.getSenha().equals(senha)) {
			model.addAttribute("login",login);
			model.addAttribute("user_name", user.getNome());
			return "eventos-list";
		}else {
			model.addAttribute("erro", "Login ou senha inválidos");
			return "form-login";
		}
	}

}
