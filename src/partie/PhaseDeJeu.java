package partie;

public enum PhaseDeJeu {

	PhaseD�fausse ("Phase de d�fausse"),
	
	PhasePioche ("Phase de Pioche"),
	
	PhaseJeu ("Phase de jeu"),
	
	PhaseAdversaire ("Tour des adversaires"),
	
	PhaseTourFini ("Tour termin�"),
	
	PhaseGuideSpirituel ("S�lection Croyant"),
	
	FinPhaseGuideSpirituel (""),
	
	S�lectionAdversaire ("S�l�ction adversaire");

	private String phase;

	// Constructeur
	PhaseDeJeu(String phase) {
		this.phase = phase;
	}

	public String toString() {
		return phase;
	}
	
}
