package KarnaughClass;

public class Group1 {
	public int width;
	public int height;
	public int posi;
	public int posj;
	public int[][] table;
	public Group1(int posi,int posj,int height,int width){
		table=new int[height][width];
		int[][] t=new int[4][4];
		int x=0;
		for(int i=0;i<4;i++) for(int j=0;j<4;j++){ t[i][j]=x;x++;}
		for(int i=0;i<height;i++) for(int j=0;j<width;j++) table[i][j]=getElement(posi,posj,j,i,t);
		this.width=width;
		this.height=height;
		this.posi=posi;
		this.posj=posj;
	}
	public boolean isEqual(Group1 a){
		if(a.posi==posi && a.posj==posj && a.width==width && a.height==height) return true;
		else return false;
	}
	public String returnString(String binary){
		String query="";
		String[] bintable=binary.split(",");
		boolean same1=true;
		boolean same2=true;
		boolean same3=true;
		boolean same4=true;
		String key1=bintable[table[0][0]].substring(0,1);
		String key2=bintable[table[0][0]].substring(1,2);
		String key3=bintable[table[0][0]].substring(2,3);
		String key4=bintable[table[0][0]].substring(3,4);
		for(int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				if(!bintable[table[i][j]].substring(0,1).equals(key1)) same1=false;
				if(!bintable[table[i][j]].substring(1,2).equals(key2)) same2=false;
				if(!bintable[table[i][j]].substring(2,3).equals(key3)) same3=false;
				if(!bintable[table[i][j]].substring(3,4).equals(key4)) same4=false;
			}
		}
		if(same1 && key1.equals("0")) query+="A!";
		else if(same1 && key1.equals("1")) query+="A";
		if(same2 && key2.equals("0")) query+="B!";
		else if(same2 && key2.equals("1")) query+="B";
		if(same3 && key3.equals("0")) query+="C!";
		else if(same3 && key3.equals("1")) query+="C";
		if(same4 && key4.equals("0")) query+="D!";
		else if(same4 && key4.equals("1")) query+="D";
		return query;
	}
	public int getElement(int initialrow,int initialcolumn,int leftright,int updown,int[][] t){
		int rowreturn=(initialrow+updown)%4;
		int colreturn=(initialcolumn+leftright)%4;
		if(rowreturn<0)rowreturn+=4;
		if(colreturn<0)colreturn+=4;
		return t[rowreturn][colreturn];
	}
	public boolean isInArray(int posi,int posj){
		boolean found=false;
		int pos=posi*4+posj;
		for(int i=0;i<height;i++) for(int j=0;j<width;j++){
			if(pos==table[i][j]){
				found=true;
				break;
			}
		}
		return found;
	}
}
