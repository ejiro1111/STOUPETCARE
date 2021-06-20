package serviceusage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import customFont.customFont;
import PetCare.MyConnect;


public class ServInfoForm extends JFrame implements ActionListener{
	
	Connection con = MyConnect.getConnection();
	
	private DefaultTableModel modelCustomer;
	JButton mainManu;
	JLabel lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8, lb9, lb10, lb11, lb12, lb13, lb14, lb15, lb16, lb17, lb18, lb19, lb20, lb21, lb22, lb23, lb24, lb25;
	JTable tablecust;
	JPanel Searchform, Textinfo1, Textinfo2, Textinfoform, btPanel, panelTable;
	
	Color isMe = new Color(51, 153, 255);
	Color myWhite = new Color(255, 255, 255);
	
	JTextField txtSearche;
	
	public ServInfoForm() {
		UIManager.put("Table.font", customFont.normalFont);
		UIManager.put("TableHeader.font", customFont.normalFont);
		UIManager.put("Label.font", customFont.normalFont);
		UIManager.put("Button.font", customFont.normalFont);
		
		Container ct = this.getContentPane();
		ct.setLayout(new BorderLayout());

		lb1 = new JLabel("     บริการและอัตราค่าบริการ");
		lb1.setForeground(Color.black);
	/*	lb2.setFont(new Font("TimesRoman", Font.ITALIC,20));
		lb2.setFont(new Font("TimesRoman", Font.ITALIC,20));*/
		
		//panel แสดงข้อความบนสุด
		Searchform = new JPanel(new GridLayout(1, 1, 0, 0));
		Searchform.add(lb1);
		
		panelTable = new JPanel();
		JScrollPane scrolltabal = new JScrollPane();
		scrolltabal.setPreferredSize(new Dimension(600,78));
		tablecust = new JTable();
		Object data[][] = {
				{null,null,null,null},
				{null,null,null,null},
				{null,null,null,null},
				
		};
		String[] column_text = {"ประเภทสัตว์เลี้ยง","ค่าฝากต่อวัน","ค่ามัดจำ","ค่าอาหาร"};
		
		modelCustomer = new DefaultTableModel(data,column_text) 
				{
					public boolean isCellEditable(int row,int column) {
							return false;
					}
				};
				tablecust.setModel(modelCustomer);
				modelCustomer = (DefaultTableModel)tablecust.getModel();
				showData();
				
		scrolltabal.setViewportView(tablecust);
		panelTable.add(scrolltabal);	
		

		lb2 = new JLabel("*เงื่อนไขเพิ่มเติม");
		lb2.setForeground(Color.red);

		lb3 = new JLabel("       " + String.valueOf('\u00bb') + "  ลูกค้าต้องนำอาหารสัตว์เลื้ยงมาเอง ในกรณีที่ไม่นำอาหารมาหรือให้ทางร้านเป็นผู้จัดหา");
		lb4 = new JLabel("           ลูกค้าต้องชำระค่าใช่จ่ายเพิ่มเติมในอัตราเหมาวันละ 100 บาท");	
		lb5 = new JLabel("       " + String.valueOf('\u00bb') + "  ทางศูนย์ไม่รับผิดชอบต่อความเสียหายอันเกินจาก โรคประจำตัว อาการบาดเจ็บ ความสียหายที่เกิดก่อนรับบริการ");	
		lb6 = new JLabel("           หรือความเสียหายที่ไม่เกี่ยวกับการให้บริการ");
		lb3.setForeground(Color.red); lb4.setForeground(Color.red); lb5.setForeground(Color.red); lb6.setForeground(Color.red);

		lb7 = new JLabel("รูปแบบการใช้บริการ");
		lb7.setForeground(Color.black);

		lb8 = new JLabel("           ลูกค้าสามารถเข้าใช้บริการได้ 2 วิธี ดังนี้");
		lb9 = new JLabel("            " + String.valueOf('\u2022') + "  ติดต่อรับบริการแบบโดยตรง (walk in) ที่ศูนย์");
		lb10 = new JLabel("            " + String.valueOf('\u2022') + "  จองผ่านโทรศัพท์");
		lb11 = new JLabel("       ในการใช้บริการทั้ง 2 แบบนั้น ลูกค้าต้องเตรียมเอกสารสำคัญดังต่อไปนี้");
		lb12 = new JLabel("            " + String.valueOf('\u00bb') + "  สำเนาบัตรประชาชนและข้อมูลสำคัญอื่นๆ คือ เบอร์โทรศัพท์ อีเมล์");
		lb13 = new JLabel("                และข้อมูลสำหรับติดต่อในกรณีฉุกเฉิน");
		lb14 = new JLabel("            " + String.valueOf('\u00bb') + "  ใบจอง(ประกอบด้วย วันเข้า วันออก)");
		lb15 = new JLabel("            " + String.valueOf('\u00bb') + "  ข้อมูลสัตว์เลื้ยง ประกอบด้วย ประเภท ชื่อ รูปถ่าย");
		lb8.setForeground(Color.black); lb9.setForeground(Color.black); lb10.setForeground(Color.black); lb11.setForeground(Color.black);
		lb12.setForeground(Color.black); lb13.setForeground(Color.black); lb14.setForeground(Color.black); lb15.setForeground(Color.black);
		
		lb16 = new JLabel("รูปแบบการชำระค่าบริการ");
		lb16.setForeground(Color.black);

		lb17 = new JLabel("            " + String.valueOf('\u00bb') + "  ศูนย์จะส่งใบแจ้งหนี้ (invoice) 3 วันก่อนครบกำหนดวันออก (checkout)");
		lb18 = new JLabel("            " + String.valueOf('\u00bb') + "  การชำระเงินสามารถชำระเป็นเงินสดหรือบัตรเครดิต");
		lb17.setForeground(Color.black); lb18.setForeground(Color.black);

		//panel เนื้อหาส่วนที่ 1
		Textinfo1 = new JPanel(new GridLayout(9, 1, 0, 0));
		Textinfo1.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		
		Textinfo1.add(lb2); Textinfo1.add(lb3); Textinfo1.add(lb4); Textinfo1.add(lb5); Textinfo1.add(lb6); 
		Textinfo1.add(lb7); Textinfo1.add(lb8); Textinfo1.add(lb9); Textinfo1.add(lb10);
		
		
		//panel เนื้อหาส่วนที่ 2
		Textinfo2 = new JPanel(new GridLayout(8, 1, 0, 0));
		Textinfo2.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		
		Textinfo2.add(lb11); Textinfo2.add(lb12); Textinfo2.add(lb13); Textinfo2.add(lb14); Textinfo2.add(lb15);
		Textinfo2.add(lb16); Textinfo2.add(lb17); Textinfo2.add(lb18); 
		
		
		//panel เนื้อหารวม
		Textinfoform = new JPanel(new GridLayout(3, 1, 0, 0));
		
		Textinfoform.add(panelTable);
		Textinfoform.add(Textinfo1);
		Textinfoform.add(Textinfo2);
		
		
		//panel ส่วนล่าง
		btPanel = new JPanel();
		mainManu = new JButton("กลับสู่เมนูหลัก");
		//mainManu.setBackground(Color.blue);
		//mainManu.setForeground(Color.white);
		btPanel.add(mainManu);

		
		//container เก็บทุก panel แบ่งเป็น 3 ส่วน
		ct.add(Searchform,BorderLayout.PAGE_START);
		ct.add(Textinfoform, BorderLayout.CENTER); 
		ct.add(btPanel, BorderLayout.PAGE_END);
		
		mainManu.addActionListener(this);
	}
	
	public void showData() {
		try 
		{
			int totalRow = tablecust.getRowCount()-1;
			while (totalRow > -1) {
				modelCustomer.removeRow(totalRow);
				totalRow--;
			}
			String sql = "SELECT * FROM service_available";
					
			ResultSet rs = con.createStatement().executeQuery(sql);
			int row = 0 ;
			while(rs.next()) {
				modelCustomer.addRow(new Object[0]);
				modelCustomer.setValueAt(rs.getString("SERV_DESC"),  row, 0);
				modelCustomer.setValueAt(rs.getString("DAY_PRICE"), row, 1);
				modelCustomer.setValueAt(rs.getString("DEPOSIT"), row, 2);
				modelCustomer.setValueAt(rs.getString("ADD_PRICE"),row, 3);
				row++;
			}
			tablecust.setModel(modelCustomer);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ServInfoForm ServInfoForm = new ServInfoForm();
		//ชุดคำสั่ง set font รวมทั้งระบบ

		SwingUtilities.updateComponentTreeUI(ServInfoForm);
		//
		ServInfoForm.setSize(750,500);
		ServInfoForm.setLocationRelativeTo(null);
		ServInfoForm.setResizable(false);
		ServInfoForm.setTitle("ข้อมูลบริการ (ServInfoForm)");
		ServInfoForm.setDefaultCloseOperation(3);//ใส่เลข3หรือEXIT_ON_CLOSE
		ServInfoForm.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == mainManu){
			shutdown();
		}
	}
	public void shutdown() {
		this.dispose();
	}
}

	
	

