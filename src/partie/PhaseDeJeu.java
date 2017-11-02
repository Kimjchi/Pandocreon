package partie;

public enum PhaseDeJeu {

	PhaseDéfausse ("Phase de défausse"),
	
	PhasePioche ("Phase de Pioche"),
	
	PhaseJeu ("Phase de jeu"),
	
	PhaseAdversaire ("Tour des adversaires"),
	
	PhaseTourFini ("Tour terminé"),
	
	PhaseGuideSpirituel ("Sélection Croyant"),
	
	FinPhaseGuideSpirituel (""),
	
	SélectionAdversaire ("Séléction adversaire");

	private String phase;

	// Constructeur
	PhaseDeJeu(String phase) {
		this.phase = phase;
	}

	public String toString() {
		return phase;
	}
	
}
