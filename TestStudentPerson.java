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
    public String sno;
    public String classno;

    Student(String name, String sex, int age, String sno, String classno) {
        super(name, sex, age);
        this.sno = sno;
        this.classno = classno;
    }

    public void updateAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "name:" + name + "\nsex:" + sex + "\nage:" + age + "\nsno:" + sno + "\nclassno:" + classno;
    }
}

public class TestStudentPerson {
    public static void main(String[] args) {
        Student[] students = new Student[3];
        students[0] = new Student("dangchengliang", "male", 19, "201404135021", "1401");
        students[1] = new Student("sunyuan", "male", 19, "201404135022", "1401");
        students[2] = new Student("shenyang", "male", 19, "201404135023", "1401");

        for (int i = 0; i < 3; i++) {
            students[i].updateAge(students[i].age);
            System.out.println(students[i].toString());
            System.out.println("===========================");
        }
    }
}
