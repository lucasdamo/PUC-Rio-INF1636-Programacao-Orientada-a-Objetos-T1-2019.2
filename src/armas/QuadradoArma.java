package armas;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Line2D.Double;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import controladores.EstadoJogo;

public class QuadradoArma extends JPanel {
	private int tamX = 10;
	private int tamY = 5;
	private Color cor;
	public int getLargura() {
		return tamX;
	}
	public int getAltura() {
		return tamY;
	}
	public void setCor(Color cor) {
		this.cor = cor;
	}
	public void desenha(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		Rectangle2D rt;
		g2d.setColor(cor);
		rt = new Rectangle2D.Double(0, 0, tamX, tamY);
		g2d.fill(rt);
	}
}
