package personalcloset;

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;

public class MainProgramFrame extends JFrame {
    public MainProgramFrame() {
        setTitle("Main Program");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(153, 204, 153));

        JButton openSpecificProgramButton = new JButton("");
        openSpecificProgramButton.setBackground(new Color(255, 255, 255));
        openSpecificProgramButton.setIcon(new ImageIcon("C:\\Users\\petroadmin\\Downloads\\closet.png"));
        openSpecificProgramButton.setBounds(150, 320, 104, 65);
        openSpecificProgramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSpecificProgram();
            }
        });
        panel.setLayout(null);
        panel.add(openSpecificProgramButton);

        JButton openAnotherProgramButton = new JButton("");
        openAnotherProgramButton.setBackground(new Color(255, 255, 255));
        openAnotherProgramButton.setIcon(new ImageIcon("C:\\Users\\petroadmin\\Downloads\\noticeboard.png"));
        openAnotherProgramButton.setBounds(353, 320, 113, 65);
        openAnotherProgramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAnotherProgram();
            }
        });
        panel.add(openAnotherProgramButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setHorizontalAlignment(SwingConstants.LEADING);
        logoutButton.setBackground(new Color(51, 153, 102));
        logoutButton.setForeground(new Color(51, 153, 102));
        logoutButton.setIcon(new ImageIcon("C:\\Users\\petroadmin\\Downloads\\logout (1).png"));
        logoutButton.setBounds(10, 11, 53, 42);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        panel.add(logoutButton);

        getContentPane().add(panel);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\petroadmin\\Downloads\\JLabel.jpg"));
        lblNewLabel.setBounds(59, 64, 500, 245);
        panel.add(lblNewLabel);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBounds(92, 11, 434, 42);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("OWN YOUR CLOTHES");
        lblNewLabel_1.setForeground(new Color(0, 102, 51));
        lblNewLabel_1.setBackground(new Color(51, 153, 102));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 22));
        lblNewLabel_1.setBounds(37, 0, 363, 42);
        panel_1.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Open closet");
        lblNewLabel_2.setForeground(new Color(34, 139, 34));
        lblNewLabel_2.setBackground(new Color(0, 100, 0));
        lblNewLabel_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
        lblNewLabel_2.setBounds(160, 377, 81, 34);
        panel.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Open Statistics");
        lblNewLabel_3.setForeground(new Color(34, 139, 34));
        lblNewLabel_3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
        lblNewLabel_3.setBounds(363, 381, 103, 26);
        panel.add(lblNewLabel_3);
        setLocationRelativeTo(null);
    }

    private void openSpecificProgram() {
    	 JOptionPane.showMessageDialog(this, "Opening Closet");
        dispose(); 
        SwingUtilities.invokeLater(() -> new OwnYourClothes("OWN YOUR CLOTHES").setVisible(true));
    }

    private void openAnotherProgram() {
        JOptionPane.showMessageDialog(this, "Opening Statistics");
         dispose(); 
         SwingUtilities.invokeLater(() -> new StatisticsView().setVisible(true));
    }

    private void logout() {
        JOptionPane.showMessageDialog(this, "Logged out!");
        dispose();
        SwingUtilities.invokeLater(() -> new LoginOYC_().setVisible(true));
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainProgramFrame().setVisible(true));
    }
}
