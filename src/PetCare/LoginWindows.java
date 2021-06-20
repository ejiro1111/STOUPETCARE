package PetCare;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import menuwindow.ManagementMenu;
import menuwindow.ServiceMenu;

public class LoginWindows extends JFrame {

	ImageIcon logimg;
	JLabel logmain, loglimg, logi, logp;
	JPanel logn, logw, logc, logs;
	JTextField login, pass;
	JButton logb;
	Font l1 = new Font("tahoma", Font.BOLD, 16);
	Font l2 = new Font("tahoma", Font.PLAIN, 12);
	Container logcon;
	

	LoginWindows() {
		setSize(400, 350);
		setTitle("STOU Pet Care login");
		logcon = this.getContentPane();
		logcon.setLayout(new BorderLayout());
		logn = new JPanel();
		logn.setLayout(new GridLayout(2, 1));
		logn.setMaximumSize(new Dimension(500, 150));
		logmain = new JLabel("บริษัท Stou Pet care จำกัด", SwingConstants.CENTER);
		logmain.setFont(l1);
		logn.add(logmain);
		logimg = new ImageIcon(getClass().getResource("avatar.png"));
		loglimg = new JLabel(logimg);
		logn.add(loglimg);
		logw = new JPanel();
		logw.setLayout(new GridLayout(2, 1));
		logi = new JLabel("Username");
		logi.setFont(l2);
		logw.add(logi);
		logp = new JLabel("Password");
		logp.setFont(l2);
		logw.add(logp);
		logc = new JPanel();
		login = new JTextField("");
		login.setPreferredSize(new Dimension(200, 30));
		logc.add(login);
		pass = new JPasswordField("");
		pass.setPreferredSize(new Dimension(200, 30));
		logc.add(pass);
		logs = new JPanel();
		logb = new JButton("Login");
		logb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String puname = login.getText();
				String ppaswd = pass.getText();
				String em_name = MyConnect.login_Employee(puname, ppaswd);
				String cs_name = MyConnect.login_Customer(puname, ppaswd);
				if (!em_name.isEmpty()) {
					JOptionPane.showMessageDialog(null, "สวัสดี " + em_name + " เข้าสู่ระบบ");
					dispose();
					ManagementMenu addManagementMenu = new ManagementMenu();
					addManagementMenu.setSize(1000,600);
					addManagementMenu.setTitle("เมนูลูกค้า : ธุรกิจดูแลสัตว์เลี้ยง");
					addManagementMenu.setLocationRelativeTo(null);
					addManagementMenu.setVisible(true); 
				} else if (!cs_name.isEmpty()) {
					JOptionPane.showMessageDialog(null, "ยินดีต้อนรับคุณ " + cs_name + " เข้าสู่ระบบ");
					dispose();
					ServiceMenu addServiceMenu = new ServiceMenu();
					addServiceMenu.setSize(750,550);
					addServiceMenu.setTitle("เมนูหลักเจ้าหน้าที่ศูนย์:ธุรกิจดูแลสัตว์เลี้ยง");
					addServiceMenu.setLocationRelativeTo(null);
					addServiceMenu.setVisible(true);
				} else if (puname.equals("") && ppaswd.equals("")) {
					JOptionPane.showMessageDialog ( null, "Please insert Username and Password.", "Information Alert ", JOptionPane.ERROR_MESSAGE); 
				} else {
					JOptionPane.showMessageDialog ( null, "Username / Password incorrect.", "Information Alert", JOptionPane.ERROR_MESSAGE);
					login.setText("");
					pass.setText("");
					login.requestFocus();
				}
			}
		});
		logs.add(logb);
		logcon.add(logn, BorderLayout.NORTH);
		logcon.add(logw, BorderLayout.WEST, SwingConstants.CENTER);
		logcon.add(logc, BorderLayout.CENTER);
		logcon.add(logs, BorderLayout.SOUTH);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
