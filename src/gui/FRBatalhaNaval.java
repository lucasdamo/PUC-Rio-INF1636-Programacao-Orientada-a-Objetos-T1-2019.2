package gui;

import regras.*;

import java.awt.*;
import javax.swing.*;

public class FRBatalhaNaval extends JFrame {
	
	public FRBatalhaNaval(CtrlRegras c) {
		CampoDeBatalha tabEsq;
		CampoDeBatalha tabDir;
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		double wid = screenSize.getWidth(), hei = screenSize.getHeight();
		setBounds(0,0,(int)wid,(int)hei);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		tabEsq = new CampoDeBatalha(50,50, (wid/2) - 50, hei - 3 * 50, c.getTab1());
		tabDir = new CampoDeBatalha(wid/2 + 50, 50, wid - 50, hei - 3 * 50, c.getTab2());
		getContentPane().add(new PNBatalhaNaval(c));
		setTitle("Batalha Naval");
	}
}
