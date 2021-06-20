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
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
//import org.jdesktop.swingx.JXDatePicker;

import PetCare.MyConnect;
import customFont.customFont;
import menuwindow.ReportMenu;





public class OperationReport extends JFrame {
	private static final Border Border = null;
	JPanel panelSearch, panelForm, panelButton, panelsearch_form, panelReport;
	JTextField txtSearchMount,txtSearchWeek,txtSearchDay,txtUser,txtDog,txtCat,txtRappid,txtAnimal,txtList,txtUseCamera,txtCamera,txtTotalCamera;
	JLabel txtMount,lbReport2;
	JButton cmd_search, cmd_backMainmenu;
	Connection connect = MyConnect.getConnection();
	
	Date picker1, picker2;
	//JXDatePicker date_1, date_2;
	
	static Font f2 = customFont.normalFont;
	SqlDateModel dateModel,dateModel2;
	Properties p;
	JDatePanelImpl datePanel,datePanel2;
	public JDatePickerImpl datePicker,datePicker2;
	
	//Color isMe = new Color(11, 119, 219);
	//Color myWhite = new Color(255, 255, 255);
	
	JLabel txt_sdate, txt_ldate;
	String strDate1, strDate2, showDate1, showDate2;
	
	//public static Icon icon = new ImageIcon("C:/Users/Admin/eclipse-workspace/STOUPETCARE_2/src/report/pic22.jpg"); //path icon image
	public static DateFormat dateFormat  = new SimpleDateFormat("dd-MM-yyyy",Locale.US);
	
	public OperationReport() {
		Container c_operation = this.getContentPane();
		c_operation.setLayout(new BorderLayout());
		
		UIManager.put("Label.font", f2);
		UIManager.put("TextField.font", f2);
		UIManager.put("Button.font", f2);
		UIManager.put("Table.font", f2);
		UIManager.put("TableHeader.font", f2);
		
		panelSearch = new JPanel();
		panelSearch.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelSearch.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panelSearch.setBorder(BorderFactory.createTitledBorder(null, "ค้นหา", NORMAL, NORMAL, f2));
	    
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
		cmd_search.setForeground(myWhite);
		*/
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
		
		panelReport = new JPanel();
		panelReport.setLayout(new GridLayout(12,3,10,5));
		panelReport.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		//1
		JLabel lbReport1 = new JLabel("รายงานผลการดำเนินงาน");
		lbReport1.setFont(new Font("Tahoma",Font.PLAIN,12));
		panelReport.add(lbReport1);
		lbReport2 = new JLabel("");
		lbReport2.setFont(new Font("Tahoma",Font.PLAIN,12));
		panelReport.add(lbReport2);
		JLabel lbReport31 = new JLabel("");
		panelReport.add(lbReport31);
		
		//2
		JLabel lbReport3 = new JLabel("จำนวนลูกค้าที่ใช้บริการ");
		lbReport3.setFont(new Font("Tahoma",Font.BOLD,16));
		lbReport3.setForeground(Color.BLUE);
		panelReport.add(lbReport3);
		txtUser = new JTextField("");
		txtUser.setPreferredSize(new Dimension(100,30));
		panelReport.add(txtUser);
		JLabel lbReport4 = new JLabel("คน");
		lbReport4.setFont(new Font("Tahoma",Font.PLAIN,12));
		panelReport.add(lbReport4);
		
		//3
		JLabel lbReport5 = new JLabel("จำนวนสัตว์เลี่ยง");
		lbReport5.setFont(new Font("Tahoma",Font.BOLD,16));
		lbReport5.setForeground(Color.BLUE);
		panelReport.add(lbReport5);
		JLabel lbReport6 = new JLabel("");
		panelReport.add(lbReport6);
		JLabel lbReport7 = new JLabel("");
		panelReport.add(lbReport7);
		
		//4
		JLabel lbReport8 = new JLabel("                              สุนัข");
		lbReport8.setFont(new Font("Tahoma",Font.PLAIN,12));
		panelReport.add(lbReport8);
		txtDog = new JTextField("");
		txtDog.setPreferredSize(new Dimension(100,30));
		panelReport.add(txtDog);
		JLabel lbReport9 = new JLabel("");
		panelReport.add(lbReport9);
		
		//5
		JLabel lbReport10 = new JLabel("                              แมว");
		lbReport10.setFont(new Font("Tahoma",Font.PLAIN,12));
		panelReport.add(lbReport10);
		txtCat = new JTextField("");
		txtCat.setPreferredSize(new Dimension(100,30));
		panelReport.add(txtCat);
		JLabel lbReport11 = new JLabel("");
		panelReport.add(lbReport11);
		
		//6
		JLabel lbReport12 = new JLabel("                              กระต่าย");
		lbReport12.setFont(new Font("Tahoma",Font.PLAIN,12));
		panelReport.add(lbReport12);
		txtRappid = new JTextField("");
		txtRappid.setPreferredSize(new Dimension(100,30));
		panelReport.add(txtRappid);
		JLabel lbReport13 = new JLabel("");
		panelReport.add(lbReport13);
		
		//7
		JLabel lbReport14 = new JLabel("                              รวม");
		lbReport14.setFont(new Font("Tahoma",Font.PLAIN,12));
		panelReport.add(lbReport14);
		txtAnimal = new JTextField("");
		txtAnimal.setPreferredSize(new Dimension(100,30));
		panelReport.add(txtAnimal);
		JLabel lbReport15 = new JLabel("ตัว");
		lbReport15.setFont(new Font("Tahoma",Font.PLAIN,12));
		panelReport.add(lbReport15);
		
		//8
		JLabel lbReport16 = new JLabel("จำนวนรายการที่ให้บริการ");
		lbReport16.setFont(new Font("Tahoma",Font.BOLD,16));
		lbReport16.setForeground(Color.BLUE);
		panelReport.add(lbReport16);
		txtList = new JTextField("");
		txtList.setPreferredSize(new Dimension(100,30));
		panelReport.add(txtList);
		JLabel lbReport17 = new JLabel("รายการ");
		lbReport17.setFont(new Font("Tahoma",Font.PLAIN,12));
		panelReport.add(lbReport17);
		
		//9
		JLabel lbReport18 = new JLabel("จำนวนกล้องวงจรปิด");
		lbReport18.setFont(new Font("Tahoma",Font.BOLD,16));
		lbReport18.setForeground(Color.BLUE);
		panelReport.add(lbReport18);
		JLabel lbReport19 = new JLabel("");
		panelReport.add(lbReport19);
		JLabel lbReport20 = new JLabel("");
		panelReport.add(lbReport20);
		
		//10
		JLabel lbReport21 = new JLabel("                              ใช้งานอยู่");
		lbReport21.setFont(new Font("Tahoma",Font.PLAIN,12));
		panelReport.add(lbReport21);
		txtUseCamera = new JTextField("");
		txtUseCamera.setPreferredSize(new Dimension(100,30));
		panelReport.add(txtUseCamera);
		JLabel lbReport33 = new JLabel("");
		panelReport.add(lbReport33);
		
		//11
		JLabel lbReport22 = new JLabel("                              ว่าง");
		lbReport22.setFont(new Font("Tahoma",Font.PLAIN,12));
		panelReport.add(lbReport22);
		txtCamera = new JTextField("");
		txtCamera.setPreferredSize(new Dimension(100,30));
		panelReport.add(txtCamera);
		JLabel lbReport34 = new JLabel("");
		panelReport.add(lbReport34);
		
		//12
		JLabel lbReport23 = new JLabel("                              รวม");
		lbReport23.setFont(new Font("Tahoma",Font.PLAIN,12));
		panelReport.add(lbReport23);
		txtTotalCamera = new JTextField("");
		txtTotalCamera.setPreferredSize(new Dimension(100,30));
		panelReport.add(txtTotalCamera);
		JLabel lbReport24 = new JLabel("ตัว");
		lbReport24.setFont(new Font("Tahoma",Font.PLAIN,12));
		panelReport.add(lbReport24);
		
		panelButton = new JPanel(new FlowLayout());
		cmd_backMainmenu = new JButton("กลับสู่เมนูหลัก");
		cmd_backMainmenu.addActionListener(e->{
			ReportMenu rm = new ReportMenu();
			rm.setVisible(true);
			this.setVisible(false);
		});
		cmd_backMainmenu.setFont(new Font("Tahoma",Font.PLAIN,12));
		panelButton.add(cmd_backMainmenu);
		
		c_operation.add(panelsearch_form,BorderLayout.PAGE_START);
		c_operation.add(panelReport,BorderLayout.CENTER);
		c_operation.add(panelButton,BorderLayout.PAGE_END);
		
		cmd_search.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				DateFormat dateFormats  = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
				picker1 = (Date) datePicker.getModel().getValue();
				strDate1 = dateFormats.format(picker1);
				showDate1 = dateFormat.format(picker1);
			
				picker2 = (Date) datePicker2.getModel().getValue();
				strDate2 = dateFormats.format(picker2);
				showDate2 = dateFormat.format(picker2);
				lbReport2.setText("วันที่  " + showDate1 + "  ถึงวันที่  " + showDate2);
				
				showData(strDate1, strDate2);
			}
		});
	}
	
	public void showData (String search1, String search2) {
		ResultSet rs1 = null;
		ResultSet rs2 = null;

		try {
			
			String sql_rs1 = "SELECT COUNT(CASE WHEN CAM_STATUS = 'Enable' THEN 1 END) as CAM_USE,"
					+ " COUNT(CAM_NUM) as CAM_TOTAL"
					+ " FROM camera";
	
			rs1 = connect.createStatement().executeQuery(sql_rs1);
			while(rs1.next()) {
				txtUseCamera.setText(rs1.getString("CAM_USE"));
				txtTotalCamera.setText(rs1.getString("CAM_TOTAL"));
				txtCamera.setText(String.valueOf(rs1.getInt("CAM_TOTAL")-rs1.getInt("CAM_USE")));
			}
			
			String sql_rs2 = "SELECT COUNT(service_provided.SERVPRO_NUM) as SERVICE_TOTAL,"
					+" COUNT(reservation_order.CS_ID) as SERVICE_CUSTOMER,"
					+" COUNT(CASE WHEN pet.PET_ID = '1' THEN 1 END) as DOG_TOTAL,"
					+" COUNT(CASE WHEN pet.PET_ID = '2' THEN 1 END) as RABBIT_TOTAL,"
					+" COUNT(CASE WHEN pet.PET_ID = '3' THEN 1 END) as CAT_TOTAL"
					+" FROM invoice"
					+" INNER JOIN service_provided"
					+" ON invoice.SERVPRO_NUM = service_provided.SERVPRO_NUM"
					+" INNER JOIN reservation_order"
					+" ON service_provided.RESERV_NUM = reservation_order.RESERV_NUM"
					+" INNER JOIN service_available"
					+" ON reservation_order.SERV_CODE = service_available.SERV_CODE"
					+" INNER JOIN pet"
					+" ON reservation_order.PET_ID = pet.PET_ID"
					+" WHERE INV_DATE BETWEEN '"+ search1 +"' AND '"+ search2 +"'";
			rs2 = connect.createStatement().executeQuery(sql_rs2);
			while(rs2.next()) {
				txtUser.setText(rs2.getString("SERVICE_CUSTOMER"));
				txtList.setText(rs2.getString("SERVICE_TOTAL"));
				txtDog.setText(rs2.getString("DOG_TOTAL"));
				txtRappid.setText(rs2.getString("RABBIT_TOTAL"));
				txtCat.setText(rs2.getString("CAT_TOTAL"));
				txtAnimal.setText(String.valueOf(rs2.getInt("DOG_TOTAL")+rs2.getInt("RABBIT_TOTAL")+rs2.getInt("CAT_TOTAL")));
			}
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
		// TODO Auto-generated method stub
		OperationReport frame = new OperationReport();
		//UIManager.put("JXDatePicker.arrowIcon", icon);
		SwingUtilities.updateComponentTreeUI(frame);
		frame.setTitle("รายงานข้อมูลการดำเนินงาน (OperationReport)");
		frame.setLocationRelativeTo(null);
		frame.setSize(800, 720);		
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
