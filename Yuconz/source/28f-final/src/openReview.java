import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

@SuppressWarnings("serial")
public class OpenReview extends JFrame {

	private JPanel reviewPanel = new JPanel();

	private JLabel perfReviewLabel = new JLabel("Performance review");
	private JLabel staffNoLabel = new JLabel("Staff no.");
	private JLabel nameLabel = new JLabel("Name");
	private JLabel managerLabel = new JLabel("Manager/Director");
	private JLabel secondManagerLabel = new JLabel("Second manager/Director");
	private JLabel sectionLabel = new JLabel("Section");
	private JLabel jobLabel = new JLabel("Job title");
	private JLabel reviewLabel = new JLabel("A review of past performance: achievements and outcomes");
	private JLabel objectivesLabel = new JLabel("Objectives");
	private JLabel achievementLabel = new JLabel("Achievements");
	private JLabel performanceLabel = new JLabel("Performance summary");

	private JTextField staffNoText = new JTextField(5);
	private JTextField nameText = new JTextField(5);
	private JTextField managerText = new JTextField(5);
	private JTextField secondManagerText = new JTextField(5);
	private JTextField sectionText = new JTextField(5);
	private JTextField jobText = new JTextField(5);
	// page two
	private JTextField reviewSignatureTextField = new JTextField(5);
	private JTextField managerSignatureTextField = new JTextField(5);
	private JTextField secondReviewerSignatureTextField = new JTextField(5);

	private JTextArea objectivesText = new JTextArea(5, 5);
	private JTextArea achievementText = new JTextArea(5, 5);
	private JTextArea performanceText = new JTextArea(5, 5);
	// page two
	private JTextArea performanceTextPageTwo = new JTextArea(5, 5);
	private JTextArea reviewTextPageTwo = new JTextArea(5, 5);

	private JButton pageOneBtn = new JButton("Go back");
	private JButton pageTwoBtn = new JButton("Next");
	private JButton createReviewbtn = new JButton("Create Review");
	private JButton readReviewbtn = new JButton("Read Review");
	private JButton amendReviewbtn = new JButton("Amend Review");

	// page two
	private JLabel performancePageTwoLabel = new JLabel("A preview of future performance: goals /planned outcomes");
	private JLabel numberLabel = new JLabel("no:");
	private JLabel reviewCommentLabel = new JLabel("Reviewer Comments");
	private JLabel recommendationLabel = new JLabel("Recommendation:");
	private JLabel reviewPageTwoLabel = new JLabel("Reviewee signature");
	private JLabel managerPageTwoLabel = new JLabel("Manager/Director signature");
	private JLabel secondReviewerLabel = new JLabel("Second Reviewer signature");


	// Drop down box
	private JComboBox<?> recommendationDropDown = new JComboBox<Object> (new String[]{"Stay in post", "Salary increase", "Promotion", "Probation", "Termination"});

	// Putting labels of first page into array
	JLabel[] reviewLabelsPg1 = new JLabel[] { perfReviewLabel, staffNoLabel, nameLabel, managerLabel,
			secondManagerLabel, sectionLabel, jobLabel, reviewLabel, objectivesLabel, achievementLabel,
			performanceLabel };

	// Putting combo box into array, in case we want to add more combo boxes in future we can use array created
	@SuppressWarnings("rawtypes")
	JComboBox[] recComboBox = new JComboBox[] { recommendationDropDown};

	// Putting text fields of first page into array
	JTextField[] reviewTextFieldsPg1 = new JTextField[] { staffNoText, nameText, managerText, secondManagerText,
			sectionText, jobText };
	// Putting text areas of first page into array
	JTextArea[] reviewTextAreasPg1 = new JTextArea[] { objectivesText, achievementText, performanceText };

	// Putting navigation buttons into array
	JButton[] reviewButtons = new JButton[] { pageOneBtn, pageTwoBtn };

	// Putting labels of second page into array
	JLabel[] reviewLabelsPg2 = new JLabel[] { performancePageTwoLabel, numberLabel, reviewCommentLabel,
			recommendationLabel, reviewPageTwoLabel, managerPageTwoLabel, secondReviewerLabel };
	// Putting text fields of second page into array
	JTextField[] reviewTextFieldsPg2 = new JTextField[] { reviewSignatureTextField, managerSignatureTextField,
			secondReviewerSignatureTextField };

	// Putting text areas of second page into array
	JTextArea[] reviewTextAreasPg2 = new JTextArea[] { performanceTextPageTwo, reviewTextPageTwo };

	// Putting menu buttons into array
	JButton[] menuButtons = new JButton[] { createReviewbtn, amendReviewbtn, readReviewbtn };

	boolean pageOne; // Page switcher
	int authLevel ;
	String username;
	private YuconzFileManager fileManager;
	private File file;

	OpenReview(int a,String user) {
		super("Review view");
		setupPageOne();
		setupPageTwo();
		pageOne = true;

		authLevel = a;
		username = user;
		fileManager = new YuconzFileManager(user);

		reviewPanel.setLayout(null);
		setSize(1000, 700);
		setLocation(500, 280); //

		getContentPane().add(new JScrollPane(reviewPanel));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);



		// For loops created through each array in order to add them into panel/make them visible
		for (JLabel rLabelsPg1 : reviewLabelsPg1) {
			reviewPanel.add(rLabelsPg1);
		}

		for (JTextField rTextFieldsPg1 : reviewTextFieldsPg1) {
			reviewPanel.add(rTextFieldsPg1);
		}

		for (JTextArea rTextAreasPg1 : reviewTextAreasPg1) {
			reviewPanel.add(rTextAreasPg1);
		}

		for (JButton rButtons : reviewButtons) {
			reviewPanel.add(rButtons);
		}
		for (JButton mButtons : menuButtons) {
			reviewPanel.add(mButtons);
		}

		for (JLabel rLabelsPg2 : reviewLabelsPg2) {
			reviewPanel.add(rLabelsPg2);
			rLabelsPg2.setVisible(false);
		}

		for (JTextField rTextFieldsPg2 : reviewTextFieldsPg2) {
			reviewPanel.add(rTextFieldsPg2);

			rTextFieldsPg2.setVisible(false);
		}

		for (JTextArea rTextAreasPg2 : reviewTextAreasPg2) {
			reviewPanel.add(rTextAreasPg2);
			rTextAreasPg2.setVisible(false);
		}

		for (JComboBox<?> recComboBoxes : recComboBox ) {
			reviewPanel.add(recComboBoxes);
			recComboBoxes.setVisible(false);
		}
		// setting bounds
		recommendationDropDown.setBounds(155, 410, 205, 25);
		perfReviewLabel.setBounds(375, 15, 170, 25);
		staffNoLabel.setBounds(370, 50, 60, 25);
		nameLabel.setBounds(380, 90, 55, 25);
		staffNoText.setBounds(435, 50, 165, 25);
		nameText.setBounds(435, 90, 165, 25);
		managerLabel.setBounds(70, 130, 100, 25);
		secondManagerLabel.setBounds(25, 170, 150, 30);
		sectionLabel.setBounds(125, 215, 55, 20);
		jobLabel.setBounds(120, 250, 55, 25);
		managerText.setBounds(180, 130, 205, 25);
		secondManagerText.setBounds(180, 175, 205, 25);
		sectionText.setBounds(180, 210, 205, 25);
		jobText.setBounds(180, 250, 210, 25);
		reviewLabel.setBounds(40, 280, 440, 30);
		objectivesText.setBounds(30, 345, 185, 260);
		achievementText.setBounds(260, 345, 195, 260);
		performanceText.setBounds(495, 345, 435, 260);
		objectivesLabel.setBounds(70, 315, 100, 25);
		achievementLabel.setBounds(310, 315, 100, 25);
		performanceLabel.setBounds(615, 315, 145, 25);
		pageOneBtn.setBounds(725, 615, 145, 25);
		pageTwoBtn.setBounds(875, 615, 145, 25);
		performanceTextPageTwo.setBounds(35, 90, 405, 290);
		numberLabel.setBounds(45, 60, 100, 25);
		reviewTextPageTwo.setBounds(520, 90, 395, 290);
		reviewCommentLabel.setBounds(640, 60, 120, 25);
		recommendationLabel.setBounds(35, 410, 115, 25);
		readReviewbtn.setBounds(10, 30, 85, 25);
		createReviewbtn.setBounds(135, 30, 110, 25);
		amendReviewbtn.setBounds(655, 30, 105, 25);
		reviewPageTwoLabel.setBounds(265, 475, 140, 25);
		managerPageTwoLabel.setBounds(260, 510, 175, 30);
		secondReviewerLabel.setBounds(265, 540, 165, 25);
		reviewSignatureTextField.setBounds(385, 475, 100, 25);
		managerSignatureTextField.setBounds(425, 510, 100, 25);
		secondReviewerSignatureTextField.setBounds(425, 540, 100, 25);

		readReviewRecordBTN();



	}
	/*
	Page method that uses for loop to set arrays visible or not depending on the page they're on
	 */
	public void page() {

		for (JLabel rLabelsPg1 : reviewLabelsPg1) {
			rLabelsPg1.setVisible(pageOne);
		}
		for (JTextField rTextFieldsPg1 : reviewTextFieldsPg1) {
			rTextFieldsPg1.setVisible(pageOne);
		}
		for (JTextArea rTextAreasPg1 : reviewTextAreasPg1) {
			rTextAreasPg1.setVisible(pageOne);
		}
		for (JLabel rLabelsPg1 : reviewLabelsPg2) {
			rLabelsPg1.setVisible(!pageOne);
		}
		for (JTextField rTextFieldsPg1 : reviewTextFieldsPg2) {
			rTextFieldsPg1.setVisible(!pageOne);
		}

		for (JTextArea rTextAreasPg1 : reviewTextAreasPg2) {
			rTextAreasPg1.setVisible(!pageOne);
		}
		for (JComboBox<?> recComboBoxes : recComboBox ) {
			recComboBoxes.setVisible(!pageOne);
		}
	}

	/*
	Page one button method, simply tells that page one is selected and calls page method
	 */
	public void setupPageOne() {
		pageOneBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
			pageOne = true;
				System.out.println("testpageone");
				page();
			}
		});

	}
	/*
        Page two button method, simply tells that page one is selected and calls page method
         */
	public void setupPageTwo() {
		pageTwoBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
			pageOne = false;
				System.out.println("testpagetwo"); // changed
				page();
			}
		});
	}
	public void readReviewRecordBTN() {
		readReviewbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				readReviewRecord();
			}
		});
	}

	public void readReviewRecord() {
		List<String> detailsList = new ArrayList<>();
		File file;
		if(authLevel == 1) { // HR staff chose any review record
			FileSystemView fsv = new DirectoryRestrictedFileSystemView(new File("reviews/"));
			JFileChooser fileChooser = new JFileChooser("reviews/",fsv);
			int returnVal = fileChooser.showOpenDialog(OpenReview.this);

			if(returnVal == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile();
				detailsList = Arrays.asList(fileManager.readRecord(file));
			}
		} else if (authLevel == 0) { // Users are given the choice of their own reviews

			file = new File("reviews/");
			List<Object> options = new ArrayList<>();
			try (DirectoryStream<Path> stream = Files.newDirectoryStream(file.toPath(), username + "*.txt")) {
				for (Path entry: stream) {
					options.add(entry.toFile().getName());
				}
			} catch (DirectoryIteratorException ex) {
				// I/O error encountered during the iteration, the cause is an IOException
			} catch (IOException e) {
				Logging.logger.severe("Error reading ");
				e.printStackTrace();
			}
			Object selectionObject = JOptionPane.showInputDialog(OpenReview.this, "Choose", "Menu", JOptionPane.PLAIN_MESSAGE, null, options.toArray(), options.get(0));
			file = new File("reviews/" + ((String) selectionObject));
			detailsList = Arrays.asList(fileManager.readRecord(file));

		} else if (authLevel == 3) { // Reviewers can choose any review records of someone they are reviewing

			file = new File("reviews/");
			List<Object> options = new ArrayList<>();
			String[] reviewees = fileManager.checkReviewees();
			for(String reviewee : reviewees) {
				try (DirectoryStream<Path> stream = Files.newDirectoryStream(file.toPath(), reviewee + "*.txt")) {
					for (Path entry: stream) {
						options.add(entry.toFile().getName());
					}
				} catch (DirectoryIteratorException ex) {
					// I/O error encountered during the iteration, the cause is an IOException
				} catch (IOException e) {
					Logging.logger.severe("Error reading ");
					e.printStackTrace();
				}
			}
			Object selectionObject = JOptionPane.showInputDialog(OpenReview.this, "Choose", "Menu", JOptionPane.PLAIN_MESSAGE, null, options.toArray(), options.get(0));
			file = new File((String) selectionObject);
			detailsList = Arrays.asList(fileManager.readRecord(file));

		}

		int tableCount = 0;
		int count = 0;
		if(pageOne) {
			for(int i = 0; i < detailsList.size(); i++) {
				System.out.println(detailsList.get(i));
				if(detailsList.get(i).equals("<table>")) {
					i++;
					while(!detailsList.get(i).equals("</table>")) {
						reviewTextAreasPg1[tableCount].append(detailsList.get(i) + "/n");
					}
					i++;
					tableCount++;
				}  else if (detailsList.get(i).equals("<2>")) {
					i = detailsList.size();
				} else {
					reviewTextFieldsPg1[count].setText(detailsList.get(i));
					count++;
				}
			}
		} else {
			int i = 0;
			while (!detailsList.get(i).equals("<2>")) {
				i++;
			}
			i++;
			for(; i < detailsList.size(); i++) {
				if(detailsList.get(i).equals("<table>")) {
					i++;
					while(!detailsList.get(i).equals("</table>")) {
						reviewTextAreasPg2[tableCount].append(detailsList.get(i) + "/n");
					}
					i++;
					tableCount++;
				} else {
					reviewTextFieldsPg2[count].setText(detailsList.get(i));
					count++;
				}
			}


		}
	}

	public void amendReiewRecordBTN() {
		amendReviewbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				amendReviewRecord();
			}
		});
	}

	public void amendReviewRecord() {
		if(file != null) {
			List<String> toWrite = new ArrayList<>();
			for (JTextField rTextFieldsPg1 : reviewTextFieldsPg1) {
				toWrite.add(rTextFieldsPg1.getText());
			}
			for (JTextArea rTextAreasPg1 : reviewTextAreasPg1) {
				toWrite.add(rTextAreasPg1.getText());
			}
			for (JTextField rTextFieldsPg2 : reviewTextFieldsPg1) {
				toWrite.add(rTextFieldsPg2.getText());
			}
			for (JTextArea rTextAreasPg2 : reviewTextAreasPg1) {
				toWrite.add(rTextAreasPg2.getText());
			}
			Object[] objResults = toWrite.toArray();
			String[] results = Arrays.copyOf(objResults, objResults.length, String[].class);
			fileManager.amendRecord(results, file);
		} else {
			JOptionPane.showMessageDialog(null, "Read record first.");
		}
	}

	public void createReviewRecordBTN() {
		createReviewbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				createReviewRecord();
			}
		});
	}

	public void createReviewRecord() {
		String staffID = JOptionPane.showInputDialog("Enter StaffID:");
		String fileName = staffID + Calendar.getInstance().get(Calendar.YEAR);
		fileManager.createReview(fileName);
	}


}



