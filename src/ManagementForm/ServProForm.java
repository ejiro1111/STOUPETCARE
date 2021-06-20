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
import javax.swing.JComboBox;
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
import customFont.customFont;
import menuwindow.ManagementMenu;

public class ServProForm extends JFrame  {
	Connection con = MyConnect.getConnection();
	private DefaultTableModel modelServPro;
	JTextField txtServProNum, txtReservNum, txtTotalPrice, txtTotalDate, txtTotalCharge, txtSearchServ;
	JTable tableServPro;
	JComboBox reservBox;
	static Font f2 = customFont.normalFont;
	
	public ServProForm() {
		UIManager.put("Label.font", f2);
		UIManager.put("TextField.font", f2);
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		

		JPanel panelForm = new JPanel();
		panelForm.setLayout(new GridLayout(5,0,5,5));
		
		JPanel panelServProNum = new JPanel();
		panelServProNum.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lbServProNum = new JLabel("หมายเลขการให้บริการ :");
		lbServProNum.setPreferredSize(new Dimension(210,26));
		panelServProNum.add(lbServProNum);
		txtServProNum = new JTextField();
		txtServProNum.setPreferredSize(new Dimension(290,26));
		panelServProNum.add(txtServProNum);
		panelForm.add(panelServProNum);
		
		JPanel panelReservNum = new JPanel();
		panelReservNum.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lbReservNum = new JLabel("หมายเลขการจอง :");
		lbReservNum.setPreferredSize(new Dimension(210,26));
		panelReservNum.add(lbReservNum);
		JComboBox reservBox = new JComboBox();
		try {
			
			String sql2 = "SELECT RESERV_NUM FROM reservation_order";
			
			ResultSet rs2 = con.createStatement().executeQuery(sql2);
			int row2 = 0;
			while (rs2.next()) {
				reservBox.addItem(rs2.getString("RESERV_NUM"));
				row2++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.reservBox = reservBox;
		panelReservNum.add(reservBox);
		
		//txtReservNum = new JTextField();
		//txtReservNum.setPreferredSize(new Dimension(290,26));
		//panelReservNum.add(txtReservNum);
		panelForm.add(panelReservNum);
		
		JPanel panelTotalPrice = new JPanel();
		panelTotalPrice.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lbDeposit = new JLabel("ค่าบริการรวม :");
		lbDeposit.setPreferredSize(new Dimension(210,26));
		panelTotalPrice.add(lbDeposit);
		txtTotalPrice = new JTextField();
		txtTotalPrice.setPreferredSize(new Dimension(290,26));
		panelTotalPrice.add(txtTotalPrice);
		panelForm.add(panelTotalPrice);
		
		JPanel panelTotalDate = new JPanel();
		panelTotalDate.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lbTotalDate = new JLabel("วันที่ใช้บริการรวม :");
		lbTotalDate.setPreferredSize(new Dimension(210,26));
		panelTotalDate.add(lbTotalDate);
		txtTotalDate = new JTextField();
		txtTotalDate.setPreferredSize(new Dimension(290,26));
		panelTotalDate.add(txtTotalDate);
		panelForm.add(panelTotalDate);
		
		JPanel panelTotalCharge = new JPanel();
		panelTotalCharge.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lbTotalCharge = new JLabel("ค่าบริการเพิ่มเติม :");
		lbTotalCharge.setPreferredSize(new Dimension(210,26));
		panelTotalCharge.add(lbTotalCharge);
		txtTotalCharge = new JTextField();
		txtTotalCharge.setPreferredSize(new Dimension(290,26));
		panelTotalCharge.add(txtTotalCharge);
		panelForm.add(panelTotalCharge);
		
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
		tableServPro = new JTable();
		Object data[][] = 
		{
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null}
		};
		String columns[] = {"หมายเลขการให้บริการ","หมายเลขการจอง","ค่าบริการรวม","วันที่ใช้บริการรวม","ค่าบริการเพิ่มเติม"};
		DefaultTableModel tableModel = new DefaultTableModel(data,columns) 
		{
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			};
		};
		
		tableServPro.setModel(tableModel);
		tableServPro.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				int index = tableServPro.getSelectedRow();
				txtServProNum.setEditable(false);
				txtServProNum.setText(tableServPro.getValueAt(index, 0).toString());
				reservBox.setSelectedItem(tableServPro.getValueAt(index, 1).toString());
				//txtReservNum.setText(tableServPro.getValueAt(index, 1).toString());
				txtTotalPrice.setText(tableServPro.getValueAt(index, 2).toString());
				txtTotalDate.setText(tableServPro.getValueAt(index, 3).toString());
				txtTotalCharge.setText(tableServPro.getValueAt(index, 4).toString());
			}
		});
		
		scrollTable.setViewportView(tableServPro);
		panelTable.add(scrollTable);
		c.add(panelTable,BorderLayout.CENTER);
		modelServPro = (DefaultTableModel)tableServPro.getModel();
		showData();
	}

	public void showData() {
		try {
			int totalRow = tableServPro.getRowCount() - 1;
			while (totalRow > -1) {
				modelServPro.removeRow(totalRow);
				totalRow--;
			}
			String search = txtSearchServ.getText().trim();
			String sql = "SELECT * FROM service_provided"
			+" WHERE SERVPRO_NUM LIKE '%" + search + "%'"
			+" OR RESERV_NUM LIKE '%" + search + "%'"
			+" OR TOTAL_PRICE LIKE '%" + search + "%'"
			+" OR TOTAL_DATE LIKE '%" + search + "%'"
			+" OR TOTAL_CHARGE LIKE '%" + search + "%'";

			ResultSet rs = con.createStatement().executeQuery(sql);
			int row = 0;
			while (rs.next()) {
				modelServPro.addRow(new Object[0]);
				modelServPro.setValueAt(rs.getString("SERVPRO_NUM"), row, 0);
				modelServPro.setValueAt(rs.getString("RESERV_NUM"), row, 1);
				modelServPro.setValueAt(rs.getString("TOTAL_PRICE"), row, 2);
				modelServPro.setValueAt(rs.getString("TOTAL_DATE"), row, 3);
				modelServPro.setValueAt(rs.getString("TOTAL_CHARGE"), row, 4);
				row++;
			}
			tableServPro.setModel(modelServPro);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insert()
	{
		try
		{
			String sql = "INSERT INTO service_provided VALUES (?,?,?,?,?)";
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1,txtServProNum.getText().trim());
			pre.setString(2,reservBox.getSelectedItem().toString().trim());
			//pre.setString(2,txtReservNum.getText().trim());
			pre.setString(3,txtTotalPrice.getText().trim());
			pre.setString(4,txtTotalDate.getText().trim());
			pre.setString(5,txtTotalCharge.getText().trim());
		
			if(pre.executeUpdate() != -1)
			{
				JOptionPane.showMessageDialog(this, "บันทึกรายการสำเร็จ","ผลการบันทึกรายการ",JOptionPane.INFORMATION_MESSAGE);
				showData();
				txtServProNum.setText("");
				//txtReservNum.setText("");
				txtTotalPrice.setText("");
				txtTotalDate.setText("");
				txtTotalCharge.setText("");
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
		{if(tableServPro.getSelectedRow() == -1) 
			return;
			String sql = "UPDATE service_provided SET "
					+ " RESERV_NUM=?, "
					+ " TOTAL_PRICE=?, "
					+ " TOTAL_DATE=?, "
					+ " TOTAL_CHARGE=? "
					+ " WHERE SERVPRO_NUM=? ";
			
			PreparedStatement pre = con.prepareStatement(sql);
			//pre.setString(1,txtReservNum.getText().trim());
			pre.setString(1,reservBox.getSelectedItem().toString().trim());
			pre.setString(2,txtTotalPrice.getText().trim());
			pre.setString(3,txtTotalDate.getText().trim());
			pre.setString(4,txtTotalCharge.getText().trim());
			pre.setString(5,txtServProNum.getText().trim());
			
			if(pre.executeUpdate() != -1)
				{
				JOptionPane.showMessageDialog(this, "แก้ไขรายการสำเร็จ","ผลการบันทึกรายการ",JOptionPane.INFORMATION_MESSAGE);
				showData();
				txtServProNum.setEditable(true);
				txtServProNum.setText("");
				//txtReservNum.setText("");
				txtTotalPrice.setText("");
				txtTotalDate.setText("");
				txtTotalCharge.setText("");
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
		{if(tableServPro.getSelectedRow() == -1) 
			return;
			String sql = "DELETE FROM service_provided "
					+ " WHERE SERVPRO_NUM=? ";
			
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1,txtServProNum.getText().trim());
			
			if(pre.executeUpdate() != -1)
				{
				JOptionPane.showMessageDialog(this, "ลบรายการสำเร็จ","ผลการบันทึกรายการ",JOptionPane.INFORMATION_MESSAGE);
				showData();
				txtServProNum.setEditable(true);
				txtServProNum.setText("");
				//txtReservNum.setText("");
				txtTotalPrice.setText("");
				txtTotalDate.setText("");
				txtTotalCharge.setText("");
				}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ServProForm ServProFormFrame = new ServProForm();
		ServProFormFrame.setSize(800,600);
		ServProFormFrame.setTitle("หน้าจอข้อมูลการให้บริการ (ServiceProForm)");
		ServProFormFrame.setLocationRelativeTo(null);
		ServProFormFrame.setVisible(true);
		ServProFormFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

}