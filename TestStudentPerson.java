class Person {
	public String name;
	public String sex;
	public int age;
	
	Person(String name, String sex, int age) {
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
}

class Student extends Person {
	public int classno;
	public String sno;
	
	Student(String name, String sex, int age, String sno, int classno) {
		super(name, sex, age);
		this.sno = sno;
		this.classno = classno;
	}
	
	public int updateAge(int age) {
		age = age+1;
		return age;
	}
	
	public void toString(String name, String sex, int age, String sno, int classno) {
		System.out.println("name:"+name+" sex:"+sex+" age:"+age+" sno:"+sno+" classno:"+classno);
	}
}

public class TestStudentPerson {
	public static void main(String[] args) {
		Student[] information = new Student[3];
		information[0] = new Student("dangchengliang","man",19,"201404135021",1401);
		information[1] = new Student("sunyuan","man",19,"201404135022",1401);
		information[2] = new Student("shenyang","man",19,"201404135023",1401);
		
		for (int i = 0; i < 3; i++) {
			information[i].age = information[i].updateAge(information[i].age);
			information[i].toString(information[i].name, information[i].sex, information[i].age, information[i].sno, information[i].classno);
		}
	}
}
