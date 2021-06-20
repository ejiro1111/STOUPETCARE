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

import PetCare.MyConnect;
import customFont.customFont;
import menuwindow.ManagementMenu;



public class CameraForm extends JFrame 
{
	Connection con = MyConnect.getConnection();
	private DefaultTableModel modelCamera;
	JTextField txtCAM_NUM, txtCAM_STATUS, txtPET_ID, txtSearch;
	JTable tableCam;
	JComboBox statusBox;
	Font fn1 = customFont.normalFont;
	
	public CameraForm()
	{
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		
		JPanel panelLeft = new JPanel();
		panelLeft.setLayout(new GridLayout(3,2,0,10));
		panelLeft.setBorder(BorderFactory.createEmptyBorder(10,0,0,100));
		panelLeft.setPreferredSize(new Dimension(500,120));
		
		JLabel lbCAM_NUM = new JLabel("หมายเลขกล้องวงจรปิด :");               
		lbCAM_NUM.setFont(fn1);
		panelLeft.add(lbCAM_NUM);
		txtCAM_NUM = new JTextField();																	//CAM_NUM
		panelLeft.add(txtCAM_NUM);
		
		JLabel lbCAM_STATUS = new JLabel("สถานะ :");           
		lbCAM_STATUS.setFont(fn1);
		panelLeft.add(lbCAM_STATUS);
		JComboBox statusBox = new JComboBox();
		statusBox.addItem("Enable");
		statusBox.addItem("Disable");
		this.statusBox = statusBox;
		//txtCAM_STATUS = new JTextField();																//CAM_STATUS
		//panelLeft.add(txtCAM_STATUS);
		panelLeft.add(statusBox);
		
		JLabel lbPET_ID = new JLabel("หมายเลขสัตว์รับฝาก :");     
		lbPET_ID.setFont(fn1);
		panelLeft.add(lbPET_ID);
		txtPET_ID = new JTextField();																//PET_ID
		panelLeft.add(txtPET_ID);
		
		txtSearch = new JTextField();
		
		JPanel panelRight = new JPanel();
		panelRight.setLayout(new GridLayout(5,1,0,10));
		panelRight.setPreferredSize(new Dimension(180,200));
		panelRight.setBorder(BorderFactory.createTitledBorder(null,"ค้นหา",0,0,fn1));//*****font Titleborder*****
		txtSearch.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent keyevent) 
			{
				showData();
			}
		});	
		panelRight.add(txtSearch);
			
		
		JPanel panelNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelNorth.setBorder(BorderFactory.createEmptyBorder(50,50,5,50)); // ระยะห่างจากขอบ
		
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
		tableCam = new JTable();
		tableCam.setFont(fn1);
		Object data[][]= 
			{
				{null,null,null},
				{null,null,null},
				{null,null,null},
				{null,null,null},
				{null,null,null},
		};
		String column[]= {"หมายเลขกล้องวงจรปิด","สถานะ","หมายเลขสัตว์รับฝาก"};
		JTableHeader header = tableCam.getTableHeader();  //getHeader
		header.setFont(fn1);  //SetFontHeader
		
		DefaultTableModel tablemodel = new DefaultTableModel(data,column)
				{
					public boolean isCellEditable(int row,int column) {
							return false;
					}
				};	
				tableCam.setModel(tablemodel);
				tableCam.addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent me)
					{
						int index = tableCam.getSelectedRow();
						txtCAM_NUM.setEditable(false);
						txtCAM_NUM.setText(tableCam.getValueAt(index, 0).toString().trim());
						statusBox.setSelectedItem(tableCam.getValueAt(index, 1).toString().trim());
						//txtCAM_STATUS.setText(tableCam.getValueAt(index, 1).toString().trim());
						txtPET_ID.setText(tableCam.getValueAt(index, 2).toString().trim());
					}
				});
				scrollTable.setViewportView(tableCam);
				panelTable.add(scrollTable);
							
		c.add(panelNorth,BorderLayout.PAGE_START);
		c.add(panelTable,BorderLayout.PAGE_END);	
		modelCamera = (DefaultTableModel)tableCam.getModel();
		showData();
	}
	public void showData() {
		try {
			int totalRow = tableCam.getRowCount() - 1;
			while (totalRow > -1) {
				modelCamera.removeRow(totalRow);
				totalRow--;
			}
			String search = txtSearch.getText().trim();
			String sql = "SELECT * FROM camera"
			+" WHERE CAM_NUM LIKE '%" + search + "%'"
			+" OR CAM_STATUS LIKE '%" + search + "%'"
			+" OR PET_ID LIKE '%" + search + "%'";

			ResultSet rs = con.createStatement().executeQuery(sql);
			int row = 0;
			while (rs.next()) {
				modelCamera.addRow(new Object[0]);
				modelCamera.setValueAt(rs.getString("CAM_NUM"), row, 0);
				modelCamera.setValueAt(rs.getString("CAM_STATUS"), row, 1);
				modelCamera.setValueAt(rs.getString("PET_ID"), row, 2);
				row++;
			}
			tableCam.setModel(modelCamera);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insert()
	{
		try
		{
			String sql = "INSERT INTO camera VALUES (?,?,?)";
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1,txtCAM_NUM.getText().trim());
			pre.setString(2,statusBox.getSelectedItem().toString().trim());
			//pre.setString(2,txtCAM_STATUS.getText().trim());
			pre.setString(3,txtPET_ID.getText().trim());
			if(pre.executeUpdate() != -1)
			{
				JLabel msg = new JLabel("บันทึกรายการสำเร็จ");
				msg.setFont(fn1);
				JOptionPane.showMessageDialog(this, msg,"ผลการบันทึกรายการ",JOptionPane.INFORMATION_MESSAGE);
				showData();
				txtCAM_NUM.setText("");
				//txtCAM_STATUS.setText("");
				txtPET_ID.setText("");
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
		{if(tableCam.getSelectedRow() == -1) 
			return;
			String sql = "UPDATE camera SET "
					+ " CAM_STATUS=?, "
					+ " PET_ID=? "
					+ " WHERE CAM_NUM=? ";
					
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1,statusBox.getSelectedItem().toString().trim());
			//pre.setString(1,txtCAM_STATUS.getText().trim());
			pre.setString(2,txtPET_ID.getText().trim());
			pre.setString(3,txtCAM_NUM.getText().trim());
			
			
			if(pre.executeUpdate() != -1)
				{
				JLabel msg2 = new JLabel("แก้ไขรายการสำเร็จ");
				msg2.setFont(fn1);
				JOptionPane.showMessageDialog(this, msg2,"ผลการบันทึกรายการ",JOptionPane.INFORMATION_MESSAGE);
				showData();
				txtCAM_NUM.setEditable(true);
				txtCAM_NUM.setText("");
				//txtCAM_STATUS.setText("");
				txtPET_ID.setText("");
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
		{if(tableCam.getSelectedRow() == -1) 
			return;
			String sql = "DELETE FROM camera "
					+ " WHERE CAM_NUM=? ";
			
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1,txtCAM_NUM.getText().trim());
			
			if(pre.executeUpdate() != -1)
				{
				JLabel msg3 = new JLabel("ลบรายการสำเร็จ");
				msg3.setFont(fn1);
				JOptionPane.showMessageDialog(this, msg3,"ผลการบันทึกรายการ",JOptionPane.INFORMATION_MESSAGE);
				showData();
				txtCAM_NUM.setEditable(true);
				txtCAM_NUM.setText("");
				//txtCAM_STATUS.setText("");
				txtPET_ID.setText("");
				}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) 
	{
		CameraForm frame = new CameraForm();
		frame.setSize(900,600);
		frame.setTitle("หน้าจอข้อมูลกล้องวงจรปิด (CameraForm)");	
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(3);   
		frame.setResizable(false);
		frame.setVisible(true);		
	}
}