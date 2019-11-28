package armas;

import java.awt.Color;

public class Destroyer extends Arma {
	public Destroyer(int x, int y, int relX, int relY) {
		super(x, y, relX, relY);
		this.move(this.x, this.y, this.relX, this.relY);
		rotate(this.relX, this.relY);
		cor = new Color(217, 213, 4);
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
		Color prevCor = cor;
		if(!loqa.isEmpty())
			prevCor = loqa.get(0).getCor();
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
			case DuzentosESetentaGraus:
				this.changeRotatation();
				break;
		}	
		if(prevCor != null)
			setCor(prevCor);
		this.setBounds(this.x, this.y, this.largura, this.altura);
		repaint();
	}
	@Override
	public void unOpaque() {
		// TODO Auto-generated method stub
		setCor(cor);
	}

}
