

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author as2352
 * @version 0.5
 * The main menu for the program
 * gives the option of personal details or review record management
 */
@SuppressWarnings("serial")
public class Menu extends JFrame {
    private JLabel instructionMessageLabel = new JLabel("Select type of record");
    private JPanel panel = new JPanel();
    private JButton logoutBtn = new JButton("Logout");
    private JButton pdBtn = new JButton("Personal details view");
    private JButton reviewBtn = new JButton("Review view");

    int authLevel; //
    String username;

    Menu(int a,String user) {
    	super("Yuconz login view");
        authLevel = a;
        username = user;
        setSize(225, 220);
        setLocation(500, 280); //
        panel.setLayout(null);



        panel.add(instructionMessageLabel);
        panel.add(logoutBtn);
        panel.add(pdBtn);
        panel.add(reviewBtn);

        instructionMessageLabel.setBounds(40, 4, 200, 25);
        reviewBtn.setBounds(20, 28, 160, 25);
        pdBtn.setBounds(20, 75, 160, 25);

        logoutBtn.setBounds(5, 150, 100, 20);



        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        logout();
        personalDetailsView();
        openReview();

    }

    /**
     *
     */

    public void logout() {
        logoutBtn.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent ae) {
                authLevel = 0; // setting authlevel back to default value
                Yuconz loginMenu = new Yuconz();
                loginMenu.setVisible(true);
                dispose();
            }
        });

    }

    public void personalDetailsView() {
        pdBtn.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent ae) {
             	 OpenPersonalDetails pdView = new OpenPersonalDetails(authLevel,username);
                 pdView.setVisible(true);
                 dispose();
            }
        });

    }

    public void openReview() {
        reviewBtn.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent ae) {
                OpenReview reviewView = new OpenReview(authLevel,username);
                reviewView.setVisible(true);
                dispose();

            }
        });

    }
}
