// If we call methods or access variables in a different class, no matter the calling method is static or instance method, follow the same rules as below :
// Rule #1 - call instance methods or access instance variables using an instance of the target class (MyClass in below example)
// Rule #2 - call static methods or access static variables using the target class name.
// Rule #3 - You are allowed to call static methods or access static variables using an instance of the target class. But it's not recommended and Java will replace the instance with the target class name in compilation.

package instance_vs_static_2;

class MyClass {		
	static int myStat = 10;	
	int myInst = 11;	
	static void mtd1() {}	
	void mtd2() {}
	
}

public class Demo {
		
	void mtd3() {
				
		MyClass my = new MyClass();
		
		// Rule #1
		my.mtd2();				
		int i1 = my.myInst;
		
		// Rule #2		
		MyClass.mtd1();				
		int i2 = MyClass.myStat;
		
		// Rule #3
		my.mtd1();		
		i2 = my.myStat;
				
	}
	
	static void mtd4() {
		MyClass my = new MyClass();
		
		// Rule #1
		my.mtd2();				
		int i1 = my.myInst;
				
		// Rule #2		
		MyClass.mtd1();				
		int i2 = MyClass.myStat;
				
		// Rule #3
		my.mtd1();		
		i2 = my.myStat;
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

