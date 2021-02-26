import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public abstract class Metodos {

	public static void mensaje(String mensaje, int titulo) {

		String tituloSuperior = "";

		int tipo = 0;

		switch (titulo) {

		case 1:

			tipo = JOptionPane.ERROR_MESSAGE;

			tituloSuperior = "Error";

			break;

		case 2:

			tipo = JOptionPane.INFORMATION_MESSAGE;

			tituloSuperior = "Informacion";

			break;

		case 3:

			tipo = JOptionPane.WARNING_MESSAGE;

			tituloSuperior = "Advertencia";

			break;

		default:
			break;

		}

		JLabel alerta = new JLabel(mensaje);

		alerta.setFont(new Font("Arial", Font.BOLD, 18));

		JOptionPane.showMessageDialog(null, alerta, tituloSuperior, tipo);

	}

	public static ArrayList<Objeto> leer(String file)
			throws IOException, FileNotFoundException, ClassNotFoundException {

		ArrayList<Objeto> arrayList2 = null;

		File archivo = new File(file);

		if (archivo.exists()) {

			ObjectInputStream leyendoFichero = new ObjectInputStream(new FileInputStream(file));

			arrayList2 = (ArrayList<Objeto>) leyendoFichero.readObject();

			leyendoFichero.close();

		}

		return arrayList2;
	}

	public static String saberSeparador(String os) {

		if (os.equals("Linux")) {
			return "/";
		}

		else {
			return "\\";
		}

	}

	public static String eliminarEspacios(String cadena) {

		cadena = cadena.trim();

		cadena = cadena.replace("  ", " ");

		cadena = cadena.trim();

		return cadena;
	}

	private static Clipboard getSystemClipboard() {

		Toolkit defaultToolkit = Toolkit.getDefaultToolkit();

		Clipboard systemClipboard = defaultToolkit.getSystemClipboard();

		return systemClipboard;
	}

	public static void copy(String text) {

		Clipboard clipboard = getSystemClipboard();

		clipboard.setContents(new StringSelection(text), null);
	}
}
