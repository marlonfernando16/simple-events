package model;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class Evento {
	
	private Long id;
	
	@NotEmpty(message = "Descrição é obrigatória")
	private String descricao;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Data é obrigatória")
	@Future(message = "A data deve estar no futuro")
	private Date data;
	
	private String local;
	
	public Evento() {}
	
	public Evento(String d, Date dh, String l) {
		super();
		this.descricao = d;
		this.data = dh;
		this.local = l;
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
