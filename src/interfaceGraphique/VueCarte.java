package interfaceGraphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cartes.Apocalypse;
import cartes.Carte;
import cartes.Type;
import cartes.Croyant;
import cartes.DeusEx;
import cartes.GuideSpirituel;
import cartes.capacités.GagnerPointAction;
import message.Message;

import interfaceGraphique.VueCarteDeusEx;

public class VueCarte extends JButton implements Observer, ThemeFoncé, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Carte carte;
	private BufferedImage image;
	private String nomCarte;

	public VueCarte (String nom, Carte carte) {
		this.carte = carte;
		this.nomCarte = nom;
		this.addMouseListener(this);
		this.initialize();
		
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(274, 387);
	}
	
	public void paint(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, 20, Color.cyan, true);
		g2d.setPaint(gp);
		g2d.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), this);
		g2d.setColor(Color.black);
	}
	
	public void initialize()
	// en fonction de l'id j'affiche l'image de la carte

	{

		this.image = null;
		String path = "src/resources/images/CarteAction/"+this.nomCarte+".png";
		System.out.println("C:/Users/Jérémy/Desktop/LO02/TD/Pandocreon divinae/src/resources/images/CarteAction/"+this.nomCarte+".png");
		File file = new File(path);
		try {
			this.image = ImageIO.read(file);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setSize(484, 343);
	}
	
	public void nettoyer() {
		this.carte.deleteObserver(this);
	}
	
	public void update (Observable o, Object message) {
		
	}
	
	public void mousePressed(MouseEvent event) { 
		if (this.carte.getType() == cartes.Type.DeusEx){
			new VueCarteDeusEx((DeusEx) this.carte);
		}
		
		else if(this.carte.getType()==cartes.Type.Croyant){
			new VueCroyant((Croyant) this.carte);
		}
		
		else if(this.carte.getType() == cartes.Type.GuideSpirituel){
			new VueCarteGuideSpirituel((GuideSpirituel) this.carte);
		}
		
		else if(this.carte.getType() == cartes.Type.Apocalypse){
			new VueCarteApocalypse((Apocalypse) this.carte);
		}
	}
	

	  //Méthode appelée lors du clic de souris

	  public void mouseClicked(MouseEvent event) { }


	  //Méthode appelée lors du survol de la souris

	  public void mouseEntered(MouseEvent event) { }


	  //Méthode appelée lorsque la souris sort de la zone du bouton

	  public void mouseExited(MouseEvent event) { }
	  

	  //Méthode appelée lorsque l'on relâche le clic de souris

	  public void mouseReleased(MouseEvent event) { }   

	
}

 


