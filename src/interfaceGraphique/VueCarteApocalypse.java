package interfaceGraphique;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cartes.Apocalypse;
import cartes.CarteAction;
import interfaceGraphique.buttons.ButtonApocalypse;
import interfaceGraphique.buttons.ButtonDéfausser;
import joueurs.Joueur;
import net.miginfocom.swing.MigLayout;

public class VueCarteApocalypse extends JFrame implements Observer, ThemeFoncé {
	
	private JButton buttonActiver;
	
	public VueCarteApocalypse(Apocalypse apocalypse) {
		
		apocalypse.addObserver(this);
		
		this.setTitle("Apocalypse");
		this.setResizable(false);
		
		this.setBounds(0, 0, 343, 484);
		
		this.getContentPane().setLayout(
				new MigLayout("", "0[100%,grow]0", "0[95%]0[5%]0"));
		this.getContentPane().add( new VueCarte(apocalypse.getNom(), apocalypse), "cell 0 0, grow");
		
		ButtonDéfausser buttonDéfausser = new ButtonDéfausser(this, apocalypse);
		
		ButtonApocalypse buttonApocalypse = new ButtonApocalypse(this, apocalypse);
		
	    //On ajoute le bouton au content pane de la JFrame

	    //Au centre
		JPanel Div = new JPanel();
		
		Div.setLayout(new GridLayout(1,2));
		
	   // this.getContentPane().add(new JButton("Utiliser capacité"));
	   // this.getContentPane().add(new JButton("Défausser"));
	    
	    Div.add(buttonApocalypse);
	    Div.add(buttonDéfausser);
	    
	    this.getContentPane().add(Div, "cell 0 1, grow");
	    
	    this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
