package window;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import sql.MysqlInfo;

public class SignUpFrame {
	static final String JDBC_DRIVER = MysqlInfo.JDBC_DRIVER;  
    static final String DB_URL = MysqlInfo.DB_URL;
 
    // ���ݿ�������������
    static final String USER = MysqlInfo.USER;
    static final String PASS = MysqlInfo.PASS;
    
	JFrame jf = new JFrame("ע��");
	JPanel jp1=new JPanel();  
    JPanel jp2=new JPanel();
    JPanel jp5=new JPanel();
    JPanel bottomButton=new JPanel();  
    JButton signUpButton=new JButton(new ImageIcon("Image\\register(1).png")); 
    
    JTextField userField1 = new JTextField(20);  
    JPasswordField passField1 = new JPasswordField(20);
    public void init(){
    	class myWindowListener extends WindowAdapter{  
  		  
            @Override  
            public void windowClosing(WindowEvent e) {  
                  
                System.exit(0);  
            }  
              
        }
    	
		jf.setIconImage(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/black-jiang.png")));
		jf.setLayout(null);
		jf.setBounds(0, 0, 762, 482);
		System.out.println("sign up");
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null); 
		addBackgroundImage("image//register_bgp.jpg");
    	
    	jf.addWindowListener(new myWindowListener());  
    	JPanel contentPane = (JPanel)jf.getContentPane();
    	contentPane.add(signUpButton);
    	jf.setVisible(true); 
    	
        userField1.setFont(new Font("����", Font.PLAIN, 32));
        userField1.setBounds(175, 90, 457, 60);
        userField1.setBorder(null);
        userField1.setOpaque(false);
    	contentPane.add(userField1);  
    	
    	passField1.setFont(new Font("����", Font.PLAIN, 32));
        passField1.setBounds(175, 194, 457, 60);
        passField1.setBorder(null);
        passField1.setOpaque(false);
        contentPane.add( passField1);  
    	
        signUpButton.setBounds(230, 310, 244, 60);
        signUpButton.setRolloverIcon(new ImageIcon("Image\\register(2).png"));  
        signUpButton.setPreferredSize(new Dimension(200,62));
        signUpButton.setContentAreaFilled(false);
        signUpButton.setOpaque(false);
        signUpButton.setBorderPainted(false);
        signUpButton.setFocusPainted(false);     
    	contentPane.add(signUpButton); 
    	
        signUpButton.addActionListener(e -> {  
        	System.out.println(userField1.getText() +"\t"+String.valueOf(passField1.getPassword())+"\t");  
        	try {
				cheak(userField1.getText(), String.valueOf(passField1.getPassword()));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	});
    }
  //ע����Һ����� ��1�������ȼ�����ݿ����Ƿ�����Ӧ�����ݣ�����еĻ���ʾ"����Ҵ��ڣ���ֱ�ӵ�¼��"  
    private void cheak(String userName, String userPass) throws Exception  
    {      
        if (validate(userField1.getText()))  
        {  
            JOptionPane.showMessageDialog(jf, "���˺��Ѵ��ڣ�");  
        }  
  
        else  
        {  
            String sql="insert ignore into user_info(user_name, user_password, num_win, num_lose, num_peace, submission_time) values(?,?,0,0,0, CURRENT_DATE())";  
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            PreparedStatement pstmt = conn.prepareStatement(sql);  
            pstmt.setString(1, userName);  
            pstmt.setString(2, userPass);  
            pstmt.executeUpdate();  
            JOptionPane.showMessageDialog(jf, "ע��ɹ����¼��������");  
            jf.setVisible(false);
        }  
              
          
    }  
        private boolean validate(String userName)  
        {  
            String sql="select *from user_info where user_name='"+userName+"'";  
            try(  
            		Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            		Statement pstmt = conn.createStatement();  
                    ResultSet rs=pstmt.executeQuery(sql))  
              
            {   //�����ѯ��ResultSet���г���һ���ļ�¼�����¼�ɹ�  
                    if (rs.next())  
                    {  
                        return true;  
                    }  
                  
            }  
            catch(Exception e)  
            {  
                e.printStackTrace();  
            }  
            return false;  
        }
        
        public void addBackgroundImage(String s)
    	{  
    	    //ʵ����һ��ImageIconͼ����Ķ���  
    	    ImageIcon image = new ImageIcon(s);  
    	    //ʵ����һ����ǩ��Ķ���  
    	    JLabel background = new JLabel(image);   
    	    //���ñ�ǩ��ʾ��λ�úʹ�С  
    	    background.setBounds(0,0,image.getIconWidth(),image.getIconHeight());  
    	    //����ǩ��ӵ�����ĵڶ��������  
    	    jf.getLayeredPane().add(background,new Integer(Integer.MIN_VALUE));  
    	    //��ȡ����ĵ�һ������  
    	    JPanel contentPanel = (JPanel)jf.getContentPane();  
    	    //���õ�һ�����Ϊ͸��  
    	    contentPanel.setOpaque(false);  
    	     
    	}	
   
}
