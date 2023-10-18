package point;

public class Point3D extends Point2D{
		int x,y,z;
	
	public static void main(String[] args){
		Point2D p2d1 = new Point2D(1,2);
		Point2D p2d2 = new Point2D(3,4);
		p2d1.Print();
		p2d2.Print();
		getdistance(p2d1,p2d2);
		
		Point2D p3d1 = new Point2D(2,2);
		Point2D p3d2 = new Point2D(6,5);
		p3d1.Print();
		p3d2.Print();
		getdistance(p3d1,p3d2);
	}
	
	static void getdistance(Point2D p1,Point2D p2){
		double dis = Math.sqrt(Math.pow(p1.x-p2.x,2)+Math.pow(p1.y-p2.y,2));
		System.out.println("distance = "+ dis);
	}
	
	Point3D(int x,int y,int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
		Point3D(Point2D point,int z){
		this.x = point.x;
		this.y = point.y;
		this.z = z;
	}
		public void offset(int a,int b,int c){
		this.x = a;
		this.y = b;
		this.z = c; 
	}
}

class Point2D{
	public int x;
	public int y;
	
	public Point2D(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	public Point2D(){}
	
	public void Print(){
		System.out.println("("+this.x+", "+this.y+")");
	}
	
	public void offset(int a,int b){
		this.x = a;
		this.y = b;
	}
}
