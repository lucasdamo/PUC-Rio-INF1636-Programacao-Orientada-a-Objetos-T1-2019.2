package armas;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Line2D.Double;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import controladores.EstadoJogo;
import observador.*;
import observador.Observable;
import observador.Observer;

public abstract class Arma extends JPanel implements MouseListener, Observable {
	private Rotacao rot = Rotacao.ZeroGraus;
	List<Observer> lob = new ArrayList<Observer>();
	List<QuadradoArma> loqa = new ArrayList<QuadradoArma>();
	Color cor;
	int x, relX;
	int y, relY;
	public static Color opColor = new Color(255,51,153);
	{
		addMouseListener(this);
	}
	
	public Arma(int x, int y, int relX, int relY) {
		this.x = x;
		this.y = y;
		this.relX = relX;
		this.relY = relY;
	}
	
	public void changeRotatation() {
		int index = rot.ordinal();
		int nextIndex = index + 1;
		Rotacao[] orientacoes = Rotacao.values();
		nextIndex %= orientacoes.length;
		rot = orientacoes[nextIndex];
	}
	public abstract void move(int x, int y, int relX, int relY);
	public abstract void rotate();
	
	public void setOpaque() {
		for(QuadradoArma qd : loqa) {
			qd.setCor(opColor);
		}
	}
	
	public abstract void unOpaque();
	
	public void setCor(Color cor) {
		for(QuadradoArma qd : loqa) {
			qd.setCor(cor);
		}
	}
	public void paintComponent(Graphics g) {
		for(QuadradoArma qd : loqa) {
			qd.paintComponent(g);
		}
	}
	public void addObserver(Observer o) {
		lob.add(o);
	}
	public void removeObserver(Observer o) {
		lob.add(o);
	}
	public Object get() {
		return this;
	}
	public void mouseClicked(MouseEvent e) { 
		System.out.print("Arma clicada\n");
		for(Observer o: lob) {
			o.notify(this);
		}
	}
	public void mouseEntered(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
}
