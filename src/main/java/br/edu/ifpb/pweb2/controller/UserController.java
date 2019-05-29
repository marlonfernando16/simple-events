package br.edu.ifpb.pweb2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.dao.UserDAO;
import br.edu.ifpb.pweb2.model.User;

@Controller
@RequestMapping("/usuario")
public class UserController {

	@Autowired
	UserDAO userdao;
	
	@RequestMapping("listUsers")
	public ModelAndView ListUsers() {
		ModelAndView mav = new ModelAndView("user-list");
		mav.addObject("users", userdao.findAll());
		return mav;
	}
	
	@RequestMapping("create")
	public ModelAndView create(@Valid User user, BindingResult bindingResult, RedirectAttributes attr) {
		User u = userdao.findByEmail(user.getEmail());
		if (u != null) {
			attr.addFlashAttribute("message_error", "Esse email ja esta em uso.");
			return new ModelAndView("redirect:/login/form/");
		}

		if (bindingResult.hasErrors()) {
			return new ModelAndView("user-create");
		} else {
			userdao.gravar(user);
			attr.addFlashAttribute("message_success", "Usuario cadastrado!");
			return new ModelAndView("redirect:/login/form/");
		}
	}
	
	@RequestMapping("read/{userId}")
	public ModelAndView read(@PathVariable Long userId, HttpServletRequest request, HttpServletResponse response) {
		User u = userdao.findById(userId);
		if (u != null) {
			return new ModelAndView("user-update").addObject(u);
		}
		return null;
	}

//	Update
//	@RequestMapping("update/{userId}")
//	public ModelAndView update(@PathVariable Long userId, @Valid User user, BindingResult bindingResult, RedirectAttributes attr) {
//		if (bindingResult.hasErrors()) {
//			return new ModelAndView("user-create");
//		} else {
//			if(userdao.update(user) != null) {
//				attr.addFlashAttribute("message_success", "Usuario atualizado.");
//				attr.addFlashAttribute("user", user);
//				return new ModelAndView("redirect:/usuario/listUsers");
//			}else {
//				attr.addFlashAttribute("message_error", "Usuario nao pode ser atualizado.");
//				attr.addFlashAttribute("user", user);
//				return new ModelAndView("redirect:/usuario/listUsers");
//			}
//		}
//	}

//	Delete
//	@RequestMapping("delete/{userId}")
//	private ModelAndView delete(@PathVariable Long userId, RedirectAttributes attr) {
//		User u = userdao.delete(userId);
//		if (u != null) {
//			attr.addFlashAttribute("message_success", "Usuario deletado.");
//			attr.addFlashAttribute("user", u);
//			return new ModelAndView("redirect:/usuario/listUsers");
//		}
//		return null;
//	}

	@RequestMapping({ "/", "form", "" })
	public ModelAndView cadastrar(User user) {
		ModelAndView mav = new ModelAndView("user-create");
		if (user == null) {
			mav.addObject("user", new User());
		}
		mav.addObject(user);
		return mav;
	}

}
