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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

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
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import PetCare.MyConnect;
import customFont.customFont;
import menuwindow.ManagementMenu;


public class ReserveOrderForm extends JFrame  {
	Connection con = MyConnect.getConnection();
	private DefaultTableModel modelReservOrder;
	JTextField txtReservNum, txtServCode, txtCsID, txtCheckin, txtCheckout, txtPetID, txtSearchOrder;
	JComboBox serviceBox,customerBox,petBox;
	JTable tableReservOrder;
	static Font f2 = customFont.normalFont;
	SqlDateModel dateModel,outModel;
	Properties p;
	JDatePanelImpl datePanel,outPanel;
	public JDatePickerImpl datePicker,outPicker;
	public ReserveOrderForm() {
		UIManager.put("Label.font", f2);
		UIManager.put("TextField.font", f2);
		UIManager.put("ComboBox.font", f2);
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		
		JPanel panelForm = new JPanel();
		panelForm.setLayout(new GridLayout(6,0,5,5));
		
		JPanel panelReservNum = new JPanel();
		panelReservNum.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lbReservNum = new JLabel("หมายเลขการจอง :");
		lbReservNum.setPreferredSize(new Dimension(200,26));
		panelReservNum.add(lbReservNum);
		txtReservNum = new JTextField();
		txtReservNum.setPreferredSize(new Dimension(300,26));
		panelReservNum.add(txtReservNum);
		panelForm.add(panelReservNum);
		
		JPanel panelServCode = new JPanel();
		panelServCode.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lbServCode = new JLabel("ประเภทบริการ :");
		lbServCode.setPreferredSize(new Dimension(200,26));
		panelServCode.add(lbServCode);
		JComboBox serviceBox = new JComboBox();
			try {
				
				String sql2 = "SELECT SERV_CODE FROM service_available";
				
				ResultSet rs2 = con.createStatement().executeQuery(sql2);
				int row2 = 0;
				while (rs2.next()) {
					serviceBox.addItem(rs2.getString("SERV_CODE"));
					row2++;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		this.serviceBox = serviceBox ;
		//txtServCode = new JTextField();
		//txtServCode.setPreferredSize(new Dimension(300,26));
		//panelServCode.add(txtServCode);
		panelServCode.add(serviceBox);
		panelForm.add(panelServCode);
		
		JPanel panelCsID = new JPanel();
		panelCsID.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lbCsID = new JLabel("หมายเลขลูกค้า :");
		lbCsID.setPreferredSize(new Dimension(200,26));
		panelCsID.add(lbCsID);
		JComboBox customerBox = new JComboBox();
		try {
			
			String sql3 = "SELECT CS_ID FROM customer";
			
			ResultSet rs3 = con.createStatement().executeQuery(sql3);
			int row3 = 0;
			while (rs3.next()) {
				customerBox.addItem(rs3.getString("CS_ID"));
				row3++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	this.customerBox = customerBox ;
		//txtCsID = new JTextField();
		//txtCsID.setPreferredSize(new Dimension(300,26));
		//panelCsID.add(txtCsID);
		panelCsID.add(customerBox);
		panelForm.add(panelCsID);
		
		
		JPanel panelCheckin = new JPanel();
		panelCheckin.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lbCheckin = new JLabel("วันเริ่มใช้บริการ :");
		lbCheckin.setPreferredSize(new Dimension(200,26));
		panelCheckin.add(lbCheckin);
		
		SqlDateModel  dateModel = new SqlDateModel();
		dateModel.setSelected(true);
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(dateModel,p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel,new DateLabelFormatter());
		this.datePanel = datePanel;
		this.datePicker = datePicker;
		panelCheckin.add(datePicker);
		panelForm.add(panelCheckin);
		
		
		
		JPanel panelCheckout = new JPanel();
		panelCheckout.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lbCheckout = new JLabel("วันสิ้นสุดการใช้บริการ :");
		lbCheckout.setPreferredSize(new Dimension(200,26));
		panelCheckout.add(lbCheckout);
		SqlDateModel outModel = new SqlDateModel();
		outModel.setSelected(true);
		JDatePanelImpl outPanel = new JDatePanelImpl(outModel,p);
		JDatePickerImpl outPicker = new JDatePickerImpl(outPanel,new DateLabelFormatter());
		this.outPicker = outPicker;
		this.outPanel = outPanel;
		panelCheckout.add(outPicker);
		panelForm.add(panelCheckout);
		
		JPanel panelPetID = new JPanel();
		panelPetID.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lbPetID = new JLabel("หมายเลขสัตว์เลี้ยง :");
		lbPetID.setPreferredSize(new Dimension(200,26));
		panelPetID.add(lbPetID);
		JComboBox petBox = new JComboBox();
		try {
			
			String sql4 = "SELECT PET_ID FROM pet";
			
			ResultSet rs4 = con.createStatement().executeQuery(sql4);
			int row4 = 0;
			while (rs4.next()) {
				petBox.addItem(rs4.getString("PET_ID"));
				row4++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	this.petBox = petBox ;
		//txtPetID = new JTextField();
		//txtPetID.setPreferredSize(new Dimension(300,26));
		//panelPetID.add(txtPetID);
		panelPetID.add(petBox);
		panelForm.add(panelPetID);
		
		JPanel panelSearch = new JPanel();
		panelSearch.setBorder(BorderFactory.createEmptyBorder(0,30,0,5));
		UIManager.put("Button.font", f2);
		panelSearch.setLayout(new GridLayout(6,0,10,10));
		JLabel lbSearch = new JLabel("ค้นหา");
		panelSearch.add(lbSearch);
		txtSearchOrder = new JTextField();
		txtSearchOrder.setPreferredSize(new Dimension(200,26));
		txtSearchOrder.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent keyevent) 
			{
				showData();
			}
		});
		panelSearch.add(txtSearchOrder);
		
		JButton btnAddOrder = new JButton("เพิ่มข้อมูล");
		btnAddOrder.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				insert();
			}
		});
		panelSearch.add(btnAddOrder);
		
		JButton btnUpdateOrder = new JButton("แก้ไขข้อมูล");
		btnUpdateOrder.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				update();
			}
		});
		panelSearch.add(btnUpdateOrder);
		
		JButton btnDeleteOrder = new JButton("ลบข้อมูล");
		btnDeleteOrder.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				delete();
			}
		});
		panelSearch.add(btnDeleteOrder);
		
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
		tableReservOrder = new JTable();
		Object data[][] = 
		{
				{null,null,null,null,null,null},
				{null,null,null,null,null,null},
				{null,null,null,null,null,null},
				{null,null,null,null,null,null}
		};
		String columns[] = {"หมายเลขการจอง","ประเภทบริการ","หมายเลขลูกค้า","วันเริ่มต้น","วันสิ้นสุด","หมายเลขสัตว์เลี้ยง"};
		DefaultTableModel tableModel = new DefaultTableModel(data,columns) 
		{
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			};
		};
		
		tableReservOrder.setModel(tableModel);
		tableReservOrder.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				int index = tableReservOrder.getSelectedRow();
				txtReservNum.setEditable(false);
				txtReservNum.setText(tableReservOrder.getValueAt(index, 0).toString());
				//txtServCode.setText(tableReservOrder.getValueAt(index, 1).toString());
				serviceBox.setSelectedItem(tableReservOrder.getValueAt(index, 1).toString());
				//txtCsID.setText(tableReservOrder.getValueAt(index, 2).toString());
				customerBox.setSelectedItem(tableReservOrder.getValueAt(index, 2).toString());
				
				datePicker.getModel().setDay(Integer.parseInt(tableReservOrder.getValueAt(index, 3).toString().substring(8,10)));
				datePicker.getModel().setMonth(Integer.parseInt(tableReservOrder.getValueAt(index, 3).toString().substring(5,7))-1);
				datePicker.getModel().setYear(Integer.parseInt(tableReservOrder.getValueAt(index, 3).toString().substring(0,4)));
				
				outPicker.getModel().setDay(Integer.parseInt(tableReservOrder.getValueAt(index, 4).toString().substring(8,10)));
				outPicker.getModel().setMonth(Integer.parseInt(tableReservOrder.getValueAt(index, 4).toString().substring(5,7))-1);
				outPicker.getModel().setYear(Integer.parseInt(tableReservOrder.getValueAt(index, 4).toString().substring(0,4)));
				
				//txtPetID.setText(tableReservOrder.getValueAt(index, 5).toString());
				petBox.setSelectedItem(tableReservOrder.getValueAt(index, 5).toString());
				
				//System.out.println(datePicker.getModel().getYear() + "-" + 
				//(datePicker.getModel().getMonth()+1) + "-" + 
				//datePicker.getModel().getDay());
				
				//System.out.println(serviceBox.getSelectedItem().toString().trim());
			}
		});
		scrollTable.setViewportView(tableReservOrder);
		panelTable.add(scrollTable);
		c.add(panelTable,BorderLayout.CENTER);
		modelReservOrder = (DefaultTableModel)tableReservOrder.getModel();
		showData();
		
	}
	
	public void showData() {
		try {
			int totalRow = tableReservOrder.getRowCount() - 1;
			while (totalRow > -1) {
				modelReservOrder.removeRow(totalRow);
				totalRow--;
			}
			String search = txtSearchOrder.getText().trim();
			String sql = "SELECT * FROM reservation_order"
			+" WHERE RESERV_NUM LIKE '%" + search + "%'"
			+" OR SERV_CODE LIKE '%" + search + "%'"
			+" OR CS_ID LIKE '%" + search + "%'"
			+" OR CHECK_IN LIKE '%" + search + "%'"
			+" OR CHECK_OUT LIKE '%" + search + "%'"
			+" OR PET_ID LIKE '%" + search + "%'";

			ResultSet rs = con.createStatement().executeQuery(sql);
			int row = 0;
			while (rs.next()) {
				modelReservOrder.addRow(new Object[0]);
				modelReservOrder.setValueAt(rs.getString("RESERV_NUM"), row, 0);
				modelReservOrder.setValueAt(rs.getString("SERV_CODE"), row, 1);
				modelReservOrder.setValueAt(rs.getString("CS_ID"), row, 2);
				modelReservOrder.setValueAt(rs.getDate("CHECK_IN"), row, 3);
				modelReservOrder.setValueAt(rs.getDate("CHECK_OUT"), row, 4);
				modelReservOrder.setValueAt(rs.getString("PET_ID"), row, 5);
				row++;
			}
			tableReservOrder.setModel(modelReservOrder);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insert()
	{
		try
		{
			String sql = "INSERT INTO reservation_order VALUES (?,?,?,?,?,?)";
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1,txtReservNum.getText().trim());
			//pre.setString(2,txtServCode.getText().trim());
			pre.setString(2,serviceBox.getSelectedItem().toString().trim());
			//pre.setString(3,txtCsID.getText().trim());
			pre.setString(3,customerBox.getSelectedItem().toString().trim());
			pre.setDate(4,(Date) datePicker.getModel().getValue());
			pre.setDate(5,(Date) outPicker.getModel().getValue());
			//pre.setString(6,txtPetID.getText().trim());
			pre.setString(6,petBox.getSelectedItem().toString().trim());
			if(pre.executeUpdate() != -1)
			{
				JOptionPane.showMessageDialog(this, "บันทึกรายการสำเร็จ","ผลการบันทึกรายการ",JOptionPane.INFORMATION_MESSAGE);
				showData();
				txtReservNum.setText("");
				
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
		{if(tableReservOrder.getSelectedRow() == -1) 
			return;
			String sql = "UPDATE reservation_order SET "
					+ " SERV_CODE=?, "
					+ " CS_ID=?, "
					+ " CHECK_IN=?, "
					+ " CHECK_OUT=?, "
					+ " PET_ID=? "
					+ " WHERE RESERV_NUM=? ";
			
			PreparedStatement pre = con.prepareStatement(sql);
			
			pre.setString(1,serviceBox.getSelectedItem().toString().trim());
			//pre.setString(1,txtServCode.getText().trim());
			pre.setString(2,customerBox.getSelectedItem().toString().trim());
			//pre.setString(2,txtCsID.getText().trim());
			//System.out.println(java.sql.Date.valueOf(datePicker.getModel().getValue().toString()));
			pre.setDate(3,(Date) datePicker.getModel().getValue());
			pre.setDate(4,(Date) outPicker.getModel().getValue());
			/*pre.setDate(4,java.sql.Date.valueOf(datePicker.getModel().getYear()+
					"-"+(datePicker.getModel().getMonth())+
					"-"+datePicker.getModel().getDay()));
			pre.setDate(5,java.sql.Date.valueOf(outPicker.getModel().getYear()+
					"-"+(outPicker.getModel().getMonth())+
					"-"+outPicker.getModel().getDay()));*/
			
			//pre.setString(5,txtPetID.getText().trim());
			pre.setString(5,petBox.getSelectedItem().toString().trim());
			pre.setString(6,txtReservNum.getText().trim());
			if(pre.executeUpdate() != -1)
				{
				JOptionPane.showMessageDialog(this, "แก้ไขรายการสำเร็จ","ผลการบันทึกรายการ",JOptionPane.INFORMATION_MESSAGE);
				showData();
				txtReservNum.setText("");
				//txtServCode.setText("");
				//txtCsID.setText("");
				//txtCheckin.setText("");
				//txtCheckout.setText("");
				//txtPetID.setText("");
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
		{if(tableReservOrder.getSelectedRow() == -1) 
			return;
			String sql = "DELETE FROM reservation_order "
					+ " WHERE RESERV_NUM=? ";
			
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1,txtReservNum.getText().trim());
			
			if(pre.executeUpdate() != -1)
				{
				JOptionPane.showMessageDialog(this, "ลบรายการสำเร็จ","ผลการบันทึกรายการ",JOptionPane.INFORMATION_MESSAGE);
				showData();
				txtReservNum.setEditable(true);
				txtReservNum.setText("");
				//txtServCode.setText("");
				//txtCsID.setText("");
				//txtCheckin.setText("");
				//txtCheckout.setText("");
				//txtPetID.setText("");
				}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ReserveOrderForm ReserveOrderFormFrame = new ReserveOrderForm();
		ReserveOrderFormFrame.setSize(800,600);
		ReserveOrderFormFrame.setTitle("หน้าจอข้อมูลการจอง (ReserveOrderForm)");
		ReserveOrderFormFrame.setLocationRelativeTo(null);
		ReserveOrderFormFrame.setVisible(true);
		ReserveOrderFormFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		

	}
	public class DateLabelFormatter extends AbstractFormatter {

	    private String datePattern = "yyyy-MM-dd";
	    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	    @Override
	    public Object stringToValue(String text) throws ParseException {
	        return dateFormatter.parseObject(text);
	    }

	    @Override
	    public String valueToString(Object value) throws ParseException {
	        if (value != null) {
	            Calendar cal = (Calendar) value;
	            return dateFormatter.format(cal.getTime());
	        }

	        return "";
	    }
}
}