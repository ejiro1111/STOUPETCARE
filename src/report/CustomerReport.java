package report;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
//import org.jdesktop.swingx.JXDatePicker;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import PetCare.MyConnect;
import customFont.customFont;
import menuwindow.ReportMenu;




public class CustomerReport extends JFrame {
	
	Connection con = MyConnect.getConnection();
	private static final Border Border = null;
	private DefaultTableModel modelCustomer;
	JPanel panelSearch, panelForm, panelButton, panelTable, panelsearch_form, panelcenter1, panelcenter2;
	JLabel txt_sdate, txt_ldate, txt_1, txt_2, txt_3, txt_4, txt_5;
	Date picker1, picker2;
	//JXDatePicker date_1, date_2;
	JButton cmd_search, cmd_back;
	JTable Table_Main;
	String strDate1, strDate2, showDate1, showDate2;
	static Font f2 = customFont.normalFont;
	SqlDateModel dateModel,dateModel2;
	Properties p;
	JDatePanelImpl datePanel,datePanel2;
	public JDatePickerImpl datePicker,datePicker2;
	
	/*Color isMe = new Color(11, 119, 219);
	Color myWhite = new Color(255, 255, 255);*/
	
	//public static Icon icone = new ImageIcon("C:/Users/Admin/eclipse-workspace/STOUPETCARE_2/src/report/pic22.jpg"); //path icon image
	public static DateFormat dateFormat  = new SimpleDateFormat("dd-MM-yyyy",Locale.US);
	
	public CustomerReport() {
		
		//UIManager.put("JXDatePicker.arrowIcon", icone);
		UIManager.put("Label.font", f2);
		UIManager.put("TextField.font", f2);
		UIManager.put("Button.font", f2);
		UIManager.put("Table.font", f2);
		UIManager.put("TableHeader.font", f2);
		Container a = this.getContentPane();
		a.setLayout(new BorderLayout());
		
		panelSearch = new JPanel();
		panelSearch.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelSearch.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panelSearch.setBorder(BorderFactory.createTitledBorder(null, "ค้นหาข้อมูล", NORMAL, NORMAL, f2));
		
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
		
		SqlDateModel  dateModel2 = new SqlDateModel();
		dateModel2.setSelected(true);
		JDatePanelImpl datePanel2 = new JDatePanelImpl(dateModel2,p);
		JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2,new DateLabelFormatter());
		this.datePanel2 = datePanel2;
		this.datePicker2 = datePicker2;
		
		/*date_1 = new JXDatePicker();
		date_1.setDate(Calendar.getInstance().getTime());
		date_1.setFormats(dateFormat);
		date_1.setPreferredSize(new Dimension(110, 30));
		date_1.setBorder(new LineBorder(new Color(51, 204, 255),1));
		date_1.getComponent(0).setPreferredSize(new Dimension(100,100)); //JFormattedTextField
		date_1.getComponent(1).setPreferredSize(new Dimension(10,100));//JButton
		
		date_2 = new JXDatePicker();
		date_2.setDate(Calendar.getInstance().getTime());
		date_2.setFormats(dateFormat);
		date_2.setPreferredSize(new Dimension(110, 30));
		date_2.setBorder(new LineBorder(new Color(51, 204, 255),1));
		date_2.getComponent(0).setPreferredSize(new Dimension(100,100)); //JFormattedTextField
		date_2.getComponent(1).setPreferredSize(new Dimension(10,100));//JButton */
		
		txt_sdate = new JLabel("   ตั้งแต่วันที่");
		txt_sdate.setPreferredSize(new Dimension(80, 30));
		txt_ldate = new JLabel("    ถึงวันที่");
		txt_ldate.setPreferredSize(new Dimension(80, 30));
		cmd_search = new JButton("ค้นหา");
		/*cmd_search.setBackground(isMe);
		cmd_search.setForeground(myWhite);*/
		
		panelForm = new JPanel();
		panelForm.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		panelForm.add(txt_sdate); 
		panelForm.add(datePicker);
		//panelForm.add(date_1); 
		panelForm.add(txt_ldate); 
		panelForm.add(datePicker2);
		//panelForm.add(date_2); 
		panelForm.add(cmd_search);
		
		panelSearch.add(panelForm);
		
		panelsearch_form = new JPanel();
		panelsearch_form.add(panelSearch);
		
		panelcenter1 = new JPanel(new GridLayout(2, 3, 12, 12));
		panelcenter1.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 5));
		txt_1 = new JLabel("รายงานข้อมูลลูกค้าที่ใช้บริการ ระหว่าง");
		txt_2 = new JLabel("");
		txt_2.setForeground(Color.red);
		txt_3 = new JLabel("");
		txt_4 = new JLabel("จำนวนลูกค้าที่ใช้บริการ");
		txt_5 = new JLabel("");
		panelcenter1.add(txt_1); panelcenter1.add(txt_2); panelcenter1.add(txt_3);
		panelcenter1.add(txt_4); panelcenter1.add(txt_5);

		cmd_search.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				DateFormat dateFormats  = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
				picker1 = (Date) datePicker.getModel().getValue();
				strDate1 = dateFormats.format(picker1);
				showDate1 = dateFormat.format(picker1);
			
				picker2 = (Date) datePicker2.getModel().getValue();
				strDate2 = dateFormats.format(picker2);
				showDate2 = dateFormat.format(picker2);
				txt_2.setText("วันที่  " + showDate1 + "  ถึงวันที่  " + showDate2);
				
				showData(strDate1, strDate2);
			}
		});
		
		panelTable = new JPanel();
		JScrollPane scrollTable = new JScrollPane();
		scrollTable.setPreferredSize(new Dimension(700, 150));
		Object data[][] = {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null}
		};
		String columns [] = {"หมายเลขลูกค้า", "ชื่อลูกค้า", "ที่อยู่", "โทรศัพท์", "อีเมล"};
		DefaultTableModel tableModel = new DefaultTableModel(data, columns) {
			public boolean isCallEditable(int row, int column) {
				return false;
			}
		};
		Table_Main = new JTable(tableModel){
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
		        Component returnComp = super.prepareRenderer(renderer, row, column);
		    	Color isMes = new Color(187, 234, 255);
		    	Color myWhite = new Color(255, 255, 255);
		        if (!returnComp.getBackground().equals(getSelectionBackground())){
		            Color bg = (row % 2 == 0 ? isMes : myWhite);
		            returnComp .setBackground(bg);
		            bg = null;
		        }
		        return returnComp;
			}};
		/*Table_Main.getTableHeader().setBackground(isMe);
		Table_Main.getTableHeader().setForeground(myWhite);*/

		Table_Main.setModel(tableModel);
//		Table_Main.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent me) {
//				int index = Table_Main.getSelectedRow();
//				str_num.setEditable(false);
//				str_num.setText(Table_Main.getValueAt(index, 0).toString());
//				str_name.setText(Table_Main.getValueAt(index, 1).toString());
//				str_addr.setText(Table_Main.getValueAt(index, 2).toString());
//				str_phone.setText(Table_Main.getValueAt(index, 3).toString());
//				str_email.setText(Table_Main.getValueAt(index, 4).toString());
//			}
//		});
		Table_Main.getColumnModel().getColumn(0).setPreferredWidth(40);
		Table_Main.getColumnModel().getColumn(2).setPreferredWidth(150);
		Table_Main.getTableHeader().setPreferredSize(new Dimension(0, 35));
		scrollTable.setViewportView(Table_Main);
		panelTable.add(scrollTable);
		
		panelButton = new JPanel(new FlowLayout());
		cmd_back = new JButton("กลับสู่เมนูหลัก");
		cmd_back.addActionListener(e->{
			ReportMenu rm = new ReportMenu();
			rm.setVisible(true);
			this.setVisible(false);
		});
		panelButton.add(cmd_back);
		
		panelcenter2 = new JPanel(new GridLayout(2, 1, 10, 10));
		panelcenter2.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 5));
		panelcenter2.add(panelsearch_form);
		panelcenter2.add(panelcenter1); //panelSearch

		a.add(panelcenter2, BorderLayout.PAGE_START);
		a.add(panelTable, BorderLayout.CENTER); 
		a.add(panelButton, BorderLayout.PAGE_END);
		
		modelCustomer = (DefaultTableModel) Table_Main.getModel();
	}
	
	public void showData (String search1, String search2) {
		try {
			int totalRow = Table_Main.getRowCount() -1;
			while(totalRow > -1) {
				modelCustomer.removeRow(totalRow);
				totalRow--;
			}
			String sql = "SELECT * FROM reservation_order, customer"
							+ " WHERE reservation_order.CS_ID = customer.CS_ID"
							+" AND DATE(CHECK_IN) between DATE('" + search1 + "') AND DATE('" + search2 + "')";
			ResultSet rs = con.createStatement().executeQuery(sql);
			int row = 0;
			while(rs.next()) {
				modelCustomer.addRow(new Object[0]);
				modelCustomer.setValueAt(rs.getString("customer.CS_ID"), row, 0);
				modelCustomer.setValueAt(rs.getString("CS_NAME"), row, 1);
				modelCustomer.setValueAt(rs.getString("CS_ADDR"), row, 2);
				modelCustomer.setValueAt(rs.getString("CS_PHONE"), row, 3);
				modelCustomer.setValueAt(rs.getString("CS_MAIL"), row, 4);
				row++;
			}
			Table_Main.setModel(modelCustomer);
			txt_5.setText(String.valueOf(row) + "    คน");

		} catch(Exception e) {
			e.printStackTrace();
		}
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
	
	public static void main(String[] args) {
		CustomerReport frame = new CustomerReport();
		SwingUtilities.updateComponentTreeUI(frame);
		frame.setTitle("รายงานข้อมูลลูกค้า (CustomerReport)");
		frame.setLocationRelativeTo(null);
		frame.setSize(900, 500);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}