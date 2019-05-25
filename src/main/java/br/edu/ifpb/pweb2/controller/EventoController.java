package br.edu.ifpb.pweb2.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.dao.EspecialidadeDAO;
import br.edu.ifpb.pweb2.dao.EventoDAO;
import br.edu.ifpb.pweb2.dao.VagaDAO;
import br.edu.ifpb.pweb2.model.Especialidade;
import br.edu.ifpb.pweb2.model.Evento;
import br.edu.ifpb.pweb2.model.User;
import br.edu.ifpb.pweb2.model.Vaga;

@Controller
@RequestMapping({"/eventos","/",""})
public class EventoController {
	@Autowired
	EventoDAO eventodao;
	@Autowired
	EspecialidadeDAO especialidadedao;
	@Autowired
	VagaDAO vagadao;
	
		
	@RequestMapping("/form")
	public ModelAndView showLoginForm(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ModelAndView("redirect:/eventos/");
		
		ModelAndView mav = new ModelAndView("evento-form");
		mav.addObject("evento", new Evento());
		List<Especialidade> especialidades = especialidadedao.findAll();
		mav.addObject("especialidades",especialidades);
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView cadastre(HttpSession session, @Valid Evento evento,
			 @RequestParam("especialidades")List<Integer> especialidades, BindingResult bindingResult,
			RedirectAttributes attr,Model model) {
				if (bindingResult.hasErrors())
					return new ModelAndView("evento-form");
				else {
					User user = (User) session.getAttribute("user");
					evento.setOwner(user);
					//eventodao.gravar(evento);
					Especialidade especialidade;
					List<Vaga> vagas = new ArrayList<>();
					for(long id : especialidades) {
						especialidade = especialidadedao.findById(id);
						Vaga vaga = new Vaga();
						vaga.setEspecialidade(especialidade);
						vaga.setQtd_vagas(1);
						vaga.setEvento(evento);
						vagas.add(vaga);
						//vagadao.gravar(vaga);
					}
					attr.addFlashAttribute("mensagem", "Evento cadastrado com sucesso!");
					ModelAndView mv = new ModelAndView("evento-next");
					mv.addObject("evento",evento);
					mv.addObject("vagas", vagas);
					return mv;
		}
	}
	
	@RequestMapping("/add")
	public ModelAndView cadastreNext(HttpSession session, @Valid Evento evento, 
			@RequestParam("vagasespecialidade")List<Integer> vagas, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ModelAndView("evento-form");
		else {
			/*
			eventodao.gravar(evento);
			vagadao.gravar(v);
			System.out.println("vagas = "+vagas);*/
			System.out.println("evento"+evento);
			System.out.println(vagas);
			}
		return null;
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
			User user = (User) session.getAttribute("user");
			if(e.getOwner().getEmail().equals(user.getEmail())) {
				attr.addFlashAttribute("message", "Evento deletado");
				attr.addFlashAttribute("evento", e);
				eventodao.delete(e.getId());
				return new ModelAndView("redirect:/eventos/");
			}else
				return new ModelAndView("redirect:/permissao-negada/");
		}
		return null;
	}

}
