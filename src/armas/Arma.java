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
	Rotacao rot = Rotacao.ZeroGraus;
	ArrayList<Observer> lob = new ArrayList<Observer>();
	ArrayList<QuadradoArma> loqa = new ArrayList<QuadradoArma>();
	int x, relX;
	int y, relY;
	int largura, altura;
	Color cor;
	public static Color opColor = new Color(255,51,153);
	{
		addMouseListener(this);
	}
	
	public Color getCor() {
		return cor;
	}
	public Arma(int x, int y, int relX, int relY) {
		this.setLayout(null);
		this.setOpaque(false);
		//this.setBackground(Color.yellow);
		this.x = x;
		this.y = y;
		this.relX = relX;
		this.relY = relY;
	}
	
	public int getLargura() {
		return largura;
	}
	public int getAltura() {
		return altura;
	}
	public int getRelX() {
		return relX;
	}
	public int getRelY() {
		return relY;
	}
	public ArrayList<QuadradoArma> getListQuadradoArma(){
		return loqa;
	}
	
	public void changeRotatation() {
		int index = rot.ordinal();
		int nextIndex = index + 1;
		Rotacao[] orientacoes = Rotacao.values();
		nextIndex %= orientacoes.length;
		rot = orientacoes[nextIndex];
		rotate(this.relX, this.relY);
	}
	
	public void move(int x, int y, int relX, int relY) {
		// TODO Auto-generated method stub
		this.x = x;
		this.y = y;
		this.relX = relX;
		this.relY = relY;
		this.setLocation(x,y);
		rotate(relX, relY);
	}
	public abstract void rotate(int relX, int relY);
	
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
		repaint();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(QuadradoArma qd : loqa) {
			this.add(qd);
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
		if(SwingUtilities.isLeftMouseButton(e)) {
			for(Observer o: lob) {
				o.notify(this);
			}
		}
		else if (SwingUtilities.isRightMouseButton(e)) {
			this.changeRotatation();
			this.rotate(this.relX, this.relY);
		}
	}
	public void mouseEntered(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
}
