package observador;
import regras.Jogador;

public interface EscutaCliqueCampoBatalha {
	public void recebeClique(int x, int y, Jogador jogador);
}
