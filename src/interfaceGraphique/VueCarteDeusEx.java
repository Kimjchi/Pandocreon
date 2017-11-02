package interfaceGraphique;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cartes.DeusEx;
import interfaceGraphique.buttons.ButtonActiverDeusEx;
import interfaceGraphique.buttons.ButtonDéfausser;
import net.miginfocom.swing.MigLayout;

public class VueCarteDeusEx extends JFrame implements Observer, ThemeFoncé{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton buttonUtiliser;
	private JButton buttonDéfausser;



	public VueCarteDeusEx(DeusEx deusEx) {
		
		deusEx.addObserver(this);
		this.setTitle("DeusEx");
		this.setResizable(false);
		
		this.setBounds(0, 0, 343, 484);
		
		this.getContentPane().setLayout(
				new MigLayout("", "0[100%,grow]0", "0[95%]0[5%]0"));
		this.getContentPane().add( new VueCarte(deusEx.getNom(),deusEx), "cell 0 0, grow");
		

	    //On ajoute le bouton au content pane de la JFrame

	    //Au centre
		JPanel Div = new JPanel();
		
		Div.setLayout(new GridLayout(1,2));
		
		ButtonDéfausser buttonDéfausser = new ButtonDéfausser(this, deusEx);
		
		ButtonActiverDeusEx buttonActiver = new ButtonActiverDeusEx(deusEx);
		
		
	   // this.getContentPane().add(new JButton("Utiliser capacité"));
	   // this.getContentPane().add(new JButton("Défausser"));
	    
	    Div.add(buttonActiver);
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
