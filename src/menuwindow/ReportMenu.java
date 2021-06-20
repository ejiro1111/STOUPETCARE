package menuwindow;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import report.CustomerReport;
import report.FinancialReport;
import report.OperationReport;

public class ReportMenu extends JFrame {

	private JPanel mainPanel, menuPanel, cmdPanel;
	private JButton btnCustomer, btnFinance, btnOperation, btnBack, btnExit;
	Font fn1 = new Font("Tahoma", Font.PLAIN, 12);

	public ReportMenu() {
		// set frame
		setTitle(" รายงาน ");
		setSize(700, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		// main panel
		mainPanel = new JPanel();
	
		mainPanel.setPreferredSize(new Dimension(700, 400));
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(Color.white);
		mainPanel.setBorder(null);
		add(mainPanel);

		// menu panel
		menuPanel = new JPanel();
		menuPanel.setLayout(null);
		menuPanel.setBackground(Color.white);
		menuPanel.setPreferredSize(new Dimension(700, 400));


		// customer button

		ImageIcon icCS = new ImageIcon(getClass().getResource("image/Customer.png"));
		btnCustomer = new JButton("รายงานข้อมูลลูกค้า", icCS);
		btnCustomer.setFont(fn1);
		btnCustomer.setVerticalTextPosition(JButton.BOTTOM);
		btnCustomer.setHorizontalTextPosition(JButton.CENTER);
		btnCustomer.setBorder(null);
		btnCustomer.setBackground(Color.white);
		btnCustomer.addActionListener(e -> {
			
			CustomerReport cr = new CustomerReport();
			cr.setTitle("รายงานข้อมูลลูกค้า (CustomerReport)");
			cr.setSize(900, 500);
			cr.setLocationRelativeTo(null);
			cr.setResizable(false);
			this.setVisible(false);
			cr.setVisible(true);
			cr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		});
		
		btnCustomer.setBounds(100, 100, 150, 100);
		
		menuPanel.add(btnCustomer);
		menuPanel.add(btnCustomer);

		// Finance

		ImageIcon icFi= new ImageIcon(getClass().getResource("image/Financial.png"));
		btnFinance = new JButton("รายงานทางการเงิน", icFi);
		btnFinance.setFont(fn1);
		btnFinance.setVerticalTextPosition(JButton.BOTTOM);
		btnFinance.setHorizontalTextPosition(JButton.CENTER);
		btnFinance.setBorder(null);
		btnFinance.setBackground(Color.white);
		btnFinance.addActionListener(e -> {
			
			FinancialReport fr = new FinancialReport();
			fr.setTitle("รายงานข้อมูลทางการเงิน (FinancialReport)");
			fr.setSize(800, 750);
			fr.setLocationRelativeTo(null);
			fr.setResizable(false);
			this.setVisible(false);
			fr.setVisible(true);
			fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		});
		btnFinance.setBounds(270, 100, 150, 100);
		menuPanel.add(btnFinance);

		// Operation
		ImageIcon icOp = new ImageIcon(getClass().getResource("image/Operation.png"));
		btnOperation = new JButton("รายงานผลการดำเนินงาน", icOp);
		btnOperation.setFont(fn1);
		btnOperation.setVerticalTextPosition(JButton.BOTTOM);
		btnOperation.setHorizontalTextPosition(JButton.CENTER);
		btnOperation.setBorder(null);
		btnOperation.setBackground(Color.white);
		btnOperation.addActionListener(e -> {
			
			OperationReport or = new OperationReport();
			or.setTitle("รายงานข้อมูลทางการเงิน (FinancialReport)");
			or.setSize(800, 720);
			or.setLocationRelativeTo(null);
			or.setResizable(false);
			this.setVisible(false);
			or.setVisible(true);
			or.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		});
		btnOperation.setBounds(440, 100, 150, 100);
		menuPanel.add(btnOperation);

		// command

		btnBack = new JButton(" กลับสู่เมนูก่อนหน้า ");
		btnBack.setFont(fn1);
		//btnBack.setBackground(Color.BLUE.darker());
		btnBack.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		//btnBack.setForeground(Color.WHITE);
		btnBack.setBounds(170, 250, 150, 40);
		menuPanel.add(btnBack);
		btnBack.addActionListener(e -> {
		ManagementMenu mm = new ManagementMenu();
		mm.setVisible(true);
		this.setVisible(false);
		});

		btnExit = new JButton(" ออกจากโปรแกรม ");
		btnExit.setFont(fn1);
		/*btnExit.setBackground(Color.red);
		btnExit.setForeground(Color.WHITE);*/
		btnExit.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btnExit.setBounds(370, 250, 150, 40);
		btnExit.addActionListener(e -> {
			System.exit(0);
		});
		menuPanel.add(btnExit);

		mainPanel.add(menuPanel);
	}

}
