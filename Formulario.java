package Actividad_6;
import javax.swing.*;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.NumberFormatException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.awt.event.ActionEvent;

public class Formulario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Formulario frame = new Formulario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Formulario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NOMBRE");
		lblNewLabel.setBounds(45, 32, 86, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("NUMERO");
		lblNewLabel_1.setBounds(45, 99, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		txtName = new JTextField();
		txtName.setBounds(159, 37, 170, 19);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtNumber = new JTextField();
		txtNumber.setBounds(159, 96, 170, 19);
		contentPane.add(txtNumber);
		txtNumber.setColumns(10);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {

					// Get the name of the contact to be updated
					// from the Command line argument
					
					String newName = String.valueOf(txtName.getText());

					// Get the number to be updated
					// from the Command line argument
					long newNumber = Long.parseLong(txtNumber.getText());

					String nameNumberString;
					String name;
					long number;
					int index;

					// Using file pointer creating the file.
					File file = new File("C:\\Users\\ASUS\\Desktop\\POO-UNAL\\friendsContact.txt");

					if (!file.exists()) {

						// Create a new file if not exists.
						file.createNewFile();
					}

					// Opening file in reading and write mode.

					RandomAccessFile raf
						= new RandomAccessFile(file, "rw");
					boolean found = false;

					// Checking whether the name
					// of contact already exists.
					// getFilePointer() give the current offset
					// value from start of the file.
					while (raf.getFilePointer() < raf.length()) {

						// reading line from the file.
						nameNumberString = raf.readLine();

						// splitting the string to get name and
						// number
						String[] lineSplit= nameNumberString.split("!");

						// separating name and number.
						name = lineSplit[0];
						number = Long.parseLong(lineSplit[1]);

						// if condition to find existence of record.
						if (name == newName|| number == newNumber) {
			                    found = true;
			                    System.out.println("the record exists");
			                    break;
			                }
					}

					if (found == false) {

						// Enter the if block when a record
						// is not already present in the file.
						nameNumberString
							= newName + "!"
							+ String.valueOf(newNumber);

						// writeBytes function to write a string
						// as a sequence of bytes.
						raf.writeBytes(nameNumberString);

						// To insert the next record in new line.
						raf.writeBytes(System.lineSeparator());

						// Print the message
						JOptionPane.showMessageDialog(null, " The friend "+newName+" added. ");
						System.out.println(" The friend "+newName+" added. ");

						// Closing the resources.
						raf.close();
					}
					// The contact to be updated
					// could not be found
					else {

						// Closing the resources.
						raf.close();

						// Print the message
						System.out.println(" Input name"
										+ " does not exists. ");
					}
				}

				catch (IOException ioe) {

					System.out.println(ioe);
				}
				catch (NumberFormatException nef) {

					System.out.println(nef);
				}
			}
		});
		btnCreate.setBounds(24, 184, 85, 21);
		contentPane.add(btnCreate);
		
		JButton btnRead = new JButton("Read");
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String nameNumberString;
					String name;
					String newName = String.valueOf(txtName.getText());

					long number;
					int index;

					// Using file pointer creating the file.
					File file = new File("C:\\Users\\ASUS\\Desktop\\POO-UNAL\\friendsContact.txt");

					if (!file.exists()) {

						// Create a new file if not exists.
						file.createNewFile();
					}

					// Opening file in reading and write mode.

					RandomAccessFile raf
						= new RandomAccessFile(file, "rw");
					boolean found = false;

					// Traversing the file
					// getFilePointer() give the current offset
					// value from start of the file.
					while (raf.getFilePointer() < raf.length()) {

						// reading line from the file.
						nameNumberString = raf.readLine();

						// splitting the string to get name and
						// number
						String[] lineSplit
							= nameNumberString.split("!");

						// separating name and number.
						name = lineSplit[0];
						number = Long.parseLong(lineSplit[1]);
						if (name.equals(newName)) {
							txtName.setText(String.valueOf(name));
							txtNumber.setText(String.valueOf(number));
							found =true;
						}
						// Print the contact data
						
						
					}
				}

					catch (IOException ioe)
					{

						System.out.println(ioe);
					}
					catch (NumberFormatException nef)
					{

						System.out.println(nef);
					}

				 
			}
		});
		btnRead.setBounds(129, 184, 85, 21);
		contentPane.add(btnRead);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					// Get the name of the contact to be updated
					// from the Command line argument
					String newName = String.valueOf(txtName.getText());


					// Get the number to be updated
					// from the Command line argument
					long newNumber = Long.parseLong(txtNumber.getText());

					String nameNumberString;
					String name;
					long number;
					int index;

					// Using file pointer creating the file.
					File file = new File("C:\\Users\\ASUS\\Desktop\\POO-UNAL\\friendsContact.txt");

					if (!file.exists()) {

						// Create a new file if not exists.
						file.createNewFile();
					}

					// Opening file in reading and write mode.
					RandomAccessFile raf
						= new RandomAccessFile(file, "rw");
					boolean found = false;

					// Checking whether the name
					// of contact already exists.
					// getFilePointer() give the current offset
					// value from start of the file.
					while (raf.getFilePointer() < raf.length()) {

						// reading line from the file.
						nameNumberString = raf.readLine();

						// splitting the string to get name and
						// number
						String[] lineSplit
							= nameNumberString.split("!");

						// separating name and number.
						name = lineSplit[0];
						number = Long.parseLong(lineSplit[1]);

						// if condition to find existence of record.
						if (name.equals(newName)==true )
							 {
							found = true;
							break;
						}
					}

					// Update the contact if record exists.
					if (found == true) {

						// Creating a temporary file
						// with file pointer as tmpFile.
						File tmpFile = new File("C:\\Users\\ASUS\\Desktop\\POO-UNAL\\friendsContact.txt");

						// Opening this temporary file
						// in ReadWrite Mode
						RandomAccessFile tmpraf= new RandomAccessFile(tmpFile, "rw");

						// Set file pointer to start
						raf.seek(0);

						// Traversing the friendsContact.txt file
						while (raf.getFilePointer()
							< raf.length()) {

							// Reading the contact from the file
							nameNumberString = raf.readLine();

							index = nameNumberString.indexOf('!');
							name = nameNumberString.substring(
								0, index);

							// Check if the fetched contact
							// is the one to be updated
							if (name.equals(newName)) {

								// Update the number of this contact
								nameNumberString
									= name + "!"
									+ String.valueOf(newNumber);
							}

							// Add this contact in the temporary
							// file
							tmpraf.writeBytes(nameNumberString);

							// Add the line separator in the
							// temporary file
							tmpraf.writeBytes(
								System.lineSeparator());
						}

						// The contact has been updated now
						// So copy the updated content from
						// the temporary file to original file.

						// Set both files pointers to start
						raf.seek(0);
						tmpraf.seek(0);

						// Copy the contents from
						// the temporary file to original file.
						while (tmpraf.getFilePointer()
							< tmpraf.length()) {
							raf.writeBytes(tmpraf.readLine());
							raf.writeBytes(System.lineSeparator());
						}

						// Set the length of the original file
						// to that of temporary.
						raf.setLength(tmpraf.length());

						// Closing the resources.
						tmpraf.close();
						raf.close();

						// Deleting the temporary file
						tmpFile.delete();

						System.out.println(" Friend updated. ");
					}

					// The contact to be updated
					// could not be found
					else {

						// Closing the resources.
						raf.close();

						// Print the message
						JOptionPane.showMessageDialog(null, " The friend's number of "+newName+" was updated. ");
						
						System.out.println(" Input name"
										+ " does not exists. ");
					}
					
				}

				catch (IOException ioe) {
					System.out.println(ioe);
				}

				catch (NumberFormatException nef) {
					System.out.println(nef);
				}
			}
		});
		btnUpdate.setBounds(224, 184, 85, 21);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					 
		            // Get the name of the contact to be updated
		            // from the Command line argument
					String newName = String.valueOf(txtName.getText());
		            long newNumber = Long.parseLong(txtNumber.getText());
		            
		            String nameNumberString;
		            String name;
		            long number;
		            int index;
		 
		            // Using file pointer creating the file.
		            File file = new File("C:\\Users\\ASUS\\Desktop\\POO-UNAL\\friendsContact.txt");
		 
		            if (!file.exists()) {
		 
		                // Create a new file if not exists.
		                file.createNewFile(); 
		            }
		 
		            // Opening file in reading and write mode.
		            RandomAccessFile raf= new RandomAccessFile(file, "rw");
		            boolean found = false;
		 
		            // Checking whether the name of contact exists.
		            // getFilePointer() give the current offset
		            // value from start of the file.
		            while (raf.getFilePointer() < raf.length()) {
		 
		                // reading line from the file.
		                nameNumberString = raf.readLine();
		 
		                // splitting the string to get name and
		                // number
		                String[] lineSplit= nameNumberString.split("!");
		 
		                // separating name and number.
		                name = lineSplit[0];
		                number = Long.parseLong(lineSplit[1]);
		 
		                // if condition to find existence of record.
		                if (name.equals(newName) ==true) {
		                    found = true;
		                    break;
		                }
		            }
		 
		            // Delete the contact if record exists.
		            if (found == true) {
		 
		                // Creating a temporary file
		                // with file pointer as tmpFile.
		                File tmpFile = new File("C:\\Users\\ASUS\\Desktop\\POO-UNAL\\temp.txt");
		 
		                // Opening this temporary file
		                // in ReadWrite Mode
		                RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");
		 
		                // Set file pointer to start
		                raf.seek(0);
		 
		                // Traversing the friendsContact.txt file
		                while (raf.getFilePointer()< raf.length()) {
		 
		                    // Reading the contact from the file
		                    nameNumberString = raf.readLine();
		 
		                    index = nameNumberString.indexOf('!');
		                    name = nameNumberString.substring(0, index);
		 
		                    // Check if the fetched contact
		                    // is the one to be deleted
		                    if (name.equals(newName)) {
		 
		                        // Skip inserting this contact
		                        // into the temporary file
		                        continue;
		                    }
		 
		                    // Add this contact in the temporary
		                    // file
		                    tmpraf.writeBytes(nameNumberString);
		 
		                    // Add the line separator in the
		                    // temporary file
		                    tmpraf.writeBytes(System.lineSeparator());
		                }
		 
		                // The contact has been deleted now
		                // So copy the updated content from
		                // the temporary file to original file.
		 
		                // Set both files pointers to start
		                raf.seek(0);
		                tmpraf.seek(0);
		 
		                // Copy the contents from
		                // the temporary file to original file.
		                while (tmpraf.getFilePointer()
		                       < tmpraf.length()) {
		                    raf.writeBytes(tmpraf.readLine());
		                    raf.writeBytes(System.lineSeparator());
		                }
		 
		                // Set the length of the original file
		                // to that of temporary.
		                raf.setLength(tmpraf.length());
		 
		                // Closing the resources.
		                tmpraf.close();
		                raf.close();
		 
		                // Deleting the temporary file
		                tmpFile.delete();
		 
		                System.out.println(" Friend deleted. ");
		            }
		 
		            // The contact to be deleted
		            // could not be found
		            else {
		 
		                // Closing the resources.
		                raf.close();
		 
		                // Print the message
		                JOptionPane.showMessageDialog(null, " The friend "+newName+" does not exists. ");
		                //System.out.println(" Input name"+ " does not exists. ");
		            }
		        }
		 
		        catch (IOException ioe) {
		            System.out.println(ioe);
		        }
			}
		});
		btnDelete.setBounds(320, 184, 85, 21);
		contentPane.add(btnDelete);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtName.setText("");
				txtNumber.setText("");
			}
		});
		btnClear.setBounds(91, 232, 85, 21);
		contentPane.add(btnClear);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(224, 232, 85, 21);
		contentPane.add(btnExit);
	}
}