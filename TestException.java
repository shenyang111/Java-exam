public class TestException {
	static void arraySize() {
		try {
			int a[] = new int [-1];
			a[-1]= 1;
		}
		catch(NegativeArraySizeException a) {
			System.out.println("the value of any dimension expression is less than zero");
		}
	}
	static void outofBound() {
		try {
			int a[] = new int [1];
			for(int i = 0; i < 2; i++) {
				a[i] = i+1;
			}
		}
		catch(IndexOutOfBoundsException a) {
			System.out.println("the value of any dimension expression is negative or equal to the length of the array");
		}
	}
	
	static void nullPointer() {
		try {
			int a[] = null;
			a[0] = 1;
		}
		catch(NullPointerException a) {
			System.out.println("the value of the array reference expression is null");
		}
		
	}
	
	public static void main(String args[]) {
		arraySize();
		outofBound();
		nullPointer();
	}

}
