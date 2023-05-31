import java.io.*;
import java.sql.*;
import java.util.*;
import oracle.jdbc.driver.*;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class AppJS extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JPanel viewTable_display_panel;
	private JPanel customerTable_display_panel;

	private static String uname;
	private static String pass;
	static Connection con;
    static Statement stmt;
    static ResultSet rs;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					AppJS frame = new AppJS(uname, pass);
					System.out.println(uname);
					System.out.println(pass);
					
					frame.pack();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
    public static Boolean connectToDatabase(String username, String password)
    {
		String driverPrefixURL="jdbc:oracle:thin:@";
		String jdbc_url="artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";

		try{
	    //Register Oracle driver
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (Exception e) {
            System.out.println("Failed to load JDBC/ODBC driver.");
            return false;
        }

       try{
            System.out.println(driverPrefixURL+jdbc_url);
            con=DriverManager.getConnection(driverPrefixURL+jdbc_url, username, password);
            DatabaseMetaData dbmd=con.getMetaData();
            stmt=con.createStatement();
            
            System.out.println("Connected.");
            
            
            if(dbmd==null){
                System.out.println("No database meta data");
                return false;
            }
            else {
                System.out.println("Database Product Name: "+dbmd.getDatabaseProductName());
                System.out.println("Database Product Version: "+dbmd.getDatabaseProductVersion());
                System.out.println("Database Driver Name: "+dbmd.getDriverName());
                System.out.println("Database Driver Version: "+dbmd.getDriverVersion());
                return true;
                
            }
        }catch( Exception e) {
        	e.printStackTrace();
        	return false;

        }
    }
	
	
	

	/**
	 * Create the frame.
	 */
	public AppJS(String uname, String pass) {
		
		this.uname = uname;
		this.pass = pass;
		System.out.println(uname);
		System.out.println(this.uname);
		connectToDatabase(this.uname, this.pass);
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel head_panel = new JPanel();
		head_panel.setForeground(Color.WHITE);
		head_panel.setBackground(Color.DARK_GRAY);
		head_panel.setBounds(0, 0, 1088, 100);
		contentPane.add(head_panel);
		head_panel.setLayout(null);
			
		JLabel lblNewLabel = new JLabel("DATABASE MAINFRAME");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 1056, 78);
		head_panel.add(lblNewLabel);
			
		JPanel app_main_canvas = new JPanel();
		app_main_canvas.setBounds(0, 100, 1088, 615);
		contentPane.add(app_main_canvas);
		app_main_canvas.setLayout(null);
			
			
		//Option 1: Edit Tables
		JButton btnCUTable = new JButton("EDIT TABLES");
		btnCUTable.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnCUTable.setForeground(Color.DARK_GRAY);
		btnCUTable.setBounds(50, 10, 180, 40);
		app_main_canvas.add(btnCUTable);

		//Option 1: View Tables
		JButton btnViewTable = new JButton("VIEW TABLES");
		btnViewTable.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnViewTable.setForeground(Color.DARK_GRAY);
		btnViewTable.setBounds(250, 10, 180, 40);
		app_main_canvas.add(btnViewTable);
		
		//Option 2: View Customer Records
		JButton btnCustomerRecords = new JButton("CUSTOMER REC");
		btnCustomerRecords.setForeground(Color.DARK_GRAY);
		btnCustomerRecords.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnCustomerRecords.setBounds(450, 10, 180, 40);
		app_main_canvas.add(btnCustomerRecords);
		
		//Option 3: View Table with selected attributes
		JButton btnAttributeSearch = new JButton("ATTRIBUTES");
		btnAttributeSearch.setForeground(Color.DARK_GRAY);
		btnAttributeSearch.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnAttributeSearch.setBounds(650, 10, 180, 40);
		app_main_canvas.add(btnAttributeSearch);
		
		//Option 4: Exit/Logout
		JButton btnExit = new JButton("EXIT");
		btnExit.setForeground(Color.DARK_GRAY);
		btnExit.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnExit.setBounds(850, 10, 180, 40);
		app_main_canvas.add(btnExit);
		
		
		//Application Body
		JPanel app_body = new JPanel();
		app_body.setBackground(Color.WHITE);
		app_body.setBounds(10, 58, 1068, 520);
		app_main_canvas.add(app_body);
		app_body.setLayout(new CardLayout(0, 0));
		
		
		JPanel edit_panel = new JPanel();
		app_body.add(edit_panel, "name_1178727728601400");
		edit_panel.setLayout(null);
		
		
		JPanel editTable_dd_panel = new JPanel();
		editTable_dd_panel.setLayout(null);
		editTable_dd_panel.setBackground(Color.WHITE);
		editTable_dd_panel.setBounds(0, 0, 1068, 70);
		edit_panel.add(editTable_dd_panel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Pick the tables you would like to view:");
		lblNewLabel_1_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(5, 5, 1068, 60);
		editTable_dd_panel.add(lblNewLabel_1_1);
		
		JComboBox editcomboBox = new JComboBox();
		editcomboBox.setBounds(290, 24, 180, 30);
		editcomboBox.addItem("PRODUCT");
		editcomboBox.addItem("CUSTOMER");
		editcomboBox.addItem("TRANSACTIONS");
		editTable_dd_panel.add(editcomboBox);
		
		JButton addButton = new JButton("ADD");
		addButton.setBounds(500, 24, 90, 30);
		editTable_dd_panel.add(addButton);
		
		JButton updateButton = new JButton("UPDATE");
		updateButton.setBounds(625, 24, 90, 30);
		editTable_dd_panel.add(updateButton);
		
		JButton deleteButton = new JButton("DELETE");
		deleteButton.setBounds(750, 24, 90, 30);
		editTable_dd_panel.add(deleteButton);
		
		JButton clearButton = new JButton("CLEAR");
		clearButton.setBounds(880, 24, 90, 30);
		editTable_dd_panel.add(clearButton);

		
		JPanel editTable_display_panel = new JPanel();
		editTable_display_panel.setBackground(Color.WHITE);
		editTable_display_panel.setBounds(0, 70, 1068, 449);
		edit_panel.add(editTable_display_panel);
		editTable_display_panel.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		editTable_display_panel.add(panel, "name_1190722524982500");
		panel.setLayout(new CardLayout(0, 0));
		
		JPanel product_edit_panel = new JPanel();
		panel.add(product_edit_panel, "name_1429800975818300");
		product_edit_panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("UPC");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 10, 100, 30);
		product_edit_panel.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(110, 10, 150, 30);
		product_edit_panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("BRAND");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(400, 10, 50, 30);
		product_edit_panel.add(lblNewLabel_2_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(450, 12, 200, 30);
		product_edit_panel.add(textField_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("PRODUCT NAME");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_2.setBounds(750, 10, 110, 30);
		product_edit_panel.add(lblNewLabel_2_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(860, 10, 200, 30);
		product_edit_panel.add(textField_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("PRODUCT DESCRIPTION");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_3.setBounds(200, 110, 160, 30);
		product_edit_panel.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("CATEGORY");
		lblNewLabel_2_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_4.setBounds(400, 60, 100, 30);
		product_edit_panel.add(lblNewLabel_2_4);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(480, 60, 170, 30);
		product_edit_panel.add(textField_4);
		
		JLabel lblNewLabel_2_5 = new JLabel("MARKED PRICE");
		lblNewLabel_2_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_5.setBounds(750, 60, 100, 30);
		product_edit_panel.add(lblNewLabel_2_5);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(860, 60, 200, 30);
		product_edit_panel.add(textField_5);
		
		JLabel lblNewLabel_2_6 = new JLabel("QUANTITY INSTOCK");
		lblNewLabel_2_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_6.setBounds(10, 60, 135, 30);
		product_edit_panel.add(lblNewLabel_2_6);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(150, 60, 110, 30);
		product_edit_panel.add(textField_6);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(370, 110, 300, 150);
		product_edit_panel.add(textArea);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 280, 1068, 168);
		product_edit_panel.add(panel_1);
		
		JPanel customer_edit_panel = new JPanel();
		panel.add(customer_edit_panel, "name_1429831058825500");
		
		JPanel transactions_edit_panel = new JPanel();
		panel.add(transactions_edit_panel, "name_1429855734210900");
		
		
		JPanel view_panel =  new JPanel();
		view_panel.setLayout(null);
		
		JPanel viewTable_dd_panel = new JPanel();
		viewTable_dd_panel.setLayout(null);
		viewTable_dd_panel.setBackground(Color.WHITE);
		viewTable_dd_panel.setBounds(0, 0, 1068, 72);
		view_panel.add(viewTable_dd_panel);
		
		JLabel lblNewLabel_1 = new JLabel("Pick the tables you would like to view:");
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 10, 1050, 60);
		viewTable_dd_panel.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(290, 24, 180, 30);
		comboBox.addItem("PRODUCT");
		comboBox.addItem("CUSTOMER");
		comboBox.addItem("TRANSACTIONS");
		viewTable_dd_panel.add(comboBox);
		
		
		viewTable_display_panel = new JPanel();
		viewTable_display_panel.setBounds(0, 71, 1068, 449);
		view_panel.add(viewTable_display_panel);
		viewTable_display_panel.setLayout(new CardLayout(0, 0));
		app_body.add(view_panel, "name_1178966697113700");

		JPanel customerT_panel = new JPanel();
		customerT_panel.setLayout(null);
		
		JPanel customerTable_input_panel = new JPanel();
		customerTable_input_panel.setLayout(null);
		customerTable_input_panel.setBackground(Color.WHITE);
		customerTable_input_panel.setBounds(0, 0, 1068, 70);
		customerT_panel.add(customerTable_input_panel);
		
		JLabel lblNewLabel_1_2 = new JLabel("Enter the Customer_ID:");
		lblNewLabel_1_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_2.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(10, 10, 1050, 60);
		customerTable_input_panel.add(lblNewLabel_1_2);
		
		JTextField ctextField = new JTextField();
		ctextField.setBounds(200, 24, 200, 30);
		customerTable_input_panel.add(ctextField);
		ctextField.setColumns(10);
		
		JButton customerApplyBtn = new JButton("APPLY");
		customerApplyBtn.setBounds(410, 24, 90, 30);
		customerTable_input_panel.add(customerApplyBtn);
		
		customerTable_display_panel = new JPanel();
		customerTable_display_panel.setBounds(0, 70, 1068, 450);
		customerT_panel.add(customerTable_display_panel);
		customerTable_display_panel.setLayout(new CardLayout(0, 0));
		app_body.add(customerT_panel, "name_1179032851416900");

		
		
		JPanel attributes_panel = new JPanel();
		app_body.add(attributes_panel, "name_1179055727306900");
		
		
		/*
		 * Action listner
		 * 
		 */
		btnCUTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//removing the panels.
				app_body.removeAll();
				app_body.repaint();
				app_body.revalidate();
				
				//adding panels.
				app_body.add(edit_panel);
				app_body.repaint();
				app_body.revalidate();
				
			}
		});
		
		editcomboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected item from the JComboBox
                String selected = (String) editcomboBox.getSelectedItem();
                System.out.println(selected);
                
                viewTable_display_panel.removeAll();
                viewTable_display_panel.repaint();
                viewTable_display_panel.revalidate();
                
                dispTable(selected, null);
            }
		});

		
		
		
		btnViewTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//removing the panels.
				app_body.removeAll();
				app_body.repaint();
				app_body.revalidate();
				
				//adding panels.
				app_body.add(view_panel);
				app_body.repaint();
				app_body.revalidate();
				
			}
		});
		
		/*
		 * Add action to DDMenu to display Table
		 */
		comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected item from the JComboBox
                String selected = (String) comboBox.getSelectedItem();
                System.out.println(selected);
                
                viewTable_display_panel.removeAll();
                viewTable_display_panel.repaint();
                viewTable_display_panel.revalidate();
                
                viewTables(selected);
            }
		});
		
		
		
		
		
		
		btnCustomerRecords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//removing the panels.
				app_body.removeAll();
				app_body.repaint();
				app_body.revalidate();
				
				//adding panels.
				app_body.add(customerT_panel);
				app_body.repaint();
				app_body.revalidate();
				
			}
		});
		
		btnAttributeSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//removing the panels.
				app_body.removeAll();
				app_body.repaint();
				app_body.revalidate();
				
				//adding panels.
				app_body.add(view_panel);
				app_body.repaint();
				app_body.revalidate();
				
			}
		});
		
		customerApplyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(ctextField.getText()); 
				customerTable_display_panel.removeAll();
				customerTable_display_panel.repaint();
				customerTable_display_panel.revalidate();
                
				customerTables(id);
			}
		});

	}

	
	/*
	 * Viewing Table.
	 */
	public void dispTable(String selected, String query) {
		
		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Vector<Vector<Object>> data = new  Vector<Vector<Object>>();
		Vector<String> header = new Vector<String>();

		if(selected.equals("PRODUCT")) {		
			header.add("UPC");
			header.add("brand");
			header.add("product_name");
			header.add("product_description");
			header.add("category");
			header.add("marked_price");
			header.add("quantity_instock");
				
			try {
				while(rs.next()) {
					Vector<Object> row = new Vector<Object>();
					
					row.add(rs.getString("UPC"));
		            row.add(rs.getString("brand")); 
		            row.add(rs.getString("product_name")); 
		            row.add(rs.getString("product_description"));
		            row.add(rs.getString("category")); 
		            row.add(rs.getString("marked_price"));
		            row.add(rs.getString("quantity_instock")); 
		            
		            data.add(row);
				}
					table = new JTable(data,header);
					table.getColumnModel().getColumn(0).setPreferredWidth(90);
					table.getColumnModel().getColumn(1).setPreferredWidth(100);
					table.getColumnModel().getColumn(2).setPreferredWidth(120);
					table.getColumnModel().getColumn(3).setPreferredWidth(130);
					table.getColumnModel().getColumn(4).setPreferredWidth(80);
					table.getColumnModel().getColumn(5).setPreferredWidth(80);
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (selected.equals("CUSTOMER")) {
			
			header.add("customer_ID");
			header.add("first_name");
			header.add("last_name");
			header.add("age");
			header.add("gender");
			header.add("zip_code");
				
			try {
				while(rs.next()) {
					Vector<Object> row = new Vector<Object>();
					
					row.add(rs.getInt("customer_ID"));
		            row.add(rs.getString("first_name")); 
		            row.add(rs.getString("last_name")); 
		            row.add(rs.getInt("age"));
		            row.add(rs.getString("gender"));
		            row.add(rs.getInt("zip_code")); 
		            
		            data.add(row);
				}
					table = new JTable(data,header);
					table.getColumnModel().getColumn(0).setPreferredWidth(90);
					table.getColumnModel().getColumn(1).setPreferredWidth(100);
					table.getColumnModel().getColumn(2).setPreferredWidth(120);
					table.getColumnModel().getColumn(3).setPreferredWidth(130);
					table.getColumnModel().getColumn(4).setPreferredWidth(80);
					table.getColumnModel().getColumn(5).setPreferredWidth(80);
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {

			header.add("transaction_ID");
			header.add("customer_ID");
			header.add("transaction_date");
			header.add("payment_method");
			header.add("total");
				
			try {
				while(rs.next()) {
					Vector<Object> row = new Vector<Object>();
					
					row.add(rs.getInt("transaction_ID"));
		            row.add(rs.getInt("customer_ID")); 
		            row.add(rs.getString("transaction_date")); 
		            row.add(rs.getInt("payment_method"));
		            row.add(rs.getFloat("total"));
		            
		            data.add(row);
				}
					table = new JTable(data,header);
					table.getColumnModel().getColumn(0).setPreferredWidth(90);
					table.getColumnModel().getColumn(1).setPreferredWidth(100);
					table.getColumnModel().getColumn(2).setPreferredWidth(120);
					table.getColumnModel().getColumn(3).setPreferredWidth(130);
					table.getColumnModel().getColumn(4).setPreferredWidth(80);
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
		JScrollPane scrollPane = new JScrollPane(table);
		viewTable_display_panel.add(scrollPane, "name_1182446034412700");
    }
	
    //Create tables picked for option 1.
    public void viewTables(String selected) {
    
    		String query = "SELECT * FROM " + selected;
    		dispTable(selected, query);
    }
    
  //Create tables picked for option 1.
    public void editTables(String selected) {
    
    		String query = "SELECT * FROM " + selected;
    		dispTable(selected, query);
    }
    
    //Create tables picked for option 1.
    public void customerTables(int id) {
       	
		String query = "SELECT Customer.customer_ID, Customer.first_name, Customer.last_name, Customer.age, Customer.gender, Customer.zip_code, COUNT(Transactions.transaction_ID) AS Transaction_Counts "
                + "FROM Customer "
                + "LEFT JOIN Transactions ON Customer.customer_ID = Transactions.customer_ID "
                + "Where Customer.customer_ID = " + id
                + " GROUP BY Customer.customer_ID, Customer.first_name, Customer.last_name, Customer.age, Customer.gender, Customer.zip_code";

    		try {
    			rs = stmt.executeQuery(query);
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		
    		Vector<Vector<Object>> data = new  Vector<Vector<Object>>();
    		Vector<String> header = new Vector<String>();
    		header.add("customer_ID");
			header.add("first_name");
			header.add("last_name");
			header.add("age");
			header.add("gender");
			header.add("zip_code");
			header.add("Transaction_Count");
				
			try {
				while(rs.next()) {
					Vector<Object> row = new Vector<Object>();
					
					row.add(rs.getInt("customer_ID"));
					System.out.println(rs.getInt("customer_ID"));
		            row.add(rs.getString("first_name")); 
		            row.add(rs.getString("last_name")); 
		            row.add(rs.getInt("age"));
		            row.add(rs.getString("gender"));
		            row.add(rs.getInt("zip_code"));
		            row.add(rs.getInt("Transaction_Counts"));
		            
		            data.add(row);
				}
				table = new JTable(data,header);
				table.getColumnModel().getColumn(0).setPreferredWidth(80);
				table.getColumnModel().getColumn(1).setPreferredWidth(90);
				table.getColumnModel().getColumn(2).setPreferredWidth(100);
				table.getColumnModel().getColumn(3).setPreferredWidth(100);
				table.getColumnModel().getColumn(4).setPreferredWidth(80);
				table.getColumnModel().getColumn(5).setPreferredWidth(80);
				table.getColumnModel().getColumn(6).setPreferredWidth(80);
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JScrollPane scrollPane = new JScrollPane(table);
			customerTable_display_panel.add(scrollPane, "name_1182446034412700");
			
    }
}
