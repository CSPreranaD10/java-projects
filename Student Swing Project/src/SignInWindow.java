import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignInWindow implements ActionListener {

	JFrame jFrame;
	JButton register, login, logout;
	JLabel usernameJLabel, passwordJLabel , displaymsgJLabel;
	JTextField usernameField;
	JPasswordField passwordField;

	@SuppressWarnings("static-access")
	SignInWindow(){
		jFrame = new JFrame();
		jFrame.setTitle("Login Page ");

		usernameJLabel = new JLabel("Enter Username : ");
		usernameField = new JTextField();
		passwordJLabel = new JLabel("Enter Password :");
		passwordField = new JPasswordField();
		displaymsgJLabel = new JLabel(" c");

		register = new JButton("Register");
		login = new JButton("Login");
		logout = new JButton("Logout");

		usernameJLabel.setBounds(80,70,150,30);
		usernameField.setBounds(270,70,150,30);
		passwordJLabel.setBounds(80,120,150,30);
		passwordField.setBounds(270,120,150,30);
		register.setBounds(410,20,100,30);
		login.setBounds(130,170, 100,30);
		logout.setBounds(300,170,100,30);
		displaymsgJLabel.setBounds(210,220,200,30);

		register.addActionListener(this);
		login.addActionListener(this);
		logout.addActionListener(this);

		jFrame.add(register);
		jFrame.add(usernameJLabel);
		jFrame.add(usernameField);
		jFrame.add(passwordJLabel);
		jFrame.add(passwordField);
		jFrame.add(login);
		jFrame.add(logout);
		jFrame.add(displaymsgJLabel);

		jFrame.setLayout(null);
		jFrame.setSize(550,300);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == register) {
			jFrame.dispose();
			new RegisterWindow();
		} else if (e.getSource() == login) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample", "root", "root");
				String username = usernameField.getText();
				String password = passwordField.getText();
				PreparedStatement pdstmt = con3.prepareStatement("select password=? from student_login where username =?");
				pdstmt.setString(1, password);
				pdstmt.setString(2, username);
				ResultSet rSet = pdstmt.executeQuery();
				if (rSet.next()) {
					jFrame.dispose();
					new StudentWindow();
				}
				else 
				{
					System.out.println("wrong input");
					displaymsgJLabel.setText("Wrong user or password");
				}

				System.out.println(username + " " + password + " ");
				con3.close();
			}catch (Exception e1) {
				e1.printStackTrace();
			}

		}else if (e.getSource() ==logout) {
			jFrame.dispose();
		}

	}
	public static void main(String[] args) {
		new SignInWindow();
	}


}

/*CREATE TABLE student_login(
		name VARCHAR(30),
		email VARCHAR(40),
		username VARCHAR(30) PRIMARY KEY,
		password VARCHAR(30)
		);
 */




