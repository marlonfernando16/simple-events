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

	@RequestMapping({ "/", "form", "" })
//	ShowForm
	public ModelAndView showUserForm(User user) {
		ModelAndView mav = new ModelAndView("user-create");
		if (user == null) {
			mav.addObject("user", new User());
		}
		mav.addObject(user);
		return mav;
	}

//	ListUsers
	@RequestMapping("listUsers")
	public ModelAndView ListUsers() {
		ModelAndView mav = new ModelAndView("user-list");
		mav.addObject("users", userdao.findAll());
		return mav;
	}

//	Create
	@RequestMapping("create")
	public ModelAndView salveUser(@Valid User user, BindingResult bindingResult, RedirectAttributes attr) {
		User u = userdao.findByEmail(user.getEmail());
			if(u != null) {
				attr.addFlashAttribute("message", "Esse email ja esta em uso");
				return new ModelAndView("redirect:/login/form/");
			}
		
		if (bindingResult.hasErrors()) {
//			VER COMO FAZER UM REDIRECT E PLOTAR OS ERROS
			return new ModelAndView("user-create");
		} else {
			userdao.gravar(user);
			attr.addFlashAttribute("message", "Usuario cadastrado");
			return new ModelAndView("redirect:/login/form/");
		} 
	}

//	Read
	@RequestMapping("read/{userId}")
	public ModelAndView readUser(@PathVariable Long userId, HttpServletRequest request, HttpServletResponse response) {
		User u = userdao.findById(userId);
		if (u != null) {
			return new ModelAndView("user-update").addObject(u);
		}
		return null;
	}

//	Update
//	@RequestMapping("update/{userId}")
//	public ModelAndView updateUser(@PathVariable Long userId, @Valid User user, BindingResult bindingResult, RedirectAttributes attr) {
//		if (bindingResult.hasErrors()) {
////			VER COMO FAZER UM REDIRECT E PLOTAR OS ERROS
//			return new ModelAndView("user-create");
//		} else {
//			if(userdao.update(user) != null) {
//				attr.addFlashAttribute("message", "Usuario atualizado");
//				attr.addFlashAttribute("user", user);
//				return new ModelAndView("redirect:/usuario/listUsers");
//			}else {
//				attr.addFlashAttribute("message", "Usuario nao pode ser atualizado");
//				attr.addFlashAttribute("user", user);
//				return new ModelAndView("redirect:/usuario/listUsers");
//			}
//		}
//	}

//	Delete
//	@RequestMapping("delete/{userId}")
//	private ModelAndView deleteUser(@PathVariable Long userId, RedirectAttributes attr) {
//		User u = userdao.delete(userId);
//		if (u != null) {
//			attr.addFlashAttribute("message", "Usuario deletado");
//			attr.addFlashAttribute("user", u);
//			return new ModelAndView("redirect:/usuario/listUsers");
//		}
//		return null;
//	}

}
