package br.edu.ifpb.pweb2.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.dao.EventoDAO;
import br.edu.ifpb.pweb2.model.Evento;
import br.edu.ifpb.pweb2.model.User;

@Controller
@RequestMapping("/eventos")
public class EventoController {
	@Autowired
	EventoDAO eventodao;
	
	@RequestMapping("/form")
	public ModelAndView showLoginForm() {
		ModelAndView mav = new ModelAndView("evento-form");
		mav.addObject("evento", new Evento());
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView cadastre(HttpSession session, @Valid Evento evento, BindingResult bindingResult, RedirectAttributes attr) {
		if (bindingResult.hasErrors())
			return new ModelAndView("evento-form");
		else {
			User user = (User) session.getAttribute("user");
			evento.setOwner(user);
			eventodao.gravar(evento);
			attr.addFlashAttribute("mensagem", "Evento cadastrado com sucesso!");
			return new ModelAndView("redirect:eventos");
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView liste(HttpSession session) {
		ModelAndView a = new ModelAndView("eventos-list");
		List<Evento> eventos = eventodao.findAll();
		User user = (User) session.getAttribute("user");
		a.addObject("usuario", user);
		a.addObject("eventos", eventos);
		return a;
	}

////	Read
	@RequestMapping("read/{eventoId}")
	public ModelAndView readEvent(@PathVariable Long eventoId, HttpServletRequest request,
			HttpServletResponse response) {
		Evento e = eventodao.findById(eventoId);
		if (e != null) {
			return new ModelAndView("evento-update").addObject(e);
		}
		return null;
	}

//	Update
	@RequestMapping("update/{eventoId}")
	public ModelAndView updateEvento(HttpSession session, @PathVariable Long eventoId,@Valid Evento evento, BindingResult bindingResult,
			RedirectAttributes attr) {
		if (bindingResult.hasErrors()) {
//			VER COMO FAZER UM REDIRECT E PLOTAR OS ERROS
			return new ModelAndView("evento-form");
		} else {
			User user = (User) session.getAttribute("user");
			evento.setOwner(user);
			Evento e = eventodao.update(eventoId,evento);
			if (e != null) {
				attr.addFlashAttribute("message", "Evento atualizado");
				attr.addFlashAttribute("evento", e);
				return new ModelAndView("redirect:/eventos/");
			} else {
				attr.addFlashAttribute("message", "Evento n�o pode ser atualizado");
				attr.addFlashAttribute("evento", e);
				return new ModelAndView("redirect:/eventos/");
			}
		}
	}
//
////	Delete
	@RequestMapping("delete/{eventoId}")
	private ModelAndView deleteEvento(HttpSession session, @PathVariable Long eventoId, RedirectAttributes attr) {
		Evento e = eventodao.findById(eventoId);
		if(e != null) {
			if(e.getOwner() == session.getAttribute("user")) {
				attr.addFlashAttribute("message", "Evento deletado");
				attr.addFlashAttribute("evento", e);
				return new ModelAndView("redirect:/eventos/");
			}else
				return new ModelAndView("redirect:/permissao-negada/");
		}
		return null;
	}

}
