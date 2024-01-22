package personalcloset;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class StatisticsView extends JFrame {
    private DefaultListModel<String> washingListModel;
    private DefaultListModel<String> favoriteListModel;

    private JLabel washingCountLabel;
    private JLabel favoriteCountLabel;
	private JLabel lblYourFavouriteList;
	private JLabel lblIsWashing;
	private JButton openMainProgramButton;
	

    public StatisticsView() {
        setTitle("Statistics");
        setSize(800, 426);
        
        JPanel washingPanel = new JPanel();
        washingPanel.setBounds(1, 0, 264, 387);
        washingPanel.setBackground(new Color(210, 180, 140));
        washingListModel = new DefaultListModel<>();
        JScrollPane washingScrollPane = new JScrollPane();
        washingScrollPane.setBounds(22, 69, 220, 318);
        washingPanel.setLayout(null);
         lblIsWashing = new JLabel("Laundry List");
        lblIsWashing.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 23));
        lblIsWashing.setForeground(new Color(255, 250, 205));
        lblIsWashing.setBackground(new Color(255, 250, 205));
        lblIsWashing.setHorizontalAlignment(SwingConstants.CENTER);
        lblIsWashing.setBounds(49, 26, 161, 27);
        washingPanel.add(lblIsWashing);
        washingPanel.add(washingScrollPane);
        
        JList<String> washingList = new JList<>(washingListModel);
        washingList.setBackground(new Color(255, 250, 240));
        washingScrollPane.setViewportView(washingList);

        JPanel favoritePanel = new JPanel();
        favoritePanel.setBounds(265, 0, 254, 387);
        favoritePanel.setBackground(new Color(255, 250, 205));
        favoriteListModel = new DefaultListModel<>();
        JScrollPane favoriteScrollPane = new JScrollPane();
        favoriteScrollPane.setBounds(20, 68, 213, 319);
        favoritePanel.setLayout(null);
        lblYourFavouriteList = new JLabel("Your Favourite List");
        lblYourFavouriteList.setForeground(new Color(210, 180, 140));
        lblYourFavouriteList.setHorizontalAlignment(SwingConstants.CENTER);
        lblYourFavouriteList.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 22));
        lblYourFavouriteList.setBounds(24, 24, 244, 33);
        favoritePanel.add(lblYourFavouriteList);
        favoritePanel.add(favoriteScrollPane);
        
        JList<String> favoriteList = new JList<>(favoriteListModel);
        favoriteList.setBackground(new Color(255, 250, 240));
        favoriteScrollPane.setViewportView(favoriteList);
        getContentPane().setLayout(null);

 
        getContentPane().add(washingPanel);
        
        JLabel lblNewLabel_3 = new JLabel("New label");
        lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\petroadmin\\Downloads\\washing-machine.png"));
        lblNewLabel_3.setBounds(23, 24, 31, 40);
        washingPanel.add(lblNewLabel_3);
        getContentPane().add(favoritePanel);
        
        JLabel lblNewLabel_4 = new JLabel("New label");
        lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\petroadmin\\Downloads\\star.png"));
        lblNewLabel_4.setBounds(10, 24, 37, 40);
        favoritePanel.add(lblNewLabel_4);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(188, 143, 143));
        panel.setBounds(518, 0, 266, 387);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\petroadmin\\Downloads\\938cab80-41ff-464b-a003-6d782ac61c21.jfif"));
        lblNewLabel.setBounds(31, 34, 210, 207);
        panel.add(lblNewLabel);
        
        JButton updateButton = new JButton("Update");
        updateButton.setForeground(new Color(205, 92, 92));
        updateButton.setBackground(new Color(250, 250, 210));
        updateButton.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 18));
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateStatistics(); 
            }
        });
        updateButton.setBounds(31, 278, 106, 98);
        panel.add(updateButton);

        openMainProgramButton = new JButton("");
        openMainProgramButton.setIcon(new ImageIcon("C:\\Users\\petroadmin\\Downloads\\arrow.png"));
        openMainProgramButton.setForeground(new Color(123, 104, 238));
        openMainProgramButton.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 18));
        openMainProgramButton.setBackground(new Color(255, 250, 250));
        openMainProgramButton.setBounds(175, 343, 44, 33);
        openMainProgramButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openMainProgram();
            }
        });
        panel.add(openMainProgramButton);
        
        JLabel lblNewLabel_1 = new JLabel("To get the newest data :");
        lblNewLabel_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        lblNewLabel_1.setForeground(new Color(255, 250, 205));
        lblNewLabel_1.setBounds(31, 244, 152, 23);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Your style is your life !");
        lblNewLabel_2.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
        lblNewLabel_2.setForeground(new Color(255, 250, 205));
        lblNewLabel_2.setBounds(10, 11, 173, 14);
        panel.add(lblNewLabel_2);
        
        washingCountLabel = new JLabel("0");
        washingCountLabel.setForeground(new Color(255, 250, 205));
        washingCountLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
        washingCountLabel.setBounds(220, 50, 34, 19);
        washingPanel.add(washingCountLabel);

        favoriteCountLabel = new JLabel("0");
        favoriteCountLabel.setForeground(new Color(210, 180, 140));
        favoriteCountLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
        favoriteCountLabel.setBounds(204, 50, 25, 19);
        favoritePanel.add(favoriteCountLabel);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        updateStatistics();
    }

    public void updateStatistics() {
        try {
        	// kết nối
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wardrobe", "root", "");
            Statement stm = conn.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM wardrobe_items");
            // xóa tất cả để chuẩn bị cho dữ liệu mới nhất
            washingListModel.clear();
            favoriteListModel.clear(); 
            // khởi tạo lại giá trị cho count 
            int washingCount = 0;
            int favoriteCount = 0;
            // vòng lặp check, xử lí dữ liệu 2list
            while (rst.next()) {
                if (rst.getString("IsWashing").equalsIgnoreCase("true")) {
                    washingCount++;
                    washingListModel.addElement("ID: " + rst.getString("ItemID") + " - Name: " + rst.getString("Name"));
                }

                if (rst.getString("IsFavourite").equalsIgnoreCase("true")) {
                    favoriteCount++;
                    favoriteListModel.addElement("ID: " + rst.getString("ItemID") + " - Name: " + rst.getString("Name"));
                }
            }
            // đóng ResultSet
            rst.close();
            // hiển thị 2 count
            washingCountLabel.setText(String.valueOf(washingCount));
            favoriteCountLabel.setText(String.valueOf(favoriteCount));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void openMainProgram() {
        dispose(); 
        SwingUtilities.invokeLater(() -> {
            new MainProgramFrame().setVisible(true);
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StatisticsView());
    }
}
