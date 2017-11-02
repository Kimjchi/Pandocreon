package controleur;

import cartes.Carte;
import joueurs.Joueur;

public class ControllerJoueur {
	
	Joueur joueur;
	
	public ControllerJoueur(Joueur joueur){
		this.joueur = joueur;
	}
	
	public Carte getDivinit�(){
		return this.joueur.getDivinit�();
	}
	
	public String getNomDivinit�(){
		return this.joueur.getDivinit�().getNom();
	}
}
