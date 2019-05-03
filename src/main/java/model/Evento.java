package model;

import java.util.ArrayList;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class Evento {

	private Long id;

	@NotEmpty(message = "Descrição é obrigatória")
	private String descricao;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Data é obrigatória")
	@Future(message = "A data deve estar no futuro")
	private Date data;
	private String local;
	/* relação com Dono do evento */
	private User owner;
	/* Relação com as vagas */
	private ArrayList<Vaga> vagas = new ArrayList<>();
	/*relação com avaliação_eventos*/
	private ArrayList<Avaliacao_evento>avaliacao_eventos = new ArrayList<>();
	
	public Evento() {}
	
	public Evento(String d, Date dh, String l) {
		super();
		this.descricao = d;
		this.data = dh;
		this.local = l;
	}

	public ArrayList<Avaliacao_evento> getAvaliacao_eventos() {
		return avaliacao_eventos;
	}

	public void setAvaliacao_eventos(ArrayList<Avaliacao_evento> avaliacao_eventos) {
		this.avaliacao_eventos = avaliacao_eventos;
	}

	public ArrayList<Vaga> getVagas() {
		return vagas;
	}

	public void setVagas(ArrayList<Vaga> vagas) {
		this.vagas = vagas;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date datahora) {
		this.data = datahora;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

}
