package PetCare;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import customFont.customFont;

public class WelcomeWindow extends JFrame {
	
	ImageIcon welimg;
	JLabel well;
	JPanel welup, welldown;
	JButton welb;
	Container welc;
	
	WelcomeWindow(){
		setSize(450,380);
		setTitle("ยินดีต้อนรับสู่Petcare");
		welc = this.getContentPane();
		welc.setLayout(new BorderLayout());
		welup = new JPanel();
		welup.setLayout(new GridLayout(1,1));
		welup.setMaximumSize(new Dimension(300, 300));
		welimg = new ImageIcon(getClass().getResource("icon2.png"));
		well = new JLabel(welimg);
		welup.add(well);
		welldown = new JPanel();
		welldown.setLayout(new GridLayout(1,1));
		welldown.setMaximumSize(new Dimension(100, 30));
		welb = new JButton("เข้าสู่ระบบ Petcare");
		welb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				LoginWindows f2 = new LoginWindows();
				setVisible(false);
			}
		});
		welldown.add(welb);
		welc.add(welup,BorderLayout.NORTH);
		welc.add(welldown,BorderLayout.SOUTH);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		try {
			MyConnect.getConnection();
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		UIManager.put("OptionPane.messageFont", customFont.normalFont);
		WelcomeWindow welf = new WelcomeWindow();
		welf.setLocationRelativeTo(null);
		
	}
	
}