package ManagementForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.File;
import java.io.FileInputStream;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;

import PetCare.MyConnect;
import menuwindow.ManagementMenu;

public class PetForm extends JFrame {
	Connection con = MyConnect.getConnection();
	private DefaultTableModel modelPet;
	JTextField txtPET_ID, txtCS_ID, txtPET_NAME, txtPET_CATEG, txtCAM_NUM, txtSearch;
	JTable tbPet;
	Font fn1 = new Font("Tahoma", Font.BOLD, 12);
	JPanel mainPanel, panelLeft, panelRight, petPanel;
	ImageIcon image1;
	String ImgPath = null;
	JLabel picLabel;

	public PetForm() {

		// set main
		setTitle(" Pet Management");
		setSize(950, 650);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		// mainPanel
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setSize(950, 650);
		mainPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.cyan.brighter(), Color.BLUE));
		add(mainPanel);

		// panel left

		petPanel = new JPanel(new GridBagLayout());
		petPanel.setPreferredSize(new Dimension(950, 300));

		JLabel lbPET_ID = new JLabel("หมายเลขสัตว์เลี้ยง :");
		lbPET_ID.setFont(fn1);
		lbPET_ID.setPreferredSize(new Dimension(150, 30));
		txtPET_ID = new JTextField();
		txtPET_ID.setPreferredSize(new Dimension(200, 30));

		JLabel lbCS_ID = new JLabel("หมายเลขลูกค้า :");
		lbCS_ID.setFont(fn1);
		lbCS_ID.setPreferredSize(new Dimension(150, 30));

		txtCS_ID = new JTextField();
		txtCS_ID.setPreferredSize(new Dimension(200, 30));

		JLabel lbPET_NAME = new JLabel("ชื่อสัตว์เลี้ยง :");
		lbPET_NAME.setFont(fn1);
		lbPET_NAME.setPreferredSize(new Dimension(150, 30));
		txtPET_NAME = new JTextField();
		txtPET_NAME.setPreferredSize(new Dimension(200, 30));

		JLabel lbPET_CATEG = new JLabel("ประเภทสัตว์เลี้ยง :");
		lbPET_CATEG.setFont(fn1);
		lbPET_CATEG.setPreferredSize(new Dimension(150, 30));
		txtPET_CATEG = new JTextField();
		txtPET_CATEG.setPreferredSize(new Dimension(200, 30));

		JLabel lbCAM_NUM = new JLabel("หมายเลขกล้อง :");
		lbCAM_NUM.setFont(fn1);
		lbCAM_NUM.setPreferredSize(new Dimension(150, 30));
		txtCAM_NUM = new JTextField();
		txtCAM_NUM.setPreferredSize(new Dimension(200, 30));

		AddPanel apPet = new AddPanel();

		apPet.addItem(petPanel, lbPET_ID, 0, 0, 2, 1, GridBagConstraints.WEST);
		apPet.addItem(petPanel, txtPET_ID, 0, 2, 4, 1, GridBagConstraints.WEST);
		apPet.addItem(petPanel, lbCS_ID, 1, 0, 2, 1, GridBagConstraints.WEST);
		apPet.addItem(petPanel, txtCS_ID, 1, 2, 4, 1, GridBagConstraints.WEST);

		apPet.addItem(petPanel, lbPET_NAME, 2, 0, 2, 1, GridBagConstraints.WEST);
		apPet.addItem(petPanel, txtPET_NAME, 2, 2, 4, 1, GridBagConstraints.WEST);
		apPet.addItem(petPanel, lbPET_CATEG, 3, 0, 2, 1, GridBagConstraints.WEST);
		apPet.addItem(petPanel, txtPET_CATEG, 3, 2, 4, 1, GridBagConstraints.WEST);

		apPet.addItem(petPanel, lbCAM_NUM, 4, 0, 2, 1, GridBagConstraints.WEST);
		apPet.addItem(petPanel, txtCAM_NUM, 4, 2, 4, 1, GridBagConstraints.WEST);

		// ----------RightJPanel----------------------------------
		panelRight = new JPanel();// RightJPanel
		panelRight.setPreferredSize(new Dimension(250, 250));
		panelRight.setLayout(new FlowLayout());

		picLabel = new JLabel();
		picLabel.setPreferredSize(new Dimension(150, 150));
		picLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		panelRight.add(picLabel);

		JButton btnChoosePic = new JButton("เลือกรูปภาพ");
		btnChoosePic.setPreferredSize(new Dimension(150, 30));
		btnChoosePic.setFont(fn1);
		btnChoosePic.addActionListener(e ->{
			choosePhoto();
		});
		panelRight.add(btnChoosePic);

		// north panel

		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(950, 200));
		northPanel.setLayout(new BorderLayout());
		northPanel.add(petPanel, BorderLayout.CENTER);
		northPanel.add(panelRight, BorderLayout.EAST);

		JPanel panelMid = new JPanel();
		JPanel panelBtn = new JPanel();
		FlowLayout layout1 = new FlowLayout();
		layout1.setHgap(20);
		panelBtn.setLayout(layout1);
		panelBtn.setPreferredSize(new Dimension(760, 60)); // ขนาด Panel
		panelBtn.setBorder(BorderFactory.createTitledBorder(null, "ค้นหา", 0, 0, fn1));

		txtSearch = new JTextField();
		txtSearch.setPreferredSize(new Dimension(180, 30));
		txtSearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent keyevent) {
					showData();
			}
		});
		panelBtn.add(txtSearch);

		Dimension btnSize = new Dimension(115, 25); // ขนาดปุ่ม
		JButton addBtn = new JButton("เพิ่มข้อมูล");
		addBtn.setFont(fn1);
		addBtn.setPreferredSize(btnSize);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					insert();
			}
		});
		panelBtn.add(addBtn);
		JButton editBtn = new JButton("แก้ไขข้อมูล");
		editBtn.setFont(fn1);
		editBtn.setPreferredSize(btnSize);
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		panelBtn.add(editBtn);
		JButton delBtn = new JButton("ลบข้อมูล");
		delBtn.setFont(fn1);
		delBtn.setPreferredSize(btnSize);
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		panelBtn.add(delBtn);
		JButton backBtn = new JButton("กลับสู่เมนูหลัก");
		backBtn.setFont(fn1);
		backBtn.setPreferredSize(btnSize);
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ManagementMenu mm = new ManagementMenu();
				mm.setVisible(true);
				setVisible(false);
			}
		});
		panelBtn.add(backBtn);
		panelMid.add(panelBtn);

		// table panel
				JPanel tablePanel = new JPanel();
				JScrollPane scrollTable = new JScrollPane();
				scrollTable.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.white, Color.BLUE));
				scrollTable.setPreferredSize(new Dimension(900,330));

				tbPet = new JTable();
				tbPet.setFont(fn1);
				tbPet.setGridColor(Color.blue.darker());
				tbPet.setPreferredSize(new Dimension(900, 330));
				tbPet.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

				String columns[] = { " หมายเลขสัตว์เลี้ยง", "หมายเลขลูกค้า", "ชื่อสัตว์เลี้ยง ", "ประเภทสัตว์เลี้ยง", "หมายเลขกล้อง" };
				Object data[][] = {};

				JTableHeader header = tbPet.getTableHeader();  //getHeader
				header.setFont(fn1);  //SetFontHeader
			
				modelPet= new DefaultTableModel(data, columns) {
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};

				tbPet.setModel(modelPet);
				tbPet.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						
						int index = tbPet.getSelectedRow();
						txtPET_ID.setEditable(false);
				
						ShowItem(index);
					}
				});

				scrollTable.setViewportView(tbPet);
				tablePanel.add(scrollTable);
				modelPet = (DefaultTableModel) tbPet.getModel();

				// add panels to mainPanel
				mainPanel.add(northPanel, BorderLayout.PAGE_START);
				mainPanel.add(panelMid, BorderLayout.CENTER);
				mainPanel.add(tablePanel, BorderLayout.PAGE_END);

				showData();
	}
	
	private void choosePhoto() {

		JFileChooser  fc = new JFileChooser(".");
	    setFileChooserFont(fc.getComponents());
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));

		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg", "png");
		
		fc.addChoosableFileFilter(filter);
		int result = fc.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fc.getSelectedFile();
			String path = selectedFile.getAbsolutePath();
			picLabel.setIcon(ResizeImage(path, null));
			ImgPath = path;
		} else {
			System.out.println("ไม่พบภาพที่เลือก");
		}
	}// end choose picture
	
	

	// Function To Resize The Image To Fit Into JLabel
	public ImageIcon ResizeImage(String ImagePath, byte[] pic) {
		ImageIcon myImage = null;

		if (ImagePath != null) {
			myImage = new ImageIcon(ImagePath);
		} else {
			myImage = new ImageIcon(pic);
		}

		Image img = myImage.getImage();
		Image img2 = img.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(img2);
		return image;

	}// end resize
	
	
	// Display Data In JTable:


		public ArrayList<Pet> getPetsList() {
			
			ArrayList<Pet> petsList = new ArrayList<Pet>();
			
			

			ResultSet rs;

			try {
				
	

				Pet pet;
				

				String search = txtSearch.getText().trim();
				
				String query = "SELECT * FROM pet "
						+"WHERE PET_ID LIKE '%" + search + "%' "
						+"OR CS_ID LIKE '%" + search + "%' "
						+"OR PET_NAME LIKE '%" + search + "%' "
						+"OR PET_TYPE LIKE '%" + search + "%' "
						+"OR CAM_NUM LIKE '%" + search + "%' ";

				
				rs = con.createStatement().executeQuery(query);


				while (rs.next()) {
					pet= new Pet(rs.getString("PET_ID"), rs.getString("CS_ID"), rs.getString("PET_NAME"),rs.getString("PET_TYPE"),
							rs.getBytes("PET_PHOTO"),rs.getString("CAM_NUM"));
					petsList.add(pet);
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			return petsList;

		}

		// check input
		public boolean checkInputs() {
			if (txtPET_ID.getText() == null || txtCS_ID.getText() == null || txtPET_NAME.getText() == null|| txtPET_CATEG.getText() == null) {
				return false;
			} else {
				try {
					return true;
				} catch (Exception ex) {
					return false;
				}
			}
		}

	// show Data

	public void showData() {
			ArrayList<Pet> list = getPetsList();
			DefaultTableModel modelPet = (DefaultTableModel) tbPet.getModel();
			
			

			modelPet.setRowCount(0);
			Object[] row = new Object[5];
			for (int i = 0; i < list.size(); i++) {
				
				row[0] = list.get(i).getPetID();
				row[1] = list.get(i).getCustomerID();
				row[2] = list.get(i).getPetName();
				row[3] = list.get(i).getPetCategory();
				row[4] = list.get(i).getCamNum();

				modelPet.addRow(row);
			}

		}
	// Show Data In Inputs
		public void ShowItem(int index) {
			txtPET_ID.setText(getPetsList().get(index).getPetID());
			txtCS_ID.setText(getPetsList().get(index).getCustomerID());
			txtPET_NAME.setText(getPetsList().get(index).getPetName());
			txtPET_CATEG.setText(getPetsList().get(index).getPetCategory());
			txtCAM_NUM.setText(getPetsList().get(index).getCamNum());
			picLabel.setIcon(ResizeImage(null, getPetsList().get(index).getPicture()));
		}

	public void insert() {
		
		if (checkInputs() && ImgPath != null) {
			try {
				
				PreparedStatement ps = con
						.prepareStatement("INSERT INTO pet (PET_ID,CS_ID,PET_NAME,PET_TYPE,PET_PHOTO,CAM_NUM)" + "values(?,?,?,?,?,?) ");
				ps.setString(1, txtPET_ID.getText());
				ps.setString(2, txtCS_ID.getText());
				ps.setString(3, txtPET_NAME.getText());
				ps.setString(4, txtPET_CATEG.getText());
				
				InputStream img = new FileInputStream(new File(ImgPath));
				ps.setBlob(5, img);
				
				ps.setString(6, txtCAM_NUM.getText());
				
				ps.executeUpdate();
			
					JLabel msg = new JLabel("บันทึกรายการสำเร็จ");
					msg.setFont(fn1);
					JOptionPane.showMessageDialog(this, msg,"ผลการบันทึกรายการ",JOptionPane.INFORMATION_MESSAGE);
				showData();
				clearData();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "บันทึกข้อมูลไม่สำเร็จ");
		}
	}
	

	public void update() {
		

		if (checkInputs() && txtPET_ID.getText() != null) {
			String UpdateQuery = null;
			PreparedStatement ps = null;
			

			// update without image
			if (ImgPath == null) {
				try {
					UpdateQuery = "UPDATE pet SET CS_ID =?, PET_NAME = ?, PET_TYPE= ? ,CAM_NUM=?  WHERE PET_ID = ?";

					ps = con.prepareStatement(UpdateQuery);

					ps.setString(1, txtCS_ID.getText());
					ps.setString(2, txtPET_NAME.getText());
					ps.setString(3, txtPET_CATEG.getText());
					ps.setString(4, txtCAM_NUM.getText());
					ps.setString(5, txtPET_ID.getText());
					ps.executeUpdate();
					
				
						JLabel msg = new JLabel("แก้ไขรายการสำเร็จ");
						msg.setFont(fn1);
						JOptionPane.showMessageDialog(this, msg,"ผลการบันทึกรายการ",JOptionPane.INFORMATION_MESSAGE);
					showData();
					clearData();


				} catch (SQLException ex) {
					ex.printStackTrace();
				}

			}
			// update With Image
			else {
				try {
					InputStream img = new FileInputStream(new File(ImgPath));

					UpdateQuery = "UPDATE pet SET CS_ID =?, PET_NAME = ?, PET_TYPE= ? ,PET_PHOTO=?,CAM_NUM=?  WHERE PET_ID = ?";

					ps = con.prepareStatement(UpdateQuery);
					
					
					ps.setString(1, txtCS_ID.getText());
					ps.setString(2, txtPET_NAME.getText());
					ps.setString(3, txtPET_CATEG.getText());
					ps.setBlob(4, img);
					ps.setString(5, txtCAM_NUM.getText());
					ps.setString(6, txtPET_ID.getText());


					ps.executeUpdate();
					
						JLabel msg = new JLabel("แก้ไขรายการสำเร็จ");
						msg.setFont(fn1);
						JOptionPane.showMessageDialog(this, msg,"ผลการบันทึกรายการ",JOptionPane.INFORMATION_MESSAGE);
					showData();
					clearData();
					

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "ข้อมูลไม่สมบูรณ์ ");
		}	
	}
	
	

	public void delete() {
		 if(!txtPET_ID.getText().equals(""))
	        {
	            try {
	                
	                PreparedStatement ps = con.prepareStatement("DELETE FROM pet WHERE PET_ID= ?");
	                int id = Integer.parseInt(txtPET_ID.getText());
	                ps.setInt(1, id);
	                ps.executeUpdate();
	            	
						JLabel msg = new JLabel("ลบรายการสำเร็จแล้ว");
						msg.setFont(fn1);
						JOptionPane.showMessageDialog(this, msg,"ผลการบันทึกรายการ",JOptionPane.INFORMATION_MESSAGE);
					showData();
					clearData();
					
	            
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	               
	            }
	         
	        }else{
	        	 JLabel msg = new JLabel("ลบรายการไม่สำเร็จ");
					msg.setFont(fn1);
					JOptionPane.showMessageDialog(this, msg,"ผลการบันทึกรายการ",JOptionPane.INFORMATION_MESSAGE);
	        }
	}
		 public void clearData() {
		    	txtPET_ID.setEditable(true);
				txtPET_ID.setText("");
				txtCS_ID.setText("");
				txtPET_NAME.setText("");
				txtPET_CATEG.setText("");
				txtCAM_NUM.setText("");
				picLabel.setIcon(null);
		    }
		 public void setFileChooserFont(Component[] comp)
		  {
		    for(int x = 0; x < comp.length; x++)
		    {
		      if(comp[x] instanceof Container) setFileChooserFont(((Container)comp[x]).getComponents());
		      try{comp[x].setFont(fn1);}
		      catch(Exception e){}//do nothing
		    }
		  }
}