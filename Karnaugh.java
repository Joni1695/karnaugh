import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
public class Karnaugh extends JPanel{
	ImageIcon number1;
	ImageIcon number0;
	ImageIcon red1;
	ImageIcon red0;
	int hover;
	int counter;
	int[][] Matrix;
	String binary;
	ArrayList<Group1> group;
	ArrayList<Group1> finalgroup;
	public Karnaugh(){
		hover=-1;
		counter=0;
		group=new ArrayList<Group1>();
		finalgroup=new ArrayList<Group1>();
		Matrix=new int[4][4];
		for(int i=0;i<4;i++) for(int j=0;j<4;j++) Matrix[i][j]=0;
		
		binary="0000,0001,0011,0010,0100,0101,0111,0110,1100,1101,1111,1110,1000,1001,1011,1010";
		number1=new ImageIcon("assets/1.png");
		number1.setImage(number1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		number0=new ImageIcon("assets/0.png");
		number0.setImage(number0.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		red1=new ImageIcon("assets/1kuqe.png");
		red1.setImage(red1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		red0=new ImageIcon("assets/0kuqe.png");
		red0.setImage(red0.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
	}
	public void paintComponent(Graphics g){
		Graphics2D g2=(Graphics2D)g;
		g2.clearRect(0, 0, getWidth(), getHeight());
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(Matrix[i][j]==0 && hover!=((i*4)+j)) g2.drawImage(number0.getImage(),j*100,i*100,this);
				else if(Matrix[i][j]==0 && hover==((i*4)+j)) g2.drawImage(red0.getImage(),j*100,i*100,this);
				else if(Matrix[i][j]==1 && hover==((i*4)+j)) g2.drawImage(red1.getImage(),j*100,i*100,this);
				else g2.drawImage(number1.getImage(),j*100,i*100,this);
			}
		}
		g2.setStroke(new BasicStroke(5));
		for(int i=0;i<finalgroup.size();i++){
			g2.setColor(genCOL());
			int row,column;
			for(int j=0;j<finalgroup.get(i).width;j++){
				row=(finalgroup.get(i).table[0][j]-finalgroup.get(i).table[0][j]%4)/4;
				column=finalgroup.get(i).table[0][j]%4;
				g2.drawLine(column*100,row*100 , (column+1)*100 ,row*100);
				row=(finalgroup.get(i).table[finalgroup.get(i).height-1][j]-finalgroup.get(i).table[finalgroup.get(i).height-1][j]%4)/4;
				column=finalgroup.get(i).table[finalgroup.get(i).height-1][j]%4;
				g2.drawLine(column*100,(row+1)*100 , (column+1)*100 ,(row+1)*100);
			}
			for(int j=0;j<finalgroup.get(i).height;j++){
				row=(finalgroup.get(i).table[j][0]-finalgroup.get(i).table[j][0]%4)/4;
				column=finalgroup.get(i).table[j][0]%4;
				g2.drawLine(column*100,row*100 , column*100 ,(row+1)*100);
				row=(finalgroup.get(i).table[j][finalgroup.get(i).width-1]-finalgroup.get(i).table[j][finalgroup.get(i).width-1]%4)/4;
				column=finalgroup.get(i).table[j][finalgroup.get(i).width-1]%4;
				g2.drawLine((column+1)*100,row*100 , (column+1)*100 ,(row+1)*100);
			}
		}
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(1));
		g2.drawRect(0, 0, getWidth()-1, getHeight()-1);
	}
	public void hovering(int pos){
		hover=pos;
		repaint();
	}
	public void nothovering(){
		hover=-1;
		repaint();
	}
	public void change(int pos){
		if(Matrix[(pos-(pos%4))/4][pos%4]==0){
			Matrix[(pos-(pos%4))/4][pos%4]=1;
			counter++;
		}
		else {
			Matrix[(pos-(pos%4))/4][pos%4]=0;
			counter--;
		}
		repaint();
	}
	public String process(){
		String query="SOP = ";
		if(counter==0) query=query+"0";
		else if(counter==16) query=query+"1";
		else{
			for(int i=0;i<4;i++){
				for(int j=0;j<4;j++){
					if(Matrix[i][j]==1){
						group8(i,j);
						group4(i,j);
						group2(i,j);
						group1(i,j);
					}
				}
			}
			for(int i=0;i<4;i++){
				for(int j=0;j<4;j++){
					if(Matrix[i][j]==1){
						if(finalGroup(i,j,8));
						else if(finalGroup(i,j,4));
						else if(finalGroup(i,j,2));
						else finalGroup(i,j,1);
					}
				}
			}
			removeOverLapping();
			for(int i=0;i<finalgroup.size();i++){
				if(i!=finalgroup.size()-1)query=query+finalgroup.get(i).returnString(binary)+"+";
				else query=query+finalgroup.get(i).returnString(binary);
			}
		}
		repaint();
		return query;
	}
	public String reset(){
		String query="SOP =";
		for(int i=0;i<4;i++) for(int j=0;j<4;j++) Matrix[i][j]=0;
		counter=0;
		group.clear();
		finalgroup.clear();
		repaint();
		return query;
	}
	public int getElement(int initialrow,int initialcolumn,int leftright,int updown){
		int rowreturn=(initialrow+updown)%4;
		int colreturn=(initialcolumn+leftright)%4;
		if(rowreturn<0)rowreturn+=4;
		if(colreturn<0)colreturn+=4;
		return Matrix[rowreturn][colreturn];
	}
	public void group8(int posi,int posj){
		//horizontal
		if(getElement(posi,posj,1,0)==1 && getElement(posi,posj,2,0)==1 && getElement(posi,posj,3,0)==1 && getElement(posi,posj,0,1)==1 && getElement(posi,posj,1,1)==1 && getElement(posi,posj,2,1)==1 && getElement(posi,posj,3,1)==1){
			Group1 tmp=new Group1(posi,posj,2,4);
			group.add(tmp);
		}
		//vertical
		if(getElement(posi,posj,1,0)==1 && getElement(posi,posj,0,1)==1 && getElement(posi,posj,1,1)==1 && getElement(posi,posj,0,2)==1 && getElement(posi,posj,1,2)==1 && getElement(posi,posj,0,3)==1 && getElement(posi,posj,1,3)==1){
			Group1 tmp=new Group1(posi,posj,4,2);
			group.add(tmp);
		}
		
	}
	public void group4(int posi,int posj){
		//horizontal
		if(getElement(posi,posj,1,0)==1 && getElement(posi,posj,2,0)==1 && getElement(posi,posj,3,0)==1){
			Group1 tmp=new Group1(posi,0,1,4);
			group.add(tmp);
		}
		//vertical
		if(getElement(posi,posj,0,1)==1 && getElement(posi,posj,0,2)==1 && getElement(posi,posj,0,3)==1){
			Group1 tmp=new Group1(0,posj,4,1);
			group.add(tmp);
		}
		//square
		if(getElement(posi,posj,1,0)==1 && getElement(posi,posj,1,1)==1 && getElement(posi,posj,0,1)==1){
			Group1 tmp=new Group1(posi,posj,2,2);
			group.add(tmp);
		}
	}
	public void group2(int posi,int posj){
		//horizontal
		if(getElement(posi,posj,1,0)==1){
			Group1 tmp=new Group1(posi,posj,1,2);
			group.add(tmp);
		}
		//vertical
		if(getElement(posi,posj,0,1)==1){
			Group1 tmp=new Group1(posi,posj,2,1);
			group.add(tmp);
		}
	}
	public void group1(int posi,int posj){
		Group1 tmp=new Group1(posi,posj,1,1);
		group.add(tmp);		
	}
	public boolean finalGroup(int posi,int posj,int product){
		boolean found=false;
		for(int i=0;i<group.size();i++){
			if(group.get(i).isInArray(posi, posj) && (group.get(i).height*group.get(i).width)==product){
				for(int j=0;j<finalgroup.size();j++){
					if(finalgroup.get(j).isEqual(group.get(i))){
						found=true;
						break;
					}
				}
				if(!found){
					finalgroup.add(group.get(i));
					found=true;
				}
				break;
			}
		}
		return found;
	}
	public Color genCOL(){
		Random generator=new Random();
		return new Color(generator.nextInt(255),generator.nextInt(255),generator.nextInt(255));
	}
	public void removeOverLapping(){
		int[] removables=new int[finalgroup.size()];
		for(int i=0;i<finalgroup.size();i++){
			boolean[][] overlap=new boolean[finalgroup.get(i).height][finalgroup.get(i).width];
			for(int i1=0;i1<finalgroup.get(i).height;i1++){
				for(int j1=0;j1<finalgroup.get(i).width;j1++){
					overlap[i1][j1]=false;
				}
			}
			for(int j=0;j<finalgroup.size();j++){
				if(finalgroup.get(i).width*finalgroup.get(i).height<=finalgroup.get(j).width*finalgroup.get(j).height && finalgroup.get(i).isEqual(finalgroup.get(j))==false){
					for(int i1=0;i1<finalgroup.get(i).height;i1++){
						for(int j1=0;j1<finalgroup.get(i).width;j1++){
							int posi=(finalgroup.get(i).table[i1][j1]-finalgroup.get(i).table[i1][j1]%4)/4;
							int posj=finalgroup.get(i).table[i1][j1]%4;
							if(finalgroup.get(j).isInArray(posi,posj)) overlap[i1][j1]=true;
						}
					}
				}
			}
			boolean alloverlap=true;
			for(int i1=0;i1<finalgroup.get(i).height;i1++){
				for(int j1=0;j1<finalgroup.get(i).width;j1++){
					if(overlap[i1][j1]==false){
						alloverlap=false;
					}
				}
			}
			if(alloverlap){
				finalgroup.remove(finalgroup.get(i));
				i--;
			}
		}
	}
}
