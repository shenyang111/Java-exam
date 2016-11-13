package cn.edu.wust.shenyang.payment;

class Manager extends Employer {
    double pay = 8000;
    
    double getPay() {
        return pay;
    }
    
    Manager(String name) {
        super(name);
        no = id;

        this.type = EmployerType.manager;
    }
    
    public String toString() {
        return "name:" + name + " no:" + no + " pay:8000.0";
    } 
}