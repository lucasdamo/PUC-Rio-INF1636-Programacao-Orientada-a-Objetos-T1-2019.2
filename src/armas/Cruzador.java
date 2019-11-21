package armas;

import java.awt.Color;

public class Cruzador extends Arma {
	public Cruzador(int x, int y, int relX, int relY) {
		super(x, y, relX, relY);
		this.move(this.x, this.y, this.relX, this.relY);
		rotate(this.relX, this.relY);
		cor = Color.green;
		setCor(cor);
	}
	public static int getLarguraPadrao() {
		return QuadradoArma.getLargura() * 4;
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
				this.largura = QuadradoArma.getLargura() * 4;
				this.altura = QuadradoArma.getAltura();
				loqa.add(new QuadradoArma(0,0, this.relX, this.relY));
				loqa.add(new QuadradoArma(QuadradoArma.getLargura(), 0, this.relX + 1, this.relY));
				loqa.add(new QuadradoArma(QuadradoArma.getLargura() * 2, 0, this.relX + 2, this.relY));
				loqa.add(new QuadradoArma(QuadradoArma.getLargura() * 3, 0, this.relX + 3, this.relY));
				break;
			case NoventaGraus:
				this.largura = QuadradoArma.getLargura();
				this.altura = QuadradoArma.getAltura() * 4;
				loqa.add(new QuadradoArma(0,0, this.relX, this.relY));
				loqa.add(new QuadradoArma(0, QuadradoArma.getAltura(), this.relX, this.relY + 1));
				loqa.add(new QuadradoArma(0, QuadradoArma.getAltura() * 2, this.relX, this.relY + 2));
				loqa.add(new QuadradoArma(0, QuadradoArma.getAltura() * 3, this.relX, this.relY + 3));
				break;
			case CentoEOitentaGraus:
				this.largura = QuadradoArma.getLargura() * 4;
				this.altura = QuadradoArma.getAltura();
				loqa.add(new QuadradoArma(0,0, this.relX, this.relY));
				loqa.add(new QuadradoArma(QuadradoArma.getLargura(), 0, this.relX + 1, this.relY));
				loqa.add(new QuadradoArma(QuadradoArma.getLargura() * 2, 0, this.relX + 2, this.relY));
				loqa.add(new QuadradoArma(QuadradoArma.getLargura() * 3, 0, this.relX + 3, this.relY));
				break;
		}	
		this.setBounds(this.x, this.y, this.largura, this.altura);
		repaint();
	}
	@Override
	public void unOpaque() {
		// TODO Auto-generated method stub
		setCor(cor);
	}

}