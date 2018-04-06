import KarnaughClass.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class GUI extends JFrame{
	Karnaugh map;
	ImageIcon process;
	ImageIcon reset;
	ImageIcon process1;
	ImageIcon reset1;
	boolean active;
	public GUI(){
		super("Karnaugh Map");
		setResizable(false);
		setLayout(null);
		setSize(700,470);
		active=true;
		
		process=new ImageIcon("assets/Process.png");
		process.setImage(process.getImage().getScaledInstance(250, 50, Image.SCALE_SMOOTH));
		process1=new ImageIcon("assets/Process1.png");
		process1.setImage(process1.getImage().getScaledInstance(250, 50, Image.SCALE_SMOOTH));
		reset=new ImageIcon("assets/Reset.png");
		reset.setImage(reset.getImage().getScaledInstance(250, 50, Image.SCALE_SMOOTH));
		reset1=new ImageIcon("assets/Reset1.png");
		reset1.setImage(reset1.getImage().getScaledInstance(250, 50, Image.SCALE_SMOOTH));
		
		AnimatedButton processing=new AnimatedButton(process,process1);
		processing.setBounds(430,20,250,50);
		AnimatedButton reseting=new AnimatedButton(reset,reset1);
		reseting.setBounds(430,90,250,50);
		
		JTextArea function=new JTextArea();
		function.setText("SOP =");
		function.setEditable(false);
		function.setLineWrap(true);
		function.setWrapStyleWord(true);
		function.setBounds(430,160,250,260);
		
		map=new Karnaugh();
		map.setBounds(20, 20, 400, 400);
		
		class motion implements MouseMotionListener{
			public void mouseDragged(MouseEvent e){}
			public void mouseMoved(MouseEvent e) {
				if(active){
					//first row
					if(e.getX()>=0 && e.getX()<100 && e.getY()>=0 && e.getY()<100) map.hovering(0);
					else if(e.getX()>=100 && e.getX()<200 && e.getY()>=0 && e.getY()<100) map.hovering(1);
					else if(e.getX()>=200 && e.getX()<300 && e.getY()>=0 && e.getY()<100) map.hovering(2);
					else if(e.getX()>=300 && e.getX()<400 && e.getY()>=0 && e.getY()<100) map.hovering(3);
					//second row
					else if(e.getX()>=0 && e.getX()<100 && e.getY()>=100 && e.getY()<200) map.hovering(4);
					else if(e.getX()>=100 && e.getX()<200 && e.getY()>=100 && e.getY()<200) map.hovering(5);
					else if(e.getX()>=200 && e.getX()<300 && e.getY()>=100 && e.getY()<200) map.hovering(6);
					else if(e.getX()>=300 && e.getX()<400 && e.getY()>=100 && e.getY()<200) map.hovering(7);
					//third row
					else if(e.getX()>=0 && e.getX()<100 && e.getY()>=200 && e.getY()<300) map.hovering(8);
					else if(e.getX()>=100 && e.getX()<200 && e.getY()>=200 && e.getY()<300) map.hovering(9);
					else if(e.getX()>=200 && e.getX()<300 && e.getY()>=200 && e.getY()<300) map.hovering(10);
					else if(e.getX()>=300 && e.getX()<400 && e.getY()>=200 && e.getY()<300) map.hovering(11);
					//fourth row
					else if(e.getX()>=0 && e.getX()<100 && e.getY()>=300 && e.getY()<400) map.hovering(12);
					else if(e.getX()>=100 && e.getX()<200 && e.getY()>=300 && e.getY()<400) map.hovering(13);
					else if(e.getX()>=200 && e.getX()<300 && e.getY()>=300 && e.getY()<400) map.hovering(14);
					else if(e.getX()>=300 && e.getX()<400 && e.getY()>=300 && e.getY()<400) map.hovering(15);
					
					else map.nothovering();
				}
			}
		}
		class clicker implements MouseListener{
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				if(active){
					if(e.getX()>=0 && e.getX()<100 && e.getY()>=0 && e.getY()<100) map.change(0);
					else if(e.getX()>=100 && e.getX()<200 && e.getY()>=0 && e.getY()<100) map.change(1);
					else if(e.getX()>=200 && e.getX()<300 && e.getY()>=0 && e.getY()<100) map.change(2);
					else if(e.getX()>=300 && e.getX()<400 && e.getY()>=0 && e.getY()<100) map.change(3);
					//second row
					else if(e.getX()>=0 && e.getX()<100 && e.getY()>=100 && e.getY()<200) map.change(4);
					else if(e.getX()>=100 && e.getX()<200 && e.getY()>=100 && e.getY()<200) map.change(5);
					else if(e.getX()>=200 && e.getX()<300 && e.getY()>=100 && e.getY()<200) map.change(6);
					else if(e.getX()>=300 && e.getX()<400 && e.getY()>=100 && e.getY()<200) map.change(7);
					//third row
					else if(e.getX()>=0 && e.getX()<100 && e.getY()>=200 && e.getY()<300) map.change(8);
					else if(e.getX()>=100 && e.getX()<200 && e.getY()>=200 && e.getY()<300) map.change(9);
					else if(e.getX()>=200 && e.getX()<300 && e.getY()>=200 && e.getY()<300) map.change(10);
					else if(e.getX()>=300 && e.getX()<400 && e.getY()>=200 && e.getY()<300) map.change(11);
					//fourth row
					else if(e.getX()>=0 && e.getX()<100 && e.getY()>=300 && e.getY()<400) map.change(12);
					else if(e.getX()>=100 && e.getX()<200 && e.getY()>=300 && e.getY()<400) map.change(13);
					else if(e.getX()>=200 && e.getX()<300 && e.getY()>=300 && e.getY()<400) map.change(14);
					else if(e.getX()>=300 && e.getX()<400 && e.getY()>=300 && e.getY()<400) map.change(15);
				}
			}
			public void mouseReleased(MouseEvent e) {}
		}
		motion m=new motion();
		clicker c=new clicker();
		map.addMouseListener(c);
		map.addMouseMotionListener(m);
		
		class buttonclicker implements ActionListener{
			public void actionPerformed(ActionEvent e){
				if(e.getSource()==processing){
					String string=map.process();
					function.setText(string);
					processing.setEnabled(false);
					map.nothovering();
					active=false;
					
				}
				else{
					function.setText(map.reset());
					processing.setEnabled(true);
					active=true;
				}
			}
		}
		buttonclicker bc=new buttonclicker();
		processing.addActionListener(bc);
		reseting.addActionListener(bc);
		
		add(function);
		add(processing);
		add(reseting);
		add(map);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);		
	}
	public static void main(String[] args) {
		GUI gui=new GUI();

	}

}
