package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Line2D.Double;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import tratadores.*;
import regras.*;
import observador.Observable;
import observador.Observer;
import observador.*;


public class PNCampoDeBatalha extends JPanel implements MouseListener, AvisaCliqueCampoBatalha, Observer {
	Jogador jog;
	Celula tab[][] = new Celula[15][15];
	Line2D.Double lnX[] = new Line2D.Double[16];
	Line2D.Double lnY[] = new Line2D.Double[16];
	double xIni, yIni, larg, alt, xFim, yFim;
	Facade f = Facade.getFacade();
	List<EscutaCliqueCampoBatalha> lob=new ArrayList<EscutaCliqueCampoBatalha>();
	public PNCampoDeBatalha(double xInicial, double yInicial, double largura, double altura) {
		double x, y;
		this.alt = altura;
		this.larg = largura;
		this.setLayout(null);
		this.setBounds( (int) xInicial, (int) yInicial, (int) largura + 1, (int) altura + 1); // +1 para incluir as ultimas linhas horizontal e vertical
		this.jog = jog;
		xIni = 20; 
		yIni = 20;
		xFim = larg;
		yFim = alt;
		alt = (alt - yIni)/15;
		larg = (larg - xIni)/15;
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
	
	public void setJog(Jogador j) {
		jog = j;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		desenhaIndices(g);
		desenhaCampo(g);
		desenhaLinhas(g);
	}
	
	private void desenhaIndices(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
		g2d.setPaint(Color.black);
		//System.out.print("\nCoordenadas Campo [" + xIni +", " + yIni + "] " + " -- [" + xIni + larg + ", " + yIni + alt + "]\n");
		for(int i =0; i<15; i++) {
			String write = Integer.toString(i+1);
			g2d.drawString(write, (int) (tab[0][i].x + larg/3), (int) yIni/2); // (yIni / 2) porque 0 não exibe a string
			g2d.drawString(write, 0, (int) (tab[i][0].y + alt/2));
			//System.out.print("Coordenadas stringX: " + (int) (tab[0][i].x + larg/2) + ", 0\n");
			//System.out.print("Coordenadas stringY: 0, " + (int) (tab[i][0].y + alt/2) + "\n");
		}
	}
	
	private void desenhaLinhas(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
		g2d.setPaint(Color.black);
		for(int i =0; i<16; i++) {
			g2d.draw(lnX[i]);
			g2d.draw(lnY[i]);
		}
	}
	
	private void desenhaCampo(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
		Rectangle2D rt;
		int campoJog[][];
		if(jog.getId() == 1) {
			campoJog = f.getTab1();
		}
		else {
			campoJog = f.getTab2();
		}
		for(int i=0;i<15;i++) {
			for(int j=0;j<15;j++) {
				if(campoJog[j][i] == 1 && jog.equals(f.getJogadorDaVez())) g2d.setPaint(new Color(27,115,2));
				else if(campoJog[j][i] == 2) g2d.setPaint(new Color(0, 10, 69));
				else if(campoJog[j][i] == 3) g2d.setPaint(new Color(207, 2, 2));
				else if(campoJog[j][i] == 4) g2d.setPaint(new Color(115, 2, 17));
				else g2d.setPaint(Color.cyan);
				rt=new Rectangle2D.Double(tab[i][j].x, tab[i][j].y, tab[i][j].larg, tab[i][j].alt);
				//System.out.print("Rectangle2D.Double("+tab1[i][j].x+", "+tab1[i][j].y + ", "+tab1[i][j].larg + ", " + tab1[i][j].alt +");\n");
				g2d.fill(rt);
			}
		}
	}
	
	
	public void mouseClicked(MouseEvent e) { 
		if(SwingUtilities.isLeftMouseButton(e)) {
			int x=e.getX(),y=e.getY();
			x = (int)( (double) (x - xIni) / larg);
			y = (int)( (double) (y - yIni) / alt);
			avisaCliqueObservadores(x, y, this.jog);
		}
	}
	
	public double getAbsX(int x, int y) {
		return tab[y][x].x;
	}
	public double getAbsY(int x, int y) {
		return tab[y][x].y;
	}
	
	public int tipo() {
		return 1;
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	public void addCliqueListener(EscutaCliqueCampoBatalha o) {
		lob.add(o);
	}

	public void removeCliqueListener(EscutaCliqueCampoBatalha o) {
		lob.remove(o);
	}

	public void avisaCliqueObservadores(int x, int y, Jogador jogador) {
		for(EscutaCliqueCampoBatalha o:lob)
			o.recebeClique(x, y, jogador);
	}

	public void notify(Observable o) {
		repaint();
	}
	
	public int getLarguraCelula() {
		return (int) larg;
	}
	public int getAlturaCelula() {
		return (int) alt;
	}
}
