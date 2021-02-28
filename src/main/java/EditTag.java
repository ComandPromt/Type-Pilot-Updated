
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("all")

public class EditTag extends javax.swing.JFrame implements ActionListener, ChangeListener {

	private JTextField busqueda;

	private JTextField atajo;

	private JScrollPane scrollPane;

	private JButton btnNewButton_1;

	JComboBox comboBox;

	JTextArea texto = new JTextArea();
	private JButton btnNewButton_2;

	public void save() {

		try {

			if (atajo.getText().isEmpty() || texto.getText().isEmpty()) {
				Metodos.mensaje("Por favor, rellena el campo del atajo y del texto", 3);
			}

			else {

				String hotkey = Metodos.eliminarEspacios(atajo.getText());

				if (!Main.getAtajos().contains(hotkey)) {

					ArrayList<Objeto> arrayList1 = new ArrayList<Objeto>();

					ArrayList<Objeto> arrayList2 = null;

					arrayList2 = Metodos.leer(Main.getDirectorioActual() + Main.separador + "atajos.dat");

					if (arrayList2 != null) {

						for (int i = 0; i < arrayList2.size(); i++) {

							arrayList1.add(arrayList2.get(i));
						}

					}

					ObjectOutputStream escribiendoFichero = new ObjectOutputStream(
							new FileOutputStream(Main.directorioActual + Main.separador + "atajos.dat"));

					arrayList1.add(new Objeto(hotkey));

					escribiendoFichero.writeObject(arrayList1);

					escribiendoFichero.close();

					arrayList1.clear();

					if (arrayList2 != null) {

						arrayList2.clear();
					}

					arrayList2 = Metodos.leer(Main.getDirectorioActual() + Main.separador + "textos_atajos.dat");

					if (arrayList2 != null) {

						for (int i = 0; i < arrayList2.size(); i++) {

							arrayList1.add(arrayList2.get(i));
						}

					}

					escribiendoFichero = new ObjectOutputStream(
							new FileOutputStream(Main.directorioActual + Main.separador + "textos_atajos.dat"));

					arrayList1.add(new Objeto(texto.getText()));

					escribiendoFichero.writeObject(arrayList1);

					escribiendoFichero.close();

					Main.inicializar();

					ordenarCombo();

				}

				else {

					Metodos.mensaje("El atajo \"" + hotkey + "\" ya está creado", 1);

				}

				limpiar();

			}

		}

		catch (Exception e) {

		}

	}

	public void ordenarCombo() {

		comboBox.removeAllItems();

		for (int i = 0; i < Main.getComboBox().getItemCount(); i++) {

			comboBox.addItem(Main.getComboBox().getItemAt(i).toString());

		}
	}

	public EditTag() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditTag.class.getResource("/imagenes/ico.png")));

		setTitle("Type Pylot - Editar Atajo");

		setType(Type.UTILITY);

		initComponents();

		this.setVisible(true);

	}

	private void guardar() {

		String busqueda = Metodos.eliminarEspacios(atajo.getText());

		String accion = Metodos.eliminarEspacios(texto.getText());

		if (!Main.getAtajos().contains(busqueda)) {

			if (accion.isEmpty()) {
				Metodos.mensaje("\"" + busqueda + "\" no es un atajo", 1);
			}

			else {

				int resp = JOptionPane.showConfirmDialog(null, "¿Quieres crear el atajo \"" + busqueda + "\"?");

				if (resp == 0) {
					save();
				}

			}

		}

		else {

			editar(busqueda);

		}

	}

	private void editar(String busqueda) {

		int indice = Main.getAtajos().indexOf(busqueda);

		ArrayList<Objeto> arrayList1 = new ArrayList<Objeto>();

		if (indice >= 0) {

			try {

				Main.getCadenas().set(indice, texto.getText());

				for (int i = 0; i < Main.getAtajos().size(); i++) {

					arrayList1.add(new Objeto(Main.getAtajos().get(i)));
				}

				ObjectOutputStream escribiendoFichero = new ObjectOutputStream(
						new FileOutputStream(Main.directorioActual + Main.separador + "atajos.dat"));

				escribiendoFichero.writeObject(arrayList1);

				escribiendoFichero.close();

				arrayList1.clear();

				for (int i = 0; i < Main.getCadenas().size(); i++) {

					arrayList1.add(new Objeto(Main.getCadenas().get(i)));

				}

				escribiendoFichero = new ObjectOutputStream(
						new FileOutputStream(Main.directorioActual + Main.separador + "textos_atajos.dat"));

				arrayList1.add(new Objeto(texto.getText()));

				escribiendoFichero.writeObject(arrayList1);

				escribiendoFichero.close();

				Main.inicializar();

				ordenarCombo();

				limpiar();

			}

			catch (Exception e) {

			}

		}

	}

	private void limpiar() {

		atajo.setText("");

		texto.setText("");

	}

	private void initComponents() {

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Dialog", Font.BOLD, 16));

		comboBox.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {

				try {

					atajo.setText(comboBox.getSelectedItem().toString());

				}

				catch (Exception e) {

				}

			}

		});

		DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();

		listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);

		comboBox.setRenderer(listRenderer);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		setResizable(false);

		busqueda = new JTextField();
		busqueda.setFont(new Font("Dialog", Font.PLAIN, 16));
		busqueda.setHorizontalAlignment(SwingConstants.CENTER);

		busqueda.setColumns(10);

		atajo = new JTextField();
		atajo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ponerTexto(Metodos.eliminarEspacios(atajo.getText()));
				}
			}

			private void ponerTexto(String cadena) {

				if (!Main.getAtajos().isEmpty() && !Main.getCadenas().isEmpty()) {

					String[] exploded = cadena.split(",");

					int busqueda;

					String resultado = "";

					for (int i = 0; i < exploded.length; i++) {

						busqueda = Main.getAtajos().indexOf(exploded[i]);

						if (busqueda == -1) {
							resultado = exploded[i];
						}

						else {
							resultado = Main.getCadenas().get(busqueda);
						}

						texto.setText(texto.getText() + resultado);

					}

				}

				else {
					texto.setText(cadena);
				}

			}
		});
		atajo.setFont(new Font("Dialog", Font.PLAIN, 16));
		atajo.setHorizontalAlignment(SwingConstants.CENTER);

		atajo.setColumns(10);

		JButton btnNewButton = new JButton("");

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				guardar();

			}

		});

		btnNewButton.setIcon(new ImageIcon(EditTag.class.getResource("/imagenes/save.png")));

		scrollPane = new JScrollPane();

		JLabel lblNewLabel = new JLabel("");

		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		lblNewLabel.setIcon(new ImageIcon(EditTag.class.getResource("/imagenes/lupa.png")));

		btnNewButton_1 = new JButton("Buscar");

		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				String search = Metodos.eliminarEspacios(busqueda.getText());

				if (!search.isEmpty()) {

					busquedaAtajo(search);

				}

				else {

					if (comboBox.getItemCount() < Main.getAtajos().size()) {
						busquedaAtajo("");
					}

					else {

						Metodos.mensaje("Por favor, introduce un valor de búsqueda", 2);
					}

				}

			}

			public void busquedaAtajo(String search) {

				Pattern pattern = Pattern.compile(".*" + search + ".*");

				List<String> list = Main.getAtajos().stream().filter(e -> pattern.matcher(e).matches())
						.collect(Collectors.toList());

				if (!list.isEmpty()) {

					comboBox.removeAllItems();

					for (int i = 0; i < list.size(); i++) {
						comboBox.addItem(list.get(i));
					}

				}

			}

		});

		try {

			ArrayList<Objeto> etiquetas = Metodos.leer(Main.getDirectorioActual() + Main.getSeparador() + "atajos.dat");

			for (int i = 0; i < etiquetas.size(); i++) {

				comboBox.addItem(etiquetas.get(i).toString());

			}

			ordenarCombo();

		}

		catch (Exception e) {

		}

		btnNewButton_2 = new JButton("");

		btnNewButton_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				int resp = JOptionPane.showConfirmDialog(null, "¿Quieres limpiar el texto?");

				if (resp == 0) {
					texto.setText("");
				}

			}

		});

		btnNewButton_2.setIcon(new ImageIcon(EditTag.class.getResource("/imagenes/clean.png")));

		JButton btnNewButton_2_1 = new JButton("");

		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int resp = JOptionPane.showConfirmDialog(null, "¿Quieres limpiar el atajo y el texto?");

				if (resp == 0) {
					limpiar();
				}

			}

		});

		btnNewButton_2_1.setIcon(new ImageIcon(EditTag.class.getResource("/imagenes/delete.png")));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addContainerGap(22, Short.MAX_VALUE)
				.addComponent(btnNewButton_2_1, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE).addGap(49)
				.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE).addGap(113))
				.addGroup(layout.createSequentialGroup().addGap(12).addGroup(layout
						.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
						.addComponent(comboBox, 0, 344, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblNewLabel).addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(busqueda, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnNewButton_1).addGap(6)))
						.addContainerGap(17, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(atajo, 344, 344, 344)
						.addGap(17)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(lblNewLabel))
						.addGroup(layout.createSequentialGroup().addGap(20)
								.addGroup(layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(busqueda, GroupLayout.PREFERRED_SIZE, 31,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 38,
												GroupLayout.PREFERRED_SIZE))))
						.addGap(20)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(atajo, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addGap(12).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE).addGap(18)
						.addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(btnNewButton)
								.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 58,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_2_1, GroupLayout.PREFERRED_SIZE, 58,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		texto.setFont(new Font("Dialog", Font.PLAIN, 16));

		scrollPane.setViewportView(texto);
		getContentPane().setLayout(layout);
		setSize(new Dimension(373, 628));
		setLocationRelativeTo(null);
	}

	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
