package observador;

public interface AvisaCliqueCampoBatalha {
	public void addCliqueListener(EscutaCliqueCampoBatalha o);
	public void removeCliqueListener(EscutaCliqueCampoBatalha o);
	public void avisaCliqueObservadores(int x, int y, int jogador);
}
