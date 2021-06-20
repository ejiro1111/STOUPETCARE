package menuwindow;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;



import ManagementForm.CameraForm;
import ManagementForm.CustomerForm;
import ManagementForm.EmployeeForm;
import ManagementForm.InvoiceForm;
import ManagementForm.PetForm;
import ManagementForm.ReserveOrderForm;
import ManagementForm.ServAvailForm;
import ManagementForm.ServProForm;
import PetCare.LoginWindows;

public class ManagementMenu  extends JFrame{
	
	JPanel mainPanel,menuPanel,bannerPanel;
	JLabel lbMainMenu;
	JButton btnBack,btnExit, btnCS,btnPet,btnServAvail,btnReservOrder,btnServPro,btnInv,btnEmp,btnCAM,btnRep;
	
	Font fn = new Font("tahoma", Font.PLAIN, 12);

	
	
	public ManagementMenu() {
		UIManager.put("Button.font", fn);

		
		// set frame
			setTitle(" Management Menu");
				setSize(1000, 600);
				setLocationRelativeTo(null);
				setDefaultCloseOperation(EXIT_ON_CLOSE);
				setVisible(true);
		
		// main panel	
				mainPanel = new JPanel();
				mainPanel.setLayout(new BorderLayout());
				mainPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.cyan.brighter(), Color.BLUE));
				add(mainPanel);
				

	// menu panel
				menuPanel = new JPanel();
				menuPanel.setLayout(null);
				menuPanel.setBackground(Color.WHITE);
				menuPanel.setPreferredSize(new Dimension(800, 550));
				
				
	
/*---------------- Row 1-----------------------*/
				
	// customer  button
				
				ImageIcon icCS= new ImageIcon(getClass().getResource("image/customers.png"));		
				btnCS=new JButton("Customer",icCS);
				btnCS.setPreferredSize(new Dimension(150,150));
				btnCS.setVerticalTextPosition(JButton.BOTTOM);
				btnCS.setHorizontalTextPosition(JButton.CENTER);
				btnCS.setBorder(null);
				btnCS.setBackground(Color.white);
				btnCS.setBounds(200, 100, 120, 120);
				menuPanel.add(btnCS);
				
	
				
// service available			
				
				ImageIcon icServAvail	= new ImageIcon(getClass().getResource("image/service.png"));		
				btnServAvail=new JButton("Service",icServAvail);
				btnServAvail.setPreferredSize(new Dimension(150,150));
				btnServAvail.setVerticalTextPosition(JButton.BOTTOM);
				btnServAvail.setHorizontalTextPosition(JButton.CENTER);
				btnServAvail.setBorder(null);
				btnServAvail.setBackground(Color.white);
				btnServAvail.setBounds(350, 100, 120, 120);
				menuPanel.add(btnServAvail); 
				
	
// reserved order
				ImageIcon icReservOrder	= new ImageIcon(getClass().getResource("image/reservOrder.png"));
				btnReservOrder=new JButton("Order",icReservOrder);
				btnReservOrder.setPreferredSize(new Dimension(150,150));
				btnReservOrder.setVerticalTextPosition(JButton.BOTTOM);
				btnReservOrder.setHorizontalTextPosition(JButton.CENTER);
				btnReservOrder.setBorder(null);
				btnReservOrder.setBackground(Color.white);
				btnReservOrder.setBounds(500, 100, 120, 120);
				menuPanel.add(btnReservOrder);

				
// employee
				ImageIcon icEmp	= new ImageIcon(getClass().getResource("image/employee.png"));		
				btnEmp=new JButton("Employee",icEmp);
				btnEmp.setPreferredSize(new Dimension(150,150));
				btnEmp.setVerticalTextPosition(JButton.BOTTOM);
				btnEmp.setHorizontalTextPosition(JButton.CENTER);
				btnEmp.setBorder(null);
				btnEmp.setBackground(Color.white);
				btnEmp.setBounds(650, 100, 120, 120);
				menuPanel.add(btnEmp);
				
/*---------------- Row 2-----------------------*/		
	
	// pet
				ImageIcon icPet	= new ImageIcon(getClass().getResource("image/Pet.png"));		
				btnPet=new JButton("Pet",icPet);
				btnPet.setPreferredSize(new Dimension(150,150));
				btnPet.setVerticalTextPosition(JButton.BOTTOM);
				btnPet.setHorizontalTextPosition(JButton.CENTER);
				btnPet.setBorder(null);
				btnPet.setBackground(Color.white);
				btnPet.setBounds(200, 240, 150, 150);
				menuPanel.add(btnPet);
				
			
	// ServPro button
				ImageIcon icServPro	= new ImageIcon(getClass().getResource("image/servicePro.png"));		
				btnServPro=new JButton("Service Provided",icServPro);
				 btnServPro.setPreferredSize(new Dimension(150,150));
				 btnServPro.setVerticalTextPosition(JButton.BOTTOM);
				 btnServPro.setHorizontalTextPosition(JButton.CENTER);
				 btnServPro.setBorder(null);
				 btnServPro.setBackground(Color.white);
				 btnServPro.setBounds(350, 250, 120, 120);
				menuPanel.add( btnServPro);
				
				
	// invoice available		
				
				ImageIcon icInvoice	= new ImageIcon(getClass().getResource("image/invoice.png"));		
				btnInv=new JButton("Invoice",icInvoice);
				btnInv.setPreferredSize(new Dimension(150,150));
				btnInv.setVerticalTextPosition(JButton.BOTTOM);
				btnInv.setHorizontalTextPosition(JButton.CENTER);
				btnInv.setBorder(null);
				btnInv.setBackground(Color.white);
				btnInv.setBounds(500, 250, 120, 120);
				menuPanel.add(btnInv);
			
	
	// camera order			
				ImageIcon icCAM	= new ImageIcon(getClass().getResource("image/camera.png"));		
				btnCAM=new JButton("Camera",icCAM);
				btnCAM.setPreferredSize(new Dimension(150,150));
				btnCAM.setVerticalTextPosition(JButton.BOTTOM);
				btnCAM.setHorizontalTextPosition(JButton.CENTER);
				btnCAM.setBorder(null);
				btnCAM.setBackground(Color.white);
				btnCAM.setBounds(650, 250, 120, 120);
				menuPanel.add(btnCAM);
		
				// Report		
				ImageIcon icREPORT	= new ImageIcon(getClass().getResource("image/Report.png"));		
				btnRep=new JButton("REPORT",icREPORT);
				btnRep.setVerticalTextPosition(JButton.BOTTOM);
				btnRep.setHorizontalTextPosition(JButton.CENTER);
				btnRep.setBorder(null);
				btnRep.setBackground(Color.white);
				btnRep.setBounds(300, 392, 120, 140);
				menuPanel.add(btnRep);		
				
		// command		
				ImageIcon icExitProgram= new ImageIcon(getClass().getResource("image/exit.png"));		
				btnExit =new JButton("Exit Program",icExitProgram);
				btnExit.setPreferredSize(new Dimension(150,150));
				btnExit .setVerticalTextPosition(JButton.BOTTOM);
				btnExit .setHorizontalTextPosition(JButton.CENTER);
				btnExit .setBorder(null);
				btnExit .setBackground(Color.white);
				btnExit .setBounds(520,400,120,120);
				mainPanel.add(btnExit);
				
//				btnBack= new JButton(" Back ");
//				btnBack.setFont(fn);
//				btnBack.setPreferredSize(new Dimension(200, 25));
//				btnBack.setBackground(Color.BLUE.darker());	
//				btnBack.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
//				btnBack.setForeground(Color.WHITE);
//				btnBack.setBounds(360, 400, 120, 30);
//				menuPanel.add( btnBack);
				
				
//				btnExit = new JButton(" Exit ");
//				btnExit.setFont(fn);
//				btnExit.setPreferredSize(new Dimension(200, 25));
//				btnExit.setBackground(Color.BLUE.darker());	
//				btnExit.setForeground(Color.WHITE);
//				btnExit.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
//				btnExit.setBounds(510, 400, 120, 30);
//				menuPanel.add( btnExit);
				
				
				
				
				btnCS.addActionListener(new ButtonListener());
				btnServAvail.addActionListener(new ButtonListener());
				btnReservOrder.addActionListener(new ButtonListener());
				btnEmp.addActionListener(new ButtonListener());
				
				btnPet.addActionListener(new ButtonListener());
				btnServPro.addActionListener(new ButtonListener());
				btnInv.addActionListener(new ButtonListener());
				btnCAM.addActionListener(new ButtonListener());
				btnRep.addActionListener(new ButtonListener());
//				btnBack.addActionListener(new ButtonListener());
				btnExit.addActionListener(new ButtonListener());

				mainPanel.add(menuPanel,BorderLayout.CENTER);
		
}
	
	private class ButtonListener implements ActionListener {
		private Object ServButton;

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == btnCS) {
				CustomerForm frame = new CustomerForm();
				frame.setSize(900,600);
				frame.setTitle("หน้าจอข้อมูลลูกค้า (CustomerForm)");	
				frame.setLocationRelativeTo(null);  
				frame.setVisible(true);	
				setVisible(false);
				
		}else if(e.getSource() == btnPet){
				PetForm frame = new PetForm();
				frame.setSize(950, 650);
				frame.setTitle("หน้าจอข้อมูลสัตว์เลี้ยง (PetForm)");	
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);	
				setVisible(false);
				
		}else if(e.getSource() == btnCAM){
				CameraForm frame = new CameraForm();
				frame.setSize(900,600);
				frame.setTitle("หน้าจอข้อมูลกล้องวงจรปิด (CameraForm)");	
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);		
				setVisible(false);
				
		}else if(e.getSource() == btnReservOrder){
				ReserveOrderForm ReserveOrderFormFrame = new ReserveOrderForm();
				ReserveOrderFormFrame.setSize(800,600);
				ReserveOrderFormFrame.setTitle("หน้าจอข้อมูลการจอง (ReserveOrderForm)");
				ReserveOrderFormFrame.setLocationRelativeTo(null);
				ReserveOrderFormFrame.setVisible(true);
				setVisible(false);
				
			}else if(e.getSource() == btnServAvail){
				ServAvailForm ServAvailFormFrame = new ServAvailForm();
				ServAvailFormFrame.setSize(800,600);
				ServAvailFormFrame.setTitle("หน้าจอข้อมูลบริการที่เปิดดำเนินการ (ServiceAvailableForm)");
				ServAvailFormFrame.setLocationRelativeTo(null);
				ServAvailFormFrame.setVisible(true);
				setVisible(false);
				
			}else if(e.getSource() == btnServPro){
				ServProForm ServProFormFrame = new ServProForm();
				ServProFormFrame.setSize(800,600);
				ServProFormFrame.setTitle("หน้าจอข้อมูลการให้บริการ (ServiceProForm)");
				ServProFormFrame.setLocationRelativeTo(null);
				ServProFormFrame.setVisible(true);
				setVisible(false);
	
			}else if(e.getSource() == btnInv){
				InvoiceForm frame = new InvoiceForm();
				frame.setSize(900,600);
				frame.setTitle("หน้าจอข้อมูลใบแจ้งหนี้ (InvoiceForm)");	
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);	 
				setVisible(false);
				
			}else if(e.getSource() == btnEmp){				
				EmployeeForm EmployeeFormFrame = new EmployeeForm();
				EmployeeFormFrame.setSize(800,600);
				EmployeeFormFrame.setTitle("หน้าจอข้อมูลพนักงาน (EmployeeForm)");
				EmployeeFormFrame.setLocationRelativeTo(null);
				EmployeeFormFrame.setVisible(true);
				setVisible(false);
				
			}else if(e.getSource() == btnRep){				
				ReportMenu Frame = new ReportMenu();
				Frame.setSize(700, 400);
				Frame.setTitle("หน้าจอรายงานข้อมูล (RepornMenu)");
				Frame.setLocationRelativeTo(null);
				Frame.setVisible(true);
				setVisible(false);
				
			}if (e.getSource() == btnExit) {
				System.exit(0);
			} // end exit button
		
		}
	}// end button listener method
		
	public static void main(String[] args) 
	{
		ManagementMenu frame = new ManagementMenu();
		frame.setSize(1000,600);
		frame.setTitle("ManagementMenu");	
		frame.setLocationRelativeTo(null);  
		frame.setResizable(false);
		frame.setVisible(true);		
	}
}// end page
