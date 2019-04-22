package controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import model.Evento;
import repository.EventosRepository;

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
	public String salvarEvento(@Valid Evento evento, Model model, BindingResult br) {
		System.out.println("oi");
		if(br.hasErrors())
			return "evento-form";
		else {
			EventosRepository.store(evento);
			model.addAttribute("evento", evento);
			model.addAttribute("eventos", EventosRepository.findAll());
			return "redirect:/eventos/";
		}
	}
	
	@RequestMapping("/")
	public String listarEventos(Model model) {
		model.addAttribute("eventos", EventosRepository.findAll());
		return "eventos-list";
	}
	
}
