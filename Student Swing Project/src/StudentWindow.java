import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class StudentWindow implements ActionListener {

	JFrame jFrame;
	JLabel imageLabel, nameLabel, rollnoLabel, marksLabel, resultJLabel;
	JTextField nameField, rollnoField, marksField;
	JButton addStudent, deleteStudent, updateStudent, showStudent, exit;
	JTextArea resultta;

	@SuppressWarnings("static-access")
	StudentWindow(){
		jFrame = new JFrame("Student Details");

		imageLabel = new JLabel(new ImageIcon("C:\\Users\\Admin\\Desktop\\classroom.jpg"));
		nameLabel = new JLabel("Enter your name : ");
		nameField = new JTextField();
		rollnoLabel = new JLabel("Enter your roll no : ");
		rollnoField = new JTextField();
		marksLabel = new JLabel("Enter your marks : ");
		marksField = new JTextField();
		addStudent = new JButton("Add Student");
		deleteStudent = new JButton("Delete Student ");
		updateStudent = new JButton("Update Student Details");
		showStudent = new JButton("Show all Students");
		exit = new JButton("Exit");
		resultJLabel = new JLabel();
		resultta = new JTextArea();

		imageLabel.setBounds(80,15,317,220);
		nameLabel.setBounds(60,260,150,30);
		nameField.setBounds(190,260,150,30);
		rollnoLabel.setBounds(60, 320,150,30);
		rollnoField.setBounds(190, 320,150,30);
		marksLabel.setBounds(60,380 ,150,30);
		marksField.setBounds(190, 380,150,30);
		addStudent.setBounds( 30, 440,130,30);
		deleteStudent.setBounds( 180, 440,130,30);
		showStudent.setBounds( 330, 440,150,30);
		updateStudent.setBounds( 70, 490,200,30);
		exit.setBounds( 290, 490,100,30);
		resultJLabel.setBounds(100,540,300,100);
		resultta.setBounds(100,540,300,150);

		addStudent.addActionListener(this);
		deleteStudent.addActionListener(this);
		updateStudent.addActionListener(this);
		showStudent.addActionListener(this);
		exit.addActionListener(this);

		jFrame.add(imageLabel);
		jFrame.add(nameLabel);
		jFrame.add(nameField);
		jFrame.add(rollnoLabel);
		jFrame.add(rollnoField);
		jFrame.add(marksLabel);
		jFrame.add(marksField);
		jFrame.add(addStudent);
		jFrame.add(deleteStudent);
		jFrame.add(updateStudent);
		jFrame.add(showStudent);
		jFrame.add(exit);
		jFrame.add(resultJLabel);
		jFrame.add(resultta);

		jFrame.getContentPane().setBackground(Color.orange);
		jFrame.setLayout(null);
		jFrame.setSize(550,750);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new StudentWindow();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addStudent) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample", "root", "root");
				int rol = Integer.parseInt(rollnoField.getText());
				String name = nameField.getText();
				int mark = Integer.parseInt(marksField.getText());

				PreparedStatement pdstmt = con2.prepareStatement("insert into student_info values(?,?,?)");
				pdstmt.setInt(2, rol);
				pdstmt.setString(1, name);
				pdstmt.setInt(3, mark);
				pdstmt.executeUpdate();
				con2.close();
				resultJLabel.setText("Record Inserted");
			}catch (Exception e1) {
				e1.printStackTrace();
			}
		}else if (e.getSource() == deleteStudent) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample", "root", "root");
				int rol = Integer.parseInt(rollnoField.getText());

				PreparedStatement pdstmt = con2.prepareStatement("delete from student_info where rollno = ?");
				pdstmt.setInt(1, rol);
				pdstmt.executeUpdate();
				con2.close();
				resultJLabel.setText("Record Deleted");
			}catch (Exception e1) {
				e1.printStackTrace();
			}
		}else if (e.getSource() == updateStudent) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample", "root", "root");
				int rol = Integer.parseInt(rollnoField.getText());
				String name = nameField.getText();
				int mark = Integer.parseInt(marksField.getText());

				PreparedStatement pdstmt = con2.prepareStatement("update student_info set name = ?, marks = ? where rollno = ?");
				pdstmt.setInt(3, rol);
				pdstmt.setString(1, name);
				pdstmt.setInt(2, mark);
				pdstmt.executeUpdate();
				con2.close();
				resultJLabel.setText("Record Updated");
			}catch (Exception e1) {
				e1.printStackTrace();
			}
		}else if (e.getSource() == showStudent) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample", "root", "root");
				PreparedStatement pdstmt = con2.prepareStatement("select * from student_info");

				ResultSet resultSet =  pdstmt.executeQuery();
				String all=" ";
				while(resultSet.next()) {
					int rol = resultSet.getInt(2);
					String name = resultSet.getString(1);
					int mark = resultSet.getInt(3);

					String record = rol + " " + name + " " + mark;
					all = all +"\n"+ record;
					resultta.setText(all);
				}
				con2.close();
			}catch (Exception e1) {
				System.out.println(e1);
			}
		}else if (e.getSource() == exit) {
			System.exit(0);
		}
	}
}


/* 
CREATE TABLE student_info(
name VARCHAR(30),
rollno INT PRIMARY KEY,
marks INT
);
 */
