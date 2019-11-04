package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Line2D.Double;
import java.awt.event.*;
import tratadores.*;
import regras.*;


public class CampoDeBatalha extends JPanel implements MouseListener {
	int jog;
	Celula tab[][] = new Celula[15][15];
	Line2D.Double lnX[] = new Line2D.Double[16];
	Line2D.Double lnY[] = new Line2D.Double[16];
	double xIni, yIni, larg, alt, xFim, yFim;
	Facade f = Facade.getFacade();
	public CampoDeBatalha(double xInicial, double yInicial, double largura, double altura, int jog) {
		double x, y;
		this.alt = altura;
		this.larg = largura;
		this.setLayout(null);
		this.setBounds( (int) xInicial, (int) yInicial, (int) largura + 1, (int) altura + 1); // +1 para incluir as ultimas linhas horizontal e vertical
		this.jog = jog;
		xIni = 0; 
		yIni = 0;
		xFim = larg;
		yFim = alt;
		alt = alt/15;
		larg = larg/15;
		addMouseListener(this);
		y = yIni;
		for(int i =0; i<15; i++) {
			// Para cada linha
			x = xIni;
			for(int j=0; j<15; j++) {
				// Em cada coluna
				tab[i][j] = new Celula(x, y, larg, alt);
				x+=larg;
				
			}
			y+=alt;
		}
		y = yIni;
		// Cria as linhas horizontais
		for(int i = 0; i<16; i++) {
			lnY[i] = new Line2D.Double(xIni,y,xFim,y);
			y+=alt;
		}
		x = xIni;
		// Cria as linhas verticais
		for(int i = 0; i<16; i++) {
			lnX[i] = new Line2D.Double(x,yIni,x,yFim);
			x+=larg;
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		Rectangle2D rt;
		int campoJog[][];
		if(jog == 1) {
			campoJog = f.getTab1();
		}
		else {
			campoJog = f.getTab2();
		}
		for(int i=0;i<15;i++) {
			for(int j=0;j<15;j++) {
				if(campoJog[j][i] == 1) g2d.setPaint(Color.red);
				else g2d.setPaint(Color.cyan);
				rt=new Rectangle2D.Double(tab[i][j].x, tab[i][j].y, tab[i][j].larg, tab[i][j].alt);
				//System.out.print("Rectangle2D.Double("+tab1[i][j].x+", "+tab1[i][j].y + ", "+tab1[i][j].larg + ", " + tab1[i][j].alt +");\n");
				g2d.fill(rt);
			}
		}
		g2d.setPaint(Color.black);
		// Desenha todas as linhas 
		for(int i =0; i<16; i++) {
			g2d.draw(lnX[i]);
			g2d.draw(lnY[i]);
		}
		// Desenha os indices
		for(int i =0; i<15; i++) {
			String write = Integer.toString(i+1);
			g2d.drawString(write, (float) ( tab[0][i].x + larg/2 ), (float) ( yIni - alt/2 ) );
			g2d.drawString(write, (float) ( xIni - larg/2 ), (float) (tab[i][0].y + alt/2));;
		}
	}
	
	public void mouseClicked(MouseEvent e) { 
		int x=e.getX(),y=e.getY();
		System.out.print("Mouse Clicado x=" + x + " y=" + y + "\n");
		x = (int)( (double) x / larg);
		y = (int)( (double) y / alt);
		f.atira(x, y, jog);
		repaint();
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
