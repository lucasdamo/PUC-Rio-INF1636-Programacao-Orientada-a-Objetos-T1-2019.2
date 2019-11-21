package armas;

import java.awt.Color;

public class Destroyer extends Arma {
	public static Color cor = Color.yellow;
	public Destroyer(int x, int y, int relX, int relY) {
		super(x, y, relX, relY);
		setCor(Destroyer.cor);
		this.move(this.x, this.y, this.relX, this.relY);
		rotate();
		repaint();
	}
	@Override
	public void rotate() {
		// Submarino so tem 1 quadrado, rotacionar nao importa
		switch(this.rot) {
			case ZeroGraus:
				loqa.add(new QuadradoArma(0,0, this.relX, this.relY));
				loqa.add(new QuadradoArma(QuadradoArma.getLargura(), 0, this.relX + 1, this.relY));
				break;
			case NoventaGraus:
				loqa.add(new QuadradoArma(0,0, this.relX, this.relY));
				loqa.add(new QuadradoArma(0, QuadradoArma.getAltura(), this.relX, this.relY + 1));
				break;
			case CentoEOitentaGraus:
				loqa.add(new QuadradoArma(0,0, this.relX, this.relY));
				loqa.add(new QuadradoArma(QuadradoArma.getLargura(), 0, this.relX + 1, this.relY));	
				break;
		}	
	}
	@Override
	public void unOpaque() {
		// TODO Auto-generated method stub
		setCor(Destroyer.cor);
		repaint();
	}
	@Override
	public void move(int x, int y, int relX, int relY) {
		// TODO Auto-generated method stub
		this.x = x;
		this.y = y;
		this.relX = relX;
		this.relY = relY;
		System.out.print("Moved to " + this.x + ", " + this.y + "\n");
		switch(this.rot) {
			case ZeroGraus:
				this.setBounds(this.x, this.y, QuadradoArma.getLargura() * 2, QuadradoArma.getAltura());
				break;
			case NoventaGraus:
				this.setBounds(this.x, this.y, QuadradoArma.getLargura(), QuadradoArma.getAltura() * 2);
				break;
			case CentoEOitentaGraus:
				this.setBounds(this.x, this.y, QuadradoArma.getLargura() * 2, QuadradoArma.getAltura());
				break;
		}
	}

}
