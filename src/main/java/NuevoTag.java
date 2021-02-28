
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("all")

public class NuevoTag extends javax.swing.JFrame implements ActionListener, ChangeListener {

	private JTextField atajo;

	JTextArea texto;

	public NuevoTag() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(NuevoTag.class.getResource("/imagenes/ico.png")));

		setAlwaysOnTop(true);

		setTitle("Nuevo Tag");

		setType(Type.UTILITY);

		initComponents();

		this.setVisible(true);

	}

	private void ponerMensajeAlerta(String mensaje, int estado) {

		setAlwaysOnTop(false);

		Metodos.mensaje(mensaje, estado);

		setAlwaysOnTop(true);

	}

	public void guardar() {

		try {

			if (atajo.getText().isEmpty() || texto.getText().isEmpty()) {
				ponerMensajeAlerta("Por favor, rellena el campo del atajo y del texto", 3);
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

				}

				else {

					ponerMensajeAlerta("El atajo \"" + hotkey + "\" ya está creado", 1);

				}

				atajo.setText("");

				texto.setText("");

			}
		}

		catch (Exception e) {

		}

	}

	private void initComponents() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		setResizable(false);

		JButton btnNewButton = new JButton("");

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				guardar();

			}

		});

		btnNewButton.setIcon(new ImageIcon(NuevoTag.class.getResource("/imagenes/save.png")));

		texto = new JTextArea();
		texto.setFont(new Font("Dialog", Font.PLAIN, 16));

		texto.addKeyListener(new KeyAdapter() {

			@Override

			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					guardar();

				}
			}

		});

		atajo = new JTextField();

		atajo.setFont(new Font("Dialog", Font.PLAIN, 16));

		atajo.setHorizontalAlignment(SwingConstants.CENTER);

		atajo.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();

		JLabel lblNewLabel = new JLabel("Nombre del atajo");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblTextoDelAtajo = new JLabel("Texto del atajo");
		lblTextoDelAtajo.setHorizontalAlignment(SwingConstants.CENTER);
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(163)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(170, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup().addGap(37)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTextoDelAtajo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 318,
										Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE).addComponent(
										atajo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
						.addGap(34)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(lblNewLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(atajo, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE).addComponent(lblTextoDelAtajo)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnNewButton).addGap(18)));

		scrollPane.setViewportView(texto);
		getContentPane().setLayout(layout);
		setSize(new Dimension(389, 431));
		setLocationRelativeTo(null);
	}

	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
