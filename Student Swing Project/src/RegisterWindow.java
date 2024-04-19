import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;

public class RegisterWindow implements ActionListener {

	JFrame jFrame;
	JLabel  headingLabel ,nameLabel, emailLabel, usernameLabel, passwordLabel, displaymsgLabel;
	JTextField nameTextField, emailTextField, usernameTextField;
	JPasswordField passwordField;
	JButton register, cancel;
	Timer timer;

	@SuppressWarnings("static-access")
	public RegisterWindow(){
		jFrame = new JFrame("Registration Form ");

		headingLabel = new JLabel("Registration for students ");
		nameLabel = new JLabel("Enter full name : ");
		nameTextField = new JTextField();
		emailLabel = new JLabel("Enter your mail id : ");
		emailTextField = new JTextField();
		usernameLabel = new JLabel("Enter the username : ");
		usernameTextField = new JTextField();
		passwordLabel = new JLabel("Enter your password : ");
		passwordField = new JPasswordField();
		register = new JButton("Register");
		cancel = new JButton("Cancel");
		displaymsgLabel = new JLabel(""); 

		headingLabel.setBounds(130,20,200,30);
		nameLabel.setBounds(70,80,150,30);
		nameTextField.setBounds(240,80,150,30);
		emailLabel.setBounds(70,140,150,30);
		emailTextField.setBounds(240,140,150,30);
		usernameLabel.setBounds(70,200,150,30);
		usernameTextField.setBounds(240,200,150,30);
		passwordLabel.setBounds(70,260,150,30);
		passwordField.setBounds(240,260,150,30);
		register.setBounds(90,320,100,30);
		cancel.setBounds(220,320,100,30);
		displaymsgLabel.setBounds(180,380,200,30);

		register.addActionListener(this);
		cancel.addActionListener(this);


		jFrame.add(headingLabel);
		jFrame.add(nameLabel);
		jFrame.add(nameTextField);
		jFrame.add(emailLabel);
		jFrame.add(emailTextField);
		jFrame.add(usernameLabel);
		jFrame.add(usernameTextField);
		jFrame.add(passwordLabel);
		jFrame.add(passwordField);
		jFrame.add(register);
		jFrame.add(cancel);
		jFrame.add(displaymsgLabel);


		jFrame.setLayout(null);
		jFrame.setSize(450,500);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == register) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample", "root", "root");
				String name = nameTextField.getText();
				String mail = emailTextField.getText();
				String username = usernameTextField.getText();
				String password = passwordField.getText();
				PreparedStatement pdstmt = con1.prepareStatement("insert into student_login values(?,?,?,?)");
				pdstmt.setString(1, name);
				pdstmt.setString(2, mail);
				pdstmt.setString(3, username);
				pdstmt.setString(4, password);
				pdstmt.executeUpdate();
				displaymsgLabel.setText("Registered successfully");
				//System.out.println("Record insert into student_login ");

				jFrame.dispose();
				new SignInWindow();
				con1.close();
			}catch (Exception e1) {
				e1.printStackTrace();
			}
			jFrame.dispose();

		}else if (e.getSource() == cancel) {
			jFrame.dispose();
			new SignInWindow();
		}
	}

	public static void main(String[] args) {
		new RegisterWindow();
	}

}
