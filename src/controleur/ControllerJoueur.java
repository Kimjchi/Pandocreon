package controleur;

import cartes.Carte;
import joueurs.Joueur;

public class ControllerJoueur {
	
	Joueur joueur;
	
	public ControllerJoueur(Joueur joueur){
		this.joueur = joueur;
	}
	
	public Carte getDivinité(){
		return this.joueur.getDivinité();
	}
	
	public String getNomDivinité(){
		return this.joueur.getDivinité().getNom();
	}
}
