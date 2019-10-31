package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Line2D.Double;
import java.awt.event.*;
import tratadores.*;
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
	double yIni2 = yIni;
	double yFim2 = yFim;
	double larg = (xFim - xIni)/15;
	double alt = (yFim - yIni) / 15;
	Celula tab1[][] = new Celula[15][15];
	Celula tab2[][] = new Celula[15][15];
	Line2D.Double lnX1[] = new Line2D.Double[16];
	Line2D.Double lnX2[] = new Line2D.Double[16];
	Line2D.Double lnY1[] = new Line2D.Double[16];
	Line2D.Double lnY2[] = new Line2D.Double[16];
	public PNBatalhaNaval(CtrlRegras c) {
		double x1, x2, y1, y2;
		y1 = yIni; y2=yIni;
		addMouseListener(this);
		ctrl = c;
		for(int i =0; i<15; i++) {
			// Para cada linha
			x1 = xIni; x2=xIni2;
			for(int j=0; j<15; j++) {
				// Em cada coluna
				tab1[i][j] = new Celula(x1, y1, larg, alt);
				tab2[i][j] = new Celula(x2, y2, larg, alt);
				x1+=larg;
				x2+=larg;
			}
			y1+=alt;
			y2+=alt;
		}
		y1 = yIni; y2=yIni;
		// Cria as linhas horizontais
		for(int i = 0; i<16; i++) {
			lnY1[i] = new Line2D.Double(xIni,y1,xFim,y1);
			lnY2[i] = new Line2D.Double(xIni2,y2,xFim2,y2);
			y1+=alt;
			y2+=alt;
		}
		x1 = xIni; x2=xIni2;
		// Cria as linhas verticais
		for(int i = 0; i<16; i++) {
			lnX1[i] = new Line2D.Double(x1,yIni,x1,yFim);
			lnX2[i] = new Line2D.Double(x2,yIni,x2,yFim);
			x1+=larg;
			x2+=larg;
		}
	}
	
	public void paintComponent(Graphics g) {
		int campoJog1[][], campoJog2[][];
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		Rectangle2D rt;
		campoJog1 = ctrl.getTab1();
		campoJog2 = ctrl.getTab2();
		for(int i=0;i<15;i++) {
			for(int j=0;j<15;j++) {
				if(campoJog1[j][i] == 1) g2d.setPaint(Color.red);
				else g2d.setPaint(Color.cyan);
				rt=new Rectangle2D.Double(tab1[i][j].x, tab1[i][j].y, tab1[i][j].alt, tab1[i][j].larg);
				//System.out.print("Rectangle2D.Double("+tab1[i][j].x+", "+tab1[i][j].y + ", "+tab1[i][j].larg + ", " + tab1[i][j].alt +");\n");
				g2d.fill(rt);
				if(campoJog2[j][i] == 1) g2d.setPaint(Color.red);
				else g2d.setPaint(Color.cyan);
				rt=new Rectangle2D.Double(tab2[i][j].x, tab2[i][j].y, tab2[i][j].alt, tab2[i][j].larg);
				g2d.fill(rt);
			}
		}
		g2d.setPaint(Color.black);
		// Desenha todas as linhas 
		for(int i =0; i<16; i++) {
			g2d.draw(lnX1[i]);
			g2d.draw(lnX2[i]);
			g2d.draw(lnY1[i]);
			g2d.draw(lnY2[i]);
		}
		// Desenha os indices
		for(int i =0; i<15; i++) {
			String write = Integer.toString(i+1);
			g2d.drawString(write, (float) ( tab1[0][i].x + larg/2 ), (float) ( yIni - alt/2 ) );
			g2d.drawString(write, (float) ( xIni - larg/2 ), (float) (tab1[i][0].y + alt/2));
			g2d.drawString(write, (float) ( tab2[0][i].x + larg/2 ), (float) ( yIni2 - alt/2 ) );
			g2d.drawString(write, (float) ( xIni2 - larg/2 ), (float) (tab2[i][0].y + alt/2));
		}
		
	}
	
	public void mouseClicked(MouseEvent e) {
		MouseClicado(e);
		repaint();
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}