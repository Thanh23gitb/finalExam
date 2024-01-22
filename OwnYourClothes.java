package personalcloset;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import java.sql.*;
import java.util.Vector;

public class OwnYourClothes extends JFrame implements ActionListener, MouseListener {
    private Connection conn;
    private Statement stm;
    private ResultSet rst;
    private JTextField searchField;
    private JButton searchButton;
    private Vector<Vector<String>> vData = new Vector<Vector<String>>();
    private Vector<String> vTitle = new Vector<String>();
    private JScrollPane tableResult;
    private DefaultTableModel model;
    private JTable tb = new JTable();
    private int selectedRow = 0;
    private JButton resetButton;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton editButton;
    private JButton openMainProgramButton;
    private JComboBox<String> categoryComboBox;
    private JButton filterButton;


    public OwnYourClothes(String title) {
        super(title);
        setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
        getContentPane().setBackground(new Color(240, 255, 255));
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wardrobe", "root", "");
            stm = conn.createStatement();
            getContentPane().setLayout(null);

            reload();

            model = new DefaultTableModel(vData, vTitle);
            tb = new JTable(model);
            tb.addMouseListener(this);
            tb.getTableHeader().setBackground(new Color(147, 112, 219)); 
            tb.getTableHeader().setForeground(new Color(152, 251, 152)); 
            tb.setSelectionBackground(new Color(255, 228, 225));

            Font currentFont = tb.getTableHeader().getFont(); 
            Font newFont = new Font("Berlin Sans FB Demi", currentFont.getStyle(), currentFont.getSize());

            tb.getTableHeader().setFont(newFont); 
            tableResult = new JScrollPane(tb);
            tableResult.setBounds(274, 0, 510, 410);
            getContentPane().add(tableResult);
            
            JPanel panel = new JPanel();
            panel.setBackground(new Color(221, 160, 221));
            panel.setBounds(10, 79, 258, 331);
            getContentPane().add(panel);
            panel.setLayout(null);
            
            resetButton = new JButton("Reset");
            resetButton.setBackground(new Color(255, 182, 193));
            resetButton.setForeground(new Color(123, 104, 238));
            resetButton.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 18));
            resetButton.addActionListener(this);
            resetButton.setBounds(32, 290, 184, 30);
            panel.add(resetButton);

            insertButton = new JButton("Insert");
            insertButton.addActionListener(this);
            insertButton.setForeground(new Color(123, 104, 238));
            insertButton.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 18));
            insertButton.setBackground(new Color(255, 182, 193));
            insertButton.setBounds(32, 256, 184, 30);
            panel.add(insertButton);

            deleteButton = new JButton("Delete");
            deleteButton.addActionListener(this);
            deleteButton.setForeground(new Color(123, 104, 238));
            deleteButton.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 18));
            deleteButton.setBackground(new Color(255, 182, 193));
            deleteButton.setBounds(32, 221, 184, 30);
            panel.add(deleteButton);

            editButton = new JButton("Edit");
            editButton.addActionListener(this);
            editButton.setForeground(new Color(123, 104, 238));
            editButton.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 18));
            editButton.setBackground(new Color(255, 182, 193));
            editButton.setBounds(32, 187, 183, 30);
            panel.add(editButton);
            
            searchField = new JTextField();
            searchField.setBounds(20, 30, 209, 30);
            panel.add(searchField);
            
                        searchButton = new JButton("Search");
                        searchButton.setForeground(new Color(152, 251, 152));
                        searchButton.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 16));
                        searchButton.setBackground(new Color(147, 112, 219));
                        searchButton.setBounds(137, 64, 92, 30);
                        searchButton.addActionListener(this);
                        panel.add(searchButton);
                        
                        categoryComboBox = new JComboBox<>();
                        categoryComboBox.setForeground(new Color(138, 43, 226));
                        categoryComboBox.addItem("All");
                        categoryComboBox.addItem("Top");
                        categoryComboBox.addItem("Bottom");
                        categoryComboBox.addItem("Accessory");
                        categoryComboBox.addItem("Shoes");
                        categoryComboBox.setBounds(20, 105, 209, 30);
                        panel.add(categoryComboBox);

                        filterButton = new JButton("Filter");
                        filterButton.setForeground(new Color(152, 251, 152));
                        filterButton.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 16));
                        filterButton.setBackground(new Color(147, 112, 219));
                        filterButton.setBounds(137, 139, 92, 30);
                        filterButton.addActionListener(this);
                        panel.add(filterButton);
                        
                        JSeparator separator = new JSeparator();
                        separator.setBackground(new Color(138, 43, 226));
                        separator.setForeground(new Color(138, 43, 226));
                        separator.setBounds(0, 98, 258, 8);
                        panel.add(separator);
                        
                        openMainProgramButton = new JButton("");
                        openMainProgramButton.setIcon(new ImageIcon("C:\\Users\\petroadmin\\Downloads\\arrow.png"));
                        openMainProgramButton.setBounds(10, 64, 37, 30);
                        panel.add(openMainProgramButton);
                        openMainProgramButton.setForeground(new Color(123, 104, 238));
                        openMainProgramButton.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 18));
                        openMainProgramButton.setBackground(new Color(147, 112, 219));
                        openMainProgramButton.addActionListener(this);

                        
                        JPanel panel_1 = new JPanel();
                        panel_1.setBackground(new Color(147, 112, 219));
                        panel_1.setBounds(10, 0, 258, 79);
                        getContentPane().add(panel_1);
                        panel_1.setLayout(null);
                        
                        JLabel lblOwnYourClothes = new JLabel("OWN YOUR CLOTHES");
                        lblOwnYourClothes.setForeground(new Color(152, 251, 152));
                        lblOwnYourClothes.setHorizontalAlignment(SwingConstants.CENTER);
                        lblOwnYourClothes.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 22));
                        lblOwnYourClothes.setBackground(new Color(144, 238, 144));
                        lblOwnYourClothes.setBounds(10, 11, 238, 57);
                        panel_1.add(lblOwnYourClothes);
                        
                      
                         
                         JLabel lblNewLabel = new JLabel("Enter Name :");
                         lblNewLabel.setBounds(21, 11, 99, 23);
                         panel.add(lblNewLabel);
                         lblNewLabel.setForeground(new Color(147, 112, 219));
                         lblNewLabel.setFont(new Font("Berlin Sans FB", Font.BOLD, 14));
                         lblNewLabel.setBackground(new Color(147, 112, 219));
                         
                         JSeparator separator_1 = new JSeparator();
                         separator_1.setForeground(new Color(138, 43, 226));
                         separator_1.setBackground(new Color(138, 43, 226));
                         separator_1.setBounds(0, 174, 258, 8);
                         panel.add(separator_1);

            this.setSize(800, 449);
            this.setLocation(200, 100);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    
    public void reload() {
        try {
        	// xóa dữ liệu ban đầu
        	vTitle.clear();
            vData.clear();
            // tạo ResultSet, ResultSetMetaDate để chuânr bị thao tác
            ResultSet rst = stm.executeQuery("SELECT * FROM wardrobe_items");
            ResultSetMetaData rstmeta = rst.getMetaData();
           // thao tác xử lí dữ liêu với 2 vòng lặp to
            int numColumns = rstmeta.getColumnCount();
            for (int i = 1; i <= numColumns; i++) {
                vTitle.add(rstmeta.getColumnLabel(i));
            }
            while (rst.next()) {
                Vector<String> row = new Vector<String>(numColumns);
                for (int i = 1; i <= numColumns; i++) {
                    row.add(rst.getString(i));
                }
                vData.add(row);
            }
            rst.close();
          // ktra model đã có hay chưa
            if (model != null) {
                model.setDataVector(vData, vTitle);
           
            } else {
               model= new DefaultTableModel(vData, vTitle);
               tb.setModel(model);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    public void reset() {
        searchField.setText("");
        reload();
        JOptionPane.showMessageDialog(this, "Reset Successful");
    }
    public void delete() {
        try {
        	//hàng đc chọn mới thực hiện lệnh
            if (selectedRow >= 0) {
            // lấy id và tạo lệnh, thực hiện lệnh sql
            	String id = vData.get(selectedRow).get(0);
                String sql = "DELETE FROM wardrobe_items WHERE ItemID = \"" + id + "\"";
                stm.executeUpdate(sql);
                //tạo mới và show message
                reload();
                JOptionPane.showMessageDialog(this, "Delete Successful");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void insert() {
        try {
        	//tạo box nhập dữ liệu
            String name = JOptionPane.showInputDialog("Enter item name:");
            String category = JOptionPane.showInputDialog("Enter item category:");
            String isWashing = JOptionPane.showInputDialog("Is the item washable? (true/false):");
            String isFavourite = JOptionPane.showInputDialog("Is the item a favorite? (true/false):");

           //tạo lệnh sql và xử lí
            String sql = "INSERT INTO wardrobe_items (Name, Category, IsWashing, IsFavourite) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setString(2, category);
                pstmt.setString(3, isWashing);
                pstmt.setString(4, isFavourite);
                pstmt.executeUpdate();
            }
           // tạo mới dữ liệu và show message
            reload();

            JOptionPane.showMessageDialog(this, "Insert Successful");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void edit(int row, int column) {
        try {
        	//lấy id, tên cột
            String id = vData.get(row).get(0);
            String columnName = vTitle.get(column);
            //tạo chỗ nhập dữ liệu, tạo lệnh sql
            if (!columnName.equals("ItemID")) {
                String newValue = JOptionPane.showInputDialog("Enter new value for " + columnName + ":");
                String sql = "UPDATE wardrobe_items SET " + columnName + " = ? WHERE ItemID = ?";
            // thưc hiện lệnh sql
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, newValue);
                    pstmt.setString(2, id);

                    pstmt.executeUpdate();
                }

                reload();

                JOptionPane.showMessageDialog(this, "Edit Successful");
            } else {
                JOptionPane.showMessageDialog(this, "Cannot edit ItemID.");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    private void filterByCategory() {
    	// lấy dữ liệu và tạo lệnh sql
    	String selectedCategory = categoryComboBox.getSelectedItem().toString();
        String sql;
        if (selectedCategory.equals("All")) {
            sql = "SELECT * FROM wardrobe_items";
        } else {
            sql = "SELECT * FROM wardrobe_items WHERE Category = '" + selectedCategory + "'";
        }
        // tạo resultSet, xóa dữ liệu 
        try {
           ResultSet rst= stm.executeQuery(sql);
           vData.clear();
        //vòng lặp để truy hồi dữ liệu
          while(rst.next()) {
        	  Vector<String> row= new Vector<String>(vTitle.size());
        	  for (int i=1;i<=vTitle.size();i++) {
        		  row.add(rst.getString(i));
        	  }
        	  vData.add(row);
          }
         //đóng resultSet và set dữ liệu cho bảng
          rst.close();
          model.setDataVector(vData, vTitle);
           
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void search() {
        String searchName = searchField.getText().trim();
        if (!searchName.isEmpty()) {
            try {
            	// tạo lệnh và ResultSet,tạo mới dữ liệu
                String sql = "SELECT * FROM wardrobe_items WHERE Name LIKE '%" + searchName + "%'";
                ResultSet rst = stm.executeQuery(sql);
                vData.clear();
                // vòng lặp để check,xử lí dữ liệu
                while (rst.next()) {
                    Vector<String> row = new Vector<String>(vTitle.size());
                    for (int i = 1; i <= vTitle.size(); i++) {
                        row.add(rst.getString(i));
                    }
                    vData.add(row);
                }
                // đóng ResultSet và set dữ liệu cho model
                rst.close();

                model.setDataVector(vData, vTitle);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void openMainProgram() {
        dispose(); 
        SwingUtilities.invokeLater(() -> new MainProgramFrame().setVisible(true));
    }

    public static void main(String[] args) {
        new OwnYourClothes("Personal Wardrobe Management");
    }

    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == filterButton) {
    	    filterByCategory();
    	}
    	else if (e.getSource() == searchButton) {
            search();
        } else if (e.getSource() == resetButton) {
            reset();
        } else if (e.getSource() == insertButton) {
            insert();
        } else if (e.getSource() == deleteButton) {
           delete();
        } else if (e.getSource() == editButton) {
        	if (tb.getSelectedRow() >= 0 && tb.getSelectedColumn() >= 0) {
                edit(tb.getSelectedRow(), tb.getSelectedColumn());
            } else {
                JOptionPane.showMessageDialog(this, "Please select a cell to edit.");
            }
        } else if (e.getSource() == openMainProgramButton) {
            openMainProgram();
        }
        
    }

	@Override
	public void mouseClicked(MouseEvent e) {
	    if (e.getSource() == tb) {
	        selectedRow = tb.getSelectedRow();
	    }
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}