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
	private static int tamX;
	private static int tamY;
	private Color cor;
	private float opaque = (float) 0.2;
	private int x; // Abscissa PIXELS referente ao JPanel arma
	private int y; // Ordenada PIXELS referente ao JPanel arma
	private int relX; // Abscissa INDICE referente ao campo de batalha
	private int relY; // Ordenada INDICE referente ao campo de batalha
	public QuadradoArma(int x, int y, int relX, int relY) {
		this.setLayout(null);
		this.relX = relX;
		this.relY = relY;this.setLayout(null);
		this.x = x;
		this.y = y;
		System.out.print("Quadrado criado " + x + ", " + y + ", " + relX + ", " + relY + "\n");
	}
	public int getRelX() {
		return relX;
	}
	public int getRelY() {
		return relY;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public static void setLargura(int largura) {
		tamX = largura;
	}
	public static void setAltura(int altura) {
		tamY = altura;
	}
	public static int getLargura() {
		return tamX;
	}
	public static int getAltura() {
		return tamY;
	}
	public void setCor(Color cor) {
		this.cor = cor;
	}
	public void setOpaque(float opValue) {
		this.opaque = opValue;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d=(Graphics2D) g;
		// Não funciona
		//AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.opaque);
		g2d.setColor(cor);
		//g2d.setComposite(alcom);
		
		Rectangle2D rt;		
		rt = new Rectangle2D.Double(this.x, this.y, tamX, tamY);
		g2d.fill(rt);
		
		// g2d.dispose(); IMPEDE A VISUALIZAÇÃO DOS QUADRADOS QUE NÃO O PRIMEIRO ! ???
	}
}
