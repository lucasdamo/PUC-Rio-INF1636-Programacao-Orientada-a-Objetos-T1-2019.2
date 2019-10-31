package tratadores;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JOptionPane;

public class MouseClicado implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		int x=e.getX(),y=e.getY();
		if(x < xFim && x > xIni && y < yFim && y > yIni) {
			x = (int)(((double) x - xIni) / larg);
			y = (int)(((double) y - yIni) / alt);
			JOptionPane.showMessageDialog(this, "Vez " + ctrl.getVez() + " Tabuleiro 1   x=" + x + " y=" + y);
			ctrl.atira(1, x, y);
			
		}
		else if (x < xFim2 && x > xIni2 && y < yFim2 && y > yIni2) {
			x = (int)(((double) x - xIni2) / larg);
			y = (int)(((double) y - yIni2) / alt);
			JOptionPane.showMessageDialog(this, "Vez " + ctrl.getVez() + " Tabuleiro 2   x=" + x + " y=" + y);
			ctrl.atira(2, x, y);
		}
//		else if(x < xFim2 && x > xIni2 && y < yFim2 && y > yIni2);
		System.out.print("Mouse Clicado");
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
