package report;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;


import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
//import org.jdesktop.swingx.JXDatePicker;
import customFont.customFont;
import menuwindow.ReportMenu;
import PetCare.MyConnect;



public class FinancialReport extends JFrame {
	SqlDateModel dateModel,dateModel2;
	Properties p;
	JDatePanelImpl datePanel,datePanel2;
	public JDatePickerImpl datePicker,datePicker2;
	
	JPanel panelSearch, panelForm, panelButton, panelsearch_form, panel1;
	JLabel txt_sdate, txt_ldate, txt_1, txt_2, txt_3, txt_4, txt_5, txt_6, txt_7, txt_8, txt_9, txt_10, txt_11, txt_12, txt_13, txt_14, txt_15, txt_16, txt_17, txt_18,
	txt_19, txt_20, txt_21, txt_22, txt_23, txt_24, txt_25, txt_26, txt_27, txt_28, txt_29, txt_30, txt_31, txt_32, txt_33, txt_34, txt_35, txt_36, txt_37,
	txt_38, txt_39, txt_40, txt_41, txt_42;
	Date picker1, picker2;
	//JXDatePicker date_1, date_2;
	JButton cmd_search, cmd_backMainmenu;
	String strDate1, strDate2, showDate1, showDate2;
	Connection connect = MyConnect.getConnection();
	static Font f2 = customFont.normalFont;
	//Color isMe = new Color(11, 119, 219);
	//Color myWhite = new Color(255, 255, 255);
	
	//public static Icon icone = new ImageIcon("C:\\Users\\MasterJas\\eclipse-workspace\\pic22.png"); //path icon image
	public static DateFormat dateFormat  = new SimpleDateFormat("dd-MM-yyyy",Locale.US);
	
	public FinancialReport() {
		UIManager.put("Label.font", f2);
		UIManager.put("TextField.font", f2);
		UIManager.put("Button.font", f2);
		
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
		date_2.getComponent(1).setPreferredSize(new Dimension(10,100));//JButton*/
		
		txt_sdate = new JLabel("   ตั้งแต่วันที่");
		txt_sdate.setPreferredSize(new Dimension(80, 30));
		txt_ldate = new JLabel("    ถึงวันที่");
		txt_ldate.setPreferredSize(new Dimension(80, 30));
		cmd_search = new JButton("ค้นหา");
		//cmd_search.setBackground(isMe);
		//cmd_search.setForeground(myWhite);
		
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

		panel1 = new JPanel(new GridLayout(14, 1, 0, 0));

		txt_1 = new JLabel("     รายรับจากการให้บริการ ประจำ");
		txt_2 = new JLabel(" ");
		txt_2.setForeground(Color.red);
		txt_3 = new JLabel(" ");
		txt_4 = new JLabel("     รายรับจากค่าบริการรับฝาก");
		txt_4.setForeground(Color.blue);
		txt_5 = new JLabel("");
		txt_6 = new JLabel("");
		txt_7 = new JLabel("");
		txt_8 = new JLabel("สุนัข");
		txt_9 = new JLabel("");
		txt_10 = new JLabel("");
		txt_11 = new JLabel("แมว");
		txt_12 = new JLabel("");
		txt_13 = new JLabel("");
		txt_14 = new JLabel("กระต่าย");
		txt_15 = new JLabel("");
		txt_16 = new JLabel("     รวมรายรับค่าบริการรับฝาก");
		txt_17 = new JLabel("");
		txt_18 = new JLabel("");

		txt_19 = new JLabel("______________________________________________________________");
		txt_20 = new JLabel("______________________________________________________________");
		txt_21 = new JLabel("______________________________________________________________");
		txt_19.setForeground(Color.blue); txt_20.setForeground(Color.blue); txt_21.setForeground(Color.blue);
		
		txt_22 = new JLabel("     รายรับจากค่าบริการเพิ่มเติม");
		txt_22.setForeground(Color.blue);
		txt_23 = new JLabel("");
		txt_24 = new JLabel("");
		txt_25 = new JLabel("");
		txt_26 = new JLabel("ค่าบริการเพิ่มเติม");
		txt_27 = new JLabel("");
		txt_28 = new JLabel("     รวมรายรับค่าบริการเพิ่มเติม");
		txt_29 = new JLabel("");
		txt_30 = new JLabel("");

		txt_31 = new JLabel("______________________________________________________________");
		txt_32 = new JLabel("______________________________________________________________");
		txt_33 = new JLabel("______________________________________________________________");
		txt_31.setForeground(Color.blue); txt_32.setForeground(Color.blue); txt_33.setForeground(Color.blue); 
		

		txt_34 = new JLabel("     รายรับจากการให้บริการ");
		txt_34.setForeground(Color.blue);
		txt_35 = new JLabel("");
		txt_36 = new JLabel("");

		txt_37 = new JLabel("______________________________________________________________");
		txt_38 = new JLabel("______________________________________________________________");
		txt_39 = new JLabel("______________________________________________________________");
		txt_37.setForeground(Color.blue); txt_38.setForeground(Color.blue); txt_39.setForeground(Color.blue); 

		panel1.add(txt_1); panel1.add(txt_2); panel1.add(txt_3);
		panel1.add(txt_4); panel1.add(txt_5); panel1.add(txt_6); 
		panel1.add(txt_7); panel1.add(txt_8); panel1.add(txt_9);
		panel1.add(txt_10); panel1.add(txt_11); panel1.add(txt_12); 
		panel1.add(txt_13); panel1.add(txt_14); panel1.add(txt_15); 
		panel1.add(txt_16); panel1.add(txt_17); panel1.add(txt_18); 
		panel1.add(txt_19); panel1.add(txt_20); panel1.add(txt_21); 
		panel1.add(txt_22); panel1.add(txt_23); panel1.add(txt_24); 
		panel1.add(txt_25); panel1.add(txt_26); panel1.add(txt_27); 
		panel1.add(txt_28); panel1.add(txt_29); panel1.add(txt_30); 
		panel1.add(txt_31); panel1.add(txt_32); panel1.add(txt_33); 
		panel1.add(txt_34); panel1.add(txt_35); panel1.add(txt_36); 
		panel1.add(txt_37); panel1.add(txt_38); panel1.add(txt_39); 

		panelButton = new JPanel(new FlowLayout());
		cmd_backMainmenu = new JButton("กลับสู่เมนูหลัก");
		cmd_backMainmenu.addActionListener(e->{
			ReportMenu rm = new ReportMenu();
			rm.setVisible(true);
			this.setVisible(false);
		});
		panelButton.add(cmd_backMainmenu);
		
		a.add(panelsearch_form, BorderLayout.PAGE_START);
		a.add(panel1, BorderLayout.CENTER); 
		a.add(panelButton, BorderLayout.PAGE_END);
		
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
	}
	
	public void showData (String search1, String search2) {
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs_total = null;
		int price_dog1 = 0;
		int price_cat1 = 0;
		int price_rabbit1 = 0;
		int price_allpet = 0;
		int total_addprice = 0;

		try {
			String dog = "'สุนัข'";
			String cat = "'แมว'";
			String rabbit = "'กระต่าย'";

			String sql = "SELECT pet.PET_ID, reservation_order.PET_ID, pet.PET_TYPE, service_provided.RESERV_NUM,"
					+ " reservation_order.RESERV_NUM, CHECK_IN, CHECK_OUT, TOTAL_PRICE, TOTAL_CHARGE,"
					+ " SUM(TOTAL_PRICE) AS SUM_PRICE, SUM(TOTAL_CHARGE) AS SUM_CHARGE"
					+ " FROM reservation_order, pet, service_provided"
					+ " WHERE service_provided.RESERV_NUM = reservation_order.RESERV_NUM"
					+ " AND DATE(CHECK_IN) between DATE('" + search1 + "') AND DATE('" + search2 + "')"
					+ " AND reservation_order.PET_ID = pet.PET_ID";

			String sqls = sql + "  AND PET_TYPE = ";

			rs_total = connect.createStatement().executeQuery(sql);
			rs1 = connect.createStatement().executeQuery(sqls + dog);
			rs2 = connect.createStatement().executeQuery(sqls + cat);
			rs3 = connect.createStatement().executeQuery(sqls + rabbit);
			
			while(rs1.next()) {
				price_dog1 = rs1.getInt("SUM_PRICE");
				txt_9.setText(String.valueOf(price_dog1) + "  บาท");
			}
			while(rs2.next()) {
				price_cat1 = rs2.getInt("SUM_PRICE");
				txt_12.setText(String.valueOf(price_cat1) + "  บาท");
			}
			while(rs3.next()) {
				price_rabbit1 = rs3.getInt("SUM_PRICE");
				txt_15.setText(String.valueOf(price_rabbit1) + "  บาท");
			}
			
			price_allpet = price_dog1 + price_cat1 + price_rabbit1;
			txt_18.setText(String.valueOf(price_allpet) + "  บาท");
			
			while(rs_total.next()) {
				total_addprice = rs_total.getInt("SUM_CHARGE");
				txt_27.setText(String.valueOf(total_addprice) + "  บาท");
				txt_30.setText(String.valueOf(total_addprice) + "  บาท");
			}
			
			txt_36.setText(String.valueOf(price_allpet + total_addprice) + "  บาท");
			
		} catch(SQLException e) {
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
		FinancialReport frame = new FinancialReport();
		//UIManager.put("JXDatePicker.arrowIcon", icone);
		SwingUtilities.updateComponentTreeUI(frame);
		frame.setTitle("รายงานข้อมูลทางการเงิน (FinancialReport)");
		frame.setLocationRelativeTo(null);
		frame.setSize(800, 750);		
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}