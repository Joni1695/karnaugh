package KarnaughClass;

import javax.swing.JButton;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
public class AnimatedButton extends JButton{
	ImageIcon background,background1;
	boolean hover;
	public AnimatedButton(ImageIcon a,ImageIcon b){
		background=a;
		background1=b;
		hover=false;
		class listener implements MouseListener{

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				hover=true;
				repaint();
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				hover=false;
				repaint();
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		}
		listener l=new listener();
		this.addMouseListener(l);
	}
	public void paint(Graphics g){
		Graphics2D g2=(Graphics2D) g;
		g2.clearRect(0, 0, getWidth(), getHeight());
		if(hover) g2.drawImage(background1.getImage(),0,0,this);
		else g2.drawImage(background.getImage(),0,0,this);
		g2.drawRect(0, 0, getWidth()-1, getHeight()-1);
	}
}
