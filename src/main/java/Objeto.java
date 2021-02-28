
import java.io.Serializable;

public class Objeto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String _dato;

	public Objeto(String dato) {
		this._dato = dato;
	}

	public String toString() {
		return this._dato;
	}

}
