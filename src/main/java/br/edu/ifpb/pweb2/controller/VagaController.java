package br.edu.ifpb.pweb2.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/vagas")
public class VagaController {
	@Autowired
	VagaDAO vagadao;
	@Autowired
	EventoDAO eventodao;
	@Autowired
	EspecialidadeDAO especialidadedao;
	
	@RequestMapping("delete/{vagaId}")
	private ModelAndView deleteVaga(HttpSession session, @PathVariable Long vagaId, 
			RedirectAttributes attr) {
			Evento e = vagadao.findEventoByVaga(vagaId);
			if(e.getVagas().size()>1) {
					vagadao.delete(vagaId);
					attr.addFlashAttribute("message_success", "Vaga deletada.");
					attr.addFlashAttribute("evento", e);
					return new ModelAndView("redirect:/eventos/"+e.getId());
				}else {
				attr.addFlashAttribute("message_error", "não é possível manter um evento sem vagas");
				return new ModelAndView("redirect:/eventos/"+e.getId());
			}
		}
	@RequestMapping("/update")
	private ModelAndView updateVaga(HttpSession session, 
			@RequestParam("idvaga") Long idvaga,
			@RequestParam("quantidadevaga") Integer quantidadevaga, 
			RedirectAttributes attr) {
			Vaga vaga = vagadao.findById(idvaga);
			vaga.setQtd_vagas(quantidadevaga);
			vagadao.update(vaga.getId(), vaga);
			attr.addFlashAttribute("message_success", "Vaga atualizada.");
			return new ModelAndView("redirect:/eventos/"+vaga.getEvento().getId());
			
		}
	
	@RequestMapping("/add")
	private ModelAndView addVagaToEvento(HttpSession session, 
			@RequestParam("especialidade") Long idespecialidade,
			@RequestParam("idevento") Long idevento,
			@RequestParam("quantidadevagas") Integer quantidadevagas, 
			RedirectAttributes attr) {
			Especialidade especialidade = especialidadedao.findById(idespecialidade);
			Evento evento = eventodao.findById(idevento);
			Vaga vaga = new Vaga();
			vaga.setEspecialidade(especialidade);
			vaga.setQtd_vagas(quantidadevagas);
			vaga.setEvento(evento);
			vagadao.gravar(vaga);
			attr.addFlashAttribute("message_success", "Vaga adicionada.");
			return new ModelAndView("redirect:/eventos/"+idevento);
		}
	
}
