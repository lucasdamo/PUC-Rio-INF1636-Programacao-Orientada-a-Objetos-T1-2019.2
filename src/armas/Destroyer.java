package armas;

import java.awt.Color;

public class Destroyer extends Arma {
	public Destroyer(int x, int y, int relX, int relY) {
		super(x, y, relX, relY);
		this.move(this.x, this.y, this.relX, this.relY);
		rotate(this.relX, this.relY);
		cor = Color.yellow;
		setCor(cor);
	}
	public static int getLarguraPadrao() {
		return QuadradoArma.getLargura() * 2;
	}
	public static int getAlturaPadrao() {
		return QuadradoArma.getAltura();
	}

	
	@Override
	public void rotate(int relX, int relY) {
		// Submarino so tem 1 quadrado, rotacionar nao importa
		loqa.removeAll(loqa);
		switch(this.rot) {
			case ZeroGraus:
				this.largura = QuadradoArma.getLargura() * 2;
				this.altura = QuadradoArma.getAltura();
				loqa.add(new QuadradoArma(0,0, this.relX, this.relY));
				loqa.add(new QuadradoArma(QuadradoArma.getLargura(), 0, this.relX + 1, this.relY));
				break;
			case NoventaGraus:
				this.largura = QuadradoArma.getLargura();
				this.altura = QuadradoArma.getAltura() * 2;
				loqa.add(new QuadradoArma(0,0, this.relX, this.relY));
				loqa.add(new QuadradoArma(0, QuadradoArma.getAltura(), this.relX, this.relY + 1));
				break;
			case CentoEOitentaGraus:
				this.largura = QuadradoArma.getLargura() * 2;
				this.altura = QuadradoArma.getAltura();
				loqa.add(new QuadradoArma(0,0, this.relX, this.relY));
				loqa.add(new QuadradoArma(QuadradoArma.getLargura(), 0, this.relX + 1, this.relY));	
				break;
		}	
		repaint();
	}
	@Override
	public void unOpaque() {
		// TODO Auto-generated method stub
		setCor(cor);
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
		rotate(relX, relY);
	}

}
