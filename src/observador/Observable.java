package observador;

public interface Observable {
	public void addObserver(Observer o);
	public void removeObserver(Observer o);
	public Object get();
	public int tipo();  // Retorna um inteiro que representa a classe do objeto
}
