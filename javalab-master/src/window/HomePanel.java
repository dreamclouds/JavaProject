package window;
import java.awt.Graphics;  
import java.awt.Image;  
import java.awt.event.ActionEvent;  
  
import javax.swing.ImageIcon;  
import javax.swing.JPanel;  
public class HomePanel extends JPanel{
    ImageIcon icon;  
    Image img; 
	public HomePanel() {
		// TODO Auto-generated constructor stub
        icon=new ImageIcon("Image\\info.png");  
        img=icon.getImage(); 
	}
    public void paintComponent(Graphics g) {  
        super.paintComponent(g);  
       // ����������Ϊ�˱���ͼƬ���Ը��洰�����е�����С�������Լ����óɹ̶���С  
         g.drawImage(img, 0, 0,this.getWidth(), this.getHeight(), this);  
    }  

}
