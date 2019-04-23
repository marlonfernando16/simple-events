package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import model.Evento;
import model.User;
import repository.EventosRepository;
import repository.UserRepository;

@Controller
@RequestMapping("/eventos")
public class EventoController {

	@RequestMapping("/add")
	public ModelAndView showAlunoForm() {
		ModelAndView mav = new ModelAndView("evento-form");
		mav.addObject("evento", new Evento());
		return mav;
	}
	
	@RequestMapping("/salvar")
	public ModelAndView salvarEvento(@Valid Evento evento, BindingResult bindingResult, RedirectAttributes attr) {
		System.out.println("oi");
		if(bindingResult.hasErrors())
			return new ModelAndView("event-form");
		else {
			EventosRepository.store(evento);
			attr.addFlashAttribute("evento", evento);
			attr.addFlashAttribute("eventos", EventosRepository.findAll());
       
			return new ModelAndView("redirect:/eventos/");
		}
	}
	
	@RequestMapping("/")
	public ModelAndView listarEventos() {
		ModelAndView mav = new ModelAndView("eventos-list");
		mav.addObject("eventos", EventosRepository.findAll());
		return mav;
	}
	
//	Read
	@RequestMapping("read/{eventoId}")
	public ModelAndView readEvent(@PathVariable Long eventoId, HttpServletRequest request, HttpServletResponse response) {
		Evento e = EventosRepository.findById(eventoId);
		if (e != null) {
			return new ModelAndView("evento-update").addObject(e);
		}
		return null;
	}
	
//	Update
	@RequestMapping("update/{eventoId}")
	public ModelAndView updateEvento(@PathVariable Long eventoId, @Valid Evento evento, BindingResult bindingResult, RedirectAttributes attr) {
		if (bindingResult.hasErrors()) {
//			VER COMO FAZER UM REDIRECT E PLOTAR OS ERROS
			return new ModelAndView("evento-form");
		} else {
			if(EventosRepository.update(eventoId, evento)) {
				attr.addFlashAttribute("message", "Evento atualizado");
				attr.addFlashAttribute("evento", evento);
				return new ModelAndView("redirect:/eventos/");
			}else {
				attr.addFlashAttribute("message", "Evento não pode ser atualizado");
				attr.addFlashAttribute("evento", evento);
				return new ModelAndView("redirect:/eventos/");
			}
		}
	}
	
//	Delete
	@RequestMapping("delete/{eventoId}")
	private ModelAndView deleteEvento(@PathVariable Long eventoId, RedirectAttributes attr) {
		Evento e = EventosRepository.delete(eventoId);
		if (e != null) {
			attr.addFlashAttribute("message", "Evento deletado");
			attr.addFlashAttribute("evento", e);
			return new ModelAndView("redirect:/eventos/");
		}
		return null;
	}
	
}
