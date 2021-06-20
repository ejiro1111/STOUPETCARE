package ManagementForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.*;
import customFont.customFont;
import menuwindow.ManagementMenu;
import PetCare.MyConnect;

public class CustomerForm extends JFrame 
{
	Connection con = MyConnect.getConnection();
	private DefaultTableModel modelCustomer;
	JTextField txtCS_ID, txtCS_NAME, txtCARD_NUM, txtCS_ADDR,txtCS_PHONE, txtCS_MAIL,txtURGT_CONT, txtSearch;
	JTable tableCust;
	Font fn1 = new Font("Tahoma",Font.BOLD ,12);
	
	public CustomerForm()
	{
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		
		JPanel panelLeft = new JPanel();
		panelLeft.setLayout(new GridLayout(7,2,0,10));
		panelLeft.setBorder(BorderFactory.createEmptyBorder(10,0,0,100));
		panelLeft.setPreferredSize(new Dimension(500,260));
		
		JLabel lbCS_ID  = new JLabel("หมายเลขลูกค้า :");               
		lbCS_ID.setFont(customFont.normalFont);
		panelLeft.add(lbCS_ID);
		txtCS_ID = new JTextField();																	//CS_ID
		panelLeft.add(txtCS_ID);
		
		JLabel lbCS_NAME = new JLabel("ชื่อ-นามสกุล :");           
		lbCS_NAME.setFont(customFont.normalFont);
		panelLeft.add(lbCS_NAME);
		txtCS_NAME = new JTextField();
		txtCS_NAME.setFont(customFont.normalFont);														//CS_NAME
		panelLeft.add(txtCS_NAME);
		
		JLabel lbCARD_NUM = new JLabel("หมายเลขบัตรประชาชน :");     
		lbCARD_NUM.setFont(customFont.normalFont);
		panelLeft.add(lbCARD_NUM);
		txtCARD_NUM = new JTextField();
		panelLeft.add(txtCARD_NUM);																	//CARD_NUM
		
		JLabel lbCS_ADDR = new JLabel("ที่อยู่ :");                        
		lbCS_ADDR.setFont(customFont.normalFont);
		panelLeft.add(lbCS_ADDR);
		txtCS_ADDR = new JTextField();
		txtCS_ADDR.setFont(customFont.normalFont);	//CS_ADDR
		panelLeft.add(txtCS_ADDR);
		
		JLabel lbCS_PHONE = new JLabel("โทรศัพท์ :");                
		lbCS_PHONE.setFont(customFont.normalFont);
		panelLeft.add(lbCS_PHONE);
		
		txtCS_PHONE = new JTextField();															//CS_PHONE
		panelLeft.add(txtCS_PHONE);
		
		JLabel lbCS_MAIL = new JLabel("อีเมล :");                           
		lbCS_MAIL.setFont(customFont.normalFont);
		panelLeft.add(lbCS_MAIL);
		txtCS_MAIL = new JTextField();																//CS_MAIL
		panelLeft.add(txtCS_MAIL);
		
		JLabel lbURGT_CONT = new JLabel("ข้อมูลติดต่อฉุกเฉิน :");           
		lbURGT_CONT.setFont(customFont.normalFont);
		panelLeft.add(lbURGT_CONT);
		txtURGT_CONT = new JTextField(); 																//URGT_CONT
		panelLeft.add(txtURGT_CONT);
	
		JPanel panelRight = new JPanel();
		panelRight.setLayout(new GridLayout(5,1,0,10));
		panelRight.setPreferredSize(new Dimension(180,200));
		panelRight.setBorder(BorderFactory.createTitledBorder(null,"ค้นหา",0,0,fn1));//*****font Titleborder*****
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent keyevent) 
			{
				showData();
			}
		});	
		panelRight.add(txtSearch);
				
		JPanel panelNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelNorth.setBorder(BorderFactory.createEmptyBorder(20,50,5,50)); // ระยะห่างจากขอบ	
		panelNorth.add(panelLeft);
		panelNorth.add(panelRight);
		
		JButton addBtn = new JButton("เพิ่มข้อมูล");
		addBtn.setFont(fn1);	
		addBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				insert();
			}
		});
		panelRight.add(addBtn);
		JButton editBtn = new JButton("แก้ไขข้อมูล");
		editBtn.setFont(fn1);
		editBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				update();
			}
		});
		panelRight.add(editBtn);
		
		JButton delBtn = new JButton("ลบข้อมูล");
		delBtn.setFont(fn1);
		delBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				delete();
			}
		});
		panelRight.add(delBtn);
		
		JButton backBtn = new JButton("กลับสู่เมนูหลัก");
		backBtn.setFont(fn1);
		backBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ManagementMenu mm = new ManagementMenu();
				mm.setVisible(true);
				setVisible(false);
			}
		});
		panelRight.add(backBtn);
	
		JPanel panelTable = new JPanel();
		JScrollPane scrollTable = new JScrollPane();
		scrollTable.setPreferredSize(new Dimension(800,250));
		tableCust = new JTable();
		tableCust.setFont(fn1);
		Object data[][]= 
			{
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
		};
		String column[]= {"หมายเลขลูกค้า","ชื่อ- นามสกุล","บัตรประชาชน","ที่อยู่","โทรศัพท์","อีเมล","ติดต่อฉุกเฉิน"};
		JTableHeader header = tableCust.getTableHeader();  //getHeader
		header.setFont(fn1);  //SetFontHeader
		
		DefaultTableModel tablemodel = new DefaultTableModel(data,column)
				{
					public boolean isCellEditable(int row,int column) {
							return false;
					}
				};	
				tableCust.setModel(tablemodel);
				tableCust.addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent me)
					{
						int index = tableCust.getSelectedRow();
						txtCS_ID.setEditable(false);
						txtCS_ID.setText(tableCust.getValueAt(index, 0).toString());
						txtCS_NAME.setText(tableCust.getValueAt(index, 1).toString());
						txtCARD_NUM.setText(tableCust.getValueAt(index, 2).toString());
						txtCS_ADDR.setText(tableCust.getValueAt(index, 3).toString());
						txtCS_PHONE.setText(tableCust.getValueAt(index, 4).toString());
						txtCS_MAIL.setText(tableCust.getValueAt(index, 5).toString());
						txtURGT_CONT.setText(tableCust.getValueAt(index, 6).toString());
						
					}
				});
				
				scrollTable.setViewportView(tableCust);
				panelTable.add(scrollTable);							
		c.add(panelNorth,BorderLayout.PAGE_START);
		c.add(panelTable,BorderLayout.PAGE_END);
		modelCustomer = (DefaultTableModel)tableCust.getModel();
		showData();
	}
	
	public void showData() {
		try {
			int totalRow = tableCust.getRowCount() - 1;
			while (totalRow > -1) {
				modelCustomer.removeRow(totalRow);
				totalRow--;
			}
			String search = txtSearch.getText().trim();
			String sql = "SELECT * FROM customer"
			+" WHERE CS_ID LIKE '%" + search + "%'"
			+" OR CS_NAME LIKE '%" + search + "%'"
			+" OR CARD_NUM LIKE '%" + search + "%'"
			+" OR CS_ADDR LIKE '%" + search + "%'"
			+" OR CS_PHONE LIKE '%" + search + "%'"
			+" OR CS_MAIL LIKE '%" + search + "%'"
			+" OR URGT_CONT LIKE '%" + search + "%'";

			ResultSet rs = con.createStatement().executeQuery(sql);
			int row = 0;
			while (rs.next()) {
				modelCustomer.addRow(new Object[0]);
				modelCustomer.setValueAt(rs.getString("CS_ID"), row, 0);
				modelCustomer.setValueAt(rs.getString("CS_NAME"), row, 1);
				modelCustomer.setValueAt(rs.getString("CARD_NUM"), row, 2);
				modelCustomer.setValueAt(rs.getString("CS_ADDR"), row, 3);
				modelCustomer.setValueAt(rs.getString("CS_PHONE"), row, 4);
				modelCustomer.setValueAt(rs.getString("CS_MAIL"), row, 5);
				modelCustomer.setValueAt(rs.getString("URGT_CONT"), row, 6);
				row++;
			}
			tableCust.setModel(modelCustomer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insert()
	{
		try
		{
			String sql = "INSERT INTO customer VALUES (?,?,?,?,?,?,?)";
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1,txtCS_ID.getText().trim());
			pre.setString(2,txtCS_NAME.getText().trim());
			pre.setString(3,txtCARD_NUM.getText().trim());
			pre.setString(4,txtCS_ADDR.getText().trim());
			pre.setString(5,txtCS_PHONE.getText().trim());
			pre.setString(6,txtCS_MAIL.getText().trim());
			pre.setString(7,txtURGT_CONT.getText().trim());
			if(pre.executeUpdate() != -1)
			{
				JLabel msg = new JLabel("บันทึกรายการสำเร็จ");
				msg.setFont(fn1);
				JOptionPane.showMessageDialog(this, msg,"ผลการบันทึกรายการ",JOptionPane.INFORMATION_MESSAGE);
				showData();
				txtCS_ID.setText("");
				txtCS_NAME.setText("");
				txtCARD_NUM.setText("");
				txtCS_ADDR.setText("");
				txtCS_PHONE.setText("");
				txtCS_MAIL.setText("");
				txtURGT_CONT.setText("");		
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void update()
	{
		try
		{if(tableCust.getSelectedRow() == -1) 
			return;
			String sql = "UPDATE customer SET "
					+ " CS_NAME=?, "
					+ " CARD_NUM=?, "
					+ " CS_ADDR=?, "
					+ " CS_PHONE=?, "
					+ " CS_MAIL=?, "
					+ " URGT_CONT=? "
					+ " WHERE CS_ID=? ";
			
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1,txtCS_NAME.getText().trim());
			pre.setString(2,txtCARD_NUM.getText().trim());
			pre.setString(3,txtCS_ADDR.getText().trim());
			pre.setString(4,txtCS_PHONE.getText().trim());
			pre.setString(5,txtCS_MAIL.getText().trim());
			pre.setString(6,txtURGT_CONT.getText().trim());
			pre.setString(7,txtCS_ID.getText().trim());
			
			if(pre.executeUpdate() != -1)
				{
				JLabel msg2 = new JLabel("แก้ไขรายการสำเร็จ");
				msg2.setFont(fn1);
				JOptionPane.showMessageDialog(this, msg2,"ผลการบันทึกรายการ",JOptionPane.INFORMATION_MESSAGE);
				showData();
				txtCS_ID.setEditable(true);
				txtCS_ID.setText("");
				txtCS_NAME.setText("");
				txtCARD_NUM.setText("");
				txtCS_ADDR.setText("");
				txtCS_PHONE.setText("");
				txtCS_MAIL.setText("");
				txtURGT_CONT.setText("");
				}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void delete()
	{
		try
		{if(tableCust.getSelectedRow() == -1) 
			return;
			String sql = "DELETE FROM customer "
					+ " WHERE CS_ID=? ";
			
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1,txtCS_ID.getText().trim());
			
			if(pre.executeUpdate() != -1)
				{
				JLabel msg3 = new JLabel("ลบรายการสำเร็จ");
				msg3.setFont(fn1);
				JOptionPane.showMessageDialog(this, msg3,"ผลการบันทึกรายการ",JOptionPane.INFORMATION_MESSAGE);
				showData();
				txtCS_ID.setEditable(true);
				txtCS_ID.setText("");
				txtCS_NAME.setText("");
				txtCARD_NUM.setText("");
				txtCS_ADDR.setText("");
				txtCS_PHONE.setText("");
				txtCS_MAIL.setText("");
				txtURGT_CONT.setText("");
				}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
	
		CustomerForm frame = new CustomerForm();
		frame.setSize(900,600);
		frame.setTitle("หน้าจอข้อมูลลูกค้า (CustomerForm)");	
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(3);   
		frame.setResizable(false);
		frame.setVisible(true);		
	}
}