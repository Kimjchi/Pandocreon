package interfaceGraphique;

import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import partie.Partie;

public class VueResultatD� extends JPanel implements ThemeFonc�, Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VueResultatD�() {
		Partie.getInstance().addObserver(this);
		ImageIcon imgDiv = new ImageIcon(VueResultatD�.class.getClassLoader().getResource("resources/images/Base.png"));
		JLabel Div = new JLabel(imgDiv);
		Div.setIcon(imgDiv);
		this.add(Div);

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		this.removeAll();
		switch (Partie.getInstance().getR�sultatD�()) {
		case 1:
			ImageIcon imgDiv = new ImageIcon(
					VueResultatD�.class.getClassLoader().getResource("resources/images/Jour.png"));
			JLabel Div = new JLabel(imgDiv);
			Div.setToolTipText("Jour");
			Div.setIcon(imgDiv);
			this.add(Div);
			break;
		case 2:
			ImageIcon imgDiv2 = new ImageIcon(
					VueResultatD�.class.getClassLoader().getResource("resources/images/Nuit.png"));
			JLabel Div2 = new JLabel(imgDiv2);
			Div2.setToolTipText("Nuit");
			Div2.setIcon(imgDiv2);
			this.add(Div2);
			break;
			
		case 3:
			ImageIcon imgDiv3 = new ImageIcon(
					VueResultatD�.class.getClassLoader().getResource("resources/images/N�ant.png"));
			JLabel Div3 = new JLabel(imgDiv3);
			Div3.setToolTipText("N�ant");
			Div3.setIcon(imgDiv3);
			this.add(Div3);
			break;
		}
	}
}
