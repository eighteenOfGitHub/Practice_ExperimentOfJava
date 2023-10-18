package b;
import a.*;
public class B{
	public void func(){
		System.out.println("this is B");
		A aaa = new A();
		aaa.func();
	}
}