package ManagementForm;

import java.awt.*;
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

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.table.*;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import org.jdatepicker.impl.UtilDateModel;

import PetCare.MyConnect;
import customFont.customFont;
import menuwindow.ManagementMenu;


public class InvoiceForm extends JFrame 
{
	Connection con = MyConnect.getConnection();
	private DefaultTableModel modelInv;
	JTextField txtINV_NUM, txtSERVPRO_NUM, txtINV_DATE, txtINV_DUE,  txtSearch;
	JTable tableInv;
	JComboBox servProBox;
	Font fn1 = customFont.normalFont;
	SqlDateModel model, model2;
	public JDatePickerImpl datePicker, datePicker2;
	
	public InvoiceForm()
	{
		UIManager.put("Label.font", fn1);
		UIManager.put("TextField.font", fn1);
		UIManager.put("ComboBox.font", fn1);
		UIManager.put("Button.font", fn1);
		UIManager.put("Table.font", fn1);
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		
		JPanel panelLeft = new JPanel();
		panelLeft.setLayout(new GridLayout(4,2,0,10));
		panelLeft.setBorder(BorderFactory.createEmptyBorder(10,0,0,100));
		panelLeft.setPreferredSize(new Dimension(500,150));
		
		JLabel lbINV_NUM = new JLabel("หมายเลขใบแจ้งหนี้ :");               
		panelLeft.add(lbINV_NUM);	
		txtINV_NUM = new JTextField();																	//INV_NUM
		panelLeft.add(txtINV_NUM);
		
		JLabel custName = new JLabel("หมายเลขการให้บริการ :");           
		panelLeft.add(custName);
		JComboBox servProBox = new JComboBox();
		try {
			
			String sql2 = "SELECT SERVPRO_NUM FROM service_provided";
			
			ResultSet rs2 = con.createStatement().executeQuery(sql2);
			int row2 = 0;
			while (rs2.next()) {
				servProBox.addItem(rs2.getString("SERVPRO_NUM"));
				row2++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	this.servProBox = servProBox ;
	panelLeft.add(servProBox);
		//txtSERVPRO_NUM = new JTextField();																//SERVPRO_NUM
		//panelLeft.add(txtSERVPRO_NUM);
		
		JLabel lbINV_DATE = new JLabel("วันที่ออก :");     
		panelLeft.add(lbINV_DATE);
		
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
	
		SqlDateModel model = new SqlDateModel();
		model.setSelected(true);
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		JFormattedTextField txtINV_DATE = datePicker.getJFormattedTextField();
		txtINV_DATE.setFont(fn1);
		this.datePicker = datePicker;
		panelLeft.add(datePicker);
		//txtINV_DATE = new JTextField();																	//INV_DATE
		//panelLeft.add(txtINV_DATE);
		
		
		
		JLabel lbINV_DUE = new JLabel("วันครบกำหนด :");                        
		panelLeft.add(lbINV_DUE);	
		
		SqlDateModel model2 = new SqlDateModel();
		model2.setSelected(true);
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p);
		JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
		JFormattedTextField txtINV_DUE = datePicker2.getJFormattedTextField();
		txtINV_DUE.setFont(fn1);
		this.datePicker2 = datePicker2;
		panelLeft.add(datePicker2);
		
		//txtINV_DUE = new JTextField();																//INV_DUE
		//panelLeft.add(txtINV_DUE);
	
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
		panelNorth.setBorder(BorderFactory.createEmptyBorder(50,50,5,50)); // ระยะห่างจากขอบ	
		panelNorth.add(panelLeft);
		panelNorth.add(panelRight);
		
		JButton addBtn = new JButton("เพิ่มข้อมูล");
		addBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				insert();
			}
		});
		panelRight.add(addBtn);
		JButton editBtn = new JButton("แก้ไขข้อมูล");
		editBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				update();
			}
		});
		panelRight.add(editBtn);
		JButton delBtn = new JButton("ลบข้อมูล");
		delBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				delete();
			}
		});
		panelRight.add(delBtn);
		JButton backBtn = new JButton("กลับสู่เมนูหลัก");
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
		tableInv = new JTable();
		Object data[][]= 
			{
				{null,null,null,null},
				{null,null,null,null},
				{null,null,null,null},
				{null,null,null,null},
				{null,null,null,null},
		};
		String column[]= {"หมายเลขใบแจ้งหนี้","หมายเลขการให้บริการ","วันที่ออก","วันครบกำหนด"};
		JTableHeader header = tableInv.getTableHeader();  //getHeader
		header.setFont(fn1);  //SetFontHeader
		
		DefaultTableModel tablemodel = new DefaultTableModel(data,column)
				{
					public boolean isCellEditable(int row,int column) {
							return false;
					}
				};	
				tableInv.setModel(tablemodel);
				tableInv.addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent me)
					{
						int index = tableInv.getSelectedRow();
						txtINV_NUM.setEditable(false);
						txtINV_NUM.setText(tableInv.getValueAt(index, 0).toString());
						servProBox.setSelectedItem(tableInv.getValueAt(index, 1).toString());
						//txtSERVPRO_NUM.setText(tableInv.getValueAt(index, 1).toString());
						datePicker.getModel().setDay(Integer.parseInt(tableInv.getValueAt(index, 2).toString().substring(8,10)));
						datePicker.getModel().setMonth(Integer.parseInt(tableInv.getValueAt(index, 2).toString().substring(5,7))-1);
						datePicker.getModel().setYear(Integer.parseInt(tableInv.getValueAt(index, 2).toString().substring(0,4)));
						//txtINV_DATE.setText(tableInv.getValueAt(index, 2).toString());
						datePicker2.getModel().setDay(Integer.parseInt(tableInv.getValueAt(index, 3).toString().substring(8,10)));
						datePicker2.getModel().setMonth(Integer.parseInt(tableInv.getValueAt(index, 3).toString().substring(5,7))-1);
						datePicker2.getModel().setYear(Integer.parseInt(tableInv.getValueAt(index, 3).toString().substring(0,4)));
						//txtINV_DUE.setText(tableInv.getValueAt(index, 3).toString());
						
					}
				});
				scrollTable.setViewportView(tableInv);
				panelTable.add(scrollTable);
							
		c.add(panelNorth,BorderLayout.PAGE_START);
		c.add(panelTable,BorderLayout.PAGE_END);		
		modelInv = (DefaultTableModel)tableInv.getModel();
		showData();
	}
	
	public void showData() {
		try {
			int totalRow = tableInv.getRowCount() - 1;
			while (totalRow > -1) {
				modelInv.removeRow(totalRow);
				totalRow--;
			}
			String search = txtSearch.getText().trim();
			String sql = "SELECT * FROM invoice"
			+" WHERE INV_NUM LIKE '%" + search + "%'"
			+" OR SERVPRO_NUM LIKE '%" + search + "%'"
			+" OR INV_DATE LIKE '%" + search + "%'"
			+" OR INV_DUE LIKE '%" + search + "%'";

			ResultSet rs = con.createStatement().executeQuery(sql);
			int row = 0;
			while (rs.next()) {
				modelInv.addRow(new Object[0]);
				modelInv.setValueAt(rs.getString("INV_NUM"), row, 0);
				modelInv.setValueAt(rs.getString("SERVPRO_NUM"), row, 1);
				modelInv.setValueAt(rs.getString("INV_DATE"), row, 2);
				modelInv.setValueAt(rs.getString("INV_DUE"), row, 3);
				row++;
			}
			tableInv.setModel(modelInv);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insert()
	{
		try
		{
			Date selectedDate = (Date) datePicker.getModel().getValue();
			
			String sql = "INSERT INTO invoice VALUES (?,?,?,?)";
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1,txtINV_NUM.getText().trim());
			pre.setString(2,servProBox.getSelectedItem().toString().trim());
			//pre.setString(2,txtSERVPRO_NUM.getText().trim());
			//pre.setString(3,txtINV_DATE.getText().trim());
			//pre.setString(4,txtINV_DUE.getText().trim());
			pre.setDate(3,(Date) datePicker.getModel().getValue());
			pre.setDate(4,(Date) datePicker2.getModel().getValue());
			
			
		
			
			
			if(pre.executeUpdate() != -1)
			{
				JLabel msg = new JLabel("บันทึกรายการสำเร็จ");
				msg.setFont(fn1);
				JOptionPane.showMessageDialog(this, msg,"ผลการบันทึกรายการ",JOptionPane.INFORMATION_MESSAGE);
				showData();
				txtINV_NUM.setText("");
				//txtSERVPRO_NUM.setText("");
				//txtINV_DATE.setText("");
				//txtINV_DUE.setText("");	
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
		{if(tableInv.getSelectedRow() == -1) 
			return;
			String sql = "UPDATE invoice SET "
					+ " SERVPRO_NUM=?, "
					+ " INV_DATE=?, "
					+ " INV_DUE=? "
					+ " WHERE INV_NUM=? ";
			
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1,servProBox.getSelectedItem().toString().trim());
			//pre.setString(1,txtSERVPRO_NUM.getText().trim());
			//pre.setString(2,txtINV_DATE.getText().trim());
			//pre.setString(3,txtINV_DUE.getText().trim());
			pre.setDate(2,(Date) datePicker.getModel().getValue());
			pre.setDate(3,(Date) datePicker2.getModel().getValue());
			pre.setString(4,txtINV_NUM.getText().trim());
			
			if(pre.executeUpdate() != -1)
				{
				JLabel msg2 = new JLabel("แก้ไขรายการสำเร็จ");
				msg2.setFont(fn1);
				JOptionPane.showMessageDialog(this, msg2,"ผลการบันทึกรายการ",JOptionPane.INFORMATION_MESSAGE);
				showData();
				txtINV_NUM.setEditable(true);
				txtINV_NUM.setText("");
				//txtSERVPRO_NUM.setText("");
				//txtINV_DATE.setText("");
				//txtINV_DUE.setText("");
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
		{if(tableInv.getSelectedRow() == -1) 
			return;
			String sql = "DELETE FROM invoice "
					+ " WHERE INV_NUM=? ";
			
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1,txtINV_NUM.getText().trim());
			
			if(pre.executeUpdate() != -1)
				{
				JLabel msg3 = new JLabel("ลบรายการสำเร็จ");
				msg3.setFont(fn1);
				JOptionPane.showMessageDialog(this, msg3,"ผลการบันทึกรายการ",JOptionPane.INFORMATION_MESSAGE);
				showData();
				txtINV_NUM.setEditable(true);
				txtINV_NUM.setText("");
				//txtSERVPRO_NUM.setText("");
				//txtINV_DATE.setText("");
				//txtINV_DUE.setText("");
				}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) 
	{
		InvoiceForm frame = new InvoiceForm();
		frame.setSize(900,600);
		frame.setTitle("หน้าจอข้อมูลใบแจ้งหนี้ (InvoiceForm)");	
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(3);   
		frame.setResizable(false);
		frame.setVisible(true);		
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