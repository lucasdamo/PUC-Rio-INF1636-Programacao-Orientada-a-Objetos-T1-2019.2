package armas;

import java.awt.Color;

public class Hidroaviao extends Arma {
	public Hidroaviao(int x, int y, int relX, int relY) {
		super(x, y, relX, relY);
		this.move(this.x, this.y, this.relX, this.relY);
		rotate(this.relX, this.relY);
		cor = new Color(10, 79, 1);
		setCor(cor);
	}
	public static int getLarguraPadrao() {
		return QuadradoArma.getLargura() * 	3;
	}
	public static int getAlturaPadrao() {
		return QuadradoArma.getAltura() * 2;
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
				this.largura = QuadradoArma.getLargura() * 3;
				this.altura = QuadradoArma.getAltura() * 2;
				loqa.add(new QuadradoArma(QuadradoArma.getLargura(), 0, this.relX + 1, this.relY));
				loqa.add(new QuadradoArma(0, QuadradoArma.getAltura(), this.relX, this.relY + 1));
				loqa.add(new QuadradoArma(QuadradoArma.getLargura() * 2, QuadradoArma.getAltura(), this.relX + 2, this.relY + 1));
				break;
			case NoventaGraus:
				this.largura = QuadradoArma.getLargura() * 2;
				this.altura = QuadradoArma.getAltura() * 3;
				loqa.add(new QuadradoArma(QuadradoArma.getLargura(),QuadradoArma.getAltura(),this.relX + 1, this.relY + 1));
				loqa.add(new QuadradoArma(0, 0, this.relX, this.relY));
				loqa.add(new QuadradoArma(0, QuadradoArma.getAltura()*2, this.relX, this.relY+2));
				break;
			case CentoEOitentaGraus:
				this.largura = QuadradoArma.getLargura() * 3;
				this.altura = QuadradoArma.getAltura() * 2;
				loqa.add(new QuadradoArma(0,0, this.relX, this.relY));
				loqa.add(new QuadradoArma(QuadradoArma.getLargura()*2, 0, this.relX + 2, this.relY));
				loqa.add(new QuadradoArma(QuadradoArma.getLargura()*1, QuadradoArma.getAltura(), this.relX + 1, this.relY + 1));	
				break;
			case DuzentosESetentaGraus:
				this.largura = QuadradoArma.getLargura() * 2;
				this.altura = QuadradoArma.getAltura() * 3;
				loqa.add(new QuadradoArma(0, QuadradoArma.getAltura(), this.relX, this.relY + 1));
				loqa.add(new QuadradoArma(QuadradoArma.getLargura(), 0, this.relX + 1, this.relY));
				loqa.add(new QuadradoArma(QuadradoArma.getLargura(), QuadradoArma.getAltura() * 2, this.relX + 1, this.relY + 2));	
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
