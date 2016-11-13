package cn.edu.wust.shenyang.payment;

class Manager extends Employer {
    double pay = 8000;
    
    double getPay() {
        return pay;
    }

    Manager(String name) {
        super(name);
        this.type = EmployerType.manager;
    }

    Manager(long no, String name) {
        super(no, name);
        this.type = EmployerType.manager;
    }

    public String toString() {
        return "no:" + no + ", name:" + name + ", pay:8000.0";
    } 
}