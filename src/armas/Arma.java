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
	Color cor;
	public static Color opColor = new Color(255,51,153);
	{
		addMouseListener(this);
	}
	public void rotate() {
		int index = rot.ordinal();
		int nextIndex = index + 1;
		Rotacao[] orientacoes = Rotacao.values();
		nextIndex %= orientacoes.length;
		rot = orientacoes[nextIndex];
	}
	public abstract void setOpaque();
	public abstract void paintComponent(Graphics g);
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
