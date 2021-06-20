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

public class EmployeeForm extends JFrame  {
	Connection con = MyConnect.getConnection();
	private DefaultTableModel modelEmployee;
	JTextField txtEm_ID, txtEm_Name, txtEm_Post, txtEm_Cont, txtEm_User, txtEm_Password, txtSearchEmp;
	JTable tableEmp;
	static Font f2 = new Font("Tahoma",Font.PLAIN, 14);
	public EmployeeForm() {
		UIManager.put("Label.font", f2);
		UIManager.put("TextField.font", f2);
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		
		JPanel panelForm = new JPanel();
		panelForm.setLayout(new GridLayout(6,0,5,5));
		
		JPanel panelEm_ID = new JPanel();
		panelEm_ID.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lbEm_ID = new JLabel("รหัสพนักงาน (EM_ID) :");
		lbEm_ID.setPreferredSize(new Dimension(200,26));
		panelEm_ID.add(lbEm_ID);
		txtEm_ID = new JTextField();
		txtEm_ID.setPreferredSize(new Dimension(300,26));
		panelEm_ID.add(txtEm_ID);
		panelForm.add(panelEm_ID);
		
		JPanel panelEm_Name = new JPanel();
		panelEm_Name.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lbEm_Name = new JLabel("ชื่อพนักงาน (EM_NAME) :");
		lbEm_Name.setPreferredSize(new Dimension(200,26));
		panelEm_Name.add(lbEm_Name);
		txtEm_Name = new JTextField();
		txtEm_Name.setPreferredSize(new Dimension(300,26));
		panelEm_Name.add(txtEm_Name);
		panelForm.add(panelEm_Name);
		
		JPanel panelEm_Post = new JPanel();
		panelEm_Post.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lbEm_Post = new JLabel("กลุ่ม (EM_POST) :");
		lbEm_Post.setPreferredSize(new Dimension(200,26));
		panelEm_Post.add(lbEm_Post);
		txtEm_Post = new JTextField();
		txtEm_Post.setPreferredSize(new Dimension(300,26));
		panelEm_Post.add(txtEm_Post);
		panelForm.add(panelEm_Post);
		
		JPanel panelEm_Cont = new JPanel();
		panelEm_Cont.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lbEm_Cont = new JLabel("ข้อมูลติดต่อ (EM_CONT) :");
		lbEm_Cont.setPreferredSize(new Dimension(200,26));
		panelEm_Cont.add(lbEm_Cont);
		txtEm_Cont = new JTextField();
		txtEm_Cont.setPreferredSize(new Dimension(300,26));
		panelEm_Cont.add(txtEm_Cont);
		panelForm.add(panelEm_Cont);
		
		JPanel panelEm_User = new JPanel();
		panelEm_User.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lbEm_User = new JLabel("ชื่อผู้ใช้พนักงาน :");
		lbEm_User.setPreferredSize(new Dimension(200,26));
		panelEm_User.add(lbEm_User);
		txtEm_User = new JTextField();
		txtEm_User.setPreferredSize(new Dimension(300,26));
		panelEm_User.add(txtEm_User);
		panelForm.add(panelEm_User);
		
		JPanel panelEm_Password = new JPanel();
		panelEm_Password.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lbEm_Password = new JLabel("รหัสผ่าน :");
		lbEm_Password.setPreferredSize(new Dimension(200,26));
		panelEm_Password.add(lbEm_Password);
		txtEm_Password = new JTextField();
		txtEm_Password.setPreferredSize(new Dimension(300,26));
		panelEm_Password.add(txtEm_Password);
		panelForm.add(panelEm_Password);
		
		JPanel panelSearch = new JPanel();
		panelSearch.setBorder(BorderFactory.createEmptyBorder(0,30,0,5));
		UIManager.put("Button.font", f2);
		panelSearch.setLayout(new GridLayout(6,0,10,10));
		JLabel lbSearch = new JLabel("ค้นหา");
		panelSearch.add(lbSearch);
		txtSearchEmp = new JTextField();
		txtSearchEmp.setPreferredSize(new Dimension(200,26));
		txtSearchEmp.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent keyevent) 
			{
				showData();
			}
		});
		panelSearch.add(txtSearchEmp);
		
		JButton btnAddEmp = new JButton("เพิ่มข้อมูล");
		btnAddEmp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				insert();
			}
		});
		panelSearch.add(btnAddEmp);
		
		JButton btnUpdateEmp = new JButton("แก้ไขข้อมูล");
		btnUpdateEmp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				update();
			}
		});
		panelSearch.add(btnUpdateEmp);
		
		JButton btnDeleteEmp = new JButton("ลบข้อมูล");
		btnDeleteEmp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				delete();
			}
		});
		panelSearch.add(btnDeleteEmp);
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
		tableEmp = new JTable();
		Object data[][] = 
		{
				{null,null,null,null,null,null},
				{null,null,null,null,null,null},
				{null,null,null,null,null,null},
				{null,null,null,null,null,null}
		};
		String columns[] = {"รหัสพนักงาน","ชื่อพนักงาน","กลุ่ม","ข้อมูลติดต่อ","ชื่อผู้ใช้พนักงาน","รหัสผ่าน"};
		DefaultTableModel tableModel = new DefaultTableModel(data,columns) 
		{
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			};
		};
		
		tableEmp.setModel(tableModel);
		tableEmp.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				int index = tableEmp.getSelectedRow();
				txtEm_ID.setEditable(false);
				txtEm_ID.setText(tableEmp.getValueAt(index, 0).toString());
				txtEm_Name.setText(tableEmp.getValueAt(index, 1).toString());
				txtEm_Post.setText(tableEmp.getValueAt(index, 2).toString());
				txtEm_Cont.setText(tableEmp.getValueAt(index, 3).toString());
				txtEm_User.setText(tableEmp.getValueAt(index, 4).toString());
				txtEm_Password.setText(tableEmp.getValueAt(index, 5).toString());
			}
		});
		
		
		
		scrollTable.setViewportView(tableEmp);
		panelTable.add(scrollTable);
		c.add(panelTable,BorderLayout.CENTER);
		modelEmployee = (DefaultTableModel)tableEmp.getModel();
		showData();
		
	}
	public void showData() {
		try {
			int totalRow = tableEmp.getRowCount() - 1;
			while (totalRow > -1) {
				modelEmployee.removeRow(totalRow);
				totalRow--;
			}
			String search = txtSearchEmp.getText().trim();
			String sql = "SELECT * FROM employee"
			+" WHERE EM_ID LIKE '%" + search + "%'"
			+" OR EM_NAME LIKE '%" + search + "%'"
			+" OR EM_POST LIKE '%" + search + "%'"
			+" OR EM_CONT LIKE '%" + search + "%'"
			+" OR EM_USER LIKE '%" + search + "%'"
			+" OR EM_PASSWORD LIKE '%" + search + "%'";

			ResultSet rs = con.createStatement().executeQuery(sql);
			int row = 0;
			while (rs.next()) {
				modelEmployee.addRow(new Object[0]);
				modelEmployee.setValueAt(rs.getString("EM_ID"), row, 0);
				modelEmployee.setValueAt(rs.getString("EM_NAME"), row, 1);
				modelEmployee.setValueAt(rs.getString("EM_POST"), row, 2);
				modelEmployee.setValueAt(rs.getString("EM_CONT"), row, 3);
				modelEmployee.setValueAt(rs.getString("EM_USER"), row, 4);
				modelEmployee.setValueAt(rs.getString("EM_PASSWORD"), row, 5);
				row++;
			}
			tableEmp.setModel(modelEmployee);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insert()
	{
		try
		{
			String sql = "INSERT INTO employee VALUES (?,?,?,?,?,?)";
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1,txtEm_ID.getText().trim());
			pre.setString(2,txtEm_Name.getText().trim());
			pre.setString(3,txtEm_Post.getText().trim());
			pre.setString(4,txtEm_Cont.getText().trim());
			pre.setString(5,txtEm_User.getText().trim());
			pre.setString(6,txtEm_Password.getText().trim());
			if(pre.executeUpdate() != -1)
			{
				JOptionPane.showMessageDialog(this, "บันทึกรายการาสำเร็จ","ผลการบันทึกรายการ",JOptionPane.INFORMATION_MESSAGE);
				showData();
				txtEm_ID.setText("");
				txtEm_Name.setText("");
				txtEm_Post.setText("");
				txtEm_Cont.setText("");
				txtEm_User.setText("");
				txtEm_Password.setText("");
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
		{if(tableEmp.getSelectedRow() == -1) 
			return;
			String sql = "UPDATE employee SET "
					+ " EM_NAME=?, "
					+ " EM_POST=?, "
					+ " EM_CONT=?, "
					+ " EM_USER=?, "
					+ " EM_PASSWORD=? "
					+ " WHERE EM_ID=? ";
			
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1,txtEm_Name.getText().trim());
			pre.setString(2,txtEm_Post.getText().trim());
			pre.setString(3,txtEm_Cont.getText().trim());
			pre.setString(4,txtEm_User.getText().trim());
			pre.setString(5,txtEm_Password.getText().trim());
			pre.setString(6,txtEm_ID.getText().trim());
			
			if(pre.executeUpdate() != -1)
				{
				JOptionPane.showMessageDialog(this, "แก้ไขรายการสำเร็จ","ผลการบันทึกรายการ",JOptionPane.INFORMATION_MESSAGE);
				showData();
				txtEm_ID.setEditable(true);
				txtEm_ID.setText("");
				txtEm_Name.setText("");
				txtEm_Post.setText("");
				txtEm_Cont.setText("");
				txtEm_User.setText("");
				txtEm_Password.setText("");
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
		{if(tableEmp.getSelectedRow() == -1) 
			return;
			String sql = "DELETE FROM employee "
					+ " WHERE EM_ID=? ";
			
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1,txtEm_ID.getText().trim());
			
			if(pre.executeUpdate() != -1)
				{
				JOptionPane.showMessageDialog(this, "ลบรายการสำเร็จ","ผลการบันทึกรายการ",JOptionPane.INFORMATION_MESSAGE);
				showData();
				txtEm_ID.setEditable(true);
				txtEm_ID.setText("");
				txtEm_Name.setText("");
				txtEm_Post.setText("");
				txtEm_Cont.setText("");
				txtEm_User.setText("");
				txtEm_Password.setText("");
				}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
	
		EmployeeForm EmployeeFormFrame = new EmployeeForm();
		EmployeeFormFrame.setSize(800,600);
		EmployeeFormFrame.setTitle("หน้าจอข้อมูลพนักงาน (EmployeeForm)");
		EmployeeFormFrame.setLocationRelativeTo(null);
		EmployeeFormFrame.setVisible(true);
		EmployeeFormFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

}
