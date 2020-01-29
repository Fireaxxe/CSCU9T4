import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.table.TableRowSorter;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI extends JFrame implements ActionListener {
	private Object[][] data;
	private Object[] row = new Object[6];
	private String[] columnNames = { "REFERENCE", "ADDRESS", "CATEGORY", "LISTED", "COUNCIL", "PARISH" };
	private DefaultTableModel tableModel;
	private JTable table;
	private ScotlandHistoricList myList;
	static JFileChooser fileopen;
	private String path;
	private JTextField jtfFilter;
	public JTextField jtfReference;
	public JTextField jtfAddress;
	public JTextField jtfCategory;
	public JTextField jtfListed;
	public JTextField jtfCouncil;
	public JTextField jtfParish;

	public GUI(String title) {
		super(title);
		setBounds(800, 800, 1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// let the user choose the file
		JFileChooser fileopen = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("Text/CSV file", "txt", "csv");
		fileopen.addChoosableFileFilter(filter);

		int ret = fileopen.showDialog(null, "Choose file");
		if (ret == JFileChooser.APPROVE_OPTION) {
			fileopen.getSelectedFile().getPath(); // gets the path from the chosen file
		}
		else{
			System.exit(0);
		}

		this.setTitle(fileopen.getSelectedFile().getPath());

		final JLabel lblResult = new JLabel("Input File: ");
		lblResult.setText("Input File: " + fileopen.getSelectedFile().toString()); // set the label with the file path

		jtfFilter = new JTextField("", 20); // new JTextField for the searching area
		jtfFilter.setText("Searching area: ");
		jtfFilter.setForeground(Color.LIGHT_GRAY); // set the background for searching to ghost text

		jtfFilter.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) { // focus event when the user click on the searching box
				jtfFilter.setText(""); // it will set the text from "Searching area:" to ""
				jtfFilter.setForeground(Color.BLACK);
			}

			@Override
			public void focusLost(FocusEvent e) {

			} // when focus lost we can enter the ghost "Searching area: " again but this time
				// it will search for it
		}); // so we can't do anything here

		myList = new ScotlandHistoricList();
		myList.readFromCSV(fileopen); // read the file
		data = myList.convert2Data();
		tableModel = new DefaultTableModel(data, columnNames);
		table = new JTable(tableModel);
		table.setAutoCreateRowSorter(true);
		JScrollPane scrollPane = new JScrollPane(table); // add a scroll panel to the table
		scrollPane.setPreferredSize(new Dimension(800, 600));
		JPanel panel = new JPanel();

		JButton saveButton = new JButton("Save as CSV");
		JButton addInputs = new JButton("Add Inputs");

		JLabel lblAdd = new JLabel("Add :");
		JLabel addReference = new JLabel("Reference:");
		JLabel addAddress = new JLabel("Address:");
		JLabel addCategory = new JLabel("Category:");
		JLabel addListed = new JLabel("Listed:");
		JLabel addCouncil = new JLabel("Council:");
		JLabel addParish = new JLabel("Parish:");

		jtfReference = new JTextField();
		jtfAddress = new JTextField();
		jtfCategory = new JTextField();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		JFormattedTextField jtfListed = new JFormattedTextField(df);
		jtfCouncil = new JTextField();
		jtfParish = new JTextField();

		saveButton.addActionListener(this);
		panel.add(scrollPane);

		lblAdd.setBounds(50, 700, 50, 25);
		getContentPane().add(lblAdd);

		addReference.setBounds(90, 675, 125, 25);
		getContentPane().add(addReference);
		addAddress.setBounds(190, 675, 125, 25);
		getContentPane().add(addAddress);
		addCategory.setBounds(290, 675, 125, 25);
		getContentPane().add(addCategory);
		addListed.setBounds(390, 675, 125, 25);
		getContentPane().add(addListed);
		addCouncil.setBounds(490, 675, 125, 25);
		getContentPane().add(addCouncil);
		addParish.setBounds(590, 675, 125, 25);
		getContentPane().add(addParish);

		jtfReference.setBounds(90, 700, 100, 25);
		getContentPane().add(jtfReference);
		jtfReference.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) { // use keylistener to specify what characters should the user input
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
					JOptionPane.showMessageDialog(null, "Please enter a number!");
				}
			}
		});

		jtfAddress.setBounds(190, 700, 100, 25);
		getContentPane().add(jtfAddress);
		jtfAddress.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c == ','))) {
					getToolkit().beep();
					e.consume();
					JOptionPane.showMessageDialog(null, "Please don't use: ' , '");
				}
			}
		});

		jtfCategory.setBounds(290, 700, 100, 25);
		getContentPane().add(jtfCategory);
		jtfCategory.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if (jtfCategory.getText().length() >= 1) {
					e.consume();
					JOptionPane.showMessageDialog(null, "Only one character is available");
				}
				if (Character.isDigit(ch) || (Character.isLowerCase(ch))) {
					getToolkit().beep();
					e.consume();
					JOptionPane.showMessageDialog(null, "Please enter a capital character");
				}

			}
		});

		jtfListed.setBounds(390, 700, 100, 25);
		getContentPane().add(jtfListed);
		jtfListed.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if (!((ch >= '0') && (ch <= '9') || (ch == KeyEvent.VK_BACK_SPACE) || (ch == KeyEvent.VK_DELETE)
						|| (ch == KeyEvent.VK_SLASH))) {
					JOptionPane.showMessageDialog(null, "Please enter valid date (dd/MM/yyyy)");
					e.consume();
				}
				if (jtfListed.getText().length() >= 10) {
					e.consume();
					JOptionPane.showMessageDialog(null, "Please enter valid date (dd/MM/yyyy)");
				}
			}
		});

		jtfCouncil.setBounds(490, 700, 100, 25);
		getContentPane().add(jtfCouncil);
		jtfCouncil.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if (((ch == ','))) {
					getToolkit().beep();
					e.consume();
					JOptionPane.showMessageDialog(null, "Please don't use: ' , '");
				}
			}
		});

		jtfParish.setBounds(590, 700, 100, 25);
		getContentPane().add(jtfParish);
		jtfParish.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if (((ch == ','))) {
					getToolkit().beep();
					e.consume();
					JOptionPane.showMessageDialog(null, "Please don't use: ' , '");
				}
			}
		});

		// button add row
		addInputs.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {

				// checks if all the boxes are filled
				if (jtfReference.getText().equals("") || (jtfAddress.getText().equals(""))
						|| (jtfCategory.getText().equals("")) || (jtfListed.getText().equals(""))
						|| (jtfCouncil.getText().equals("")) || (jtfParish.getText().equals(""))) {
					addInputs.disable(); // if any of the boxes are empty the addInputs button is disable
					JOptionPane.showMessageDialog(null, "Please fill the boxes");
				} else {
					addInputs.enable(); // if the boxes are filed the addInputs button is enable
					// create an array of objects to set the row data
					row[0] = jtfReference.getText();
					row[1] = jtfAddress.getText();
					row[2] = jtfCategory.getText();
					row[3] = jtfListed.getText();
					row[4] = jtfCouncil.getText();
					row[5] = jtfParish.getText();

					checkTable(table, jtfReference, jtfAddress);

					// Reset Text Field
					jtfReference.setText("");
					jtfAddress.setText("");
					jtfCategory.setText("");
					jtfListed.setText("");
					jtfCouncil.setText("");
					jtfParish.setText("");

				}

			}
		});

		addInputs.setBounds(750, 700, 125, 25);
		getContentPane().add(addInputs);
		saveButton.setBounds(750, 625, 125, 25);
		getContentPane().add(saveButton);
		lblResult.setBounds(250, 625, 500, 25);
		getContentPane().add(lblResult);
		jtfFilter.setBounds(90, 625, 150, 25);
		getContentPane().add(jtfFilter);

		add(panel, BorderLayout.CENTER);
		path = fileopen.getSelectedFile().toString(); // take the path of the file and parse it into a string

		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(rowSorter);

		jtfFilter.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				String text = jtfFilter.getText();

				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				String text = jtfFilter.getText();

				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				throw new UnsupportedOperationException("Not supported yet.");
			}

		});
	}

	public static void main(String[] args) {
		GUI myApp = new GUI("");
		myApp.setVisible(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		myApp.setLocation(dim.width / 2 - myApp.getSize().width / 2, dim.height / 2 - myApp.getSize().height / 2);
		// it takes the whole program and set it to the
		// exact middle of the screen in ANY resolution so
		// it can appear to the middle of the screen to any
		// computer
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		exportToCSV(table, path);
	}

	public void checkTable(JTable table, JTextField jtfReference, JTextField jtfAddress) {
		String refCheck = "";
		String addressCheck = "";
		boolean exists = false;
		for (int i = 0; i < table.getRowCount(); i++) {
			refCheck = table.getValueAt(i, 0).toString().trim();
			addressCheck = table.getValueAt(i, 1).toString().trim();

			if (jtfReference.getText().equals(refCheck) || (jtfAddress.getText().equals(addressCheck))) {
				exists = true;
				break;
			}
		}

		if (!exists) {
			// add row to the model
			tableModel.addRow(row);
			JOptionPane.showMessageDialog(null, "Data Successfully Added");
		} else {
			JOptionPane.showMessageDialog(null,
					"Data Already Exist.\nPlease enter different Reference number and Address.");
		}
	}

	public static boolean exportToCSV(JTable table, String path) {
		// this method is reading the whole table (included the new rows that the user
		// added) and export it to the same location, same file type, same name and
		// replace the current one
		try {

			TableModel model = table.getModel();
			FileWriter csv = new FileWriter(new File(path));

			for (int i = 0; i < model.getColumnCount(); i++) {
				csv.write(model.getColumnName(i) + ",");
			}

			csv.write("\n");

			for (int i = 0; i < model.getRowCount(); i++) {
				for (int j = 0; j < model.getColumnCount(); j++) {
					csv.write(model.getValueAt(i, j).toString() + ",");
				}
				csv.write("\n");
			}

			csv.close();
			JOptionPane.showMessageDialog(null, "Data Saved Successfully to the path: \n" + path);
			return true;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
