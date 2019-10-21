package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Line2D.Double;
import java.awt.event.*;
import regras.*;
 
public class PNBatalhaNaval extends JPanel implements MouseListener {
	int iClick,jClick;
	Toolkit tk=Toolkit.getDefaultToolkit();
	Dimension screenSize=tk.getScreenSize();
	CtrlRegras ctrl;
	double wid = screenSize.getWidth(), hei = screenSize.getHeight();
	double xIni = 50.0, xFim = (wid/2) - xIni; 
	double yIni = 50.0, yFim = hei - 3* yIni;
	double xIni2 = wid/2 + xIni;
	double xFim2 = wid - xIni;
	Celula tab1[][] = new Celula[15][15];
	Celula tab2[][] = new Celula[15][15];
	Line2D.Double lnX1[] = new Line2D.Double[16];
	Line2D.Double lnX2[] = new Line2D.Double[16];
	Line2D.Double lnY1[] = new Line2D.Double[16];
	Line2D.Double lnY2[] = new Line2D.Double[16];
	public PNBatalhaNaval(CtrlRegras c) {
		double x1, x2, y1, y2;
		y1 = yIni; y2=yIni;
		double larg = (xFim - xIni)/15;
		double alt = (yFim - yIni) / 15;
		addMouseListener(this);
		
		for(int i =0; i<15; i++) {
			x1 = xIni; x2=xIni2;
			for(int j=0; j<15; j++) {
				tab1[i][j] = new Celula(x1, y1, larg, alt);
				tab2[i][j] = new Celula(x2, y2, larg, alt);
				x1+=alt;
				x2+=alt;
			}
			y1+=larg;
			y2+=larg;
		}
		y1 = yIni; y2=yIni;
		for(int i = 0; i<16; i++) {
			lnY1[i] = new Line2D.Double(xIni,y1,xFim,y1);
			lnY2[i] = new Line2D.Double(xIni2,y2,xFim2,y2);
			y1+=larg;
			y2+=larg;
		}
		x1 = xIni; x2=xIni2;
		for(int i = 0; i<16; i++) {
			lnX1[i] = new Line2D.Double(x1,yIni,x1,yFim);
			lnX2[i] = new Line2D.Double(x2,yIni,x2,yFim);
			x1+=alt;
			x2+=alt;
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		Rectangle2D rt;
		for(int i=0;i<15;i++) {
			for(int j=0;j<15;j++) {
				g2d.setPaint(Color.cyan);
				rt=new Rectangle2D.Double(tab1[i][j].x, tab1[i][j].y, tab1[i][j].larg - 4.0, tab1[i][j].alt - 4.0);
				//System.out.print("Rectangle2D.Double("+tab1[i][j].x+", "+tab1[i][j].y + ", "+tab1[i][j].larg + ", " + tab1[i][j].alt +");\n");
				g2d.fill(rt);
				rt=new Rectangle2D.Double(tab2[i][j].x, tab2[i][j].y, tab2[i][j].larg - 4.0, tab2[i][j].alt - 4.0);
				g2d.fill(rt);
			}
		}
		g2d.setPaint(Color.black);
		for(int i =0; i<16; i++) {
			g2d.draw(lnX1[i]);
			g2d.draw(lnX2[i]);
			g2d.draw(lnY1[i]);
			g2d.draw(lnY2[i]);
		}
		
	}
	
	public void mouseClicked(MouseEvent e) {
		int x=e.getX(),y=e.getY();
		if(x < xFim && x > xIni && y < yFim && y > yIni) {
			JOptionPane.showMessageDialog(this, "Tabuleiro1");
		}
		else if (x < xFim2 && x > xIni2 && y < yFim2 && y > yIni2) {
			JOptionPane.showMessageDialog(this, "Tabuleiro2");
		}
//		else if(x < xFim2 && x > xIni2 && y < yFim2 && y > yIni2);
		System.out.print("Mouse Clicado");
		repaint();
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}