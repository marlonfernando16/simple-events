package br.edu.ifpb.pweb2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.dao.UserDAO;
import br.edu.ifpb.pweb2.model.User;

@Controller
@RequestMapping("/login")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class LoginController {
	
	@Autowired	
	UserDAO userdao;
	
	
	
	@RequestMapping("/form")
	public ModelAndView showForm() {
		return new ModelAndView("form-login");
	}
	
	@RequestMapping("/valide")
	public ModelAndView valide(HttpSession session, String login, String senha, RedirectAttributes attr) {
		User user = userdao.findByEmail(login);
		if(user != null && user.getSenha().equals(senha)) {
			session.setAttribute("user", user);
			return new ModelAndView("redirect:/eventos") ;
		}else {
			attr.addFlashAttribute("erro", "Login ou senha inválidos");
			return new ModelAndView("redirect:form");
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "form-login";
	}

}
