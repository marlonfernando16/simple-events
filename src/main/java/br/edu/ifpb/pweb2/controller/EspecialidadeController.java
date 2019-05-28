package br.edu.ifpb.pweb2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.dao.EspecialidadeDAO;
import br.edu.ifpb.pweb2.model.Especialidade;
import br.edu.ifpb.pweb2.model.User;

@Controller
@RequestMapping("/especialidade")
public class EspecialidadeController {
	
	@Autowired
	EspecialidadeDAO dao;
	@RequestMapping({"", "/"})
	public ModelAndView listar(HttpSession session, Especialidade e) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return new ModelAndView("redirect:/eventos/");
		}
		else if(!user.isAdmin())
			return new ModelAndView("redirect:/eventos/");
		ModelAndView mav = new ModelAndView("especialidade-list");
		mav.addObject("especialidades", dao.findAll());
		return mav;
	}
	
	@RequestMapping("/form")
	public ModelAndView cadastrar(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ModelAndView("redirect:/permissao-negada/");
		
		if(!user.isAdmin())
			return new ModelAndView("redirect:/permissao-negada/");
		
		ModelAndView mav = new ModelAndView("especialidade-form");
		mav.addObject("especialidade", new Especialidade());
		return mav;
	}
	
	@RequestMapping("/add")
	public ModelAndView cadastre(HttpSession session, @Valid Especialidade especialidade, 
			BindingResult bindingResult, RedirectAttributes attr) {
		if (bindingResult.hasErrors())
			return new ModelAndView("especialidade-form");
		else {
			dao.gravar(especialidade);
			attr.addFlashAttribute("message", "Especialidade cadastrada com sucesso!");
			return new ModelAndView("redirect:/especialidade/");
		}
	}
	
	@RequestMapping("read/{especialidadeId}")
	public ModelAndView readEvent(@PathVariable Long especialidadeId, HttpServletRequest request,
			HttpServletResponse response) {
		Especialidade e = dao.findById(especialidadeId);
		if (e != null) {
			return new ModelAndView("especialidade-update").addObject(e);
		}
		return null;
	}
	
	@RequestMapping("update/{especialidadeId}")
	public ModelAndView updateEvento(HttpSession session, @PathVariable Long especialidadeId, @Valid Especialidade especialidade, 
			BindingResult bindingResult, RedirectAttributes attr) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("especialidade-form");
		} else {
			Especialidade e = dao.update(especialidadeId, especialidade);
			if (e != null) {
				attr.addFlashAttribute("message", "Especialidade atualizada");
				attr.addFlashAttribute("especialidade", e);
				return new ModelAndView("redirect:/especialidade/");
			} else {
				attr.addFlashAttribute("message", "Especialidade n�o pode ser atualizada");
				attr.addFlashAttribute("especialidade", e);
				return new ModelAndView("redirect:/especialidade/");
			}
		}
	}

}
