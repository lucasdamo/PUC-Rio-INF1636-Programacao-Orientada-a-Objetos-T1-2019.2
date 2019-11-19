package armas;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Line2D.Double;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import controladores.EstadoJogo;

public class Submarino extends Arma {
	private QuadradoArma bloco1;
	
	public Submarino() {
		bloco1 = new QuadradoArma();
		this.setBounds(0,0, QuadradoArma.getLargura(), QuadradoArma.getAltura());
	}
	public void paintComponent(Graphics g) {
		bloco1.paintComponent(g);
		repaint();
	}
}
