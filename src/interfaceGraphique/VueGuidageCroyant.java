package interfaceGraphique;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import interfaceGraphique.buttons.ButtonStop;
import net.miginfocom.swing.MigLayout;

public class VueGuidageCroyant extends JFrame implements ThemeFoncé{

	private ButtonStop stop = new ButtonStop();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public VueGuidageCroyant() {
		this.setTitle("Fermer la fenêtre quand vous avez fini de guider");
		this.setResizable(false);
		this.setBackground(DARK_BG);
		this.setForeground(LIGHT_FG);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(0, 200, dim.width, (int) (dim.height / 3.06 + 30));
		;
		JPanel panel = new JPanel();
		//panel.add(stop);
		
		panel.setLayout(new GridLayout(2,1));
		panel.add(new VueMilieuTable());
		//this.setPreferredSize(new Dimension(dim.width, (int) (dim.height / 3.06)));
		this.setContentPane(panel);
		
		this.getContentPane().setLayout(
				new MigLayout("", "0[100%,grow]0", "0[100%, grow]0"));
		

		//this.getContentPane().add(panel, "cell 0 0, grow");
		this.setVisible(true);
	}
	
	public ButtonStop getStop() {
		//if (stop.aFini() == true) { this.dispose();}
		return this.stop;
	}
	
	

}
