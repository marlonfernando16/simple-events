package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@RequestMapping("/form")
	public String showForm() {
		return "form-login";
	}
	
	@RequestMapping("/valide")
	public String valide(String login, String senha, Model model) {
		if(login.equals("admin") && senha.equals("123")) {
			model.addAttribute("login",login);
			return "eventos-list";
		}else {
			model.addAttribute("erro", "Login ou senha inválidos");
			return "form-login";
		}
	}

}
