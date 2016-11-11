package cn.edu.wust.shenyang.payment;

class Manager extends Employer {
    double pay = 8000;
    
    double getPay() {
        return 8000;
    }
    
    Manager(String name) {
        super(name);
        no = id;
    }
    
    public String toString() {
        return "name:" + name + " no:" + no + " pay:8000.0";
    } 
}