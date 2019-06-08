package br.edu.ifpb.pweb2.controller;

import java.util.ArrayList;

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
import br.edu.ifpb.pweb2.dao.VagaDAO;
import br.edu.ifpb.pweb2.model.Especialidade;
import br.edu.ifpb.pweb2.model.User;
import br.edu.ifpb.pweb2.model.Vaga;

@Controller
@RequestMapping("/especialidades")
public class EspecialidadeController {

	@Autowired
	EspecialidadeDAO dao;

	@Autowired
	VagaDAO vagadao;

	@RequestMapping({ "", "/" })
	public ModelAndView listar(HttpSession session, Especialidade especialidade) {
		User user = (User) session.getAttribute("user");
		if (user == null)
			return new ModelAndView("redirect:/permissao-negada/");

		else if (!user.isAdmin())
			return new ModelAndView("redirect:/permissao-negada/");

		ModelAndView mav = new ModelAndView("especialidade-list");
		mav.addObject("especialidades", dao.findAll());
		return mav;
	}

	@RequestMapping("/add")
	public ModelAndView create(HttpSession session, @Valid Especialidade especialidade, BindingResult bindingResult,
			RedirectAttributes attr) {
		if (bindingResult.hasErrors())
			return new ModelAndView("especialidade-form");
		else {
			dao.gravar(especialidade);
			attr.addFlashAttribute("message_success", "Especialidade cadastrada com sucesso!");
			return new ModelAndView("redirect:/especialidades/");
		}
	}

	@RequestMapping("read/{especialidadeId}")
	public ModelAndView read(@PathVariable Long especialidadeId, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes attr) {
		Especialidade especialidade = dao.findById(especialidadeId);
		if (especialidade != null)
			return new ModelAndView("especialidade-update").addObject(especialidade);
		else {
			attr.addFlashAttribute("message_error", "Essa especialidade não existe");
			return new ModelAndView("redirect:/especialidades/");
		}
	}

	@RequestMapping("update/{especialidadeId}")
	public ModelAndView update(HttpSession session, @PathVariable Long especialidadeId,
			@Valid Especialidade especialidade, BindingResult bindingResult, RedirectAttributes attr) {
		if (bindingResult.hasErrors())
			return new ModelAndView("especialidade-form");
		
		ArrayList<Vaga> vagas_relacionadas = vagadao.findByEspecialidade(especialidadeId);
		if (vagas_relacionadas.size() > 0) {
			attr.addFlashAttribute("message_error", "Ja ha vagas relacionadas a essa especialidade");
			return new ModelAndView("redirect:/especialidades/");
		}
		
		Especialidade e = dao.update(especialidadeId, especialidade);
		if (e != null) {
			attr.addFlashAttribute("message_success", "Especialidade atualizada!");
			attr.addFlashAttribute("especialidade", e);
			return new ModelAndView("redirect:/especialidades/");
		} else {
			attr.addFlashAttribute("message_error", "Especialidade nao pode ser atualizada.");
			attr.addFlashAttribute("especialidade", e);
			return new ModelAndView("redirect:/especialidades/");
		}
	}

	@RequestMapping("/delete/{especialidadeId}")
	public ModelAndView delete(HttpSession session, @PathVariable Long especialidadeId, RedirectAttributes attr) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			attr.addFlashAttribute("message_error", "Voce nao tem permissao para apagar uma especialidade");
			return new ModelAndView("redirect:/especialidades/");
		}
		if (!user.isAdmin()) {
			attr.addFlashAttribute("message_error", "Voce nao tem permissao para apagar uma especialidade");
			return new ModelAndView("redirect:/especialidades/");
		}
		ArrayList<Vaga> vagas_relacionadas = vagadao.findByEspecialidade(especialidadeId);
		if (vagas_relacionadas.size() > 0) {
			attr.addFlashAttribute("message_error", "Ja ha vagas relacionadas a essa especialidade");
			return new ModelAndView("redirect:/especialidades/");
		}
		dao.delete(especialidadeId);
		attr.addFlashAttribute("message_success", "Especialidade deletada com sucesso!");
		return new ModelAndView("redirect:/especialidades/");
	}

	@RequestMapping("/form")
	public ModelAndView cadastrar(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null)
			return new ModelAndView("redirect:/permissao-negada/");

		if (!user.isAdmin())
			return new ModelAndView("redirect:/permissao-negada/");

		ModelAndView mav = new ModelAndView("especialidade-form");
		mav.addObject("especialidade", new Especialidade());
		return mav;
	}

}
