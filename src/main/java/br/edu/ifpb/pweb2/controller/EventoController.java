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

import br.edu.ifpb.pweb2.dao.Avaliacao_EventoDAO;
import br.edu.ifpb.pweb2.dao.Candidato_VagaDAO;
import br.edu.ifpb.pweb2.dao.EspecialidadeDAO;
import br.edu.ifpb.pweb2.dao.EventoDAO;
import br.edu.ifpb.pweb2.dao.VagaDAO;
import br.edu.ifpb.pweb2.model.Avaliacao_Evento;
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
	
	@Autowired
	@Qualifier("Avaliacao_EventoDAO")
	Avaliacao_EventoDAO avaliacaoeventodao;

	@RequestMapping({ "", "/" })
	public ModelAndView listar(HttpSession session) {
		ModelAndView mav = new ModelAndView("eventos-list");
		List<Evento> eventos = eventodao.findAll();
		User user = (User) session.getAttribute("user");
		mav.addObject("usuario", user);
		mav.addObject("eventos", eventos);
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(HttpSession session, @Valid @ModelAttribute("evento") Evento evento,
			BindingResult bindingResult,
			@RequestParam(value="especialidades", required=false) List<Long> especialidades,
			@RequestParam(value="quantidadevagas", required=false) List<Integer> quantidadevagas,
			RedirectAttributes attr, Model model) {
		if (bindingResult.hasErrors()) {
			attr.addFlashAttribute("message_error", "Escolha uma data no futuro.");
			return new ModelAndView("redirect:/eventos/form/");
		}
		if (especialidades == null) {
			attr.addFlashAttribute("message_error", "Escolha pelo menos uma vaga!");
			ModelAndView mav = new ModelAndView("redirect:/eventos/form");
			return mav;
		}
		
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
	public ModelAndView read(HttpSession session, @PathVariable Long eventoId, RedirectAttributes attr) {
		Evento evento = eventodao.findById(eventoId);
		User user = (User) session.getAttribute("user");
		if (evento == null) {
			attr.addFlashAttribute("message_error", "Evento não existe.");
			return new ModelAndView("redirect:/eventos/");
		} 
		if(evento.isFinalizado()) {
			return new ModelAndView("evento-finalizado").addObject("evento", evento);
			//return new ModelAndView("redirect:/eventos/finalizado/"+evento.getId());
		}
		if (user!=null && evento.getOwner().getId().equals(user.getId())) {
			ModelAndView mv = new ModelAndView("evento-update");
			List<Especialidade> especialidades = eventodao.findEspecialidadesByEvento(eventoId);
			List<Especialidade> especialidadesFiltered = especialidadedao.findEspecialidadesFilter(especialidades);
			mv.addObject("evento", evento);
			mv.addObject("especialidades",especialidadesFiltered);
			return mv;
		}else {
			ModelAndView mav = new ModelAndView("evento-candidatura");
			mav.addObject("evento", evento);
			return mav;
		}
	}

	@RequestMapping("finalizado/{eventoId}")
	public ModelAndView evento_finalizado(RedirectAttributes attr,@PathVariable Long eventoId ) {
		Evento evento = eventodao.findById(eventoId);
		attr.addFlashAttribute("message_success", "Evento finalizado com sucesso.");
		return new ModelAndView("evento-finalizado").addObject("evento", evento);
	}

	@RequestMapping("delete/{eventoId}")
	private ModelAndView deleteEvento(HttpSession session, @PathVariable Long eventoId,
			RedirectAttributes attr) {
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
	
	@RequestMapping("/avaliar/desempenho")
	public ModelAndView avaliarDesempenho(HttpSession session,
			@RequestParam("idcandidatovaga") Long idcandidatovaga,
			@RequestParam("starde") Integer nota,
			RedirectAttributes attr) {
		User user = (User) session.getAttribute("user");
		if (user == null)
			return new ModelAndView("redirect:/eventos/");
		Candidato_Vaga cv = candidatovagadao.findById(idcandidatovaga);
		Evento evento = cv.getVaga().getEvento();
		System.out.println("evento"+evento);
		if(cv.getNota_desempenho()>0) {
			attr.addFlashAttribute("message_error", "Esse  candidato ja foi avaliado!");
			return new ModelAndView("redirect:/eventos/"+evento.getId());
		}
		cv.setNota_desempenho(nota);
		candidatovagadao.update(cv.getId(), cv);
		attr.addFlashAttribute("message_success", "Candidato avaliado com sucesso!");
		return new ModelAndView("redirect:/eventos/"+evento.getId());
	}
	
	@RequestMapping("/avaliar/evento")
	public ModelAndView avaliarEvento(HttpSession session,
			@RequestParam("idevento") Long idevento,
			@RequestParam("star") Integer nota,
			RedirectAttributes attr) {
		User user = (User) session.getAttribute("user");
		if (user == null)
			return new ModelAndView("redirect:/eventos/");
		Evento evento = eventodao.findById(idevento);
		if(user.getId().equals(evento.getOwner().getId())) {
			System.out.println("promotr dono do evento");
			return new ModelAndView("evento-finalizado").addObject("evento", evento).addObject("message_error", "O promotor não pode avaliar o próprio evento");
		}
		List<Vaga> vagas = evento.getVagas();
		boolean apto = false;//variavel usado pra verificar se o usuario ja foi deferido em uma das vagas
		for (Vaga vaga: vagas) {
			List<Candidato_Vaga> candidato_vagas = vaga.getCandidato_vaga();
			for (Candidato_Vaga cv : candidato_vagas) {
				if(cv.getCandidato().getId().equals(user.getId())) {
					if(cv.getState()== State.APROVADO) {
						apto = true;
					}
				}
			}
		}
		if(apto) {
			System.out.println("candidato apto");
			Avaliacao_Evento av = avaliacaoeventodao.findByUserAndEvento(user, evento);
			if(av == null) {
				System.out.println("oi");
				av = new Avaliacao_Evento();
				av.setEvento(evento);
				av.setNota_avaliacao_evento(nota);
				av.setParticipante(user);
				avaliacaoeventodao.gravar(av);
				attr.addFlashAttribute("message_success", "Evento avaliado com sucesso");
				return new ModelAndView("redirect:/eventos/"+evento.getId());
			}else {
				attr.addFlashAttribute("message_error", "você ja avaliou esse evento");
				return new ModelAndView("redirect:/eventos/"+evento.getId());
			}

		}else {
			attr.addFlashAttribute("message_error", "você não participou desse evento");
			return new ModelAndView("redirect:/eventos/"+evento.getId());
			
		}
		//return null;
	}

	@RequestMapping("finalizar/{eventoId}")
	public ModelAndView finalizar(HttpSession session, @PathVariable Long eventoId,
			@RequestParam(name = "deferimentos_vagas",required=false)List<Long> candidatos_ids,
			@RequestParam(name="deferejs",required=false)List<String> deferimentos_values,
			RedirectAttributes attr) {
		ArrayList<Candidato_Vaga> candidatos_avaliados_memoria = new ArrayList<Candidato_Vaga>();
		List<Candidato_Vaga> candidatos_distinct = new ArrayList<Candidato_Vaga>();
		if(deferimentos_values == null || candidatos_ids == null) {
			attr.addFlashAttribute("message_error", "Defira ao menos uma vaga.");
			return new ModelAndView("redirect:/eventos/{eventoId}");
		}
		int count_aprovados_duplicados = 0;
		ArrayList<String> candidatos_unicos = new ArrayList<String>();
		int id = 0;
		for(long cand:candidatos_ids) {
			Candidato_Vaga candidato = candidatovagadao.findById(cand);  
			String state =  deferimentos_values.get(id);
			if(state.equals("DEFERIDO")) {
				candidato.setState(State.APROVADO);
				String nome_candidato_deferido = candidato.getCandidato().getNome();
				if(!candidatos_unicos.contains(nome_candidato_deferido)) {
					candidatos_unicos.add(nome_candidato_deferido);
				}else {
					count_aprovados_duplicados++;
				}

			}   
			if(state.equals("NAO_DEFERIDO")) {			   
				candidato.setState(State.NAO_APROVADO);
			}  
			id++;
			candidatos_avaliados_memoria.add(candidato);
			if(count_aprovados_duplicados > 0) {
				attr.addFlashAttribute("message_error", "Candidato s� pode ser deferido em uma unica vaga.");
				return new ModelAndView("redirect:/eventos/{eventoId}");
			}

		}		
		
		Evento ev = eventodao.findById(eventoId);
		for(Vaga vaga_banco: ev.getVagas()) {
			for(Candidato_Vaga cand_memoria:candidatos_avaliados_memoria) {
				boolean flag = vaga_banco.getId().equals(cand_memoria.getVaga().getId());
				if(flag) {
					for(Candidato_Vaga cand_vaga_banco:vaga_banco.getCandidato_vaga()) {
						if(cand_vaga_banco.getId().equals(cand_memoria.getId())){
							Integer index_candidato_banco = vaga_banco.getCandidato_vaga().indexOf(cand_vaga_banco);
							vaga_banco.getCandidato_vaga().set(index_candidato_banco,cand_memoria);
							long index_candidato_memoria_long = index_candidato_banco.longValue();
							vagadao.update(index_candidato_memoria_long,vaga_banco);
						}
					}
				}
			}
		}
		ev.setFinalizado(true);
		eventodao.update(eventoId, ev);
		return new ModelAndView("redirect:/eventos/finalizado/"+ev.getId());
	}
	@RequestMapping("update/{eventoId}")
	public ModelAndView update(HttpSession session, @PathVariable Long eventoId, @Valid Evento evento,
			BindingResult bindingResult, RedirectAttributes attr) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("redirect:/eventos/" + evento.getId()).addObject("evento", evento);
		} else {
			User user = (User) session.getAttribute("user");
			evento.setOwner(user);
			Evento e = eventodao.update(eventoId, evento);
			if (e != null) {
				attr.addFlashAttribute("message_success", "Evento atualizado!");
				attr.addFlashAttribute("evento", e);
				return new ModelAndView("redirect:/eventos/{eventoId}") ;
			} else {
				attr.addFlashAttribute("message_error", "Evento nao pode ser atualizado.");
				attr.addFlashAttribute("evento", e);
				return new ModelAndView("redirect:/eventos/");
			}
		}
	}


}
