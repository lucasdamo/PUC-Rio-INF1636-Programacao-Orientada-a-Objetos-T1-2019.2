package armas;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Line2D.Double;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import controladores.EstadoJogo;

public abstract class Arma extends JPanel {
	private Rotacao rot = Rotacao.ZeroGraus;
	public void rotate() {
		int index = rot.ordinal();
		int nextIndex = index + 1;
		Rotacao[] orientacoes = Rotacao.values();
		nextIndex %= orientacoes.length;
		rot = orientacoes[nextIndex];
	}
	public abstract void paintComponent(Graphics g);
}
