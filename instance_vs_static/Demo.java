// A class can have instance members and static members. 
// Instance members refer to instance variables or instance methods.
// Static members refer to static variables or static methods. 'static' keyword is required when declaring static members.

// Instance members are only with one instance of the class.
// Static members are with the class and shared by all instances your create.

// This demo shows you how to access instance and static members when the calling method is in the same class as the target class.
// We demo two cases : 
// Case 1 : calling method is instance method. (mtd3)
// Case 2 : calling method is static method. (mtd4)

package instance_vs_static;

public class Demo {
	
	static int myStat = 10;
	int myInst = 11;	
	static void mtd1() {}	
	void mtd2() {}
	
	void mtd3() {
		mtd2();		// within the same class, an instance method can call another instance method directly.
		
		mtd1();		// within the same class, an instance method can call a static method directly.
		
		int i1 = myInst;	// within the same class, an instance method can access an instance variable directly.
		
		int i2 = myStat;	// within the same class, an instance method can access a static variable directly.
	}
	
	static void mtd4() {
		mtd1();		// within the same class, a static method can call another static method directly.
		
		mtd2();		// static method cannot call instance method without a reference of the instance.
		
		int i1 = myStat; // within the same class, a static method can access a static variable directly.
		
		int i2 = myInst;	// static method cannot access instance variable without a reference of the instance.
		
		Demo d = new Demo();	// Correction to above error - create an instance reference d
		d.mtd2();
		i2 = d.myInst;
				
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
