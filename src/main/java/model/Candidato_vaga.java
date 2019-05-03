package model;

public class Candidato_vaga {
	private Vaga vaga;
	private int nota_desempenho;
	private State state;
	private User candidato;
	
	public Candidato_vaga() {};
	
	public Vaga getVaga() {
		return vaga;
	}
	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}
	public int getNota_desempenho() {
		return nota_desempenho;
	}
	public void setNota_desempenho(int nota_desempenho) {
		this.nota_desempenho = nota_desempenho;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public User getCandidato() {
		return candidato;
	}
	public void setCandidato(User candidato) {
		this.candidato = candidato;
	}
	

}
