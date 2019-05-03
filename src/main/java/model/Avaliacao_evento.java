package model;

public class Avaliacao_evento {
	private Evento evento;
	private int nota_avaliacao_evento;
	private User participante;

	public Avaliacao_evento() {};

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public int getNota_avaliacao_evento() {
		return nota_avaliacao_evento;
	}

	public void setNota_avaliacao_evento(int nota_avaliacao_evento) {
		this.nota_avaliacao_evento = nota_avaliacao_evento;
	}

	public User getParticipante() {
		return participante;
	}

	public void setParticipante(User participante) {
		this.participante = participante;
	}

}
