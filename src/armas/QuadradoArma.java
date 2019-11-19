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
	private static int tamX = 40;
	private static int tamY = 30;
	private Color cor;
	public static int getLargura() {
		return tamX;
	}
	public static int getAltura() {
		return tamY;
	}
	public void setCor(Color cor) {
		this.cor = cor;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		Rectangle2D rt;
		g2d.setColor(cor);
		rt = new Rectangle2D.Double(0, 0, tamX, tamY);
		g2d.fill(rt);
	}
}
