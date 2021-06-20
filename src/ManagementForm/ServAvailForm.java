package ManagementForm;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import PetCare.MyConnect;
import menuwindow.ManagementMenu;

public class ServAvailForm extends JFrame  {
	Connection con = MyConnect.getConnection();
	private DefaultTableModel modelServAvail;
	JTextField txtServ_Code, txtServDesc, txtDay_Price, txtDeposit, txtAdd_Price, txtSearchServ;
	JTable tableServAvail;
	static Font f2 = new Font("Tahoma",Font.PLAIN, 14);
	
	public ServAvailForm() {
		UIManager.put("Label.font", f2);
		UIManager.put("TextField.font", f2);
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());

		JPanel panelForm = new JPanel();
		panelForm.setLayout(new GridLayout(5,0,5,5));
		
		JPanel panelServ_Code = new JPanel();
		panelServ_Code.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lbServ_Code = new JLabel("รหัสประเภทบริการ (SERV_CODE) :");
		lbServ_Code.setPreferredSize(new Dimension(210,26));
		panelServ_Code.add(lbServ_Code);
		txtServ_Code = new JTextField();
		txtServ_Code.setPreferredSize(new Dimension(290,26));
		panelServ_Code.add(txtServ_Code);
		panelForm.add(panelServ_Code);
		
		JPanel panelServ_Desc = new JPanel();
		panelServ_Desc.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lbServ_Desc = new JLabel("รายละเอียด (SERV_DESC) :");
		lbServ_Desc.setPreferredSize(new Dimension(210,26));
		panelServ_Desc.add(lbServ_Desc);
		txtServDesc = new JTextField();
		txtServDesc.setPreferredSize(new Dimension(290,26));
		panelServ_Desc.add(txtServDesc);
		panelForm.add(panelServ_Desc);
		
		JPanel panelDay_Price = new JPanel();
		panelDay_Price.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lbDay_Price = new JLabel("ค่าบริการต่อวัน (DAY_PRICE) :");
		lbDay_Price.setPreferredSize(new Dimension(210,26));
		panelDay_Price.add(lbDay_Price);
		txtDay_Price = new JTextField();
		txtDay_Price.setPreferredSize(new Dimension(290,26));
		panelDay_Price.add(txtDay_Price);
		panelForm.add(panelDay_Price);
		
		JPanel panelDeposit = new JPanel();
		panelDeposit.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lbDeposit = new JLabel("ค่ามัดจำ (DEPOSIT) :");
		lbDeposit.setPreferredSize(new Dimension(210,26));
		panelDeposit.add(lbDeposit);
		txtDeposit = new JTextField();
		txtDeposit.setPreferredSize(new Dimension(290,26));
		panelDeposit.add(txtDeposit);
		panelForm.add(panelDeposit);
		
		JPanel panelAdd_Price = new JPanel();
		panelAdd_Price.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lbAddPrice = new JLabel("ค่าใช้จ่ายเพิ่มเติม (ADD_PRICE) :");
		lbAddPrice.setPreferredSize(new Dimension(210,26));
		panelAdd_Price.add(lbAddPrice);
		txtAdd_Price = new JTextField();
		txtAdd_Price.setPreferredSize(new Dimension(290,26));
		panelAdd_Price.add(txtAdd_Price);
		panelForm.add(panelAdd_Price);
		
		JPanel panelSearch = new JPanel();
		panelSearch.setBorder(BorderFactory.createEmptyBorder(0,30,5,0));
		UIManager.put("Button.font", f2);
		panelSearch.setLayout(new GridLayout(6,0,10,10));
		JLabel lbSearch = new JLabel("ค้นหา");
		panelSearch.add(lbSearch);
		txtSearchServ = new JTextField();
		txtSearchServ.setPreferredSize(new Dimension(200,26));
		txtSearchServ.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent keyevent) 
			{
				showData();
			}
		});
		panelSearch.add(txtSearchServ);
		
		JButton btnAddServ = new JButton("เพิ่มข้อมูล");
		btnAddServ.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				insert();
			}
		});
		panelSearch.add(btnAddServ);
		
		JButton btnUpdateServ = new JButton("แก้ไขข้อมูล");
		btnUpdateServ.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				update();
			}
		});
		panelSearch.add(btnUpdateServ);
		
		JButton btnDeleteServ = new JButton("ลบข้อมูล");
		btnDeleteServ.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				delete();
			}
		});
		panelSearch.add(btnDeleteServ);
		
		JButton btnReturn = new JButton("กลับสู่เมนูหลัก");
		btnReturn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ManagementMenu mm = new ManagementMenu();
				mm.setVisible(true);
				setVisible(false);
			}
		});
		panelSearch.add(btnReturn);
	
		JPanel panelNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelNorth.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		panelNorth.add(panelForm);
		panelNorth.add(panelSearch);
		c.add(panelNorth,BorderLayout.PAGE_START);
		
		UIManager.put("Table.font", f2);
		UIManager.put("TableHeader.font", f2);
		JPanel panelTable = new JPanel();
		JScrollPane scrollTable = new JScrollPane();
		scrollTable.setPreferredSize(new Dimension(750,300));
		tableServAvail = new JTable();
		Object data[][] = 
		{
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null}
		};
		String columns[] = {"รหัสประเภทบริการ","รายละเอียด","ค่าบริการต่อวัน","ค่ามัดจำ","ค่าใช้จ่ายเพิ่มเติม"};
		DefaultTableModel tableModel = new DefaultTableModel(data,columns) 
		{
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			};
		};
		
		tableServAvail.setModel(tableModel);
		tableServAvail.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				int index = tableServAvail.getSelectedRow();
				txtServ_Code.setEditable(false);
				txtServ_Code.setText(tableServAvail.getValueAt(index, 0).toString());
				txtServDesc.setText(tableServAvail.getValueAt(index, 1).toString());
				txtDay_Price.setText(tableServAvail.getValueAt(index, 2).toString());
				txtDeposit.setText(tableServAvail.getValueAt(index, 3).toString());
				txtAdd_Price.setText(tableServAvail.getValueAt(index, 4).toString());
			}
		});
		
		scrollTable.setViewportView(tableServAvail);
		panelTable.add(scrollTable);
		c.add(panelTable,BorderLayout.CENTER);
		modelServAvail = (DefaultTableModel)tableServAvail.getModel();
		showData();
	}
	public void showData() {
		try {
			int totalRow = tableServAvail.getRowCount() - 1;
			while (totalRow > -1) {
				modelServAvail.removeRow(totalRow);
				totalRow--;
			}
			String search = txtSearchServ.getText().trim();
			String sql = "SELECT * FROM service_available"
			+" WHERE SERV_CODE LIKE '%" + search + "%'"
			+" OR SERV_DESC LIKE '%" + search + "%'"
			+" OR DAY_PRICE LIKE '%" + search + "%'"
			+" OR DEPOSIT LIKE '%" + search + "%'"
			+" OR ADD_PRICE LIKE '%" + search + "%'";

			ResultSet rs = con.createStatement().executeQuery(sql);
			int row = 0;
			while (rs.next()) {
				modelServAvail.addRow(new Object[0]);
				modelServAvail.setValueAt(rs.getString("SERV_CODE"), row, 0);
				modelServAvail.setValueAt(rs.getString("SERV_DESC"), row, 1);
				modelServAvail.setValueAt(rs.getString("DAY_PRICE"), row, 2);
				modelServAvail.setValueAt(rs.getString("DEPOSIT"), row, 3);
				modelServAvail.setValueAt(rs.getString("ADD_PRICE"), row, 4);
				row++;
			}
			tableServAvail.setModel(modelServAvail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insert()
	{
		try
		{
			String sql = "INSERT INTO service_available VALUES (?,?,?,?,?)";
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1,txtServ_Code.getText().trim());
			pre.setString(2,txtServDesc.getText().trim());
			pre.setString(3,txtDay_Price.getText().trim());
			pre.setString(4,txtDeposit.getText().trim());
			pre.setString(5,txtAdd_Price.getText().trim());
		
			if(pre.executeUpdate() != -1)
			{
				JOptionPane.showMessageDialog(this, "บันทึกรายการสำเร็จ","ผลการบันทึกรายการ",JOptionPane.INFORMATION_MESSAGE);
				showData();
				txtServ_Code.setText("");
				txtServDesc.setText("");
				txtDay_Price.setText("");
				txtDeposit.setText("");
				txtAdd_Price.setText("");
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
		{if(tableServAvail.getSelectedRow() == -1) 
			return;
			String sql = "UPDATE service_available SET "
					+ " SERV_DESC=?, "
					+ " DAY_PRICE=?, "
					+ " DEPOSIT=?, "
					+ " ADD_PRICE=? "
					+ " WHERE SERV_CODE=? ";
			
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1,txtServDesc.getText().trim());
			pre.setString(2,txtDay_Price.getText().trim());
			pre.setString(3,txtDeposit.getText().trim());
			pre.setString(4,txtAdd_Price.getText().trim());
			pre.setString(5,txtServ_Code.getText().trim());
			
			if(pre.executeUpdate() != -1)
				{
				JOptionPane.showMessageDialog(this, "แก้ไขรายการสำเร็จ","ผลการบันทึกรายการ",JOptionPane.INFORMATION_MESSAGE);
				showData();
				txtServ_Code.setEditable(true);
				txtServ_Code.setText("");
				txtServDesc.setText("");
				txtDay_Price.setText("");
				txtDeposit.setText("");
				txtAdd_Price.setText("");
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
		{if(tableServAvail.getSelectedRow() == -1) 
			return;
			String sql = "DELETE FROM service_available "
					+ " WHERE SERV_CODE=? ";
			
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1,txtServ_Code.getText().trim());
			
			if(pre.executeUpdate() != -1)
				{
				JOptionPane.showMessageDialog(this, "ลบรายการสำเร็จ","ผลการบันทึกรายการ",JOptionPane.INFORMATION_MESSAGE);
				showData();
				txtServ_Code.setEditable(true);
				txtServ_Code.setText("");
				txtServDesc.setText("");
				txtDay_Price.setText("");
				txtDeposit.setText("");
				txtAdd_Price.setText("");
				}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ServAvailForm ServAvailFormFrame = new ServAvailForm();
		ServAvailFormFrame.setSize(800,600);
		ServAvailFormFrame.setTitle("หน้าจอข้อมูลบริการที่เปิดดำเนินการ (ServiceAvailableForm)");
		ServAvailFormFrame.setLocationRelativeTo(null);
		ServAvailFormFrame.setVisible(true);
		ServAvailFormFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

}
