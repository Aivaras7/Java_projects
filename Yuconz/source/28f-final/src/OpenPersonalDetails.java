import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

/**
 * @author as2352, dd387
 * @version 1
 * The personal details menu for the program
 * Is passed the staffID/user name and auth level
 * Using this it displays the options the user can take and the files they can use
 */
@SuppressWarnings("serial")
public class OpenPersonalDetails extends JFrame {



	private YuconzFileManager fileManager;
	int authLevel;
	String username;
	File file;



	// Creating panel view
	private JPanel personalDetailsPanel = new JPanel();


	// Creating buttons
	private JButton createPDbtn = new JButton("Create PD");
	private JButton readPDbtn = new JButton("Read PD");
	private JButton amendPDbtn = new JButton("Amend PD");
	private JButton backbtn = new JButton("Back");
	private JButton confirmAmendBtn = new JButton("Confirm amend");
	private JButton confirmCreateBtn = new JButton("Create PD File");
	private JButton cancelBtn = new JButton("Cancel");
	// Creating text fields

	// Move this into a constructor or main method
/*	String[] textFieldNames = new String[] {"addressTxt", "dobText", "nameTxt", "townTxt", "surnameTxt", "postcodeTxt", "countyTxt", "econtactTxt", "econtactNumberTxt", "mnumberTxt", "tnumberTxt"};

	for (int i=0; i< textFieldNames.length; i++) {
		String name = textFieldNames[];
		private JTextField name = newJTextField();
	}
*/

	private JTextField addressTxt = new JTextField();
	private JTextField dobTxt = new JTextField();
	private JTextField nameTxt = new JTextField();
	private JTextField townTxt = new JTextField();
	private JTextField surnameTxt = new JTextField();
	private JTextField postcodeTxt = new JTextField();
	private JTextField countyTxt = new JTextField();
	private JTextField econtactTxt = new JTextField();
	private JTextField eContactNumberTxt = new JTextField();
	private JTextField mnumberTxt = new JTextField();
	private JTextField tnumberTxt = new JTextField();
	// Creating labels
	private JLabel surnameLabel = new JLabel("Surname");
	private JLabel dobLabel = new JLabel("Date of Birth");
	private JLabel nameLabel = new JLabel("First name");
	private JLabel addressLabel = new JLabel("Address");
	private JLabel townLabel = new JLabel("Town/city");
	private JLabel countyLabel = new JLabel("County");
	private JLabel tNumberLabel = new JLabel("Telephone number");
	private JLabel mNumberLabel = new JLabel("Mobile number");
	private JLabel eContactLabel = new JLabel("Emergency contact");
	private JLabel eContactNumberLabel = new JLabel("E. contact number");
	private JLabel postcodeLabel = new JLabel("Postcode");
	private JLabel tutorialMsgLabel = new JLabel("");

	// adding labels to array
	JLabel[] pDLabels = new JLabel[] { surnameLabel, dobLabel, nameLabel, addressLabel, townLabel, countyLabel,
			tNumberLabel, mNumberLabel, eContactLabel, eContactNumberLabel, postcodeLabel, tutorialMsgLabel };
	// adding text fields to array
	JTextField[] pDTextFields = new JTextField[] {nameTxt, surnameTxt, dobTxt, addressTxt,  townTxt, countyTxt, postcodeTxt, tnumberTxt, mnumberTxt,
			 econtactTxt, eContactNumberTxt};
	//adding amend/create buttons to array
	JButton[] amendButtons = new JButton[] { confirmAmendBtn, cancelBtn, confirmCreateBtn };
	// adding menu buttons to array
	JButton[] menuButtons = new JButton[] { createPDbtn, readPDbtn, amendPDbtn, backbtn };


	/**
	 * Constructor setting up elements
	 *
	 * @param a -- the user authorisation level
	 * @param user -- the username/staffID
	 */
	OpenPersonalDetails(int a,String user) {
		super("Personal details view");
		authLevel = a;
		username = user;
		fileManager = new YuconzFileManager(user);
		setSize(785, 640);
		setLocation(500, 280); //
		personalDetailsPanel.setLayout(null);

		//for loops to add JSwing to panel
		for (JButton mButtons : menuButtons) {
			personalDetailsPanel.add(mButtons);
		}

		for (JLabel label : pDLabels) {
			personalDetailsPanel.add(label);
		}
		for (JTextField textField : pDTextFields) {
			personalDetailsPanel.add(textField);
		}

		for (JButton aButtons : amendButtons) {
			personalDetailsPanel.add(aButtons);
		}


		// setting bounds to JSwing
		readPDbtn.setBounds(10, 30, 85, 25);
		backbtn.setBounds(10, 570, 120, 25);
		createPDbtn.setBounds(135, 30, 110, 25);
		amendPDbtn.setBounds(655, 30, 105, 25);
		addressTxt.setBounds(230, 220, 305, 35);
		dobTxt.setBounds(230, 170, 305, 35);
		nameTxt.setBounds(230, 85, 305, 35);
		townTxt.setBounds(230, 265, 305, 35);
		surnameTxt.setBounds(230, 130, 305, 35);
		postcodeTxt.setBounds(230, 355, 305, 35);
		surnameLabel.setBounds(100, 135, 100, 25);
		dobLabel.setBounds(100, 180, 100, 25);
		nameLabel.setBounds(100, 90, 100, 25);
		addressLabel.setBounds(100, 225, 100, 25);
		townLabel.setBounds(100, 270, 100, 25);
		countyLabel.setBounds(100, 315, 100, 25);
		tutorialMsgLabel.setBounds(285, 20, 250, 50);
		confirmAmendBtn.setBounds(545, 540, 145, 35);
		confirmCreateBtn.setBounds(545, 540, 145, 35);
		cancelBtn.setBounds(10, 15, 130, 30);
		tnumberTxt.setBounds(230, 400, 305, 35);
		postcodeLabel.setBounds(100, 360, 100, 25);
		countyTxt.setBounds(230, 310, 305, 35);
		econtactTxt.setBounds(230, 495, 305, 35);
		mnumberTxt.setBounds(230, 445, 305, 35);
		eContactNumberTxt.setBounds(230, 540, 305, 35);
		tNumberLabel.setBounds(100, 405, 135, 25);
		mNumberLabel.setBounds(100, 450, 100, 25);
		eContactLabel.setBounds(100, 500, 125, 25);
		eContactNumberLabel.setBounds(100, 545, 125, 25);

		getContentPane().add(personalDetailsPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // JFrame closes on exit and doesn't run in background


		// calling methods
		setupPDView();
		backToMenu();
		amendPersonalDetailsBTN();
		cancelAmend();
		createPersonalDetailsBTN();
		readPersonalDetailsBTN();
		confirmCreateBTN();
		confirmAmendBTN();

	}

	 /**
     * Sets up the personal details view for user and
     * only shows buttons if user has correct auth level
     */
	public void setupPDView() {

		for (JLabel label : pDLabels) {
			label.setVisible(true);
		}
		for (JTextField textField : pDTextFields) {
			textField.setVisible(true);
			textField.setEditable(false);
		}
		for (JButton aButtons : amendButtons) {
			aButtons.setVisible(false);
		}
		for (JButton mButtons : menuButtons) {
			if(mButtons != createPDbtn) {
				mButtons.setVisible(true);
			}
			else if(authLevel == 1) {
				mButtons.setVisible(true);
			}

		}
		if(authLevel != 1) {
			createPDbtn.setVisible(false);
		}
		tutorialMsgLabel.setText("");
	}


	/**
     * Code for handling create personal details button when clicked.
     * When button is clicked, open new box asking for staff ID, and
     * after authorisation, display text fields and show relevant buttons
     */
	public void createPersonalDetailsBTN() {
		createPDbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				createPersonalDetails();
			}
		});

	}

	private void createPersonalDetails() {
		String fileName = JOptionPane.showInputDialog("Enter StaffID:");
		if(fileName == null) {
			// User pressed cancel on the above input dialog, which returns null
		}
		else if (fileManager.createPD(fileName)) {
			for (JButton mButtons : menuButtons) {
				mButtons.setVisible(false);
			}
			for (JTextField textField : pDTextFields) {
				textField.setEditable(true);
				textField.setText("");
			}
			cancelBtn.setVisible(true);
			confirmCreateBtn.setVisible(true);
			tutorialMsgLabel.setText("Create Personal Details:");
		}
		else {
			int a = JOptionPane.showConfirmDialog(null,
					"Error while creating file, try again?", "choose one", JOptionPane.YES_NO_OPTION);
			if(a == 0) {
				// Yes option returns 0
				createPersonalDetails();
			}
		}
	}


	/**
     * Code for handling button to confirm creation of record
     */
	public void confirmCreateBTN() {
		confirmCreateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				confirmCreate();
			}
		});
	}

	private void confirmCreate() {
		String[] toWrite = new String[10];
		for(int i = 0; i < 10; i++) {
			toWrite[i] = pDTextFields[i].getText();
		}
		if(!fileManager.amendRecord(toWrite, file)) {
			JOptionPane.showMessageDialog(null, "Error while writing to file");
		}
		Logging.logger.info(username + " created file: " + file.getName());
		setupPDView();
	}

	public void readPersonalDetailsBTN() {
		readPDbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				readPersonalDetails();
			}
		});
	}

	private void readPersonalDetails() {
		String[] detailsList = new String[11];

		if(authLevel == 1) {
			FileSystemView fsv = new DirectoryRestrictedFileSystemView(new File("pd/"));
			JFileChooser fileChooser = new JFileChooser("pd/",fsv);
			int returnVal = fileChooser.showOpenDialog(OpenPersonalDetails.this);

			if(returnVal == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile();
				detailsList = fileManager.readRecord(file);
			}
		}
		else {
		  file = new File("pd/" + username + ".txt");
		  detailsList = fileManager.readRecord(file);
		}

		if(detailsList == null) {
            JOptionPane.showMessageDialog(null, "Database error");
		}

		for(int i = 0; i < 11; i++) {
			pDTextFields[i].setText(detailsList[i]);
		}
		Logging.logger.info(username + " Read file: " + file.getName());
	}

	/**
	 * Takes user input and sends it to YuconzFileManager to write to file
	 */
	public void amendPersonalDetailsBTN() {
		amendPDbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {

				for (JButton button : amendButtons) {
					button.setVisible(true);
				}
				for (JButton mButtons : menuButtons) {
					mButtons.setVisible(false);
				}
				for (JTextField textField : pDTextFields) {
					textField.setEditable(true);
				}
				tutorialMsgLabel.setText("Amend Personal Details:");
				readPersonalDetails();
			}
		});

	}

	public void confirmAmendBTN() {
		confirmAmendBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				confirmAmend();
			}
		});
	}

	private void confirmAmend() {
		String[] toWrite = new String[10];
		for(int i = 0; i < 10; i++) {
			toWrite[i] = pDTextFields[i].getText();
		}

		if(!fileManager.amendRecord(toWrite, file)) {
			JOptionPane.showMessageDialog(null, "Error while writing to file");
		}

		Logging.logger.info(username + " amended file: " + file.getName());
	}

	public void cancelAmend() {
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setupPDView();
			}
		});

	}

	public void backToMenu() {
		backbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Menu menuView = new Menu(authLevel,username);
				menuView.setVisible(true);
				dispose();
			}
		});

	}

}