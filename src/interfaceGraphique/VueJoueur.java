package interfaceGraphique;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import cartes.Divinit�;
import joueurs.IA;
import joueurs.Joueur;
import message.Message;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import net.miginfocom.swing.MigLayout;

public class VueJoueur extends JButton implements Observer, ThemeFonc�, MouseListener {
	/**
	 * Constante d'identification pour la s�rialisation.
	 */
	public static final long serialVersionUID = 1l;
	
	/**
	 * R�f�rence vers le joueur observ� par le composant.
	 * 
	 * Une valeur nulle signifie que le composant est dans un �tat non 
	 * utilisable.
	 * 
	 * @see fr.bragabresolin.menhir.Core.Joueurs.Joueur
	 */
	private Joueur joueur;
	
	/**
	 * Label affich� contenant le nom du joueur suivi.
	 */
	private JLabel labelNom;

	/**
	 * Label affich� contenant le nombre de points du joueur suivi.
	 */
	private JLabel labelPointsJour;
	
	/**
	 * Label affich� contenant le nombre de points du joueur suivi.
	 */
	private JLabel labelPointsNuit;
	
	/**
	 * Label affich� contenant le nombre de points du joueur suivi.
	 */
	private JLabel labelPointsN�ant;

	/**
	 * Label affich� contenant le nombre de graines du joueur suivi.
	 */
	private JLabel labelPri�res;

	/**
	 * Label affich� contenant le nombre de menhirs du joueur suivi.
	 */
	private VueDevantJoueur vueCarteDevant;
	
	public VueCarteDivinit� vueDivinit�;
	

	/**
	 * Constructeur de la vue du joueur.
	 * Une vue du joueur est compos�e de toutes les informations courantes sur 
	 * le joueur, qui sont initialis�es avec le premier �tat du joueur donn�.
	 *
	 * @param joueur Le joueur � observer
	 */
	public VueJoueur(Joueur joueur) {
		this.joueur = joueur;
		this.joueur.addObserver(this);
		this.addMouseListener(this);
		
		if (!(joueur instanceof IA)) setBackground(ACCENT_2);
		else setBackground(DARK_BG);
		setLayout(new MigLayout("", "15[20%]10[80%]", "15[25%][25%][25%][25%]"));
		
		// Label du nom 
		this.labelNom = new JLabel(this.joueur.getNom());
		this.labelNom.setVerticalAlignment(SwingConstants.TOP);
		this.labelNom.setFont(new Font("SansSerif", Font.BOLD, 14));
		this.labelNom.setHorizontalAlignment(SwingConstants.LEFT);
		this.labelNom.setForeground(LIGHT_FG);
		this.add(this.labelNom, "cell 0 0 2 1,alignx left,aligny top");
		
		// Label des points 
		JLabel lblP = new JLabel("Pri�res");
		lblP.setToolTipText("Pri�res");
		lblP.setHorizontalAlignment(SwingConstants.LEFT);
		lblP.setForeground(LIGHT_FG);
		lblP.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.add(lblP, "cell 0 1,alignx center,aligny center");
		
		this.labelPri�res = new JLabel("" + this.joueur.getnbPri�res());
		this.labelPri�res.setForeground(LIGHT_FG);
		this.labelPri�res.setFont(new Font("SansSerif", Font.PLAIN, 12));
		this.add(this.labelPri�res, "cell 1 1");
		
		
		JLabel lblAJ = new JLabel("Points d'Action Jour");
		lblAJ.setToolTipText("Points d'Action Jour");
		lblAJ.setHorizontalAlignment(SwingConstants.LEFT);
		lblAJ.setForeground(LIGHT_FG);
		lblAJ.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.add(lblAJ, "cell 0 2,alignx center,aligny center");
		
		this.labelPointsJour = new JLabel("" + this.joueur.getPointsJour());
		this.labelPointsJour.setFont(new Font("SansSerif", Font.PLAIN, 12));
		this.labelPointsJour.setForeground(LIGHT_FG);
		this.add(this.labelPointsJour, "cell 1 2");
		
		
		JLabel lblANu = new JLabel("Points d'Action Nuit");
		lblANu.setToolTipText("Points d'Action Nuit (prot\u00E9g\u00E9es)");
		lblANu.setHorizontalAlignment(SwingConstants.LEFT);
		lblANu.setForeground(LIGHT_FG);
		lblANu.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.add(lblANu, "cell 0 3,alignx center,aligny center");
		
		this.labelPointsNuit = new JLabel("" + this.joueur.getPointsNuit());
		this.labelPointsNuit.setFont(new Font("SansSerif", Font.PLAIN, 12));
		this.labelPointsNuit.setForeground(LIGHT_FG);
		this.add(this.labelPointsNuit, "cell 1 3");
		
		
		JLabel lblAN� = new JLabel("Points d'Action N�ant");
		lblAN�.setToolTipText("Points d'Action N�ant (prot\u00E9g\u00E9es)");
		lblAN�.setHorizontalAlignment(SwingConstants.LEFT);
		lblAN�.setForeground(LIGHT_FG);
		lblAN�.setFont(new Font("SansSerif", Font.BOLD, 12));
		this.add(lblAN�, "cell 0 4,alignx center,aligny center");
		
		this.labelPointsN�ant = new JLabel("" + this.joueur.getPointsNeant());
		this.labelPointsN�ant.setFont(new Font("SansSerif", Font.PLAIN, 12));
		this.labelPointsN�ant.setForeground(LIGHT_FG);
		this.add(this.labelPointsN�ant, "cell 1 4");
		
	}
	
	/**
	 * Met � jour l'affichage du joueur.
	 * On met � jour toutes ses statistiques.
	 * On met en valeur le nom du joueur si c'est son tour de jouer.
	 * 
	 * @param o L'objet observ� (le joueur)
	 * @param message Le message envoy�
	 */
	public void update (Observable o, Object message) {
		this.labelNom.setText(this.joueur.getNom());
		this.labelPointsJour.setText("" + this.joueur.getPointsJour());
		this.labelPointsNuit.setText("" + this.joueur.getPointsNuit());
		this.labelPointsN�ant.setText("" + this.joueur.getPointsNeant());
		this.labelPri�res.setText("" + this.joueur.getnbPri�res());
		//this.vueCarteDevant.update(o, message);
		
		if (message instanceof Message) {
			Message mes = (Message) message;
			switch(mes.getType()) {
			case JOUEUR_DEBUT_TOUR:
				this.labelNom.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, LIGHT_FG));
				break;
			case JOUEUR_FIN_TOUR:
				this.labelNom.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, DARK_BG));
				break;
			default:
				break;
			}
		}
		this.repaint();
		this.revalidate();
	}
	
	public void mousePressed(MouseEvent event) { 
		this.vueDivinit� = new VueCarteDivinit�(joueur.getDivinit�());
		this.vueCarteDevant = new VueDevantJoueur(joueur);
	}
	

	  //M�thode appel�e lors du clic de souris

	  public void mouseClicked(MouseEvent event) { }


	  //M�thode appel�e lors du survol de la souris

	  public void mouseEntered(MouseEvent event) { }


	  //M�thode appel�e lorsque la souris sort de la zone du bouton

	  public void mouseExited(MouseEvent event) { }
	  

	  //M�thode appel�e lorsque l'on rel�che le clic de souris

	  public void mouseReleased(MouseEvent event) { }   
	
	/**
	 * Nettoie la vue du joueur en d�connectant l'observateur.
	 * 
	 * Cette m�thode est n�cessaire afin de ne pas se retrouver avec plusieurs 
	 * observateurs sur le m�me joueur lors de la cr�ation de nouvelles vues de 
	 * joueurs (par exemple lors de changement de manche).
	 */
	public void nettoyer() {
		this.joueur.deleteObserver(this);
	}

}


