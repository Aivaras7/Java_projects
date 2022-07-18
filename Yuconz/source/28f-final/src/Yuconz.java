


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * @author as2352, ru43
 * @version 0.5
 * The main menu for the program
 * currently just a placeholder
 */
@SuppressWarnings("serial")
public class Yuconz extends JFrame {
	private static String[] roleBoxItems = {"User", "HR Employee", "Director", "Reviewer"};
	private static JComboBox<String> roleBox = new JComboBox<>(roleBoxItems);
    private JLabel idLabel = new JLabel("YuconzID:");
    private JLabel pwLabel = new JLabel("Password:");
    private JLabel roleLabel = new JLabel("Role:");
    private JButton loginBtn = new JButton("Login");
    private JPanel window = new JPanel();
    private JTextField id = new JTextField(15);
    private JPasswordField pw = new JPasswordField(15);
    private String pwd;
    private String user;


    Yuconz() {
    	super("Yuconz");
        // Adjusting login window size
        setSize(225, 220);
        setLocation(500, 280);
        // Setting layout
        window.setLayout(null);

        // adding elements to window
        window.add(id);
        window.add(pw);
        window.add(idLabel);
        window.add(pwLabel);
        window.add(roleBox);
        window.add(roleLabel);
        window.add(loginBtn);

        // changing elements place
        id.setBounds(20, 28, 160, 25);
        pw.setBounds(20, 75, 160, 25);
        idLabel.setBounds(60, 4, 100, 25);
        pwLabel.setBounds(65, 52, 100, 25);
        roleBox.setBounds(50, 115, 129, 25);
        roleLabel.setBounds(20, 115, 100, 25);
        loginBtn.setBounds(65, 150, 75, 25);

        Logging.format(); // formatting logs once application launches
        getContentPane().add(window);
        setVisible(true);
        login();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        @SuppressWarnings("unused")
		Yuconz frameTable = new Yuconz();//
    }

    /**
	 * called by a gui button on the login window with the text login
	 * it takes text from the two text boxes on the login window and the
	 * selected index of the role box to pass to the authenticate method
	 * if authenticate returns -1 then we call clear
	 * else open the menu with the auth level
	 */
    public void login() {
        loginBtn.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent ae) {
                user = id.getText();
                pwd = String.valueOf(pw.getPassword());
                int desiredRole = roleBox.getSelectedIndex();
                //create authenticate server
                Authenticate server = new Authenticate();
                int auth = server.authenticate(user, pwd, desiredRole);
                if (auth == -1) {
                    clear();
                }
                //create new window with auth - which is permission level
                else {
                	Menu regFace = new Menu(auth,user);
                    regFace.setVisible(true);
                    dispose();
                }
            }
        });
    }

    /*
	 * resets the text boxes and refocuses the login window
	 */
    public void clear() {
        id.setText("");
        pw.setText("");
        id.requestFocus();
    }





}
