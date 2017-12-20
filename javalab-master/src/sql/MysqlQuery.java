package sql;

import java.sql.*;

public class MysqlQuery {
	/**
	 * @author ������
	 * @time 20171207
	 * ���ݿ��ѯ����
	 * 
	 * 
	 */
	// JDBC �����������ݿ� URL
    static final String JDBC_DRIVER = MysqlInfo.JDBC_DRIVER;  
    static final String DB_URL = MysqlInfo.DB_URL;
 
    // ���ݿ���û���������
    static final String USER = MysqlInfo.USER;
    static final String PASS = MysqlInfo.PASS;
    
    public MysqlInfo mysql_Query(String sql)
    {
    	MysqlInfo mi = new MysqlInfo();
    	Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            // ע�� JDBC ����
            Class.forName("com.mysql.jdbc.Driver");
        
            // ������
            //System.out.println("�������ݿ�...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // ִ�в�ѯ
            //System.out.println(" ʵ����Statement��...");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        
            
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
        return mi;
    }
    
    public String signUp(String userName, String userPassword)
    {
    	String rt = "ok";
    	Connection conn = null;
    	PreparedStatement ptmt = null;
        ResultSet rs = null;
        try{
            // ע�� JDBC ����
            Class.forName("com.mysql.jdbc.Driver");
        
            // ������
            //System.out.println("�������ݿ�...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String presql = "SELECT * FROM user_info "
            		+" WHERE user_name=?;";
            ptmt = conn.prepareStatement(presql); //Ԥ����SQL������sqlִ��
            ptmt.setString(1, userName);
            rs = ptmt.executeQuery();
            if(!rs.next())
            {
            	String sql = "INSERT INTO user_info"
                		+"(user_name, user_password, num_win, num_lose, num_peace, submission_time) "
                		+"VALUES "
                		+"(?, ?, 0, 0, 0, CURRENT_DATE());";
                // ִ�в�ѯ
                //System.out.println(" ʵ����Statement��...");
                ptmt = conn.prepareStatement(sql); //Ԥ����SQL������sqlִ��
                ptmt.setString(1, userName);
                ptmt.setString(2, userPassword);
                
                ptmt.executeUpdate();
                
            }
            else rt = "error";
            
            
            // ��ɺ�ر�
            rs.close();
            ptmt.close();
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
                if(ptmt!=null) ptmt.close();
            }catch(SQLException se2){
            }// ʲô������
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    	return rt;
    }
    
    
}
