
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("all")

public class Main extends javax.swing.JFrame implements ActionListener, ChangeListener {

	static String os = System.getProperty("os.name");

	static String separador = Metodos.saberSeparador(os);

	private JTextField textField;

	public static LinkedList<String> atajos = new LinkedList<String>();

	public static LinkedList<String> cadenas = new LinkedList<String>();

	JTextArea salida = new JTextArea();

	static String directorioActual;

	static ArrayList<Objeto> etiquetas;

	static ArrayList<Objeto> textos;

	static JComboBox comboBox = new JComboBox();

	private static NuevoTag nt;

	private void ponerTexto(String texto) {

		if (!cadenas.isEmpty() && !atajos.isEmpty()) {

			String[] exploded = texto.split(",");

			int busqueda;

			String resultado = "";

			for (int i = 0; i < exploded.length; i++) {

				busqueda = atajos.indexOf(exploded[i]);

				if (busqueda == -1) {
					resultado = exploded[i];
				}

				else {
					resultado = cadenas.get(busqueda);
				}

				salida.setText(salida.getText() + resultado);

			}

		} else {
			salida.setText(texto);
		}

	}

	public static String getOs() {
		return os;
	}

	public static void setOs(String os) {
		Main.os = os;
	}

	public static String getSeparador() {
		return separador;
	}

	public static void setSeparador(String separador) {
		Main.separador = separador;
	}

	public static LinkedList<String> getAtajos() {
		return atajos;
	}

	public static void setAtajos(LinkedList<String> atajos) {
		Main.atajos = atajos;
	}

	public static LinkedList<String> getCadenas() {
		return cadenas;
	}

	public static void setCadenas(LinkedList<String> cadenas) {
		Main.cadenas = cadenas;
	}

	public static String getDirectorioActual() {
		return directorioActual;
	}

	public static void setDirectorioActual(String directorioActual) {
		Main.directorioActual = directorioActual;
	}

	public Main() throws IOException {

		setTitle("Type Pilot Updated");

		setType(Type.UTILITY);

		initComponents();

		this.setVisible(true);

	}

	private void initComponents() throws IOException {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		setResizable(false);

		textField = new JTextField();

		textField.setFont(new Font("Dialog", Font.PLAIN, 16));

		textField.addKeyListener(new KeyAdapter() {

			@Override

			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ponerTexto(Metodos.eliminarEspacios(textField.getText()));
				}

			}

		});

		textField.setHorizontalAlignment(SwingConstants.CENTER);

		textField.setColumns(10);

		comboBox.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {

				if (comboBox.getSelectedItem() != null) {
					String hotkeySeleccionado = comboBox.getSelectedItem().toString();
					textField.setText(hotkeySeleccionado);
				}
			}
		});

		comboBox.setFont(new Font("Dialog", Font.BOLD, 16));

		directorioActual = new File(".").getCanonicalPath();

		inicializar();

		JButton btnNewButton = new JButton("");

		btnNewButton.setIcon(new ImageIcon(Main.class.getResource("/imagenes/edit_1.png")));

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Metodos.copy(Metodos.eliminarEspacios(salida.getText()));
			}
		});

		btnNewButton_1.setIcon(new ImageIcon(Main.class.getResource("/imagenes/copy.png")));

		JButton btnNewButton_1_1 = new JButton("");

		btnNewButton_1_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				int resp = JOptionPane.showConfirmDialog(null, "¿Estás seguro de limpiar el texto?");

				if (resp == 0) {
					salida.setText("");
				}

			}

		});

		btnNewButton_1_1.setIcon(new ImageIcon(Main.class.getResource("/imagenes/clean.png")));

		JScrollPane scrollPane = new JScrollPane();

		JButton btnAdd = new JButton("");

		btnAdd.setIcon(new ImageIcon(Main.class.getResource("/imagenes/insert.png")));

		btnAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				nt = new NuevoTag();

				nt.setVisible(true);

			}

		});

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon(Main.class.getResource("/imagenes/delete.png")));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup().addGap(23)
						.addGroup(layout
								.createParallelGroup(Alignment.TRAILING)
								.addGroup(
										layout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(textField, Alignment.LEADING).addComponent(scrollPane,
														Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 388,
														Short.MAX_VALUE))
								.addGroup(layout.createSequentialGroup().addComponent(comboBox, 0, 251, Short.MAX_VALUE)
										.addGap(18)
										.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 49,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 52,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
								.addGap(28)
								.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnNewButton_1_1, 0, 0, Short.MAX_VALUE)
										.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 49, Short.MAX_VALUE)))
								.addGroup(layout.createSequentialGroup().addGap(18).addComponent(btnNewButton_2,
										GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)))
						.addGap(21)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(34)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(btnNewButton_2, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
										.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 48,
												Short.MAX_VALUE)))
						.addGap(28).addComponent(textField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(18).addComponent(scrollPane,
										GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addGap(50)
										.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 63,
												GroupLayout.PREFERRED_SIZE)
										.addGap(34).addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 47,
												GroupLayout.PREFERRED_SIZE)))
						.addGap(29)));
		salida.setFont(new Font("Dialog", Font.PLAIN, 16));

		scrollPane.setViewportView(salida);
		getContentPane().setLayout(layout);
		setSize(new Dimension(509, 429));
		setLocationRelativeTo(null);
	}

	public static void inicializar() {

		try {

			comboBox.removeAllItems();

			etiquetas = Metodos.leer(directorioActual + Main.separador + "atajos.dat");

			String texto = "";

			atajos.clear();

			cadenas.clear();

			for (int i = 0; i < etiquetas.size(); i++) {

				texto = etiquetas.get(i).toString();

				atajos.add(texto);

			}

			textos = Metodos.leer(directorioActual + Main.separador + "textos_atajos.dat");

			for (int i = 0; i < textos.size(); i++) {

				cadenas.add(textos.get(i).toString());

			}

			LinkedList<String> listaAtajOrdenada = new LinkedList<String>();

			LinkedList<String> listaCadenaOrdenada = new LinkedList<String>();

			for (int i = 0; i < atajos.size(); i++) {
				listaAtajOrdenada.add(atajos.get(i));

			}

			Collections.sort(listaAtajOrdenada);

			for (int i = 0; i < listaAtajOrdenada.size(); i++) {

				listaCadenaOrdenada.add(cadenas.get(atajos.indexOf(listaAtajOrdenada.get(i))));

				comboBox.addItem(listaAtajOrdenada.get(i));
			}

			DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();

			listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);

			comboBox.setRenderer(listRenderer);

			atajos.clear();

			cadenas.clear();

			atajos = listaAtajOrdenada;

			cadenas = listaCadenaOrdenada;

		}

		catch (Exception e1) {

			if (!(nt instanceof NuevoTag)) {

				nt = new NuevoTag();

				nt.setVisible(true);

			}

		}

	}

	public static void main(String[] args) throws Exception {

		new Main();

	}

	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
