package window;
import sql.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class RankFrame {
	 static final String JDBC_DRIVER = MysqlInfo.JDBC_DRIVER;  
	    static final String DB_URL = MysqlInfo.DB_URL;
	 
	    // ���ݿ���û���������
	    static final String USER = MysqlInfo.USER;
	    static final String PASS = MysqlInfo.PASS;
	    
	    
	//private Jframe jf=null;
	JPanel panel = new JPanel();
	JTable table;
	JScrollPane jscrollpane = new JScrollPane();

	Object[] columnNames = new Object[]{"����","�û���","ʤ","��","ƽ","ʤ��"};//����
	Object[][] rowData = new Object[50][6];//����������
	int index=0;
	
	public RankFrame() {
		super();
		this.getUserInfo();
		JFrame jf = new JFrame("���а�");
		table = new JTable(rowData,columnNames);
		jscrollpane.setBounds(200, 100, 550, 650);
		jscrollpane.setViewportView(table);//������Ҫ
		table.setRowHeight(30); 
		/**
		 * �־�����ʾ����
		*/
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();    
		r.setHorizontalAlignment(JLabel.CENTER);   
		table.setDefaultRenderer(Object.class,r);
		panel.add(jscrollpane);
		jf.add(panel);
		jf.setSize(500, 500);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		jf.setLocationRelativeTo(null);
	}
	
	public void getUserInfo()
	{

        Connection conn = null;
        Statement stmt = null;
        try{
        	MysqlQuery mq = new MysqlQuery();
        	String s = mq.signUp("cqj", "123321");
        	System.out.println(s);
            // ע�� JDBC ����
            Class.forName("com.mysql.jdbc.Driver");
        
            // ������
            System.out.println("�������ݿ�...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // ִ�в�ѯ
            System.out.println(" ʵ����Statement��...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM user_info";
            ResultSet rs = stmt.executeQuery(sql);
        
            // չ����������ݿ�
            while(rs.next()){
                // ͨ���ֶμ���
                int id  = rs.getInt("user_id");
                String name = rs.getString("user_name");
                String password = rs.getString("user_password");
                int num_win = rs.getInt("num_win");
                int num_lose = rs.getInt("num_lose");
                int num_peace = rs.getInt("num_peace");
                //Date datetime = rs.getDate("submission_time")
                rowData[index][0] = toString().valueOf(index+1);
                rowData[index][1] = name;
                rowData[index][2] = num_win;
                rowData[index][3] = num_lose;
                rowData[index][4] = num_peace;
                rowData[index][5] = String.format("%.2f", num_win*100.0/(num_win + num_lose + num_peace)) + "%";
                index++;
                // �������
                System.out.print("id: " + id);
                System.out.print(", name: " + name);
                System.out.print(", password: " + password);
                System.out.print(", win: " + num_win);
                System.out.print(", lose: " + num_lose);
                System.out.print(", peace: " + num_peace);
                System.out.print("\n");
            }
            // ��ɺ�ر�
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // ���� JDBC ����
            se.printStackTrace();
        }catch(Exception e){
            // ���� Class.forName ����
            e.printStackTrace();
        }finally{
            // �ر���Դ
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// ʲô������
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    
	}
//	public static void main(String[] args)
//	{
//		RankFrame r = new RankFrame();
//	}
	
}
