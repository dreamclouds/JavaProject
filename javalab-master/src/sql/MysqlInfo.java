package sql;

import java.sql.*;

public class MysqlInfo {
	/**
	 * 
	 * @author ������
	 * @time 20171209
	 * չʾ���ݿ��������Ϣ
	 * 
	 */
    // JDBC �����������ݿ� URL
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    public static final String DB_URL = "jdbc:mysql://localhost:3306/Chess";
 
    // ���ݿ���û��������룬��Ҫ�����Լ�������
    public static final String USER = "root";
    public static final String PASS = "";
 
    public static void main(String[] args) {
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
                String num_win = rs.getString("num_win");
                String num_lose = rs.getString("num_lose");
                String num_peace = rs.getString("num_peace");
                //Date datetime = rs.getDate("submission_time")
    
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
}