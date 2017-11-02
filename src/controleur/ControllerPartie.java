package controleur;

import java.util.ArrayList;

import joueurs.Joueur;
import partie.Partie;

public class ControllerPartie {
	public ControllerPartie(){
		
	}
	
	public String getNomJEnCours(){
		return Partie.getInstance().getjEnCours().getNom();
	}
	
	public void setChoixInterface(int choix){
		Partie.getInstance().setChoixInterface(choix);
	}
	
	public void initialiserPartie(int numVirtuels, int diff, ArrayList<String> nomJoueur){
		Partie.getInstance().InitialiserPartie(numVirtuels, diff, nomJoueur);
	}
	
	public boolean getEstEnCours(){
		return Partie.getInstance().getEstEnCours();
	}
	
	public void tourComplet(){
		Partie.getInstance().tourComplet(Partie.getInstance().getJoueurs(), Partie.getInstance());
	}
	
	public void terminerPartie(){
		Partie.getInstance().terminerPartie();
	}
	
	public Joueur getJEnCours(){
		return Partie.getInstance().getjEnCours();
	}
	
	
}
