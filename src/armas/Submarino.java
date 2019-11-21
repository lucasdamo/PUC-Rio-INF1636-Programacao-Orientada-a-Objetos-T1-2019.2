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
	public Submarino(int x, int y, int relX, int relY) {
		super(x, y, relX, relY);
		this.setBounds(this.x, this.y, QuadradoArma.getLargura(), QuadradoArma.getAltura());
		rotate(this.relX, this.relY);
		cor = new Color(0,51,153);
		setCor(cor);
	}
	
	public static int getLarguraPadrao() {
		return QuadradoArma.getLargura();
	}
	public static int getAlturaPadrao() {
		return QuadradoArma.getAltura();
	}
	
	@Override
	public void rotate(int relX, int relY) {
		// Submarino so tem 1 quadrado, rotacionar nao importa
		loqa.removeAll(loqa);
		loqa.add(new QuadradoArma(0,0, this.relX, this.relY));
		largura = QuadradoArma.getLargura();
		altura = QuadradoArma.getAltura();
		this.setBounds(this.x, this.y, this.largura, this.altura);
		repaint();
	}
	@Override
	public void unOpaque() {
		// TODO Auto-generated method stub
		setCor(cor);
	}
}
