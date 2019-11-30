package observador;

import java.io.Serializable;

public interface Observer {
	public void notify(Observable o);
}
