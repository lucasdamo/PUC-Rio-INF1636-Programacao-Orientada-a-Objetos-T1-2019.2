package gui;

import regras.*;

import java.awt.*;
import javax.swing.*;

public class FRBatalhaNaval extends JFrame {
	
	public FRBatalhaNaval(Facade f) {
		CampoDeBatalha tabEsq;
		CampoDeBatalha tabDir;
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		double wid = screenSize.getWidth(), hei = screenSize.getHeight();
		setBounds(0,0,(int)wid,(int)hei);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		tabEsq = new CampoDeBatalha(0, 20, (wid/3), 2*hei/3, 1);
		tabDir = new CampoDeBatalha(wid/2, 20, (wid/3), 2*hei/3, 2);
		getContentPane().add(tabEsq);
		getContentPane().add(tabDir);
		setTitle("Batalha Naval");
	}
}
