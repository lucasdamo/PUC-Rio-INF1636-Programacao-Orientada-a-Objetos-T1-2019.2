package tratadores;

public class ErroAoIdentificarJogador extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ErroAoIdentificarJogador(String msg) {
		super("ErroAoIdentificarJogador:" + msg);
	}
	
}
