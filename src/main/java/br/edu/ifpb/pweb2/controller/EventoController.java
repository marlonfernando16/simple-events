package br.edu.ifpb.pweb2.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.dao.Candidato_VagaDAO;
import br.edu.ifpb.pweb2.dao.EspecialidadeDAO;
import br.edu.ifpb.pweb2.dao.EventoDAO;
import br.edu.ifpb.pweb2.dao.VagaDAO;
import br.edu.ifpb.pweb2.model.Candidato_Vaga;
import br.edu.ifpb.pweb2.model.Especialidade;
import br.edu.ifpb.pweb2.model.Evento;
import br.edu.ifpb.pweb2.model.State;
import br.edu.ifpb.pweb2.model.User;
import br.edu.ifpb.pweb2.model.Vaga;

@Controller
@RequestMapping({ "/eventos", "/", "" })
public class EventoController {

	@Autowired
	EventoDAO eventodao;

	@Autowired
	EspecialidadeDAO especialidadedao;

	@Autowired
	VagaDAO vagadao;

	@Autowired
	@Qualifier("Candidato_VagaDAO")
	Candidato_VagaDAO candidatovagadao;

	@RequestMapping({ "", "/" })
	public ModelAndView listar(HttpSession session) {
		ModelAndView mav = new ModelAndView("eventos-list");
		List<Evento> eventos = eventodao.findAll();
		User user = (User) session.getAttribute("user");
		mav.addObject("usuario", user);
		mav.addObject("eventos", eventos);
		return mav;
	}

	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView create(HttpSession session, @ModelAttribute("evento") Evento evento,
			@RequestParam("especialidades") List<Long> especialidades,
			@RequestParam("quantidadevagas") List<Integer> quantidadevagas, BindingResult bindingResult,
			RedirectAttributes attr, Model model) {
		if (bindingResult.hasErrors())
			return new ModelAndView("evento-form");
		else {
			User user = (User) session.getAttribute("user");
			evento.setOwner(user);
			eventodao.gravar(evento);
			Especialidade especialidade;
			int i = 0;
			for (long id : especialidades) {
				especialidade = especialidadedao.findById(id);
				Vaga vaga = new Vaga();
				vaga.setEspecialidade(especialidade);
				vaga.setQtd_vagas(quantidadevagas.get(i));
				vaga.setEvento(evento);
				vagadao.gravar(vaga);
				evento.add(vaga);
				i++;
			}
			eventodao.update(evento.getId(), evento);
			attr.addFlashAttribute("message_success", "Evento cadastrado com sucesso!");
			ModelAndView mv = new ModelAndView("redirect:/eventos/");
			return mv;
		}
	}

	// Está sendo usada para trazer a página de update do evento.
	@RequestMapping("read/{eventoId}")
	public ModelAndView read(@PathVariable Long eventoId, HttpServletRequest request, HttpServletResponse response) {
		Evento e = eventodao.findById(eventoId);
		if (e != null) {
			return new ModelAndView("evento-update").addObject(e);
		}
		return null;
	}

	// Está sendo usada para ver os detalhes do evento.
	@RequestMapping("/{eventoId}")
	public ModelAndView read(@PathVariable Long eventoId, RedirectAttributes attr) {
		Evento evento = eventodao.findById(eventoId);
		if (evento == null) {
			attr.addFlashAttribute("message_error", "Evento não existe.");
			return new ModelAndView("redirect:/eventos/");
		} else {
			ModelAndView mav = new ModelAndView("evento-candidatura");
			mav.addObject("evento", evento);
			return mav;
		}
	}

	@RequestMapping("update/{eventoId}")
	public ModelAndView update(HttpSession session, @PathVariable Long eventoId, @Valid Evento evento,
			BindingResult bindingResult, RedirectAttributes attr) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("evento-form");
		} else {
			User user = (User) session.getAttribute("user");
			evento.setOwner(user);
			Evento e = eventodao.update(eventoId, evento);
			if (e != null) {
				attr.addFlashAttribute("message_success", "Evento atualizado!");
				attr.addFlashAttribute("evento", e);
				return new ModelAndView("redirect:/eventos/");
			} else {
				attr.addFlashAttribute("message_error", "Evento nao pode ser atualizado.");
				attr.addFlashAttribute("evento", e);
				return new ModelAndView("redirect:/eventos/");
			}
		}
	}

	@RequestMapping("delete/{eventoId}")
	private ModelAndView deleteEvento(HttpSession session, @PathVariable Long eventoId, RedirectAttributes attr) {
		Evento e = eventodao.findById(eventoId);
		if (e != null) {
			User user = (User) session.getAttribute("user");
			if (e.getOwner().getEmail().equals(user.getEmail())) {
				attr.addFlashAttribute("message_success", "Evento deletado.");
				attr.addFlashAttribute("evento", e);
				eventodao.delete(e.getId());
				return new ModelAndView("redirect:/eventos/");
			} else
				return new ModelAndView("redirect:/permissao-negada/");
		} else {
			attr.addFlashAttribute("message_eror", "O evento não existe.");
			return new ModelAndView("redirect:/eventos/");
		}
	}

	@RequestMapping("/form")
	public ModelAndView cadastrar(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null)
			return new ModelAndView("redirect:/eventos/");

		ModelAndView mav = new ModelAndView("evento-form");
		mav.addObject("evento", new Evento());
		List<Especialidade> especialidades = especialidadedao.findAll();
		mav.addObject("especialidades", especialidades);
		return mav;
	}

	@RequestMapping("/candidatar")
	public ModelAndView candidatar(HttpSession session, @RequestParam("vagas") List<Long> idvagas,
			RedirectAttributes attr) {
		User user = (User) session.getAttribute("user");
		Evento evento = null;
		if (user == null) {
			attr.addFlashAttribute("message_error", "Usuario precisa estar logado!");
			return new ModelAndView("redirect:/login/form");
		} else {
			Vaga vaga;
			Candidato_Vaga candidato_vaga;
			ArrayList<Candidato_Vaga> inscricoes_user_logado;
			for (long id : idvagas) {
				vaga = vagadao.findById(id);
				evento = vaga.getEvento();
				inscricoes_user_logado = (ArrayList<Candidato_Vaga>) candidatovagadao.findByUser(user);
				for (Candidato_Vaga i : inscricoes_user_logado) {
					if (vaga.getEvento().getId() == i.getVaga().getEvento().getId()) {
						if (vaga.getEspecialidade().getId() == i.getVaga().getEspecialidade().getId()) {
							ModelAndView mav = new ModelAndView("redirect:/eventos/" + evento.getId());
							attr.addFlashAttribute("message_error", "Ja inscrito nessa vaga.");
							return mav;
						}
					}
				}
				candidato_vaga = new Candidato_Vaga();
				candidato_vaga.setCandidato(user);
				candidato_vaga.setState(State.NAO_AVALIADO);
				candidato_vaga.setVaga(vaga);
				candidatovagadao.gravar(candidato_vaga);
			}
			attr.addFlashAttribute("message_success", "Candidatura efetuada com sucesso!");
			ModelAndView mv = new ModelAndView("redirect:/eventos/" + evento.getId());
			return mv;
		}
	}

}
