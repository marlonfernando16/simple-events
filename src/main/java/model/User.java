package model;

import java.util.ArrayList;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class User {

	private Long id;
	@NotEmpty(message = "Nome e obrigatorio")
	private String nome;
//	@Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$", message = "Informe um telefone [(83) 98892-1223]")
	private String telefone;
	private String email;
	@Size(min = 8, message = "a senha deve conter no minino 8 caracteres")
	private String senha;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past(message = "A data deve estar no passado")
	private Date datanascimento;
	/* relação com eventos */
	private ArrayList<Evento> eventos = new ArrayList<>();
	/* relacao com candidato_vaga */
	private Candidato_vaga candidato_vaga;
	/* relacao com Avaliacao_evento */
	private Avaliacao_evento avaliacao_evento;

	public User() {};

	public Avaliacao_evento getAvaliacao_evento() {
		return avaliacao_evento;
	}

	public void setAvaliacao_evento(Avaliacao_evento avaliacao_evento) {
		this.avaliacao_evento = avaliacao_evento;
	}

	public Candidato_vaga getCandidato_vaga() {
		return candidato_vaga;
	}

	public void setCandidato_vaga(Candidato_vaga candidato_vaga) {
		this.candidato_vaga = candidato_vaga;
	}

	public ArrayList<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(ArrayList<Evento> eventos) {
		this.eventos = eventos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void addEvento(Evento ev) {
		this.eventos.add(ev);
	}

	public void remove(Evento ev) {
		this.eventos.remove(ev);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", email=" + email + ", senha=" + senha
				+ ", datanascimento=" + datanascimento + "]";
	}

}
