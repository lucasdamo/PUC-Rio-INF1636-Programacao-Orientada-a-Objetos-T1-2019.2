package regras;

import java.io.Serializable;

public class Jogador implements Serializable {
	private static final long serialVersionUID = -1045071845628557519L;
	String nome;
	int id;
	public Jogador(String nome, int id) {
		this.nome = nome;
		this.id = id;
	}
	public int getId() {
		return this.id;
	}
	public String getNome() {
		return this.nome;
	}
	
}
