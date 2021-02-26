
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("all")

public class EditTag extends javax.swing.JFrame implements ActionListener, ChangeListener {
	private JTextField textField;
	private JTextField textField_1;
	private JScrollPane scrollPane;
	private JButton btnNewButton_1;

	public EditTag() {

		setTitle("Type Pylot - Editar Atajo");

		setType(Type.UTILITY);

		initComponents();

		this.setVisible(true);
	}

	private void initComponents() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		textField = new JTextField();
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("New button");

		scrollPane = new JScrollPane();

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(EditTag.class.getResource("/imagenes/lupa.png")));

		btnNewButton_1 = new JButton("New button");

		JComboBox comboBox = new JComboBox();
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(lblNewLabel).addGap(18)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE).addGap(18)
						.addComponent(btnNewButton_1).addGap(30))
				.addGroup(Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(59, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(textField_1, Alignment.TRAILING)
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
										.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(layout.createSequentialGroup().addComponent(btnNewButton).addGap(96)))
						.addGap(41)));
		layout.setVerticalGroup(
				layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(14)
								.addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(lblNewLabel)
										.addGroup(layout.createParallelGroup(Alignment.BASELINE)
												.addComponent(textField, GroupLayout.PREFERRED_SIZE, 31,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnNewButton_1)))
								.addGap(30)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addGap(30)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(btnNewButton).addGap(49)));

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		getContentPane().setLayout(layout);
		setSize(new Dimension(398, 534));
		setLocationRelativeTo(null);
	}

	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
