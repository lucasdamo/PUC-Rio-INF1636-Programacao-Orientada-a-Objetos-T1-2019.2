package gui;

import regras.*;

import java.awt.*;
import javax.swing.*;

public class FRBatalhaNaval extends JFrame {
	
	public FRBatalhaNaval(CtrlRegras c) {
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		setBounds(0,0,sl,sa);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(new PNBatalhaNaval(c));
		setTitle("Batalha Naval");
	}
	
	public static void main(String args[]) {
		
		(new FRBatalhaNaval(new CtrlRegras())).setVisible(true);
	}
}
