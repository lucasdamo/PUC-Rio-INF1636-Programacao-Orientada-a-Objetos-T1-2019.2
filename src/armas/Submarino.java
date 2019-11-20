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

public class Submarino extends Arma {
	private QuadradoArma bloco1;
	public static Color cor = new Color(0,51,153);
	public Submarino(int x, int y) {
		bloco1 = new QuadradoArma();
		setCor(Submarino.cor);
		this.setBounds(x,y, QuadradoArma.getLargura(), QuadradoArma.getAltura());
	}
	
	private void setCor(Color cor) {
		bloco1.setCor(cor);
	}
	public void setOpaque() {
		//bloco1.setOpaque((float)0.5);
		bloco1.setCor(Submarino.opColor);
	}
	public void paintComponent(Graphics g) {
		bloco1.paintComponent(g);
		repaint();
	}
	
}
