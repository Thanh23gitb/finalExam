package personalcloset;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Font;


public class LoginOYC_ extends JFrame {
	
	 private JCheckBox cbShowPassword; 
	  private JTextField tfUsername;
	     private JPasswordField pfPassword;
	     private JButton btnLogin;
	 public LoginOYC_() {
	  setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Desktop\\2250207.png"));
	  setTitle("OYC Login");
	        setSize(606, 399);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);

	        JPanel panel = new JPanel();
	        panel.setBackground(new Color(255, 204, 255));
	        getContentPane().add(panel);
	        panel.setLayout(null);

	        JLabel lblUsername = new JLabel("User's Account");
	        lblUsername.setForeground(new Color(153, 102, 255));
	        lblUsername.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 15));
	        lblUsername.setBounds(330, 68, 100, 13);
	        panel.add(lblUsername);

	        tfUsername = new JTextField(20);
	        tfUsername.setBackground(new Color(255, 255, 204));
	        tfUsername.setBounds(323, 82, 227, 33);
	        panel.add(tfUsername);

	        JLabel lblPassword = new JLabel("Password");
	        lblPassword.setForeground(new Color(153, 102, 255));
	        lblPassword.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 15));
	        lblPassword.setBounds(330, 126, 113, 13);
	        panel.add(lblPassword);

	        pfPassword = new JPasswordField(20);
	        pfPassword.setBackground(new Color(255, 255, 204));
	        pfPassword.setBounds(323, 142, 227, 33);
	        panel.add(pfPassword);
	        
	        cbShowPassword = new JCheckBox("Show the password");
	        cbShowPassword.setBackground(new Color(255, 255, 204));
	        cbShowPassword.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
	        cbShowPassword.setForeground(new Color(153, 102, 255));
	        cbShowPassword.setBounds(330, 182, 150, 17);
	        panel.add(cbShowPassword);
	        

	        btnLogin = new JButton("Login");
	        btnLogin.setForeground(new Color(102, 51, 204));
	        btnLogin.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 15));
	        btnLogin.setBackground(new Color(255, 255, 204));
	        btnLogin.setBounds(340, 210, 188, 33);
	        panel.add(btnLogin);
	        
	        JPanel panel_1 = new JPanel();
	        panel_1.setBackground(new Color(255, 255, 204));
	        panel_1.setBounds(0, 0, 293, 368);
	        panel.add(panel_1);
	        panel_1.setLayout(null);
	        
	        JLabel lblNewLabel_2 = new JLabel("OWN YOUR CLOTHES");
	        lblNewLabel_2.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 27));
	        lblNewLabel_2.setForeground(new Color(102, 102, 204));
	        lblNewLabel_2.setBounds(0, 21, 296, 46);
	        panel_1.add(lblNewLabel_2);
	        
	        JLabel lblNewLabel_1 = new JLabel("New label");
	        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\petroadmin\\Downloads\\Icons8-Ios7-Clothing-Jacket.128.png"));
	        lblNewLabel_1.setBounds(10, 78, 134, 128);
	        panel_1.add(lblNewLabel_1);
	        
	        JLabel lblNewLabel_3 = new JLabel("New label");
	        lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\petroadmin\\Downloads\\Icons8-Ios7-Clothing-Winter-Boots.128.png"));
	        lblNewLabel_3.setBounds(154, 213, 141, 128);
	        panel_1.add(lblNewLabel_3);
	        
	        JLabel lblNewLabel_4 = new JLabel("New label");
	        lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\petroadmin\\Downloads\\Icons8-Ios7-Clothing-Hat.128.png"));
	        lblNewLabel_4.setBounds(10, 217, 134, 121);
	        panel_1.add(lblNewLabel_4);
	        
	        JLabel lblNewLabel_5 = new JLabel("New label");
	        lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\petroadmin\\Downloads\\Icons8-Ios7-Clothing-Trousers.128.png"));
	        lblNewLabel_5.setBounds(154, 72, 129, 140);
	        panel_1.add(lblNewLabel_5);
	        
	        JLabel lblNewLabel = new JLabel("Let's make our journey !");
	        lblNewLabel.setForeground(new Color(255, 102, 102));
	        lblNewLabel.setFont(new Font("Gill Sans MT", Font.BOLD | Font.ITALIC, 18));
	        lblNewLabel.setBounds(303, 296, 227, 64);
	        panel.add(lblNewLabel);
	        
	       
	        cbShowPassword.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (cbShowPassword.isSelected()) {pfPassword.setEchoChar((char) 0); // Hiển thị mật khẩu
	                } else {
	                    pfPassword.setEchoChar('\u2022'); // Ẩn mật khẩu
	                }
	            }
	        });

	        btnLogin.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String username = tfUsername.getText();
	                String password = new String(pfPassword.getPassword());

	                // Kiểm tra username và password ở đây
	                if (username.equals("Thanh") && password.equals("thanhphan")) {
	                    dispose(); // Đóng Swing Đăng nhập
	                    
	                    // Mở MainProgramFrame
	                    SwingUtilities.invokeLater(() -> new MainProgramFrame().setVisible(true));
	                } else {
	                    JOptionPane.showMessageDialog(null, "Failed Login",
	                            "Notifications", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        });

	    }
	 
	 public static void main(String[] args) {
	         SwingUtilities.invokeLater(new Runnable() {
	             @Override
	             public void run() {
	            	 LoginOYC_  loginSwing = new LoginOYC_();
	                 loginSwing.setVisible(true);
	             }
	         });
	     }
	 
	 
	 
	}
