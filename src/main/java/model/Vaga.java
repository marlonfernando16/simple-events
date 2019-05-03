package model;

import java.util.ArrayList;

public class Vaga {
	private Long id;
	private Evento evento;
	private int qtd_vagas;
	private ArrayList<Candidato_vaga> candidato_vaga = new ArrayList<>();
	private Especialidade especialidade;
	
	public Vaga() {};
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public int getQtd_vagas() {
		return qtd_vagas;
	}
	public void setQtd_vagas(int qtd_vagas) {
		this.qtd_vagas = qtd_vagas;
	}
	public ArrayList<Candidato_vaga> getCandidato_vaga() {
		return candidato_vaga;
	}
	public void setCandidato_vaga(ArrayList<Candidato_vaga> candidato_vaga) {
		this.candidato_vaga = candidato_vaga;
	}
	
	
	
	
	

}
