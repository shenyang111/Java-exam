package cn.edu.wust.shenyang.payment;

enum EmployerType {
    manager,
    techer,
    saler
}

abstract class Employer {
    static long id = 1000;

    String name;
    long no;

    abstract double getPay();
    
    Employer(String name) {
        this.name = name;
        id = id + 1;
    }
}