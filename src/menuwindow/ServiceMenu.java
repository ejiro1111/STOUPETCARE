package menuwindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import ManagementForm.CustomerForm;
import serviceusage.ServInfoForm;
//import model.usedclass.AddPanel;
//import serviceUsage.ServiceInfo;



public class ServiceMenu extends JFrame {
	
			
	JPanel mainPanel;
	JButton btnCheckService,btnViewPet,btnExit;
	
	public ServiceMenu() {
		
//		setTitle(" User Menu");
//		setSize(750, 550);
//		setLocationRelativeTo(null);
//		setDefaultCloseOperation(3);
//		setVisible(true);
		
// main panel	
		
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.cyan.brighter(), Color.BLUE));
		mainPanel.setPreferredSize(new Dimension(750, 550));
		add(mainPanel);
		
// service available			
		
		ImageIcon icBanner= new ImageIcon(getClass().getResource("image/logoBanner.JPEG"));		
		JLabel lbBanner= new JLabel(icBanner);

		 lbBanner.setPreferredSize(new Dimension(150,150));
		 lbBanner.setVerticalTextPosition(JButton.BOTTOM);
		 lbBanner.setHorizontalTextPosition(JButton.CENTER);
		 lbBanner.setBorder(null);
		 lbBanner.setBackground(Color.white);
		 lbBanner.setBounds(150, 10, 400, 150);
		mainPanel.add( lbBanner);
		
		
		ImageIcon icServAvail	= new ImageIcon(getClass().getResource("image/service2.png"));		
		btnCheckService=new JButton("Check service",icServAvail);
		btnCheckService.setPreferredSize(new Dimension(150,150));
		btnCheckService.setVerticalTextPosition(JButton.BOTTOM);
		btnCheckService.setHorizontalTextPosition(JButton.CENTER);
		btnCheckService.setBorder(null);
		btnCheckService.setBackground(Color.white);
		btnCheckService.setBounds(200, 200, 120, 120);
		mainPanel.add(btnCheckService);
		
		
		
		ImageIcon icViewPet= new ImageIcon(getClass().getResource("image/Camera2.png"));		
		btnViewPet=new JButton("View pet",icViewPet);
		btnViewPet.setPreferredSize(new Dimension(150,150));
		btnViewPet.setVerticalTextPosition(JButton.BOTTOM);
		btnViewPet.setHorizontalTextPosition(JButton.CENTER);
		btnViewPet.setBorder(null);
		btnViewPet.setBackground(Color.white);
		btnViewPet.setBounds(400, 200, 120, 120);
		mainPanel.add(btnViewPet);
		
		
		
		ImageIcon icExitProgram= new ImageIcon(getClass().getResource("image/exit.png"));		
		btnExit =new JButton("Exit Program",icExitProgram);
		btnExit.setPreferredSize(new Dimension(150,150));
		btnExit .setVerticalTextPosition(JButton.BOTTOM);
		btnExit .setHorizontalTextPosition(JButton.CENTER);
		btnExit .setBorder(null);
		btnExit .setBackground(Color.white);
		btnExit .setBounds(300, 350,120, 120);
		mainPanel.add(btnExit );
		
	

		btnCheckService.addActionListener(new ButtonListener());
		btnViewPet.addActionListener(new ButtonListener());
		btnExit.addActionListener(new ButtonListener());
		
		
		
	}
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnCheckService) {
				ServInfoForm frame = new ServInfoForm();
				frame.setSize(900,600);
				frame.setTitle("ข้อมูลบริการ (ServInfoForm)");	
				frame.setLocationRelativeTo(null);  
				frame.setVisible(true);	
				
		}
			
			if (e.getSource() == btnCheckService) {
//				ServiceInfo servInfo = new ServiceInfo ();
//				servInfo.setVisible(true);
//				setVisible(false);
						
			} // end Check Service button
	
			
			if (e.getSource() == btnViewPet) {

				
			} // end See pet button
			if (e.getSource() == btnExit) {
								System.exit(0);
			} // end exit button
		
		}
		}// end button listener method
	public static void main(String[] args) 
	{
		ServiceMenu frame = new ServiceMenu();
		frame.setSize(750,550);
		frame.setTitle("ServiceMenu");	
		frame.setLocationRelativeTo(null);  
		frame.setResizable(false);
		frame.setVisible(true);		
	}
	
	
}// end page