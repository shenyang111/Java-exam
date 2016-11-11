package cn.edu.wust.shenyang.payment;

abstract class Employer {
    String name;
    long no;
    static long id = 1000;
    
    abstract double getPay();
    
    Employer(String name) {
        this.name = name;
        id = id+1;
    }
}